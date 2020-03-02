package com.travanleo.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travanleo.comment.enums.Status;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"createdDate", "lastModifiedDate","createdBy"})
public class BaseEntity implements Serializable {
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreatedDate
    @Column(name = "createdDate")
    private Date createdDate;
    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private Date lastModifiedDate;
    @CreatedBy
    @Column(name="createdBy")
    private String createdBy;

}
