package org.gabriel.academico.model.enums;

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
}
