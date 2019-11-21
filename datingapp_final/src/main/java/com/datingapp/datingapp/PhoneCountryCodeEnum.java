package com.datingapp.datingapp;

public enum PhoneCountryCodeEnum {

    DE("49"),
    ES("34"),
    FR("33"),
    LU("352"),
    UK("44"),
    USA("1");

    private String code;

    PhoneCountryCodeEnum(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.name();
    }
    /*
    public String toString() {
        return this.name() + " (+" + this.code + ")";
    }
    */

    public String getCode() {
        return this.code;
    }
}
