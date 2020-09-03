package org.gabriel.academico.service;

import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.database.MunicipioDAO;
import org.gabriel.academico.model.Estado;
import org.gabriel.academico.model.Municipio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class MunicipioService implements IService<Municipio, MunicipioDAO> {

    private final MunicipioDAO dao;

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

    public List<Municipio> findByEstado(Estado estado) throws ServiceException {
        List<Municipio> list = new ArrayList<>();
        try {
            list = dao.findByEstado(estado);
        } catch(DatabaseException e) {
            cancelTransaction("Não foi possível buscar por Estado");
        }
        return list;
    }
}
