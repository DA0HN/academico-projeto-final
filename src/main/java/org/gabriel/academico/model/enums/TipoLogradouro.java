package org.gabriel.academico.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getTipo() {
        return tipo;
    }

    @Override public String toString() {
        return tipo;
    }
}
