package org.gabriel.academico.database;

import org.gabriel.academico.model.ValueObject;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author daohn on 30/07/2020
 * @project ExercicioMapeamentoJPA
 */
public class DAO<VO extends ValueObject> implements IDAO<VO>{

    public final    Class<VO>     valueObject;
    protected final EntityManager manager;

    public DAO(EntityManager manager) {
        this.manager     = manager;
        this.valueObject = (Class<VO>) ValueObject.class;
    }

    DAO(EntityManager manager, Class<VO> valueObject) {
        this.manager     = manager;
        this.valueObject = valueObject;
    }

    @Override public DAO<VO> begin() throws DatabaseException {
        try {
            manager.getTransaction().begin();
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao iniciar transação " + e.getMessage());
        }
        return this;
    }

    @Override public DAO<VO> commit() throws DatabaseException {
        try {
            manager.getTransaction().commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Erro ao confirmar a transação " + e.getMessage());
        }
        return this;
    }

    @Override public DAO<VO> undo() {
        try {
            manager.getTransaction().rollback();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override public VO findById(Integer id) throws DatabaseException {
        try {
            return manager.find(valueObject, id);
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao tentar buscar por id " + e.getMessage());
        }
    }

    @Override public DAO<VO> save(List<VO> vos) throws DatabaseException {
        for(VO vo : vos) {
            save(vo);
        }
        return this;
    }

    @Override public DAO<VO> save(VO vo) throws DatabaseException {
        try {
            manager.persist(vo);
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao inserir entidade " + e.getMessage());
        }
        return this;
    }

    @Override public DAO<VO> update(VO vo) throws DatabaseException {
        try {
            manager.merge(vo);
            return this;
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao tentar atualizar entidade " + e.getMessage());
        }
    }

    @Override public DAO<VO> delete(VO vo) throws DatabaseException {
        try {
            manager.remove(vo);
            return this;
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao tentar deletar entidde " + e.getMessage());
        }
    }

    @Override public List<VO> findAll() throws DatabaseException {
        return findAll(10, 0);
    }

    @Override public List<VO> findAll(int limit, int offset) throws DatabaseException {
        try {
            if(valueObject == null || valueObject.equals(ValueObject.class)) {
                throw new UnsupportedOperationException("O tipo do objeto para a busca não foi " +
                                                                "inserido VO = null");
            }
            String query = "select vo from " + this.valueObject.getName() + " vo";
            return manager.createQuery(query, valueObject)
                    .setMaxResults(limit)
                    .setFirstResult(offset)
                    .getResultList();
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao executar buscar todos " + e.getMessage());
        }
    }

    @Override public void fechar() throws DatabaseException {
        try {
            manager.close();
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao fechar conexão " + e.getMessage());
        }
    }
}
