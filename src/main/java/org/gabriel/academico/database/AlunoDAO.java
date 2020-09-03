package org.gabriel.academico.database;

import org.gabriel.academico.model.Aluno;
import org.gabriel.academico.model.Curso;
import org.gabriel.academico.model.ValueObject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class AlunoDAO extends AbstractDAO<Aluno> {

    public AlunoDAO(EntityManager manager) {
        super(manager, Aluno.class);
    }

    public List<Aluno> buscarPorNome(String nome) throws DatabaseException {
        List<Aluno> alunos;
        try {
            TypedQuery<Aluno> consulta = manager.createQuery("select alu from Aluno alu " +
                                                                       "where upper(alu.nome) like :nome", Aluno.class);
            consulta.setParameter("nome", "%" + nome.trim().toUpperCase() + "%");
            alunos = consulta.getResultList();
        }catch(Exception e) {
            throw new DatabaseException("Erro ao buscar por nome - " + e.getMessage());
        }
        return alunos;
    }

    public Aluno buscarPorCodigo(Integer codigo) throws DatabaseException {
        Aluno aluno;
        try {
            aluno = this.manager.find(Aluno.class, codigo);
        } catch(Exception e) {
            throw new DatabaseException("Não foi possível buscar aluno por código - " + e.getMessage());
        }
        return aluno;
    }

    public List<Aluno> buscarPorCurso(Curso curso) throws DatabaseException {
        List<Aluno> alunos;
        try {
            TypedQuery<Aluno> query = manager.createQuery("select alu from Aluno alu where " +
                                                                    "alu.id = :idCurso", Aluno.class);
            query.setParameter("idCurso", curso.getId());
            if(query.getResultList().isEmpty()) throw new DatabaseException("Não existe " +
                                                                                        "alunos matriculados no curso " + curso.getNome());
            alunos = query.getResultList();
        } catch(Exception e) {
            throw new DatabaseException("");
        }
        return alunos;
    }
}
