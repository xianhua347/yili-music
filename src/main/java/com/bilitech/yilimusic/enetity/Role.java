package com.bilitech.yilimusic.enetity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Role extends AbstractEntity {

    @Column(length = 32,name = "name")
    private String name;

    @Column(length = 32,name = "title")
    private String title;

}
