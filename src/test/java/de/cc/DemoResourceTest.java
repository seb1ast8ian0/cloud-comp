package de.cc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DemoResourceTest {
    @Test
    void testDemoEndpoint() {
        given()
                .when().get("/demo")
                .then()
                .statusCode(200)
                .body(is("Hello World 2!!"));
    }

}