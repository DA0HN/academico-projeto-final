package org.gabriel.academico.database;

import org.gabriel.academico.model.Professor;

import javax.persistence.EntityManager;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class ProfessorDAO extends DAO<Professor>{
    public ProfessorDAO(EntityManager manager) {
        super(manager);
    }
}
