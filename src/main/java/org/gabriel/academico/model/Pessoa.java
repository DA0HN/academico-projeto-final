package org.gabriel.academico.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.gabriel.academico.model.enums.Sexo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity @Getter @NoArgsConstructor
@ToString @EqualsAndHashCode
public class Pessoa implements ValueObject {

    @CollectionTable
    @ElementCollection
    private final List<Telefone> telefones = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull @Setter
    private String nome;
    @NonNull @Setter
    private LocalDate dataNascimento;
    @NonNull @Enumerated(value = EnumType.STRING) @Setter
    private Sexo sexo;
    @NonNull @Embedded @Setter
    private Endereco endereco;

    public Pessoa(String nome, LocalDate dataNascimento, Sexo sexo, Endereco endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.endereco = endereco;
    }

    public void addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
    }

    public void addTelefones(List<Telefone> telefones) {
        telefones.forEach(this::addTelefone);
    }

    public void removeTelefone(Telefone telefones) {
        this.telefones.remove(telefones);
    }
}
