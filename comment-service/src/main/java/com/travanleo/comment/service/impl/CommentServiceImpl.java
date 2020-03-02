package com.travanleo.comment.service.impl;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travanleo.comment.dto.CommentDto;
import com.travanleo.comment.entity.Comment;
import com.travanleo.comment.enums.Status;
import com.travanleo.comment.repository.IcommentRepository;
import com.travanleo.comment.service.IcommentService;

@Service
public class CommentServiceImpl implements IcommentService {

    @Autowired
    private IcommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto addcomment(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public CommentDto updatecomment(CommentDto commentDto) {
    	Comment model = commentRepository.findBycommentId(commentDto.getCommentId());
        model.setTitle(commentDto.getTitle());
        model.setContent(commentDto.getContent());
        return modelMapper.map(commentRepository.save(model), CommentDto.class);
    }

    @Override
    public List<CommentDto> findAll() {
        Type listType = new TypeToken<List<CommentDto>>() {
        }.getType();
        List<CommentDto> commentDtos = modelMapper.map(commentRepository.findAll(), listType);
        return commentDtos;
    }

    @Override
    public CommentDto findBycommentId(long id) {
        return modelMapper.map(commentRepository.findBycommentId(id), CommentDto.class);
    }

    @Override
    public CommentDto findByTitle(String title) {
        Optional<Comment> comment = Optional.ofNullable((commentRepository.findByTitle(title)));
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> findAllByStatus(Status status) {
        Type listType = new TypeToken<List<CommentDto>>() {
        }.getType();
        return  modelMapper.map(commentRepository.findByStatus(status), listType);
    }

    @Override
    public List<CommentDto> findAllByOrderByCreatedDateAsc() {
        Type listType = new TypeToken<List<CommentDto>>() {
        }.getType();
        List<Comment> comments = commentRepository.findByOrderByCreatedDateAsc();
        if (comments.size() > 0)
            return modelMapper.map(comments, listType);
        else
            return Collections.emptyList();
    }

    @Override
    public List<CommentDto> findAllByTitleContainingOrContentContaining(String title, String content) {
        Type listType = new TypeToken<List<CommentDto>>() {
        }.getType();
        List<CommentDto> commentDtos = modelMapper.map(commentRepository.findByTitleContainingOrContentContaining(title, content), listType);
        return commentDtos;
    }
}
