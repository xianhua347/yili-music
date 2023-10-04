package com.bilitech.yilimusic.utils;

import com.github.ksuid.KsuidGenerator;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;


/**
 * 实体类主键生成器
 *
 * @author 陈现府
 */
@Component
public class KsuidIdentifierGenerator implements IdentifierGenerator {

  /**
   * @param sharedSessionContractImplementor session
   * @param o       对象
   * @return entity PRIMARY Key
   * @throws HibernateException Hibernate异常
   */
  @Override
  public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor,
      Object o) throws HibernateException {
    return KsuidGenerator.generate();
  }
}
