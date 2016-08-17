package br.com.guia_telefonico.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.guia_telefonico.model.dadosPessoais;
import br.com.guia_telefonico.util.JPAUtil;

@ManagedBean
@RequestScoped
public class dadosMBean {
	private dadosPessoais dados = new dadosPessoais();
	private List<dadosPessoais> listaDados = null;
	
	public dadosPessoais getDados() {
		return dados;
	}
	public void setDados(dadosPessoais dados) {
		this.dados = dados;
	}
	public List<dadosPessoais> getListaDados() {
		if(this.listaDados == null){
			EntityManager manager = JPAUtil.getEntityManager();
			Query query = manager.createQuery(" select d from dadosPessoais d", dadosPessoais.class);
			this.listaDados = query.getResultList();
		}
		return listaDados;
	}
	public void setListaDados(List<dadosPessoais> listaDados) {
		this.listaDados = listaDados;
	}
	public void remover(dadosPessoais dados){
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(dados));
		manager.getTransaction().commit();
		manager.close();
	}
	public void salvar(dadosPessoais dados){
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(dados);
		manager.getTransaction().commit();
		manager.close();
		
		FacesMessage facesMessage = new FacesMessage("Cadastro efetuado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void alterar(dadosPessoais dados){
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(dados);
		manager.getTransaction().commit();
		manager.close();
	}
	public List pesquisarPor(){
		EntityManager manager = JPAUtil.getEntityManager();
		Query query = manager.createQuery("select d from dadosPessoais d where d.nome like = :nome");
		query.setParameter("nome", dadosPessoais.class);
		listaDados = (List<dadosPessoais>)query.getResultList();
		if(listaDados.isEmpty()){
			return null;
		}
		return listaDados;
	}
	public List getBuscaDados(){
		return pesquisarPor();
	}
}
