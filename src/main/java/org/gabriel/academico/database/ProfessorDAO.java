package org.gabriel.academico.database;

import org.gabriel.academico.model.Professor;
import org.gabriel.academico.model.ValueObject;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class ProfessorDAO extends AbstractDAO<Professor> {
    public ProfessorDAO(EntityManager manager) {
        super(manager, Professor.class);
    }

}
