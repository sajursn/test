package com.travanleo.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travanleo.comment.dto.CommentDto;
import com.travanleo.comment.enums.Status;
import com.travanleo.comment.service.IcommentService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api")
@RestController
public class CommentController {

    @Autowired
    private IcommentService commentService;

    @PostMapping("/comment")
    @ApiOperation(value = "Create Operation", response = CommentDto.class)
    public ResponseEntity<CommentDto> addcomment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<CommentDto>(commentService.addcomment(commentDto), HttpStatus.OK);
    }

    @PutMapping("/comment")
    @ApiOperation(value = "Update Operation", response = CommentDto.class)
    public ResponseEntity<CommentDto> updatecomment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<CommentDto>(commentService.updatecomment(commentDto), HttpStatus.OK);
    }

    @GetMapping("/comment/list")
    @ApiOperation(value = "Get All Operation", response = CommentDto.class)
    public ResponseEntity<List<CommentDto>> getAll() {
        return new ResponseEntity<List<CommentDto>>(commentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/comment/id/{id}")
    @ApiOperation(value = "Get By Id Operation", response = CommentDto.class)
    public ResponseEntity<CommentDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(commentService.findBycommentId(id), HttpStatus.OK);
    }

    @GetMapping("/comment/title/{title}")
    @ApiOperation(value = "Get By Title Operation", response = CommentDto.class)
    public ResponseEntity<CommentDto> getByTitle(@PathVariable String title) {
        return new ResponseEntity<CommentDto>(commentService.findByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/comment/list/OrderByCreatedDateAsc")
    @ApiOperation(value = "Get All By Order By CreatedDate  Operation", response = CommentDto.class)
    public ResponseEntity<List<CommentDto>> getAllByOrderByCreatedDateAsc() {
        return new ResponseEntity<List<CommentDto>>(commentService.findAllByOrderByCreatedDateAsc(), HttpStatus.OK);
    }

    @GetMapping("/comment/list/status/{status}")
    @ApiOperation(value = "Get All By Status Operation", response = CommentDto.class)
    public ResponseEntity<List<CommentDto>> getAllByStatus(@PathVariable Status status) {
        return new ResponseEntity<List<CommentDto>>(commentService.findAllByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/comment/list/title/{title}/content/{content}")
    @ApiOperation(value = "Get All By Containing Content Or Title Operation", response = CommentDto.class)
    public ResponseEntity<List<CommentDto>> getAllByTitleContainingOrContentContaining(@PathVariable String title,@PathVariable String content) {
        return new ResponseEntity<List<CommentDto>>(commentService.findAllByTitleContainingOrContentContaining(title, content), HttpStatus.OK);
    }
}
