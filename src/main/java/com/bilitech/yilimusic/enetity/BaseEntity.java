package com.bilitech.yilimusic.enetity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;



@MappedSuperclass
public abstract class BaseEntity {
    @Id
    private String id;

    @CreationTimestamp
    private Date createdTime;

    @CreationTimestamp
    private Date updatedTime;
}
