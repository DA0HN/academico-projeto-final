package org.gabriel.academico.service;

import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.database.IDAO;
import org.gabriel.academico.model.ValueObject;

import java.util.List;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
interface IService<E extends ValueObject, D extends IDAO<E>> {

    default void save(E obj) throws ServiceException {
        var mensagemErros = this.validarDados(obj);
        if(!mensagemErros.isEmpty()) throw new ServiceException("Erro: \n" + mensagemErros);
        try {
            getDAO().begin();
            getDAO().save(obj);
            getDAO().commit();
        }
        catch(DatabaseException e) {
            getDAO().undo();
            e.printStackTrace();
            throw new ServiceException("Não foi possível realizar inclusão " + e.getMessage());
        }
    }
    String validarDados(E obj);
    D getDAO();
    default void alterar(E obj) throws ServiceException {
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
    default void excluir(E obj) throws ServiceException {
        try {
            getDAO().begin();
            getDAO().delete(obj);
            getDAO().commit();
        }
        catch(DatabaseException e) {
            cancelTransaction("Erro ao excluir o aluno - " + e.getMessage());
        }
    }
    default List<E> buscarTodos() throws ServiceException {
        List<E> all = null;
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
