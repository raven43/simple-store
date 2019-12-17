package com.raven43.simplestore.repo;

import com.raven43.simplestore.model.Item;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface ItemRepo extends PagingAndSortingRepository<Item, Long> {

    @Query("select distinct i.category from Item i")
    Collection<String> getCategories();

    @Query("select distinct i.tags from Item i")
    Iterable<String> getTags();

    Page<Item> findByCategory(@Length(max = 32) String category, Pageable pageable);

    Page<Item> findByCategoryAndTagsContaining(String category, Pageable pageable, String...tags);

    Page<Item> findByNameContainingAndCategory(String name, String category, Pageable pageable);

    @Query(value = "select * from #{#entityName} e order by RAND()", nativeQuery = true)
    Page<Item> getRandom(Pageable pageable);

}
