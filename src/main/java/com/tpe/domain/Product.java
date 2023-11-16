package com.tpe.domain;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor // parametresiz constructor
//@AllArgsConstructor bütün parametreleri içeren constructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // setter metodu oluşmasın
    private Long id;

    @NotBlank(message="Product name is required")
    @Column(unique = true)
    private String productName;

    @NotBlank(message="Product brand is required")
    private String brand;

    @NotNull(message = "Product price is required")
    private Double price;

    public Product(String productName, String brand, Double price) {
        this.productName = productName;
        this.brand = brand;
        this.price = price;
    }
}