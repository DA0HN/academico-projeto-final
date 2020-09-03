package org.gabriel.academico.consultas;

import org.gabriel.academico.app.ValueObjectFactory;
import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.model.Aluno;
import org.gabriel.academico.model.Curso;
import org.gabriel.academico.model.Professor;
import org.gabriel.academico.model.Telefone;
import org.gabriel.academico.service.ServiceException;

/**
 * @author daohn on 03/09/2020
 * @project EstudoDeCaso
 */
public class LetraB {

    public static void main(String[] args) throws DatabaseException, ServiceException {

        // Cria as entidades e insere no banco
        ValueObjectFactory.create();

        var manager = EntityManagerUtil.getEntityManager();
        var query = manager.createQuery(
                "select aluno from Aluno aluno order by aluno.nome",
                Aluno.class
        );
        var resultado = query.getResultList();

        System.out.println(".".repeat(50));
        for(Aluno aluno : resultado) {
            System.out.println("Id.................: " + aluno.getId());
            System.out.println("Nome...............: " + aluno.getNome());
            System.out.println("Sexo...............: " + aluno.getSexo());
            System.out.println("Telefones..........: ");
            for(Telefone telefone : aluno.getTelefones()) {
                System.out.println("\tNúmero....: " + telefone.getNumero());
                System.out.println("\tTipo......: " + telefone.getTipo());
            }
            System.out.println("Data de nascimento.: " + aluno.getDataNascimento());
            System.out.println("Endereço...........: ");
            System.out.println("\tBairro.........: " + aluno.getEndereco().getBairro());
            System.out.println("\tNúmero.........: " + aluno.getEndereco().getNumero());
            System.out.println("\tLogradouro.....: " + aluno.getEndereco().getLogradouro());
            System.out.println("\tTipo logradouro: " + aluno.getEndereco().getTipoLogradouro());
            System.out.println("\tEstado.........: " + aluno.getEndereco().getMunicipio().getEstado().getNome());
            System.out.println("\tMunicípio......: " + aluno.getEndereco().getMunicipio().getNome());

            System.out.println("Matrícula..........: " + aluno.getMatricula());

            System.out.println("Curso..............:");
            for(Curso c : aluno.getCursos()) {
                System.out.println("\tId.........: " + c.getId());
                System.out.println("\tNome.......: " + c.getNome());
                System.out.println("\tTipo.......: " + c.getTipo());

                System.out.println("\tProfessores: ");
                for(Professor professor : c.getProfessores()) {
                    System.out.println("\t\tNome: " + professor.getNome());
                }
            }
            System.out.println(".".repeat(50));
        }
    }

}
