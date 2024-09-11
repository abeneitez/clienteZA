package com.prueba.cliente.clienteza.repository;

import com.prueba.cliente.clienteza.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Prices, Long> {
    List<Prices> findAll();
    List<Prices> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);
}