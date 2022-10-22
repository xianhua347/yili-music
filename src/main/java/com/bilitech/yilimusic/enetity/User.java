package com.bilitech.yilimusic.enetity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User extends BaseEntity{

    @Column(unique = true)
  private String username;

  private String nickname;

  private String password;

  private String gender;

  private Boolean locked = false;

  private Boolean enabled = true;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
          joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
  private List<Role> roles;
}
