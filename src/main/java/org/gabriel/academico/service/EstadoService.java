package org.gabriel.academico.service;

import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.database.EstadoDAO;
import org.gabriel.academico.model.Estado;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class EstadoService implements IService<Estado, EstadoDAO>{

    private final EstadoDAO dao;

    public EstadoService() {
        this.dao = new EstadoDAO(EntityManagerUtil.getEntityManager());
    }

    @Override public String validarDados(Estado obj) {
        return null;
    }

    @Override public EstadoDAO getDAO() {
        return dao;
    }
}
