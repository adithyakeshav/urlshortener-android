package com.keshava.urlshortener.utils;

public class UrlModel {
    private String shortString, expansionString;

    public UrlModel() {
    }

    public UrlModel(String shortString, String expansionString) {
        this.shortString = shortString;
        this.expansionString = expansionString;
    }

    public String getShortString() {
        return shortString;
    }

    public void setShortString(String shortString) {
        this.shortString = shortString;
    }

    public String getExpansionString() {
        return expansionString;
    }

    public void setExpansionString(String expansionString) {
        this.expansionString = expansionString;
    }
}
