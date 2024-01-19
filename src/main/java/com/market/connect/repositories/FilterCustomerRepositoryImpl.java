package com.market.connect.repositories;


import com.market.connect.models.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class FilterCustomerRepositoryImpl implements FilterCustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findFilteredCustomers(Boolean isActive, String city, String subscription) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();

        if (isActive != null) {
            predicates.add(criteriaBuilder.equal(customerRoot.get("isActive"), isActive));
        }
        if (city != null) {
            predicates.add(criteriaBuilder.equal(customerRoot.get("city"), city));
        }
        if (subscription != null) {
            predicates.add(criteriaBuilder.equal(customerRoot.get("subscription"), subscription));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
