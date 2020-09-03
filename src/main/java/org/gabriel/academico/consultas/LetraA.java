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
public class LetraA {
    public static void main(String[] args) throws DatabaseException, ServiceException {

        // Cria as entidades e insere no banco
        ValueObjectFactory.create();

        var manager = EntityManagerUtil.getEntityManager();
        var query = manager.createQuery(
                "select p from Professor p order by p.nome",
                Professor.class
        );
        var resultado = query.getResultList();

        for(Professor p : resultado) {
            System.out.println("Id................: " + p.getId());
            System.out.println("Nome..............: " + p.getNome());
            System.out.println("Sexo..............: " + p.getSexo());
            System.out.println("Telefones.........: " + p.getTelefones());
            System.out.println("Data de nascimento: " + p.getDataNascimento());
            System.out.println("Endereço...............: ");
            System.out.println("\t\tBairro.........: " + p.getEndereco().getBairro());
            System.out.println("\t\tNúmero.........: " + p.getEndereco().getNumero());
            System.out.println("\t\tLogradouro.....: " + p.getEndereco().getLogradouro());
            System.out.println("\t\tTipo logradouro: " + p.getEndereco().getTipoLogradouro());
            System.out.println("\t\tEstado.........: " + p.getEndereco().getMunicipio().getEstado().getNome());
            System.out.println("\t\tMunicípio......: " + p.getEndereco().getMunicipio().getNome());

            System.out.println("Titulação.........: " + p.getTitulacao());
            System.out.println("Formação..........: " + p.getFormacao());

            System.out.println("Curso.........:");
            for(Curso c : p.getCursos()) {
                System.out.println("\t\tId....: " + c.getId());
                System.out.println("\t\tNome..: " + c.getNome());
                System.out.println("\t\tAlunos: " + c.getAlunos());
                System.out.println("\t\tTipo..: " + c.getTipo());
            }
        }
    }
}
