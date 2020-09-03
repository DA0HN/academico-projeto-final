package org.gabriel.academico.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.gabriel.academico.model.enums.Sexo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

@Entity @NoArgsConstructor
@Getter @ToString(exclude = {"cursos"})
public class Aluno extends Pessoa implements ValueObject {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aluno_curso", joinColumns = {@JoinColumn(name = "aluno_id")},
               inverseJoinColumns = {@JoinColumn(name = "curso_id")})
    private final List<Curso> cursos = new ArrayList<>();
    @NonNull @Setter
    private Integer matricula;

    @Builder
    public Aluno(Integer matricula, String nome, LocalDate dataNascimento, Sexo sexo,
                 Endereco endereco) {
        super(nome, dataNascimento, sexo, endereco);
        this.matricula = matricula;
    }

    public void addCurso(Curso curso) {
        cursos.add(curso);
        if(curso.getAlunos().contains(this)) return;
        curso.addAluno(this);
    }

    public void addCursos(List<Curso> cursos) {
        cursos.forEach(this::addCurso);
    }

    public void removeCurso(Curso curso) {
        curso.getAlunos().remove(this);
        cursos.remove(curso);
    }
}
