package org.gabriel.academico.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.gabriel.academico.model.enums.TipoLogradouro;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
@Embeddable @NoArgsConstructor @Getter
@ToString @EqualsAndHashCode
public class Endereco {
    @NonNull @Setter
    private String logradouro;
    @NonNull @Setter @Enumerated(value = EnumType.STRING)
    private TipoLogradouro tipoLogradouro;
    @NonNull @Setter
    private Integer numero;
    @NonNull @Setter
    private String bairro;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Municipio municipio;

    @Builder
    public Endereco(String logradouro, TipoLogradouro tipoLogradouro, Integer numero,
                    String bairro, Municipio municipio) {
        this.logradouro = logradouro;
        this.tipoLogradouro = tipoLogradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.municipio = municipio;
    }
}
