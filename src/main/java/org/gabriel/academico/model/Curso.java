package org.gabriel.academico.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.gabriel.academico.model.enums.TipoCurso;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
@Entity @NoArgsConstructor @Getter
@ToString @EqualsAndHashCode
public class Curso implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull @Setter
    private String nome;
    @NonNull @Setter @Enumerated(value = EnumType.STRING)
    private TipoCurso tipo;
    @ManyToMany(mappedBy = "cursos", cascade = CascadeType.PERSIST)
    private final List<Professor> professores = new ArrayList<>();
    @ManyToMany(mappedBy = "cursos", cascade = CascadeType.PERSIST)
    private final List<Aluno> alunos = new ArrayList<>();

    @Builder
    public Curso(String nome, TipoCurso tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public void addProfessor(Professor professor) {
        this.professores.add(professor);
        if(professor.getCursos().contains(this)) return;
        professor.addCurso(this);
    }

    public void addProfessores(List<Professor> professor) {
        this.professores.forEach(this::addProfessor);
    }

    public void removeProfessor(Professor professor) {
        professor.getCursos().remove(this);
        this.professores.remove(professor);
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        if(aluno.getCursos().contains(this)) return;
        aluno.addCurso(this);
    }

    public void removeAluno(Aluno aluno) {
        aluno.getCursos().remove(this);
        this.alunos.remove(aluno);
    }

    public void addAlunos(List<Aluno> alunos) {
        alunos.forEach(this::addAluno);
    }
}
