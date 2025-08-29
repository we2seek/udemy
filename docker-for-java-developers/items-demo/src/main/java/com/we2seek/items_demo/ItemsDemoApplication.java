package com.we2seek.items_demo;

import com.we2seek.items_demo.dao.ItemDAO;
import com.we2seek.items_demo.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ItemsDemoApplication implements ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);
    private final ItemDAO itemDAO;

    public ItemsDemoApplication(ItemDAO ItemDAO) {
        this.itemDAO = ItemDAO;
    }

    public static void main(String[] args) {
        SpringApplication.run(ItemsDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application has been successfully started...");
        List<Item> items = itemDAO.getAll();
        log.info("{}", items);
    }
}
