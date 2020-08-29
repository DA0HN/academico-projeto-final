package org.gabriel.academico.service;

import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.database.ProfessorDAO;
import org.gabriel.academico.model.Professor;
import org.gabriel.academico.service.validator.PessoaValidator;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class ProfessorService {

    private final ProfessorDAO dao;

    public ProfessorService() {
        this.dao = new ProfessorDAO(EntityManagerUtil.getEntityManager());
    }

    public void save(Professor professor) {
        var mensagemErros = this.validarDados(professor);
        if(!mensagemErros.isEmpty()) throw new ServiceException();
        try {
            dao.begin();
            dao.save(professor);
            dao.commit();
        }
        catch(DatabaseException e) {
            dao.undo();
        }
    }

    private String validarDados(Professor professor) {
        var erro = new PessoaValidator().validarPessoa(professor);
        if(professor.getCursos() == null || professor.getCursos().isEmpty()) {
            erro.add("Para inserir um professor é necessário estar " +
                             "matriculado em pelo menos 1 curso.");
        }
        if(professor.getTitulacao() == null) {
            erro.add("Para inserir um professor é necessário uma titulação");
        }
        if(professor.getFormacao() == null) {
            erro.add("Para inserir um professor é necessário uma formação");
        }
        return erro.toString();
    }
}
