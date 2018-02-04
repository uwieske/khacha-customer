package com.prikichi.kibrahacha.khachacustomer.service.datafetcher;

import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final GraphQL customerGraphQL;

    @Autowired
    public CustomerService(final GraphQL customerGraphQL) {
        this.customerGraphQL = customerGraphQL;
    }

    @Transactional(readOnly = true)
    public Object get(final String query) {
        return customerGraphQL.execute(query);
    }
}
