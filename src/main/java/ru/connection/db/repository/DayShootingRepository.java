package ru.connection.db.repository;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ru.connection.db.entity.DayShooting;
import ru.connection.db.repository.dao.Dao;

import java.util.List;

public class DayShootingRepository extends Dao<DayShooting> {
    @Override
    public List<DayShooting> find(Object filter) {
        return null;
    }

    @Override
    public DayShooting save(DayShooting dayShooting) {
        helper.update("INSERT INTO day_shooting(id, date_shooting, resources, start_time, end_time, id_actor, location) VALUES (?,?,?,?,?,?,?) ",
                dayShooting.getId(),
                dayShooting.getDateShooting(),
                dayShooting.getResources(),
                dayShooting.getStartTime(),
                dayShooting.getEndTime(),
                dayShooting.getId_actor(),
                dayShooting.getLocation());
        return dayShooting;
    }

    @Override
    public boolean delete(int id) {
        return helper.update("DELETE FROM day_shooting where id=?", id) > 0;
    }

    @Override
    public List<DayShooting> findAll() {
        return helper.query("SELECT * from day_shooting", new BeanPropertyRowMapper<>(DayShooting.class));
    }
}
