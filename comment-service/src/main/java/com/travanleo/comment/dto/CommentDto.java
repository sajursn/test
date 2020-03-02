package com.travanleo.comment.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "comment Data Transfer Object")
public class CommentDto extends BaseDto {
    @ApiModelProperty(value = "commentID")
    private long commentId;
    @ApiModelProperty(required = true, value = "Title")
    private String title;
    @ApiModelProperty(required = true, value = "Content")
    private String content;
}
