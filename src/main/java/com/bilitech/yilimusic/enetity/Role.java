package com.bilitech.yilimusic.enetity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Role extends BaseEntity{

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String title;

}
