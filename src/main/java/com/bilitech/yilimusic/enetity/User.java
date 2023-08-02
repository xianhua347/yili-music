package com.bilitech.yilimusic.enetity;

import com.bilitech.yilimusic.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
public class User extends AbstractEntity implements UserDetails {

  @Column(unique = true, length = 64, name = "username")
  private String username;

  @Column(length = 64, name = "nickname")
  private String nickname;

  @Column(length = 64, name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(length = 225, name = "gender")
  private Gender gender;

  @Column(name = "locked")
  private Boolean locked = false;

  @Column(name = "enabled")
  private Boolean enabled = true;

  @Column(name = "last_login_ip")
  private String lastLoginIp;

  @Column(name = "last_login_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastLoginTime;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
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
    return ! getLocked();
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
