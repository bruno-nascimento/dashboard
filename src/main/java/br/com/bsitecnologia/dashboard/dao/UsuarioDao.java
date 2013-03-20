package br.com.bsitecnologia.dashboard.dao;

import javax.persistence.Query;

import org.jboss.solder.exception.control.ExceptionToCatch;

import br.com.bsitecnologia.dashboard.dao.base.GenericJpaRepository;
import br.com.bsitecnologia.dashboard.model.Usuario;

public class UsuarioDao extends GenericJpaRepository<Usuario, Integer>{

	private static final long serialVersionUID = 6274856339684897752L;
	
	public Usuario authenticateUser(Usuario usuario){
		try{
			Query q = entityManager.createQuery("select usuario from Usuario usuario where usuario.email = :email and usuario.senha = :senha");
			q.setParameter("email", usuario.getEmail());
			q.setParameter("senha", usuario.getSenha());//Sha512Crypt.Sha512_crypt(usuario.getSenha()));
			usuario = (Usuario) q.getSingleResult();
			usuario.setLogado(true);
			return usuario;
		} catch (Exception e) {
			catchEvent.fire(new ExceptionToCatch(e));
		}
		return new Usuario();
	}
	
}
