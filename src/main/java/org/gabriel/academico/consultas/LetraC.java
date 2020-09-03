package org.gabriel.academico.consultas;

import org.gabriel.academico.app.ValueObjectFactory;
import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.model.Curso;
import org.gabriel.academico.model.Professor;
import org.gabriel.academico.service.ServiceException;

/**
 * @author daohn on 03/09/2020
 * @project EstudoDeCaso
 */
public class LetraC {
    public static void main(String[] args) throws DatabaseException, ServiceException {

        // Cria as entidades e insere no banco
        ValueObjectFactory.create();

        var manager = EntityManagerUtil.getEntityManager();
        var query = manager.createQuery(
                "select curso from Curso curso order by curso.nome",
                Curso.class
        );
        var resultado = query.getResultList();

        System.out.println(".".repeat(50));
        for(Curso curso : resultado) {
            System.out.println("Id................: " + curso.getId());
            System.out.println("Nome..............: " + curso.getNome());
            System.out.println("Tipo..............: " + curso.getTipo());

            if(!curso.getProfessores().isEmpty())
                System.out.println("Professores.......: ");
            for(Professor professor : curso.getProfessores()) {
                System.out.println("\tId................: " + professor.getId());
                System.out.println("\tNome..............: " + professor.getNome());
                System.out.println("\tFormação..........: " + professor.getFormacao());
                System.out.println("\tTitulação.........: " + professor.getTitulacao());
                System.out.println("\tSexo..............: " + professor.getSexo());
                System.out.println("\tData de nascimento: " + professor.getDataNascimento());
            }
        }
        System.out.println(".".repeat(50));
    }
}
