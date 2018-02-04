package com.prikichi.kibrahacha.khachacustomer.service.datafetcher;

import com.prikichi.kibrahacha.khachacustomer.model.Customer;
import com.prikichi.kibrahacha.khachacustomer.repository.CustomerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCustomersDataFetcher implements DataFetcher<List<Customer>> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> get(final DataFetchingEnvironment dataFetchingEnvironment) {
        return customerRepository.findAll();
    }
}
