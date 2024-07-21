package com.platzi.Market.persistence;

import com.platzi.Market.domain.Product;
import com.platzi.Market.domain.repository.ProductRepository;
import com.platzi.Market.persistence.crud.ProductoCrudRepository;
import com.platzi.Market.persistence.entity.Producto;
import com.platzi.Market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;


    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(productos1 -> mapper.toProducts(productos1));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public Optional<List<Producto>> filterByPriceAndCategory(double precioVenta, int idCategoria){
        return productoCrudRepository.findByPrecioVentaLessThanAndIdCategoria(precioVenta, idCategoria);
    }



    //DELETE
    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }

    //UPDATE
    public Producto update(Producto newProducto, int id){
        return productoCrudRepository.findById(id).
                map(
                    producto -> {
                        producto.setCantidadStock(newProducto.getCantidadStock());
                        producto.setIdCategoria(newProducto.getIdCategoria());
                        producto.setEstado(newProducto.getEstado());
                        producto.setNombre(newProducto.getNombre());
                        producto.setCodigoBarras(newProducto.getCodigoBarras());
                        producto.setPrecioVenta(newProducto.getPrecioVenta());
                        return productoCrudRepository.save(producto);
                    }
        ).get();
    }
}
