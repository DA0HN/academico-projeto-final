package org.gabriel.academico.service;

import org.gabriel.academico.database.AbstractDAO;
import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.model.ValueObject;

import java.util.List;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
interface IService<VO extends ValueObject, DAO extends AbstractDAO<VO>> {

    String validarDados(VO obj);
    DAO getDAO();

    default void save(VO obj) throws ServiceException {
        var mensagemErros = this.validarDados(obj);
        if(!mensagemErros.isEmpty()) throw new ServiceException("Erro: \n" + mensagemErros);
        try {
            if(obj.getId() != null) {
                getDAO().begin();
                getDAO().update(obj);
                getDAO().commit();
            } else {
                getDAO().begin();
                getDAO().save(obj);
                getDAO().commit();
            }
        }
        catch(DatabaseException e) {
            cancelTransaction("Não foi possível realizar inclusão - " + e.getMessage());
        }
    }

    default void saveAll(List<VO> objs) throws ServiceException {
        for(VO obj : objs) {
            save(obj);
        }
    }

    default void alterar(VO obj) throws ServiceException {
        String mensagemErros = this.validarDados(obj);
        if(!mensagemErros.isEmpty()) throw new ServiceException(mensagemErros);
        try {
            getDAO().begin();
            getDAO().update(obj);
            getDAO().commit();
        }
        catch(DatabaseException e) {
            cancelTransaction("Erro ao alterar a disciplina - " + e.getMessage());
        }
    }
    default void cancelTransaction(String mensagem) throws ServiceException {
        try {
            getDAO().undo();
            throw new DatabaseException(mensagem);
        }
        catch(DatabaseException exception) {
            throw new ServiceException(
                    "Erro ao tentar cancelar a transação - " + exception.getMessage());
        }
    }
    default void excluir(VO obj) throws ServiceException {
        try {
            getDAO().begin();
            getDAO().delete(obj);
            getDAO().commit();
        }
        catch(DatabaseException e) {
            cancelTransaction("Erro ao excluir o aluno - " + e.getMessage());
        }
    }
    default List<VO> buscarTodos() throws ServiceException {
        List<VO> all = null;
        try {
            getDAO().begin();
            all = getDAO().findAll();
            getDAO().commit();
        }
        catch(DatabaseException e) {
            cancelTransaction("Erro ao tentar buscar todos " + e.getMessage());
        }
        return all;
    }
}
