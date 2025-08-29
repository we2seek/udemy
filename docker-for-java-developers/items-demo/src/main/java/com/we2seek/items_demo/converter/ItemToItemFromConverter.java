package com.we2seek.items_demo.converter;

import com.we2seek.items_demo.command.ItemForm;
import com.we2seek.items_demo.domain.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ItemToItemFromConverter implements Converter<Item, ItemForm> {
    @Override
    public ItemForm convert(Item source) {
        return new ItemForm(source.getId(), source.getName(), source.isActive(), dateToString(source.getCreated()), dateToString(source.getUpdated()));
    }

    private String dateToString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return DateTimeFormatter.ISO_DATE_TIME.format(dateTime);
    }
}
