package com.market.connect.models.entities;

import com.market.connect.utils.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @ElementCollection
    @CollectionTable(name = "customer_ratings", joinColumns = @JoinColumn(name = "customer_id"))
    @MapKeyColumn(name = "customer_email")
    @Column(name = "ratings")
    private Map<Customer, Double> customerRatings = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "customer_reviews", joinColumns = @JoinColumn(name = "customer_id"))
    @MapKeyColumn(name = "customer_email")
    @Column(name = "reviews")
    private Map<Customer, Double> customerReviews = new HashMap<>();

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_category")
    private ProductCategory productCategory;

    @Column(name = "product_description")
    private ProductCategory productDescription;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
}
