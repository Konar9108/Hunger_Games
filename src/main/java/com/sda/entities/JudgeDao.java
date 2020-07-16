package com.sda.entities;


import java.util.List;
import java.util.Optional;

public class JudgeDao extends ConnectionClass implements IDao<Judge> {


    @Override
    public Optional<Judge> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Judge> getAll() {
        return null;
    }

    @Override
    public void save(Judge judge) {

    }

    @Override
    public void update(Judge judge, String[] params) {

    }

    @Override
    public void delete(Judge judge) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(judge);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();

    }
}
