package org.gabriel.academico.database;

import org.gabriel.academico.model.ValueObject;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author daohn on 30/07/2020
 * @project ExercicioMapeamentoJPA
 */
public class AbstractDAO<VO> {

    public final    Class<VO>     valueObject;
    protected final EntityManager manager;

    public AbstractDAO() {
        this.manager     = EntityManagerUtil.getEntityManager();
        this.valueObject = (Class<VO>) ValueObject.class;
    }

    public AbstractDAO(EntityManager manager, Class<VO> valueObject) {
        this.manager     = manager;
        this.valueObject = valueObject;
    }

    public AbstractDAO<VO> begin() throws DatabaseException {
        try {
            manager.getTransaction().begin();
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao iniciar transação " + e.getMessage());
        }
        return this;
    }

    public AbstractDAO<VO> commit() throws DatabaseException {
        try {
            manager.getTransaction().commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Erro ao confirmar a transação " + e.getMessage());
        }
        return this;
    }

    public AbstractDAO<VO> undo() {
        try {
            manager.getTransaction().rollback();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public VO findById(Integer id) throws DatabaseException {
        try {
            return manager.find(valueObject, id);
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao tentar buscar por id " + e.getMessage());
        }
    }

    public AbstractDAO<VO> save(VO vo) throws DatabaseException {
        try {
            manager.persist(vo);
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao inserir entidade " + e.getMessage());
        }
        return this;
    }

    public AbstractDAO<VO> save(List<VO> vos) throws DatabaseException {
        for(VO vo : vos) {
            save(vo);
        }
        return this;
    }

    public AbstractDAO<VO> update(VO vo) throws DatabaseException {
        try {
            manager.merge(vo);
            return this;
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao tentar atualizar entidade " + e.getMessage());
        }
    }

    public AbstractDAO<VO> delete(VO vo) throws DatabaseException {
        try {
            manager.remove(vo);
            return this;
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao tentar deletar entidde " + e.getMessage());
        }
    }

    public void fechar() throws DatabaseException {
        try {
            manager.close();
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao fechar conexão " + e.getMessage());
        }
    }

    public List<VO> findAll() throws DatabaseException {
        try {
            if(valueObject == null) {
                throw new UnsupportedOperationException("O tipo do objeto para a busca não foi " +
                                                                "inserido VO = null");
            }
            String query = "select vo from " + valueObject.getName() + " vo";
            System.out.println(query);
            return manager
                    .createQuery(query, valueObject)
                    .getResultList();
        }
        catch(Exception e) {
            throw new DatabaseException("Erro ao executar buscar todos " + e.getMessage());
        }
    }
}
