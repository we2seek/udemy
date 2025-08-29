package com.we2seek.items_demo.domain;

import java.util.StringJoiner;

public class Key {
    private String encodedKey;
    private Long id;
    private Long timestamp;

    public Key() {
    }

    public String getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s",
                encodedKey,
                id == null ? "null" : id.toString(),
                timestamp == null ? "null" : timestamp.toString());
    }
}
