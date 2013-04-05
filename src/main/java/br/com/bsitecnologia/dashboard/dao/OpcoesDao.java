package br.com.bsitecnologia.dashboard.dao;

import javax.persistence.Query;

import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.Campo;
import br.com.bsitecnologia.dashboard.model.Opcoes;

public class OpcoesDao extends GenericJpaRepository<Opcoes, Integer>{

	private static final long serialVersionUID = -4258994322575200303L;
	
	public void deleteByCampo(Campo campo){
		Query q = entityManager.createQuery("delete from Opcoes opcoes where opcoes.campo = :campo");
		q.setParameter("campo", campo);
		q.executeUpdate();
	}

}
