package com.myrestapi.core;

import io.restassured.http.ContentType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


public class Core {
    @BeforeTest
    public void beforeTestSetup(){

    }

    @Test
    public void getHttpMethodTest(){
        given().
                when()
                .get("http://postman-echo.com/get?foo1=bar1&foo2=bar2")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("Content-Type", "application/json; charset=utf-8")
                .and()
                .body("args.foo1", equalTo("bar1"))
                .and()
                .body("args.foo2", equalTo("bar2"))
        ;
    }

    @Test
    public void postHttpMethodTest(){
        String requestBody = "{\n" +
                "  \"name\": \"Soujan\",\n" +
                "  \"salary\": \"5000\",\n" +
                "  \"age\": \"20\"\n" +
                "}";
        given().
                when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("http://postman-echo.com/post")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("data.name", equalTo("Soujan"));
    }

    @AfterTest
    public void afterTestTeardown(){

    }
}
