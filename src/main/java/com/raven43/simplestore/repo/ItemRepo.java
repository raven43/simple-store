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

    //    @Query(value = "select distinct * from simple_store.item as i where i.category=:category and i.id not in (select i2.id from simple_store.item as i2 join i2.tags as t where t not in :tags)",nativeQuery = true)
////    @Query("SELECT i FROM Item i JOIN i.tags t WHERE t in :tags AND i.category = :category group by i.id having count(i.id) = :tagCount")

//    Page<Item> findByCategoryAndTagsContaining(@Param("category") String category, @Param("tags") List<String> tags, Pageable pageable);
//    @Query("SELECT i FROM Item i WHERE i.tags in :tags  AND i.category=:category")
//    Page<Item> findByCategoryAndTagsContaining(@Param("category") String category, @Param("tags")Iterable<String> tags, Pageable pageable);
}
