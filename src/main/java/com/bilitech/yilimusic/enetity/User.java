package com.bilitech.yilimusic.enetity;

import com.bilitech.yilimusic.Enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User extends AbstractEntity {

  @Column(unique = true, length = 64,name = "username")
  private String username;

  @Column(length = 64,name = "nickname")
  private String nickname;

  @Column(length = 64,name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(length = 225,name = "gender")
  private Gender gender;

  @Column(name = "locked")
  private Boolean locked = false;

  @Column(name = "enabled")
  private Boolean enabled = true;

  @Column(name ="last_login_ip")
  private String lastLoginIp;

  @Column(name = "last_login_time")
  private Date lastLoginTime;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
          joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
  private List<Role> roles;
}
