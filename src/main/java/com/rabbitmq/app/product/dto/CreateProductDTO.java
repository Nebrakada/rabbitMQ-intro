package com.rabbitmq.app.product.dto;

import java.math.BigDecimal;

public class CreateProductDTO {
    private String name;
    private BigDecimal price;
    private String customerName;

    public CreateProductDTO(String name, String price, String customerName) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.customerName = customerName;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCustomerName() {
        return customerName;
    }
}
