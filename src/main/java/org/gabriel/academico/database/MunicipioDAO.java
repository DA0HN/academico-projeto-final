package org.gabriel.academico.database;

import org.gabriel.academico.model.Aluno;
import org.gabriel.academico.model.Estado;
import org.gabriel.academico.model.Municipio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class MunicipioDAO extends AbstractDAO<Municipio> {
    public MunicipioDAO(EntityManager manager) {
        super(manager, Municipio.class);
    }

    public List<Municipio> findByEstado(Estado estado) throws DatabaseException {
        List<Municipio> municipios;
        try {
            TypedQuery<Municipio> query = manager.createQuery(
                    "select municipio from Municipio municipio where municipio.estado.id = :idEstado",
                    Municipio.class
            );
            query.setParameter("idEstado", estado.getId());
            if(query.getResultList().isEmpty()) {
                throw new DatabaseException("Não existe existe um municipio em " + estado.getNome());
            }
            municipios = query.getResultList();
        }
        catch(Exception e) {
            throw new DatabaseException("Não foi possível buscar por Estado");
        }
        return municipios;
    }
}