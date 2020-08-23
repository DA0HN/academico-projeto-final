package org.gabriel.academico.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.gabriel.academico.model.enums.TipoLogradouro;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
@Embeddable @NoArgsConstructor @Getter
@RequiredArgsConstructor @ToString @EqualsAndHashCode
public class Endereco {
    @NonNull @Setter
    private String logradouro;
    @NonNull @Setter @Enumerated(value = EnumType.STRING)
    private TipoLogradouro tipoLogradouro;
    @NonNull @Setter
    private Integer numero;
    @NonNull @Setter
    private String bairro;
    @ManyToOne
    private Municipio municipio;
}
