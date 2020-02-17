package com.lits.ws;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public abstract class ServiceIntegrationTemplate {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public ServiceIntegrationTemplate() {

        final ObjectMapper objectMapper = new ObjectMapper();

        RestAssured.baseURI = "https://europe-west2-search-app-263e2.cloudfunctions.net/webapp";
        RestAssured.filters(new Filter() {
            @Override
            public Response filter(
                    FilterableRequestSpecification filterableRequestSpecification,
                    FilterableResponseSpecification filterableResponseSpecification,
                    FilterContext filterContext) {

                RequestSpecification requestSpecification = filterableRequestSpecification.contentType(ContentType.JSON);

                return filterContext.next((FilterableRequestSpecification) requestSpecification, filterableResponseSpecification);
            }
        });
        RestAssured.filters(new Filter() {
            @Override
            public Response filter(
                    FilterableRequestSpecification filterableRequestSpecification,
                    FilterableResponseSpecification filterableResponseSpecification,
                    FilterContext filterContext) {

                System.out.println("=======================================");
                System.out.println("Request: " + ANSI_RED + filterableRequestSpecification.getMethod() + " " + filterableRequestSpecification.getURI() + ANSI_RESET);
                System.out.println("Headers: " + ANSI_YELLOW + filterableRequestSpecification.getHeaders().asList() + ANSI_RESET);
                System.out.println("Body   : " + ANSI_CYAN + filterableRequestSpecification.getBody() + ANSI_RESET);
                System.out.println("-----");
                Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
                System.out.println("Status  : "  + ANSI_GREEN + response.getStatusLine() + ANSI_RESET);
                System.out.println("Time    : "  + ANSI_PURPLE + response.getTimeIn(TimeUnit.MILLISECONDS) + " ms" + ANSI_RESET);
                System.out.println("Headers : " + ANSI_YELLOW + response.getHeaders().asList() + ANSI_RESET);
                System.out.print("Body    : "  + ANSI_BLUE );
                response.print();
                System.out.println(ANSI_RESET);

                return response;
            }
        });
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                new Jackson2ObjectMapperFactory() {
                    @Override
                    public ObjectMapper create(Type type, String s) {
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        return objectMapper;
                    }
                }
        ));
    }

    public abstract String getOauth2Token();

    private String cachedToken;

    public RequestSpecification request() {

        if (cachedToken == null) {
            cachedToken = getOauth2Token();
        }

        return given()
                .auth()
                .oauth2(cachedToken);
    }

    public RequestSpecification requestWithoutAuth() {
        return given();
    }
}
