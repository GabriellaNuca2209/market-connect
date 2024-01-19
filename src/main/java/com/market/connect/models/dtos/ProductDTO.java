package com.market.connect.models.dtos;

import com.market.connect.utils.ProductCategory;
import com.market.connect.models.entities.Customer;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductDTO {

    private Long id;

    private String productName;

    private Map<Customer, Double> customerRatings = new HashMap<>();

    private Map<Customer, Double> customerReviews = new HashMap<>();

    private Double productPrice;

    private ProductCategory productCategory;

    private ProductCategory productDescription;
}