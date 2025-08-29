package com.we2seek.items_demo.controller;

import com.we2seek.items_demo.command.ItemForm;
import com.we2seek.items_demo.converter.ItemFormToItemConverter;
import com.we2seek.items_demo.converter.ItemToItemFromConverter;
import com.we2seek.items_demo.dao.ItemDAO;
import com.we2seek.items_demo.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemDAO itemDAO;
    private final ItemFormToItemConverter itemFormToItemConverter;
    private final ItemToItemFromConverter itemToItemFromConverter;

    public ItemController(ItemDAO itemDAO, ItemFormToItemConverter itemFormToItemConverter, ItemToItemFromConverter itemToItemFromConverter) {
        this.itemDAO = itemDAO;
        this.itemFormToItemConverter = itemFormToItemConverter;
        this.itemToItemFromConverter = itemToItemFromConverter;
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute(
                "items",
                itemDAO.getAll().stream()
                        .map(itemToItemFromConverter::convert)
                        .collect(Collectors.toList())
        );

        return "index";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        ItemForm itemForm = new ItemForm();
        itemForm.setActive(true);
        model.addAttribute("itemForm", itemForm);

        return "item/form";
    }

    @PostMapping("/create")
    public String saveItem(ItemForm form, BindingResult bindingResult) {
        Item item = itemFormToItemConverter.convert(form);
        itemDAO.create(item);

        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Item item = itemDAO.get(id);
        ItemForm itemForm = itemToItemFromConverter.convert(item);
        model.addAttribute("itemForm", itemForm);

        return "item/form";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Integer id, Model model) {
        Item item = itemDAO.get(id);
        ItemForm itemForm = itemToItemFromConverter.convert(item);
        model.addAttribute("itemForm", itemForm);

        return "item/deleteForm";
    }

    @PostMapping(value = "/delete", params = "action=delete")
    public String deleteItem(ItemForm form) {
        itemDAO.delete(form.getId());

        return "redirect:/items";
    }

    @PostMapping(value = "/delete", params = "action=cancel")
    public String deleteItemCancel() {
        return "redirect:/items";
    }
}
