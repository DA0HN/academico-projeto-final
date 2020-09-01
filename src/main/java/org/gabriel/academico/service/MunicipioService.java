package org.gabriel.academico.service;

import org.gabriel.academico.database.DAO;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.database.MunicipioDAO;
import org.gabriel.academico.model.Municipio;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class MunicipioService implements IService<Municipio, MunicipioDAO> {

    private MunicipioDAO dao;

    public MunicipioService() {
        this.dao = new MunicipioDAO(EntityManagerUtil.getEntityManager());
    }

    @Override public String validarDados(Municipio obj) {
        // TODO (daohn, 01/09/2020): Método gerado automaticamente
        return null;
    }

    @Override public MunicipioDAO getDAO() {
        return dao;
    }
}
