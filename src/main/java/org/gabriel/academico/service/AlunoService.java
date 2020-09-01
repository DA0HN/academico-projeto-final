package org.gabriel.academico.service;

import org.gabriel.academico.database.AlunoDAO;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.model.Aluno;
import org.gabriel.academico.service.validator.PessoaValidator;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class AlunoService implements IService<Aluno, AlunoDAO> {

    private AlunoDAO dao;

    public AlunoService() {
        try {
            this.dao = new AlunoDAO(EntityManagerUtil.getEntityManager());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override public AlunoDAO getDAO() {
        return dao;
    }

    @Override public String validarDados(Aluno aluno) {
        var erro = new PessoaValidator().validarPessoa(aluno);
        if(aluno.getCursos() == null || aluno.getCursos().isEmpty()) {
            erro.add("Para inserir um aluno é necessário estar " +
                             "matriculado em pelo menos 1 curso.");
        }
        if(aluno.getMatricula() == null) {
            erro.add("Para inserir um aluno é necessário uma matricula.");
        }
        if(aluno.getMatricula() < 100) {
            erro.add("Para inserir um aluno a matricula precisa ter pelo menos 3 caracteres");
        }
        return erro.toString();
    }

}
