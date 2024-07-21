package com.platzi.Market.persistence.crud;

import com.platzi.Market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
                   //Respetar el CamelCase y los nombres para las consultas query
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
    Optional<List<Producto>> findByPrecioVentaLessThanAndIdCategoria(double precioVenta, int idCategoria);
}
