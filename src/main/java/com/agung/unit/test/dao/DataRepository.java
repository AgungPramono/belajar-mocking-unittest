package com.agung.unit.test.dao;

import java.sql.SQLException;
import java.util.List;

public interface DataRepository<T> {

    public void save(T object)throws SQLException;
    public T findById(Integer id)throws SQLException;
    public T findByCode(String code)throws SQLException;
    public List<T> findAll()throws SQLException;
    public List<T> findAllData()throws SQLException;
    public void delete(Integer id)throws SQLException;

}
