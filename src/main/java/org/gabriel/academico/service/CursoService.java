package org.gabriel.academico.service;

import org.gabriel.academico.database.CursoDAO;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.model.Curso;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class CursoService implements IService<Curso, CursoDAO> {

    private final CursoDAO dao;

    public CursoService() {
        this.dao = new CursoDAO(EntityManagerUtil.getEntityManager());
    }

    @Override public CursoDAO getDAO() {
        return this.dao;
    }

    @Override public String validarDados(Curso obj) {
        return null;
    }


}
