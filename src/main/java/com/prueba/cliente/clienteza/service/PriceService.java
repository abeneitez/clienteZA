package com.prueba.cliente.clienteza.service;

import com.prueba.cliente.clienteza.model.Prices;
import com.prueba.cliente.clienteza.repository.PriceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public List<Prices> retrieveAllPrices(){
        return priceRepository.findAll();
    }

    public List<Prices> getPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            brandId, productId, applicationDate, applicationDate);
    }    
}
