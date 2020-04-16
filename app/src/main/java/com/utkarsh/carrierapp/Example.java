package com.utkarsh.carrierapp;

class Example {

    private String name,number,email,aadhar,address;

    Example(String name, String number, String email, String aadhar, String address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.aadhar = aadhar;
        this.address = address;
    }

    String getName() {
        return name;
    }

    String getNumber() {
        return number;
    }

    String getEmail() {
        return email;
    }

    String getAadhar() {
        return aadhar;
    }

    String getAddress() {
        return address;
    }
}
