package com.we2seek.items_demo.converter;

import com.we2seek.items_demo.command.ItemForm;
import com.we2seek.items_demo.domain.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemFormToItemConverter implements Converter<ItemForm, Item> {

    @Override
    public Item convert(ItemForm source) {
        return new Item(source.getId(), source.getName(), source.isActive(), null, null);
    }
}
