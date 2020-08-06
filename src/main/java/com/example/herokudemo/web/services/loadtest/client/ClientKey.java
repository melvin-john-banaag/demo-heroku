package com.example.herokudemo.web.services.loadtest.client;

public enum ClientKey {
    CUST_OPT("CUST_optOut_optIn"),
    PROD_OFFER("prod_offer"),
    CUST_EMAIL("cust_update_email");

    private final String name;
    ClientKey(String name){
        this.name = name;
    }
    public String toString(){
        return this.name;
    }
}
