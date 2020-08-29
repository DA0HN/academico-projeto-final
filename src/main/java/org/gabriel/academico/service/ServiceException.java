package org.gabriel.academico.service;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException() {
    }
}
