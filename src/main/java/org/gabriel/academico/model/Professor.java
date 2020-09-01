package org.gabriel.academico.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.gabriel.academico.model.enums.Sexo;
import org.gabriel.academico.model.enums.Titulacao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */

@Entity
@NoArgsConstructor @Getter
@ToString
public class Professor extends Pessoa implements ValueObject {

    @Setter @NonNull
    private String formacao;
    @Setter @NonNull @Enumerated(value = EnumType.STRING)
    private Titulacao titulacao;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "professor_curso", joinColumns = {@JoinColumn(name = "professor_id")},
               inverseJoinColumns = {@JoinColumn(name = "curso_id")})
    private final List<Curso> cursos = new ArrayList<>();

    @Builder
    public Professor(String formacao, Titulacao titulacao, String nome, LocalDate dataNascimento,
                     Sexo sexo,
                     Endereco endereco) {
        super(nome, dataNascimento, sexo, endereco);
        this.formacao = formacao;
        this.titulacao = titulacao;
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
        if(curso.getProfessores().contains(this)) return;
        curso.addProfessor(this);
    }

    public void addCursos(List<Curso> cursos) {
        cursos.forEach(this::addCurso);
    }

    public void removeCurso(Curso curso) {
        curso.getProfessores().remove(this);
        this.cursos.remove(curso);
    }
}
