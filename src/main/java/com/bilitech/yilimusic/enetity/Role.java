package com.bilitech.yilimusic.enetity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Data
public class Role extends AbstractEntity {

  @Column(length = 32, name = "name")
  private String name;

  @Column(length = 32, name = "title")
  private String title;

}
