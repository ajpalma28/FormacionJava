package com.example.ej7.crudvalidation;

import org.hibernate.HibernateException;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.exception.spi.Configurable;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

public class MiGenerador implements IdentifierGenerator, Configurable {

    public String prefijo;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        Stream<String> ids = session.createQuery(query).stream();

        Long max = ids.map(o -> o.replace(prefijo + "-", ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);

        return prefijo + "-" + (max + 1);
    }

    @Override
    public void initialize(SqlStringGenerationContext context) {
        IdentifierGenerator.super.initialize(context);
    }

    @Override
    public void configure(Properties properties) throws HibernateException {
        prefijo = properties.getProperty("prefijo");
    }
}
