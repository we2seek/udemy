package com.we2seek.items_demo.command;

import java.util.StringJoiner;

public class ItemForm {
    private Integer id;
    private String name;
    private boolean active = true;
    private String created;
    private String updated;

    public ItemForm() {
    }

    public ItemForm(Integer id, String name, boolean active, String created, String updated) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.created = created;
        this.updated = updated;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ItemForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("active=" + active)
                .add("created='" + created + "'")
                .add("updated='" + updated + "'")
                .toString();
    }
}
