package org.gabriel.academico.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
@Entity @NoArgsConstructor @Getter
@RequiredArgsConstructor @ToString @EqualsAndHashCode
public class Municipio implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull @Setter
    private String nome;
    @ManyToOne
    private Estado estado;
}
