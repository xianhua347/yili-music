package com.bilitech.yilimusic.utils;

import com.github.ksuid.KsuidGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * 实体类主键生成器
 */
@Component
public class KsuidIdentifierGenerator implements IdentifierGenerator {

    /**
     * @param sharedSessionContractImplementor
     * @param o
     * @return entity PRIMARY Key
     * @throws HibernateException
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return KsuidGenerator.generate();
    }
}