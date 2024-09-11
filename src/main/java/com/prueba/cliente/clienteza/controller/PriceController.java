package com.prueba.cliente.clienteza.controller;

import com.prueba.cliente.clienteza.model.Prices;
import com.prueba.cliente.clienteza.service.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/prices")
@Validated
public class PriceController {

    private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    @Autowired
    private PriceService priceService = new PriceService();
    
    @GetMapping("/allprices")
    public List<Prices> retrieveAllPrices(){
        return priceService.retrieveAllPrices();
    }
        
    @GetMapping("/price")
    public ResponseEntity<?> getPrice (
            @RequestParam @NotNull (message = "Parámetro brandId es obligatorio")  Long brandId,
            @RequestParam @NotNull (message = "Parámetro productId es obligatorio") Long productId,
            @RequestParam @NotNull (message = "Parámetro applicationDate es obligatorio") 
            @DateTimeFormat(pattern = "yyyy-dd-MM-HH.mm.ss", iso = DateTimeFormat.ISO.NONE, fallbackPatterns = {"yyyy-dd-MM-HH.mm.ss"})
                    String applicationDate) {                        
                        
        // Convertir String a LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime dateTime = LocalDateTime.parse(applicationDate, formatter);

        // 1. Buscar todas las tarifas que coincidan con el producto, marca y fecha        
        List<Prices> listPrices = priceService.getPrice(brandId, productId, dateTime);

        // 2. Ordenar las tarifas por prioridad de forma descendente de mayor a menor prioridad (valor numérico)
        listPrices.sort(Comparator.comparing(Prices::getPriority).reversed());
        
        // 3. Si hay resultados, se coge el primer elemento del listado, que debe corresponder con el de mayor prioridad
        if (!listPrices.isEmpty()) {
            Prices price = listPrices.get(0);
            Prices pricePriority = new Prices(){};
            pricePriority.setProductId(price.getProductId());
            pricePriority.setBrandId(price.getBrandId());
            pricePriority.setPriceList(price.getPriceList());
            pricePriority.setStartDate(price.getStartDate());
            pricePriority.setEndDate(price.getEndDate());
            pricePriority.set_price(price.get_price());
            
            return ResponseEntity.ok(pricePriority);
        } else{
            // Si no se encuentran tarifas
            return new ResponseEntity("No se encontró ninguna tarifa para los datos de la entrada.", HttpStatus.NOT_FOUND);
        }
    }
}
