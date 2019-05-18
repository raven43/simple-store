package com.raven43.simplestore.repo;

import com.raven43.simplestore.model.Comment;
import com.raven43.simplestore.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends PagingAndSortingRepository<Comment, Long> {

    Page<Comment> getByTopic(Topic topic, Pageable pageable);

    @Query("select c from Comment c where c.topic = ?1")
    Page<Comment> getByTopicId(Long id, Pageable pageable);

}
