package com.orangehrm.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtility {

    // ==============================
    // GET Request
    // ==============================
    public static Response sendGetRequest(String endpoint) {

        return RestAssured
                .given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // ==============================
    // POST Request
    // ==============================
    public static Response sendPostRequest(String endpoint, String payload) {

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    // ==============================
    // Validate Status Code
    // ==============================
    public static boolean validateStatusCode(Response response, int expectedStatus) {
        return response.getStatusCode() == expectedStatus;
    }

    // ==============================
    // Get JSON Value
    // ==============================
    public static String getJsonValue(Response response, String key) {
        return response.jsonPath().getString(key);
    }
}