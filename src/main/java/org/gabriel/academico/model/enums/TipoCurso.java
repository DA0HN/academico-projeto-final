package org.gabriel.academico.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
public enum TipoCurso {
    ENSINO_MEDIO("Ensino Médio"),
    GRADUACAO("Graduação"),
    POS_GRADUACAO("Pós Graduação");

    private final String tipo;

    TipoCurso(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override public String toString() {
        return tipo;
    }
}
