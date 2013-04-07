package br.com.bsitecnologia.dashboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.jboss.solder.exception.control.ExceptionToCatch;

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
	
	public List<Opcoes> getOpcoesByCampo(Campo campo) {
		try{
			Query q = entityManager.createQuery("select distinct opcao from Opcoes opcao " +
					"where opcao.campo = :campo" );
			q.setParameter("campo", campo);
			return (List<Opcoes>)q.getResultList();
		} catch (Exception e) {
			catchEvent.fire(new ExceptionToCatch(e));
		}
		return new ArrayList<Opcoes>();
	}

}
