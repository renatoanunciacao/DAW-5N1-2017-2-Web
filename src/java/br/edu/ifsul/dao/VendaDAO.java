/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Venda;
import java.io.Serializable;

/**
 *
 * @author Renato
 */
public class VendaDAO<T> extends DAOGenerico<T>  implements Serializable{
    public VendaDAO(){
        super();
        classePersistente = Venda.class;
        ordem = "valor_total";
        
    }
            
            
}
