package ru.connection.db.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.connection.db.datasource.DataSourceFactory;

import java.util.List;

public abstract class Dao<T> {

    protected static final JdbcTemplate helper = DataSourceFactory.getInstance().getHelper();

    public abstract List<T> find(Object filter);

    public abstract T save(T crud);

    public abstract boolean delete(int id);

    public abstract List<T> findAll();

}
