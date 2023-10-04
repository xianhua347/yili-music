package com.bilitech.yilimusic.model.enetity;

import com.bilitech.yilimusic.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Accessors(chain = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User extends AbstractEntity implements UserDetails {

  @Column(unique = true, length = 64, name = "username")
  private String username;

  @Column(length = 64, name = "nickname")
  private String nickname;

  @Column(length = 64, name = "password")
  private String password;

  @Column(name = "avatar")
  private String avatar;

  /**
   * 第三方平台UUID
   */
  @Column(name = "uuid", length = 128)
  private String uuid;

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
  private List<Role> roles = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @Exclude
  private List<SocialUser> socialUsers = new ArrayList<>();

  /**
   * 根据参数初始化一个第三方用户
   *
   * @param authUser justauth框架实体类
   * @param role     权限
   * @return 第三方初始化user
   */
  public static User build(AuthUser authUser, Role... role) {
    return new User()
        .setUsername(authUser.getUsername())
        .setNickname(authUser.getNickname())
        .setAvatar(authUser.getAvatar())
        .setGender(Gender.UNKNOWN)
        .setSocialUsers(List.of(SocialUser.build(authUser)))
        .setRoles(List.of(role));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
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

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    Class<?> oEffectiveClass = o instanceof HibernateProxy
        ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
        : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    User user = (User) o;
    return getId() != null && Objects.equals(getId(), user.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}
