package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        // BookForm을 form이라는 이름으로 View에 넘긴다.
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        // 좋은 설계는 여기서 따로 파라미터로 넘기거나 해서 setter들을 전부 제거하는 것이 좋지만, 강의에서는 패스
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
}
