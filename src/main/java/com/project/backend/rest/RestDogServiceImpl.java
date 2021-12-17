package com.project.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestDogServiceImpl implements RestDogService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${rest.breeds}")
    String restBreeds;

    @Value ("${rest.apiKey}")
    String restApiKey;

    @Override
    public List<Breed> getAllBreeds() {

        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", restApiKey);
        //Create a new HttpEntity
        final HttpEntity<String> entity_ = new HttpEntity<String>(headers);
        int limit = 30;
        ResponseEntity<List<Breed>> entity = restTemplate.exchange(restBreeds, HttpMethod.GET, entity_,
                new ParameterizedTypeReference<List<Breed>>() {}, limit);
        return entity.getBody();
    }

}

