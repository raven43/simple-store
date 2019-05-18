package com.raven43.simplestore.controller;

import com.raven43.simplestore.model.Item;
import com.raven43.simplestore.repo.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class TestRestController {

    private final Logger log = LoggerFactory.getLogger(TestRestController.class);
    private final ItemRepo itemRepo;



    @Autowired
    public TestRestController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
        log.info("New Instance");
    }


    @GetMapping
    public ResponseEntity<Object> get() {
        Iterable<Item> items = itemRepo.findAll();
        for (Item item : items)
            log.info(item.toString());

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping
    public void post(
            Item item
    ) {
        log.info(item.toString());
        log.info(itemRepo.save(item).toString());
    }


    @GetMapping("/categories")
    public Iterable<String> cat() {
        return itemRepo.getCategories();
    }

//    @GetMapping("/tags")
//    public Iterable<Item> tag(
//            Pageable pageable,
//            @RequestParam String category,
//            @RequestParam ArrayList<String> tags
//    ) {
//        log.info(Arrays.toString(tags.toArray()));
//        return itemRepo.findByCategoryAndTagsContaining(category,tags,pageable);
//    }
    @GetMapping("/test")
    public Iterable<Item> test() {
        String[] tags = new String[]{"стул"};

        return itemRepo.findByCategoryAndTagsContaining("мебель",PageRequest.of(0,10),tags);
    }


}
