package com.prikichi.kibrahacha.khachacustomer;

import com.prikichi.kibrahacha.khachacustomer.model.Customer;
import com.prikichi.kibrahacha.khachacustomer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@SpringBootApplication
public class KhachaCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KhachaCustomerApplication.class, args);
    }
}

@Slf4j
@Component
@Order(1)
class MongoDataLoader implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Loading test data into Mongo database");
        loadData();
    }

    private void loadData() {
        Stream.of(
                new Customer("123", "John Doe", "jdoe@test.com", "01-02-2017"),
                new Customer("124", "Dana Barker", "dbarker@test.com", "01-09-2016"),
                new Customer("125", "Matt Sake", "msake@test.com", "15-10-2014")
        ).forEach(customer -> {
            customerRepository.save(customer);
        });
    }
}