/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.GrupoDAO;
import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Grupo;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.util.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Renato
 */
@ManagedBean(name = "controleGrupo")
@SessionScoped
public class ControleGrupo {
    
    private GrupoDAO dao;
    private Grupo objeto;
    
    public ControleGrupo(){
        dao = new GrupoDAO();
    }
    
     public String listar(){
        return "/privado/grupo/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Grupo();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public GrupoDAO getDao() {
        return dao;
    }

    public void setDao(GrupoDAO dao) {
        this.dao = dao;
    }

    public Grupo getObjeto() {
        return objeto;
    }

    public void setObjeto(Grupo objeto) {
        this.objeto = objeto;
    }
}
