package com.rabbitmq.app.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.app.model.Product;
import com.rabbitmq.app.product.dto.CreateProductDTO;
import com.rabbitmq.app.product.message.ProductMessageProducer;

@RestController("/products")
public class ProductController {

    private final ProductMessageProducer productMessageProducer;

    public ProductController(ProductMessageProducer productMessageProducer) {
        this.productMessageProducer = productMessageProducer;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(CreateProductDTO createProductDTO) {

        productMessageProducer.send(new Product(
                createProductDTO.getName(),
                createProductDTO.getPrice(),
                createProductDTO.getCustomerName())
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
