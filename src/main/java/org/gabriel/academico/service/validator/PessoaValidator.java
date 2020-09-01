package org.gabriel.academico.service.validator;

import org.gabriel.academico.model.Pessoa;

import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class PessoaValidator {

    public StringJoiner validarPessoa(Pessoa pessoa) {
        var erro = new StringJoiner("\n");
        if(pessoa.getDataNascimento() == null) {
            erro.add("Para inserir uma pessoa é necessário uma data de nascimento.");
        }
        if(pessoa.getDataNascimento().isAfter(LocalDate.now()) || pessoa.getDataNascimento().isEqual(
                LocalDate.now())) {
            erro.add("Para inserir uma pessoa a data de nascimento tem que ser anterior ao dia " +
                             "atual");
        }
        if(pessoa.getEndereco() == null) {
            erro.add("Para inserir uma pessoa é necessário um endereço.");
        }
        if(pessoa.getNome() == null) {
            erro.add("Para inserir uma pessoa é necessário um nome");
        }
        if(pessoa.getSexo() == null) {
            erro.add("Para inserir uma pessoa é necessário um sexo");
        }
        if(pessoa.getTelefones() == null || pessoa.getTelefones().isEmpty()) {
            erro.add("Para inserir uma pessoa é necessário pelo menos 1 telefone");
        }
        return erro;
    }
}
