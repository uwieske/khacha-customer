package com.prikichi.kibrahacha.khachacustomer.repository;

import com.prikichi.kibrahacha.khachacustomer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {

}
