package br.com.bsitecnologia.dashboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.jboss.solder.exception.control.ExceptionToCatch;

import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.Campo;

public class CampoDao extends GenericJpaRepository<Campo, Integer>{

	private static final long serialVersionUID = -7316826272182446506L;
	
	@Override
	public List<Campo> findAll() {
		try{
			Query q = entityManager.createQuery("select distinct campo from Campo Campo " +
					"left join fetch campo.opcoes opcao ");
			return (List<Campo>)q.getResultList();
		} catch (Exception e) {
			catchEvent.fire(new ExceptionToCatch(e));
		}
		return new ArrayList<Campo>();
	}

}
