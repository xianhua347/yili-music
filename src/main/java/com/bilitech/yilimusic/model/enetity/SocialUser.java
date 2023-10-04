package com.bilitech.yilimusic.model.enetity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;
import org.hibernate.proxy.HibernateProxy;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "social_user")
public class SocialUser extends AbstractEntity {

  @Column(length = 200, name = "access_code")
  private String accessCode;

  @Column(length = 200, name = "access_token")
  private String accessToken;

  @Column(length = 200, name = "code")
  private String code;

  @Column(length = 200, name = "expire_in")
  private Integer expireIn;

  @Column(length = 200, name = "id_token")
  private String idToken;

  @Column(length = 200, name = "openid")
  private String openId;

  @Column(length = 200, name = "refresh_token")
  private String refreshToken;

  @Column(length = 200, name = "scope")
  private String scope;

  @Column(length = 200, name = "source")
  private String source;

  @Column(length = 200, name = "token_type")
  private String tokenType;

  @Column(length = 200, name = "uId")
  private String uId;

  @Column(length = 200, name = "union_id")
  private String unionId;

  @Column(length = 200, name = "uuid")
  private String uuid;

  @Column(length = 200, name = "nickname")
  private String nickname;

  @Column(length = 200, name = "username")
  private String username;

  @Column(length = 200, name = "avatar")
  private String avatar;

  public static SocialUser build(AuthUser authUser) {
    return new SocialUser()
        .setOpenId(authUser.getToken().getOpenId())
        .setUnionId(authUser.getToken().getUnionId())
        .setTokenType(authUser.getToken().getTokenType())
        .setExpireIn(authUser.getToken().getExpireIn())
        .setUsername(authUser.getUsername())
        .setAccessCode(authUser.getToken().getAccessCode())
        .setAccessToken(authUser.getToken().getAccessToken())
        .setRefreshToken(authUser.getToken().getRefreshToken())
        .setUuid(authUser.getUuid())
        .setSource(authUser.getSource());
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
    SocialUser that = (SocialUser) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}

