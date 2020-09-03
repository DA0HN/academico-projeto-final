package org.gabriel.academico.database;

import org.gabriel.academico.model.Estado;

import javax.persistence.EntityManager;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class EstadoDAO extends AbstractDAO<Estado> {
    public EstadoDAO(EntityManager manager) {
        super(manager, Estado.class);
    }

}
