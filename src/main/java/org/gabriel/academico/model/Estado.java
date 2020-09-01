package org.gabriel.academico.model;

import lombok.Builder;
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

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
@Entity @NoArgsConstructor @Getter
@ToString @EqualsAndHashCode
public class Estado implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull @Setter
    private String nome;
    @NonNull @Setter
    private String sigla;

    @Builder
    public Estado(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }
}
