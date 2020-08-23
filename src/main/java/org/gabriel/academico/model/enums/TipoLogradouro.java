package org.gabriel.academico.model.enums;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
public enum TipoLogradouro {
    AVENIDA("Avenida"),
    RUA("Rua"),
    ALAMEDA("Alameda"),
    TRAVESSA("Travessa");

    private final String tipo;

    TipoLogradouro(String tipo) {
        this.tipo = tipo;
    }
}
