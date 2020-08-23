package org.gabriel.academico.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */

@Entity
@NoArgsConstructor @Getter
@RequiredArgsConstructor @ToString
public class Aluno extends Pessoa implements ValueObject {
    @NonNull @Setter
    private Integer matricula;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "aluno_curso", joinColumns = {@JoinColumn(name = "aluno_id")},
               inverseJoinColumns = {@JoinColumn(name = "curso_id")})
    private final List<Curso> cursos = new ArrayList<>();
}
