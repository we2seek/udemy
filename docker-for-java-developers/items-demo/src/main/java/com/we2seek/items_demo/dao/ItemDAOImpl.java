package com.we2seek.items_demo.dao;

import com.we2seek.items_demo.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ItemDAOImpl implements ItemDAO {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    private final Logger log = LoggerFactory.getLogger(ItemDAOImpl.class);
    private final RowMapper<Item> rowMapper = (rs, rowNum) ->
            new Item(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("active"),
                    toDate(rs.getTimestamp("created")),
                    toDate(rs.getTimestamp("updated"))
            );

    private LocalDateTime toDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), ZONE_ID);
    }

    private final JdbcTemplate jdbcTemplate;

    public ItemDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Item item) {
        int insert;
        if (item.getId() == null) {
            insert = jdbcTemplate.update("INSERT INTO items (name,active) VALUES (?,?)", item.getName(), item.isActive());
        } else {
            insert = jdbcTemplate.update("UPDATE items SET name = ?, active = ? WHERE id = ?",
                    item.getName(), item.isActive(), item.getId());
        }

        log.info("[add]: {}", insert);
    }

    @Override
    public void delete(int id) {
        int update = jdbcTemplate.update("DELETE FROM items WHERE id = ?", id);
        log.info("[delete]: {}", update);
    }

    @Override
    public Item get(int id) {
        Item item = jdbcTemplate.queryForObject("SELECT * FROM items WHERE id = ?", rowMapper, id);
        log.info("[get]: {}", item);

        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = jdbcTemplate.query("SELECT * FROM items", rowMapper);
        log.info("[getAll]: {}", items.size());

        return items;
    }
}
