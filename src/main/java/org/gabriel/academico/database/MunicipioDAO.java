package org.gabriel.academico.database;

import org.gabriel.academico.model.Municipio;

import javax.persistence.EntityManager;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class MunicipioDAO extends DAO<Municipio>{
    public MunicipioDAO(EntityManager manager) {
        super(manager);
    }
}
