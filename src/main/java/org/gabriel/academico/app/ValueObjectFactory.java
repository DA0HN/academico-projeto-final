package org.gabriel.academico.app;

import org.gabriel.academico.database.AbstractDAO;
import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.database.EntityManagerUtil;
import org.gabriel.academico.model.Aluno;
import org.gabriel.academico.model.Curso;
import org.gabriel.academico.model.Endereco;
import org.gabriel.academico.model.Estado;
import org.gabriel.academico.model.Municipio;
import org.gabriel.academico.model.Professor;
import org.gabriel.academico.model.Telefone;
import org.gabriel.academico.model.ValueObject;
import org.gabriel.academico.model.enums.Sexo;
import org.gabriel.academico.model.enums.TipoCurso;
import org.gabriel.academico.model.enums.TipoLogradouro;
import org.gabriel.academico.model.enums.TipoTelefone;
import org.gabriel.academico.model.enums.Titulacao;
import org.gabriel.academico.service.AlunoService;
import org.gabriel.academico.service.CursoService;
import org.gabriel.academico.service.EstadoService;
import org.gabriel.academico.service.MunicipioService;
import org.gabriel.academico.service.ProfessorService;
import org.gabriel.academico.service.ServiceException;

import java.time.LocalDate;

import static java.util.Arrays.asList;

/**
 * @author daohn on 01/09/2020
 * @project EstudoDeCaso
 */
public class ValueObjectFactory {

    private static final ProfessorService professorService = new ProfessorService();
    private static final AlunoService     alunoService     = new AlunoService();
    private static final CursoService     cursoService     = new CursoService();
    private static final MunicipioService municipioService = new MunicipioService();
    private static final EstadoService    estadoService    = new EstadoService();

    public static void main(String[] args) throws ServiceException, DatabaseException {
        create();
    }

    public static void create() throws ServiceException, DatabaseException {
        var abstractDao = new AbstractDAO<>(EntityManagerUtil.getEntityManager(),
                                            ValueObject.class
        );
        Curso[] cursos = createCursos();
        Estado[] estados = createEstados();
        Municipio[] municipios = createMunicipios(estados);
        Endereco[] enderecos = createEnderecos(municipios);
        Telefone[] telefones = createTelefones();


        var professor1 = Professor.builder()
                .dataNascimento(LocalDate.now().minusYears(78))
                .endereco(enderecos[0])
                .formacao("formacao1")
                .nome("nome1")
                .sexo(Sexo.FEMININO)
                .titulacao(Titulacao.MESTRE)
                .build();


        var aluno1 = Aluno.builder()
                .matricula(1234)
                .dataNascimento(LocalDate.now().minusYears(30))
                .endereco(enderecos[1])
                .nome("aluno1")
                .sexo(Sexo.MASCULINO)
                .build();


        professor1.addTelefone(telefones[0]);
        professor1.addCursos(asList(cursos[0], cursos[1], cursos[2]));
        aluno1.addTelefone(telefones[1]);
        aluno1.addCursos(asList(cursos[0], cursos[1]));

        abstractDao.begin()
                .save(aluno1)
                .save(professor1)
                .save(asList(estados))
                .save(asList(municipios))
                .save(asList(cursos))
                .commit();

    }

    private static Estado[] createEstados() {
        return new Estado[]{
                Estado.builder()
                        .nome("nome1")
                        .sigla("sigla1")
                        .build(),
                Estado.builder()
                        .nome("nome2")
                        .sigla("sigla2")
                        .build(),
                Estado.builder()
                        .nome("nome3")
                        .sigla("sigla3")
                        .build(),
        };
    }

    private static Municipio[] createMunicipios(Estado[] estados) {
        return new Municipio[]{
                Municipio.builder()
                        .estado(estados[0])
                        .nome("municipio1")
                        .build(),
                Municipio.builder()
                        .estado(estados[0])
                        .nome("municipio2")
                        .build(),
                Municipio.builder()
                        .estado(estados[0])
                        .nome("municipio3")
                        .build(),
                Municipio.builder()
                        .estado(estados[1])
                        .nome("municipio4")
                        .build(),
                Municipio.builder()
                        .estado(estados[1])
                        .nome("municipio5")
                        .build(),
                Municipio.builder()
                        .estado(estados[1])
                        .nome("municipio6")
                        .build(),
                Municipio.builder()
                        .estado(estados[2])
                        .nome("municipio7")
                        .build(),
                Municipio.builder()
                        .estado(estados[2])
                        .nome("municipio8")
                        .build(),
        };
    }

    private static Curso[] createCursos() throws DatabaseException {
        return new Curso[]{
                Curso.builder()
                        .nome("curso1")
                        .tipo(TipoCurso.POS_GRADUACAO)
                        .build(),
                Curso.builder()
                        .nome("curso2")
                        .tipo(TipoCurso.ENSINO_MEDIO)
                        .build(),
                Curso.builder()
                        .nome("curso3")
                        .tipo(TipoCurso.GRADUACAO)
                        .build(),
                Curso.builder()
                        .nome("curso4")
                        .tipo(TipoCurso.POS_GRADUACAO)
                        .build(),
                Curso.builder()
                        .nome("curso5")
                        .tipo(TipoCurso.GRADUACAO)
                        .build(),
                Curso.builder()
                        .nome("curso6")
                        .tipo(TipoCurso.ENSINO_MEDIO)
                        .build(),
                Curso.builder()
                        .nome("curso7")
                        .tipo(TipoCurso.ENSINO_MEDIO)
                        .build()
        };
    }

    private static Endereco[] createEnderecos(Municipio[] municipios) {
        return new Endereco[]{
                Endereco.builder()
                        .bairro("bairro1")
                        .logradouro("logradouro1")
                        .municipio(municipios[1])
                        .tipoLogradouro(TipoLogradouro.ALAMEDA)
                        .numero(1)
                        .build(),
                Endereco.builder()
                        .bairro("bairro2")
                        .logradouro("logradouro2")
                        .municipio(municipios[1])
                        .tipoLogradouro(TipoLogradouro.TRAVESSA)
                        .numero(2)
                        .build(),
                Endereco.builder()
                        .bairro("bairro3")
                        .logradouro("logradouro3")
                        .municipio(municipios[1])
                        .tipoLogradouro(TipoLogradouro.RUA)
                        .numero(3)
                        .build(),
                Endereco.builder()
                        .bairro("bairro4")
                        .logradouro("logradouro4")
                        .municipio(municipios[1])
                        .tipoLogradouro(TipoLogradouro.AVENIDA)
                        .numero(4)
                        .build(),
        };
    }

    private static Telefone[] createTelefones() {
        return new Telefone[]{
                Telefone.builder()
                        .numero("65 9 1111-0000")
                        .tipo(TipoTelefone.PESSOAL)
                        .build(),
                Telefone.builder()
                        .numero("65 9 2222-0000")
                        .tipo(TipoTelefone.PROFISSIONAL)
                        .build(),
                Telefone.builder()
                        .numero("65 9 3333-0000")
                        .tipo(TipoTelefone.PESSOAL)
                        .build(),
                Telefone.builder()
                        .numero("65 9 4444-0000")
                        .tipo(TipoTelefone.PROFISSIONAL)
                        .build(),
        };
    }
}
