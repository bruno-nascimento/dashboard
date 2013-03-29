package br.com.bsitecnologia.dashboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.jboss.solder.exception.control.ExceptionToCatch;

import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.PerfilAcaoDominio;

public class PerfilAcaoDominioDao extends GenericJpaRepository<PerfilAcaoDominio, Integer>{

	private static final long serialVersionUID = -4512647800139636558L;

	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilAcaoDominio> findAll() {
		try{
			Query q = entityManager.createQuery("select pad from PerfilAcaoDominio pad " +
					"left join fetch pad.perfil perfil " +
					"left join fetch pad.acao acao " +
					"left join fetch pad.dominio dominio " +
					"left join fetch pad.cliente cliente");
			return (List<PerfilAcaoDominio>)q.getResultList();
		} catch (Exception e) {
			catchEvent.fire(new ExceptionToCatch(e));
		}
		return new ArrayList<PerfilAcaoDominio>();
	}
	
	public List<PerfilAcaoDominio> findByPerfilAndCliente(PerfilAcaoDominio perfilAcaoDominio){
		try{
			String clienteClause = perfilAcaoDominio.getCliente() == null ? " is null" : " = :cliente";
			Query q = entityManager.createQuery("select pad from PerfilAcaoDominio pad " +
					"left join fetch pad.perfil perfil " +
					"left join fetch pad.acao acao " +
					"left join fetch pad.dominio dominio " +
					"left join fetch pad.cliente cliente " +
					"where cliente "+ clienteClause +" and perfil = :perfil");
			if (perfilAcaoDominio.getCliente() != null){
				q.setParameter("cliente", perfilAcaoDominio.getCliente());
			}
			q.setParameter("perfil", perfilAcaoDominio.getPerfil());
			return (List<PerfilAcaoDominio>)q.getResultList();
		} catch (Exception e) {
			catchEvent.fire(new ExceptionToCatch(e));
		}
		return new ArrayList<PerfilAcaoDominio>();
	}
	
}
