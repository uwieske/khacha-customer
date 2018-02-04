package com.prikichi.kibrahacha.khachacustomer.service.datafetcher;

import com.prikichi.kibrahacha.khachacustomer.model.Customer;
import com.prikichi.kibrahacha.khachacustomer.repository.CustomerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataFetcher  implements DataFetcher<Customer>{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer get(final DataFetchingEnvironment dataFetchingEnvironment) {
        final String custno = dataFetchingEnvironment.getArgument("id");
        return customerRepository.findById(custno).orElse(null);
    }
}
