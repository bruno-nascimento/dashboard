package br.com.bsitecnologia.dashboard.dao;

import javax.persistence.Query;

import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.CampoFormulario;
import br.com.bsitecnologia.dashboard.model.Formulario;

public class CampoFormularioDao extends GenericJpaRepository<CampoFormulario, Integer>{

	private static final long serialVersionUID = 3556356330524105444L;
	
	public void deleteByFormulario(Formulario formulario){
		Query q = entityManager.createQuery("delete from CampoFormulario campoFormulario where campoFormulario.formulario = :formulario");
		q.setParameter("formulario", formulario);
		q.executeUpdate();
	}

}
