package com.prikichi.kibrahacha.khachacustomer.resource;

import com.prikichi.kibrahacha.khachacustomer.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomerResource {

    @Autowired
    private GraphQLService graphQLService;


    @PostMapping
    public ResponseEntity<Object> getAllCustomers(@RequestBody final String query){
        final ExecutionResult result = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
