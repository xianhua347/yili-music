package com.bilitech.yilimusic.enetity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;



@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.bilitech.yilimusic.utils.KsuidIdentifierGenerator")
    private String id;

    @CreationTimestamp
    private Date createdTime;

    @CreationTimestamp
    private Date updatedTime;
}
