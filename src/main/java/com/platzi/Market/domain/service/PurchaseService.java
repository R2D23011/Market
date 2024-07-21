package com.platzi.Market.domain.service;

import com.platzi.Market.domain.Purchase;
import java.util.List;
import java.util.Optional;
import com.platzi.Market.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId){
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase){
     return purchaseRepository.save(purchase);
    }
}
