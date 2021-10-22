package com.unipay.uni.models;

public class Transaccion {

    public String phoneNumberFrom;
    public String phoneNumberTo;
    public String concept;
    public String createDate;
    public String balance;

    public Transaccion(String phoneNumberFrom, String phoneNumberTo, String concept, String balance, String createDate) {
        this.phoneNumberFrom = phoneNumberFrom;
        this.phoneNumberTo = phoneNumberTo;
        this.concept = concept;
        this.balance = balance;
        this.createDate = createDate;
    }

    public Transaccion() {

    }

    public String getPhoneNumberFrom() {
        return phoneNumberFrom;
    }

    public void setPhoneNumberFrom(String phoneNumberFrom) {
        this.phoneNumberFrom = phoneNumberFrom;
    }

    public String getPhoneNumberTo() {
        return phoneNumberTo;
    }

    public void setPhoneNumberTo(String phoneNumberTo) {
        this.phoneNumberTo = phoneNumberTo;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreatedate(String createDate) {
        this.createDate = createDate;
    }
}
