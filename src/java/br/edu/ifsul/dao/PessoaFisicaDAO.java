/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;

/**
 *
 * @author Renato
 */
public class PessoaFisicaDAO<T> extends DAOGenerico<PessoaFisica> implements Serializable {

    public PessoaFisicaDAO() {
        super();
        classePersistente = PessoaFisica.class;
        ordem = "nome";
    }

}
