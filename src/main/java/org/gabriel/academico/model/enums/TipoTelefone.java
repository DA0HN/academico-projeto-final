package org.gabriel.academico.model.enums;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
public enum TipoTelefone {
    PESSOAL("Pessoal"),
    PROFISSIONAL("Profissional");

    private final String tipo;

    TipoTelefone(String tipo) {
        this.tipo = tipo;
    }
}
