package com.bilitech.yilimusic.enetity;

import com.bilitech.yilimusic.Enums.Gender;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User extends AbstractEntity implements UserDetails {

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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !getLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return getEnabled();
  }
}
