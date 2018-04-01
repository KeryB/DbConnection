package ru.connection.db.repository;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ru.connection.db.entity.Actor;
import ru.connection.db.repository.dao.Dao;
import ru.connection.db.utils.QueryUtils;

import java.util.List;

public class ActorRepository extends Dao<Actor> {

    @Override
    public List<Actor> find(Object filter) {

        String query = "SELECT * FROM actor WHERE" + QueryUtils.toQuery(filter);
        List<Actor> actorList = helper.query(query, (resultSet, index) -> {
            Actor actor = new Actor();
            actor.setId(resultSet.getInt("id"));
            actor.setSalary(resultSet.getDouble("salary"));
            actor.setLastName(resultSet.getString("last_name"));
            actor.setFirstName(resultSet.getString("first_name"));
            actor.setSecondName(resultSet.getString("second_name"));
            actor.setGender(resultSet.getString("gender"));
            actor.setBirthday(resultSet.getDate("birthday"));
            return actor;
        });
        return actorList;
    }

    @Override
    public Actor save(Actor actor) {
        helper.update("INSERT INTO actor(id, birthday, first_name, second_name, last_name, gender, salary) VALUES (?,?,?,?,?,?,?) ",
                actor.getId(),
                actor.getBirthday(),
                actor.getFirstName(),
                actor.getSecondName(),
                actor.getLastName(),
                actor.getGender(),
                actor.getSalary());
        return actor;
    }

    @Override
    public boolean delete(int id) {
        return helper.update("DELETE FROM actor WHERE id=?", id) > 0;
    }

    @Override
    public List<Actor> findAll() {

        String query = "SELECT * FROM actor";
        List<Actor> actorList = helper.query(query, new BeanPropertyRowMapper<>(Actor.class));
        return actorList;
    }
}
