package com.we2seek.items_demo.controller;

import com.we2seek.items_demo.dao.ItemDAO;
import com.we2seek.items_demo.domain.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {

    private final ItemDAO itemDAO;

    public ItemRestController(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @GetMapping
    public List<Item> getAll() {
        return itemDAO.getAll();
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable int id) {
        return itemDAO.get(id);
    }

    @PostMapping
    public void add(@RequestBody Item item) {
        itemDAO.create(item);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        itemDAO.delete(id);
    }

}
