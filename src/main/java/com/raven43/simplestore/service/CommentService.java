package com.raven43.simplestore.service;

import com.raven43.simplestore.exception.NoSuchCommentException;
import com.raven43.simplestore.model.User;
import com.raven43.simplestore.repo.CommentRepo;
import com.raven43.simplestore.repo.TopicRepo;
import com.raven43.simplestore.exception.NoSuchTopicException;
import com.raven43.simplestore.model.Comment;
import com.raven43.simplestore.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final TopicRepo topicRepo;

    @Autowired
    public CommentService(
            CommentRepo commentRepo,
            TopicRepo topicRepo
    ) {
        this.commentRepo = commentRepo;
        this.topicRepo = topicRepo;
    }

    public void post(User user, Topic topic, String text) {
        Comment comment = new Comment(user, topic, text);
        commentRepo.save(comment);
    }

    public void post(User user, Long topicId, String text) {
        Topic topic = topicRepo.findById(topicId).orElseThrow(() -> new NoSuchTopicException(topicId));
        Comment comment = new Comment(user, topic, text);
        commentRepo.save(comment);
    }

    public Page<Comment> get(Topic topic, Pageable pageable) {
        return commentRepo.getByTopic(topic, pageable);
    }

    public Page<Comment> get(Long topicId, Pageable pageable) {
        Topic topic = topicRepo.findById(topicId).orElseThrow(() -> new NoSuchTopicException(topicId));
        return commentRepo.getByTopic(topic, pageable);
    }

    public void delete(Comment comment) {
        commentRepo.delete(comment);
    }

    public void delete(Long commentId) {
        commentRepo.deleteById(commentId);
    }

    public Comment getComment(Long id) {
        return commentRepo.findById(id).orElseThrow(()->new NoSuchCommentException(id));
    }
}
