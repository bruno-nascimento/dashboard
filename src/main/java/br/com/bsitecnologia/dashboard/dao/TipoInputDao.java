package br.com.bsitecnologia.dashboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.jboss.solder.exception.control.ExceptionToCatch;

import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.TipoInput;

public class TipoInputDao extends GenericJpaRepository<TipoInput, Integer>{

	private static final long serialVersionUID = 8975655705815484332L;
	
	@Override
	public List<TipoInput> findAll() {
		try{
			Query q = entityManager.createQuery("select distinct tipoInput from TipoInput tipoInput " +
					"left join fetch tipoInput.tipoInputTipoDados tipoInputTipoDados " +
					"left join fetch tipoInputTipoDados.tipoDado tipoDado ");
			return (List<TipoInput>)q.getResultList();
		} catch (Exception e) {
			catchEvent.fire(new ExceptionToCatch(e));
		}
		return new ArrayList<TipoInput>();
	}

}
