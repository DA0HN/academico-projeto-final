package org.gabriel.academico.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.gabriel.academico.model.enums.TipoCurso;

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
@RequiredArgsConstructor @ToString @EqualsAndHashCode
public class Curso implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull @Setter
    private String nome;
    @NonNull @Setter @Enumerated(value = EnumType.STRING)
    private TipoCurso tipo;
    @ManyToMany(mappedBy = "cursos")
    private final List<Professor> professores = new ArrayList<>();
    @ManyToMany(mappedBy = "cursos")
    private final List<Aluno> alunos = new ArrayList<>();
}
