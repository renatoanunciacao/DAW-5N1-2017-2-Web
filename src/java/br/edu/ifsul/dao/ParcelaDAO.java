/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Parcela;
import java.io.Serializable;

/**
 *
 * @author Renato
 */
public class ParcelaDAO<T> extends DAOGenerico<Parcela> implements Serializable {

   public ParcelaDAO(){
       super();
       classePersistente = Parcela.class;
       ordem = "valor";//ordenação padrão
   }
}
