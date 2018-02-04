package com.prikichi.kibrahacha.khachacustomer.config;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

@Configuration
public class GraphQLConfig {

    @Value("classpath:customers.graphql")
    private Resource resource;

    @Autowired
    private AllCustomersDataFetcher allCustomersDataFetcher;

    @Autowired
    private CustomerDataFetcher customerDataFetcher;

    @Bean
    public GraphQL customerGraphQL() throws IOException {

        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        return graphQL;
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCustomers", allCustomersDataFetcher)
                        .dataFetcher("customer", customerDataFetcher))
                .build();
    }

}
