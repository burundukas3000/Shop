package com.project.backend.rest;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    private String id;

    private String url;

    private Breed[] breeds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Breed[] getBreeds() {
        return breeds;
    }

    public void setBreeds(Breed[] breeds) {
        this.breeds = breeds;
    }

    @Override
    public String toString() {
        return "Image [id=" + id + ", url=" + url + ", breeds=" + Arrays.toString(breeds) + "]";
    }
}

