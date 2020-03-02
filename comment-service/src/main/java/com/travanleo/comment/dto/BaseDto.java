package com.travanleo.comment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travanleo.comment.enums.Status;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BaseDto implements Serializable {
    @ApiModelProperty(required = true, value = "Status")
    private Status status;
    private Date createdDate;
    private Date lastModifiedDate;
    private String createdBy;
}
