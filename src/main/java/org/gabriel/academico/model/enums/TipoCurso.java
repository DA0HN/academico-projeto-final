package org.gabriel.academico.model.enums;

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
}
