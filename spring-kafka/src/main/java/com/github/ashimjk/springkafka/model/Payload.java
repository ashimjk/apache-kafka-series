package com.github.ashimjk.springkafka.model;

public class Payload {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "value='" + value + '\'' +
                '}';
    }

}
