package org.gabriel.academico.database;

import org.gabriel.academico.model.Curso;

import javax.persistence.EntityManager;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class CursoDAO extends DAO<Curso> {
    public CursoDAO(EntityManager manager) {
        super(manager);
    }
}
