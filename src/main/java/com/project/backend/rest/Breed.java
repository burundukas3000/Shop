package com.project.backend.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Breed {

    private String id;

    private String name;

    private String temparament;

    private String life_span;

    private String alt_names;

    private String wikipedia_url;

    private String origin;

    private String country_code;

    private Image image;

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemparament() {
        return temparament;
    }

    public void setTemparament(String temparament) {
        this.temparament = temparament;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getAlt_names() {
        return alt_names;
    }

    public void setAlt_names(String alt_names) {
        this.alt_names = alt_names;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        return "Breed [id=" + id + ", name=" + name + ", temparament=" + temparament + ", life_span=" + life_span
                + ", alt_names=" + alt_names + ", wikipedia_url=" + wikipedia_url + ", origin=" + origin
                + ", country_code=" + country_code + "]";
    }

}
