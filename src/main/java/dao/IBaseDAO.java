package dao;

import dao.exception.DAOException;
import entity.IEntity;
import manager.QueriesEnum;

import java.util.List;

/**
 * Created by EgorVeremeychik on 23.04.2016.
 */
public interface IBaseDAO<T extends IEntity> {
    boolean create(T entity) throws DAOException;

    T read(int key);

    T read(T entity) throws DAOException;

    List<T> readAll(int start);

    List<T> readAll(String string) throws DAOException;

    boolean update(T entity) throws DAOException;

    boolean delete(int key) throws DAOException;

    boolean delete(T entity) throws DAOException;

    List<T> execute(QueriesEnum query, Object[] params, int start);

    List<T> execute(QueriesEnum query, long param, int start);

    int count(QueriesEnum query, Object[] params);
}
