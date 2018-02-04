package com.prikichi.kibrahacha.khachacustomer.service;

import com.prikichi.kibrahacha.khachacustomer.repository.CustomerRepository;
import com.prikichi.kibrahacha.khachacustomer.service.datafetcher.AllCustomersDataFetcher;
import com.prikichi.kibrahacha.khachacustomer.service.datafetcher.CustomerDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:customers.graphql")
    private Resource resource;

    private GraphQL graphQL;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AllCustomersDataFetcher allCustomersDataFetcher;

    @Autowired
    private CustomerDataFetcher customerDataFetcher;


    @PostConstruct
    public void loadSchema() throws IOException {

        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    public GraphQL getGraphQL() {
        return this.graphQL;
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCustomers", allCustomersDataFetcher)
                        .dataFetcher("customer", customerDataFetcher))
                .build();
    }
}
