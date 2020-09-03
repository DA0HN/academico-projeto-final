package org.gabriel.academico.database;

import org.gabriel.academico.model.ValueObject;

import java.util.List;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public interface IDAO<VO extends ValueObject> {
    AbstractDAO<VO> begin() throws DatabaseException;
    AbstractDAO<VO> commit() throws DatabaseException;
    AbstractDAO<VO> undo();
    VO findById(Integer id) throws DatabaseException;
    AbstractDAO<VO> save(VO vo) throws DatabaseException;
    AbstractDAO<VO> save(List<VO> vos) throws DatabaseException;
    AbstractDAO<VO> update(VO vo) throws DatabaseException;
    AbstractDAO<VO> delete(VO vo) throws DatabaseException;
    List<VO> findAll() throws DatabaseException;
    void fechar() throws DatabaseException;
}
