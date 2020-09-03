package org.gabriel.academico.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
public enum Titulacao {
    ESPECIALISTA("Especialista"),
    MESTRE("Mestre"),
    DOUTOR("Doutor");

    private final String tipo;

    Titulacao(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override public String toString() {
        return tipo;
    }
}
