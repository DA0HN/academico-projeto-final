package org.gabriel.academico.database;

import org.gabriel.academico.model.ValueObject;

import javax.persistence.EntityManager;

/**
 * @author daohn on 02/09/2020
 * @project EstudoDeCaso
 */
public class DAOImpl extends AbstractDAO<ValueObject> {
    public DAOImpl(EntityManager manager) {
        super(manager, ValueObject.class);
    }
}
