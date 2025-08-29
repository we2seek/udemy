package com.we2seek.items_demo.dao;

import com.we2seek.items_demo.domain.Item;

import java.util.List;

public interface ItemDAO {
    void create(Item item);
    void delete(int id);
    Item get(int id);
    List<Item>getAll();
}
