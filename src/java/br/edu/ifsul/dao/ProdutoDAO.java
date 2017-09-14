/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Renato
 */
public class ProdutoDAO {

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
     private String mensagem = "";
    private EntityManager em;
    
    public ProdutoDAO(){
        em = EntityManagerUtil.getEntityManager();
    }
    
     public List<Produto> getLista() {
        return getEm().createQuery("from Produto order by nome").getResultList();
    }
     
      public boolean salvar(Produto obj) {
        try {
            getEm().getTransaction().begin();
            if (obj.getId() == null) {
                getEm().persist(obj);
            } else {
                getEm().merge(obj);
            }
            getEm().getTransaction().commit();
            setMensagem("Objeto persistido com sucesso!");
            return true;
        } catch (Exception e) {
            if (getEm().getTransaction().isActive() == false) {
                getEm().getTransaction().begin();
            }
            getEm().getTransaction().rollback();
            setMensagem("Erro ao persistir objeto " + Util.getMensagemErrom(e));
            return false;
        }
    }
      
      public boolean remover(Produto obj) {
        try {
            getEm().getTransaction().begin();
            getEm().remove(obj);
            getEm().getTransaction().commit();
            setMensagem("Objeto removido com sucesso!");
            return true;
        } catch (Exception e) {
            if (getEm().getTransaction().isActive() == false) {
                getEm().getTransaction().begin();
            }
            getEm().getTransaction().rollback();
            setMensagem("Erro ao remover objeto " + Util.getMensagemErrom(e));
            return false;
        }
    }
      
      public Produto localizar(Integer id) {
        return getEm().find(Produto.class, id);
    }

}
