package br.com.bsitecnologia.dashboard.dao;

import javax.persistence.Query;

import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.Telefone;
import br.com.bsitecnologia.dashboard.model.Usuario;

public class TelefoneDao extends GenericJpaRepository<Telefone, Integer>{

	private static final long serialVersionUID = -7127667892542238454L;

	public void deleteByUsuario(Usuario usuario) {
		Query q = entityManager.createQuery("delete from Telefone telefone where telefone.usuario = :usuario");
		q.setParameter("usuario", usuario);
		q.executeUpdate();
		
	}

}
