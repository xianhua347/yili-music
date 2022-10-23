package com.bilitech.yilimusic.enetity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;



@Data
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.bilitech.yilimusic.utils.KsuidIdentifierGenerator")
    public String id;

    @CreationTimestamp
    private Date createdTime;

    @CreationTimestamp
    private Date updatedTime;
}
