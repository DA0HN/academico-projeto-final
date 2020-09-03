package org.gabriel.academico.model.enums;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String tipo;

    Sexo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override public String toString() {
        return tipo;
    }
}
