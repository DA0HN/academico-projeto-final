package org.gabriel.academico.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.gabriel.academico.model.enums.Sexo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity @NoArgsConstructor @Getter
@RequiredArgsConstructor @ToString @EqualsAndHashCode
public class Pessoa implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull @Setter
    private String nome;
    @NonNull @Setter
    private LocalDate dataNascimento;
    @NonNull @Enumerated @Setter
    private Sexo sexo;
    @NonNull @Embedded @Setter
    private Endereco endereco;
    @NonNull @Setter
    @CollectionTable
    @ElementCollection
    private List<Telefone> telefones;

}
