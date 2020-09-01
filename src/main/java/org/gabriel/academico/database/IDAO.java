package org.gabriel.academico.database;

import org.gabriel.academico.model.ValueObject;

import java.util.List;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public interface IDAO<VO extends ValueObject> {
    DAO<VO> begin() throws DatabaseException;
    DAO<VO> commit() throws DatabaseException;
    DAO<VO> undo();
    VO findById(Integer id) throws DatabaseException;
    DAO<VO> save(VO vo) throws DatabaseException;
    DAO<VO> save(List<VO> vos) throws DatabaseException;
    DAO<VO> update(VO vo) throws DatabaseException;
    DAO<VO> delete(VO vo) throws DatabaseException;
    List<VO> findAll(int limit, int offset) throws DatabaseException;
    List<VO> findAll() throws DatabaseException;
    void fechar() throws DatabaseException;
}
