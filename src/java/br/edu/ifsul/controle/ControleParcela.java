/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ParcelaDAO;
import br.edu.ifsul.modelo.Parcela;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Renato
 */
@ManagedBean(name = "controleParcela")
@SessionScoped
public class ControleParcela implements Serializable {

    private ParcelaDAO dao;
    private Parcela objeto;

    public ControleParcela() {
        dao = new ParcelaDAO();
    }

    public String listar() {
        return "/privado/parcela/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Parcela();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        if (dao.persist(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public String editar(Integer id) {
        objeto = (Parcela) dao.localizar(id);
        return "formulario?faces-redirect=true";
    }

    public void remover(Integer id) {
        objeto = (Parcela) dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public ParcelaDAO getDao() {
        return dao;
    }

    public void setDao(ParcelaDAO dao) {
        this.dao = dao;
    }

    public Parcela getObjeto() {
        return objeto;
    }

    public void setObjeto(Parcela objeto) {
        this.objeto = objeto;
    }
}
