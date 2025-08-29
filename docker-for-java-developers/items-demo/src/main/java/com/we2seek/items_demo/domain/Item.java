package com.we2seek.items_demo.domain;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.StringJoiner;

public class Item {

    private Integer id;
    private String name;
    private boolean active;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Key key;

    public Item() {
    }

    public Item(Integer id, String name, boolean active, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.created = created;
        this.updated = updated;
        Key key = new Key();
        key.setEncodedKey("key_" + System.currentTimeMillis());
        key.setTimestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    @JsonGetter("key")
    public String getKeyAsString() {
        return key != null ? key.toString() : null;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("active=" + active)
                .add("created=" + created)
                .add("updated=" + updated)
                .add("key=" + key)
                .toString();
    }
}
