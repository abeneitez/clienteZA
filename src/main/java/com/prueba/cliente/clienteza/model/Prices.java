package com.prueba.cliente.clienteza.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brandId")
    private Long brandId;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "priceList")
    private Long priceList;

    @Column(name = "priority")
    private int priority;

    @Column(name = "price")
    private Double _price;

    @Column(name = "curr")
    private String curr;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    // Constructor (vacio)
    public Prices() {
        super();
    }

    // Constructor (parametrizado)
    public Prices(Long productId, Long brandId, Long priceList, Double _price, LocalDateTime startDate, LocalDateTime endDate){
        super();
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this._price = _price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // SETTERs y GETTERs
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Double get_price() {
        return _price;
    }

    public void set_price(Double price) {
        this._price = price;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    // Getters and setters
    

}

