/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Renato
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable {

    private PessoaFisicaDAO<PessoaFisica> dao;
    private PessoaFisica objeto;
    private CidadeDAO<Cidade> daoCidade;
    private ProdutoDAO<Produto> daoProduto;
    private Telefone telefone;
    private Boolean novoTelefone;
    private Produto produto;

    public ControlePessoaFisica() {
        dao = new PessoaFisicaDAO<>();
        daoCidade = new CidadeDAO<>();
        daoProduto = new ProdutoDAO<>();
    }

    public void adicionarDesejo() {
        if (produto != null) {
            if (!objeto.getDesejos().contains(produto)) {
                objeto.getDesejos().add(produto);
                Util.mensagemInformacao("Desejo adicionado com sucesso");
            } else {
                Util.mensagemErro("Produto já existe na lista de desejos");
            }
        }
    }

    public void removerDesejo(int index) {
        objeto.getDesejos().remove(index);
        Util.mensagemInformacao("Desejo removido com sucesso");
    }

    public void novoTelefone() {
        telefone = new Telefone();
        novoTelefone = true;
    }

    public void alterarTelefone(int index) {
        telefone = objeto.getTelefones().get(index);
        novoTelefone = false;
    }

    public void salvarTelefone() {
        if (novoTelefone) {
            objeto.adicionarTelefone(telefone);
        }
        Util.mensagemInformacao("Telefone persistido com sucesso");
    }

    public void removerTelefone(int index) {
        objeto.removerTelefone(index);
        Util.mensagemInformacao("Telefone removido com sucesso");
    }

    public String listar() {
        return "/privado/pessoafisica/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new PessoaFisica();
    }

    public void salvar() {
        boolean persistiu;
        if (getObjeto().getId() == null) {
            persistiu = dao.persist(getObjeto());
        } else {
            persistiu = dao.merge(getObjeto());
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(getObjeto())) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public PessoaFisicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    public PessoaFisica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }

    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Boolean getNovoTelefone() {
        return novoTelefone;
    }

    public void setNovoTelefone(Boolean novoTelefone) {
        this.novoTelefone = novoTelefone;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
