package com.travanleo.comment.service;

import java.util.List;

import com.travanleo.comment.dto.CommentDto;
import com.travanleo.comment.enums.Status;

public interface IcommentService {
    CommentDto addcomment(CommentDto commentDto);
    CommentDto updatecomment(CommentDto commentDto);
    List<CommentDto> findAll();
    CommentDto findBycommentId(long id);
    CommentDto findByTitle(String title);
    List<CommentDto> findAllByStatus(Status status);
    List<CommentDto> findAllByOrderByCreatedDateAsc();
    List<CommentDto> findAllByTitleContainingOrContentContaining(String title, String content);
}
