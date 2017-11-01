/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.modelo.AcessoUsuario;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Renato
 */
@ManagedBean(name = "controleUsuario")
@SessionScoped
public class ControleUsuario implements Serializable {

    private UsuarioDAO<Usuario> dao;
    private Usuario objeto;
    private AcessoUsuario acesso;
    private Boolean novoAcesso;

    public void novoAcesso() {
        acesso = new AcessoUsuario();
        novoAcesso = true;
    }

    public void alterarAcesso(int index) {
        acesso = objeto.getAcessos().get(index);
        novoAcesso = false;
    }

    public void salvarAcesso() {
        if (novoAcesso) {
            objeto.adicionarAcesso(acesso);
        }
        Util.mensagemInformacao("Acesso persistido com sucesso");
    }

    public void removerAcesso(int index){
        objeto.removerAcesso(index);
        Util.mensagemInformacao("Acesso removido com sucesso");
    }
    public ControleUsuario() {
        dao = new UsuarioDAO<>();
    }

    public String listar() {
        return "/privado/usuario/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Usuario();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
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
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public UsuarioDAO getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Usuario objeto) {
        this.objeto = objeto;
    }

    public AcessoUsuario getAcessoUsuario() {
        return acesso;
    }

    public void setAcessoUsuario(AcessoUsuario acessoUsuario) {
        this.acesso = acessoUsuario;
    }

    public Boolean getNovoAcesso() {
        return novoAcesso;
    }

    public void setNovoAcesso(Boolean novoAcesso) {
        this.novoAcesso = novoAcesso;
    }

}
