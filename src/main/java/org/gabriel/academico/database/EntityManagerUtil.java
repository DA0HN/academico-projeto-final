package org.gabriel.academico.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author daohn on 30/07/2020
 * @project ExercicioMapeamentoJPA
 */
public class EntityManagerUtil {
    private static final EntityManagerFactory factory;

    private EntityManagerUtil() {
    }

    static {
        factory = Persistence.createEntityManagerFactory("academico");
    }

    public static EntityManager getEntityManager() {
        if(factory == null) throw new IllegalStateException("Unidade de persistência não iniciada");
        return factory.createEntityManager();
    }
}
