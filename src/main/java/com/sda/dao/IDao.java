package com.sda.dao;

import java.util.List;

public interface IDao<T> {


        List<T> getAll();
        void delete(T t);
    }

