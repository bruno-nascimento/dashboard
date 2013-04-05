package br.com.bsitecnologia.dashboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.jboss.solder.exception.control.ExceptionToCatch;

import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.Formulario;

public class FormularioDao extends GenericJpaRepository<Formulario, Integer>{

	private static final long serialVersionUID = -2436421436279014977L;
	
	@Override
	public List<Formulario> findAll() {
		try{
			Query q = entityManager.createQuery("select form from Formulario form " +
					"left join fetch form.campoFormularios campoFormulario " +
					"left join fetch campoFormulario.campo campo");
			return (List<Formulario>)q.getResultList();
		} catch (Exception e) {
			catchEvent.fire(new ExceptionToCatch(e));
		}
		return new ArrayList<Formulario>();
	}
	
	public Formulario getFormularioById(Integer id){
		try{
			Query q = entityManager.createQuery("select form from Formulario form " +
					"left join fetch form.campoFormularios campoFormulario " +
					"left join fetch campoFormulario.campo campo "+
					"where form.id = :formId");
			q.setParameter("formId", id);
			return (Formulario)q.getSingleResult();
		} catch (Exception e) {
			catchEvent.fire(new ExceptionToCatch(e));
		}
		return new Formulario();
	}

}
