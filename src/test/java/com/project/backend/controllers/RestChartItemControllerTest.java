package com.project.backend.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RestChartItemControllerTest {

    @Test
    void ensureThatApiReturnStatusCode200() throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/cart")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertTrue(response.statusCode()==200);
    }

    @Test
    @DisplayName("Ensure that String value is returned")
    void ensureStringValueReturned() throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/checkavailability/?q=3&id=1")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Optional<String> responseType = response.headers().firstValue("Content-Type");
        System.out.println("*" +  responseType.get());
        assertTrue(responseType.get().contains("text/plain"));
    }

}