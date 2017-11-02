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
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.dao.VendaDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Parcela;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendaItens;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Renato
 */
@ManagedBean(name = "controleVenda")
@ViewScoped
public class ControleVenda implements Serializable {

    private VendaDAO<Venda> dao;
    private Venda objeto;
    private ProdutoDAO<Produto> daoProduto;
    private PessoaFisicaDAO<PessoaFisica> daoPessoaFisica;
    private UsuarioDAO<Usuario> daoUsuario;
    private VendaItens item;
    private Boolean novoItem;

    public ControleVenda() {
        dao = new VendaDAO<>();
        daoProduto = new ProdutoDAO<>();
        daoPessoaFisica = new PessoaFisicaDAO<>();
        daoUsuario = new UsuarioDAO<>();
    }

    public void atualizaValorUnitarioItem() {
        if (item != null) {
            if (item.getProduto() != null) {
                item.setValorUnitario(item.getProduto().getPreco());
            }
        }
    }

    public void calcularValorTotalItem() {
        if (item.getValorUnitario() != null && item.getQuantidade() != null) {
            item.setValorTotal(item.getValorUnitario() * item.getQuantidade());
        }
    }

    public String listar() {
        return "/privado/venda/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Venda();
        objeto.setData(Calendar.getInstance());
    }

    public void gerarParcelas(){
        Boolean temPagamento = false;
        for (Parcela p : objeto.getListaParcelas()) {
            if (p.getDataPagamento() != null  || p.getValorPago() != null) {
                temPagamento = true;
                break;
            }
        }
        if (temPagamento) {
            Util.mensagemErro("Parcelas não podem ser geradas novamente "
                    + "por já existirem parcelas pagas!");
        } else {
            objeto.getListaParcelas().clear();
            objeto.geraParcelas();
            Util.mensagemInformacao("Parcelas geradas com sucesso!");
        }
               
    }
    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void novoItem() {
        item = new VendaItens();
        novoItem = true;
    }

    public void alterarItem(int index) {
        System.out.println("IDX alterar: " + index);
        item = objeto.getItens().get(index);
        novoItem = false;
    }

    public void salvarItem() {
        if (novoItem) {
            objeto.adicionarItem(item);
        } else {
            Double vlrAtual = 0.0;
            for (VendaItens v : objeto.getItens()) {
                vlrAtual += v.getValorTotal();
            }
            objeto.setValor_total(vlrAtual);
        }
        Util.mensagemInformacao("Operação realizada com sucesso");
    }

    public void removerItem(int index) {
        System.out.println("IDX remover: " + index);
        objeto.removerItem(index);
        Util.mensagemInformacao("Item removido com sucesso");
    }

    public VendaDAO getDao() {
        return dao;
    }

    public void setDao(VendaDAO dao) {
        this.dao = dao;
    }

    public Venda getObjeto() {
        return objeto;
    }

    public void setObjeto(Venda objeto) {
        this.objeto = objeto;
    }

    public VendaItens getItem() {
        return item;
    }

    public void setItem(VendaItens item) {
        this.item = item;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }

    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    public PessoaFisicaDAO<PessoaFisica> getDaoPessoaFisica() {
        return daoPessoaFisica;
    }

    public void setDaoPessoaFisica(PessoaFisicaDAO<PessoaFisica> daoPessoaFisica) {
        this.daoPessoaFisica = daoPessoaFisica;
    }

    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }
}
