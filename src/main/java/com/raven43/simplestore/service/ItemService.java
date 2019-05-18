package com.raven43.simplestore.service;

import com.raven43.simplestore.exception.NoSuchCategoryException;
import com.raven43.simplestore.exception.NoSuchItemException;
import com.raven43.simplestore.model.Item;
import com.raven43.simplestore.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@Service
public class ItemService {

    private final ItemRepo itemRepo;
    private final FileService fileService;


    @Autowired
    public ItemService(ItemRepo itemRepo, FileService fileService) {
        this.itemRepo = itemRepo;
        this.fileService = fileService;
    }

    public Collection<String> getCategories() {
        return itemRepo.getCategories();
    }


    public Item findById(Long id) {
        return itemRepo.findById(id).orElseThrow(() -> new NoSuchItemException(id));
    }

    public Item addItem(Item item) {
        return itemRepo.save(item);
    }

    public Item updateImg(Item item, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String imgName = fileService.upload(file);
            item.setImgName(imgName);
        }
//        itemRepo.save(item)
        return itemRepo.save(item);
    }

    public Page<Item> getPage(String category, Pageable pageable) {
        if (!getCategories().contains(category)) throw new NoSuchCategoryException(category);
        return itemRepo.findByCategory(category, pageable);
    }

    public Page<Item> getPage(String str, String category, Pageable pageable) {
        if (!getCategories().contains(category)) throw new NoSuchCategoryException(category);
        return itemRepo.findByNameContainingAndCategory(str, category, pageable);
    }
//    public Page<Item> getPage(String category, Pageable pageable,String...tags) {
//        if (!getCategories().contains(category)) throw new NoSuchCategoryException(category);
//        return itemRepo.findByCategoryAndTagsContaining(category, pageable,tags);
//    }

    public Page<Item> getRandom(int size){
        return itemRepo.getRandom(PageRequest.of(0,size));
    }
}
