package com.lits.ws;

import com.lits.ws.models.BookModel;
import com.lits.ws.models.LoginResponseModel;
import com.lits.ws.models.UserModel;
import io.restassured.specification.RequestSpecification;
import org.apache.groovy.util.Maps;
import org.hamcrest.Matchers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;


// @formatter:off
public class IntegrationServicesFacade {

    private static final String EMAIL = "asohanskyy@gmail.com";
    private static final String PASS = "Qwerty123456";

    private ServiceIntegrationTemplate serviceIntegrationTemplate = new ServiceIntegrationTemplate() {
        @Override
        public String getOauth2Token() {
            final Map<String, String> requestBody = Maps.of(
                    "email", EMAIL,
                    "password", "Qwerty123456");
            final RequestSpecification loginRequest = given().body(requestBody);
            final LoginResponseModel loginResponseModel = loginRequest.post("/api/auth/login")
                    .then()
                    .statusCode(200)
                    .body("r.access_token", Matchers.notNullValue()).extract().as(LoginResponseModel.class);
            return loginResponseModel.getR().getAccess_token();
        }
    };



    public List<BookModel> getBooks() {
        final BookModel[] booksArray = serviceIntegrationTemplate
                    .request()
                            .get("/api/v1/books")
                .as(BookModel[].class);
        return Arrays.stream(booksArray).collect(toList());
    }

    public BookModel getBookForIsbn(String isbn) {
        return serviceIntegrationTemplate
                    .request()
                        .pathParam("isbn", isbn)
                            .get("/api/v1/books/{isbn}")
                        .then().statusCode(200)
                .extract()
                    .as(BookModel.class);
    }

    public UserModel getUserForEmail(String email) {
        return  serviceIntegrationTemplate
                    .request()
                        .queryParam("email", EMAIL)
                            .get("/api/v1/users")
                    .then()
                        .statusCode(200)
                        .body("email", equalTo(EMAIL)).extract()
                            .as(UserModel.class);
    }

}
// @formatter:on
