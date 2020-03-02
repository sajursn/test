package com.travanleo.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travanleo.comment.entity.Comment;
import com.travanleo.comment.enums.Status;

@Repository
public interface IcommentRepository extends JpaRepository<Comment, Long> {
    Comment findBycommentId(long id);
    Comment findByTitle(String text);
    List<Comment> findByStatus(Status status);
    List<Comment> findByOrderByCreatedDateAsc();
    List<Comment> findByTitleContainingOrContentContaining(String text, String textAgain);
}
