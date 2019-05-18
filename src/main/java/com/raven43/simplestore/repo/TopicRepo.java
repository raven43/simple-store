package com.raven43.simplestore.repo;

import com.raven43.simplestore.model.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicRepo extends PagingAndSortingRepository<Topic, Long> {
}
