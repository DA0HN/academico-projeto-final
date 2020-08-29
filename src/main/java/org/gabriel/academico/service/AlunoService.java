package org.gabriel.academico.service;

import org.gabriel.academico.database.AlunoDAO;
import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.model.Aluno;
import org.gabriel.academico.service.validator.PessoaValidator;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class AlunoService {

    private final AlunoDAO dao;

    public AlunoService() {
        this.dao = new AlunoDAO(EntityManagerUtil.getEntityManager());
    }

    public void save(Aluno aluno) {
        var mensagemErros = this.validarDados(aluno);
        if(!mensagemErros.isEmpty()) throw new ServiceException();
        try {
            dao.begin();
            dao.save(aluno);
            dao.commit();
        }
        catch(DatabaseException e) {
            dao.undo();
        }
    }

    private String validarDados(Aluno aluno) {
        var erro = new PessoaValidator().validarPessoa(aluno);
        if(aluno.getCursos() == null || aluno.getCursos().isEmpty()) {
            erro.add("Para inserir um aluno é necessário estar " +
                             "matriculado em pelo menos 1 curso.");
        }
        if(aluno.getMatricula() == null) {
            erro.add("Para inserir um aluno é necessário uma matricula.");
        }
        if(aluno.getMatricula() > 99) {
            erro.add("Para inserir um aluno a matricula precisa ter pelo menos 3 caracteres");
        }
        return erro.toString();
    }

}
