package br.com.bsitecnologia.dashboard.infra.filters;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.solder.exception.control.ExceptionToCatch;

import br.com.bsitecnologia.dashboard.controller.base.BaseBean;
import br.com.bsitecnologia.dashboard.infra.exceptions.customexceptions.UsuarioLogadoNotAuthorizedException;
import br.com.bsitecnologia.dashboard.model.AcaoEnum;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.model.PerfilAcaoDominio;
import br.com.bsitecnologia.dashboard.model.Usuario;
import br.com.bsitecnologia.dashboard.resources.qualifiers.ControleAcesso;
import br.com.bsitecnologia.dashboard.resources.qualifiers.UsuarioLogado;

@ControleAcesso
@Interceptor
@SuppressWarnings("rawtypes")
public class ControleAcessoInterceptor implements Serializable{

	private static final long serialVersionUID = 5657743725393720210L;
	
	@UsuarioLogado
	@Inject
	Usuario usuarioLogado;
	
	@Inject
	protected Event<ExceptionToCatch> catchEvent;
	
	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		
		BaseBean bean = (BaseBean)ic.getTarget();
		DominioEnum dominio = bean.getDominio();
		AcaoEnum acao = ic.getMethod().getAnnotation(ControleAcesso.class).acao();
		if(!checkUsuarioLogadoAuthForInterceptedAcaoDominio(dominio, acao)){
			catchEvent.fire(new ExceptionToCatch(new UsuarioLogadoNotAuthorizedException(dominio, acao)));
			return bean.getClass().getMethod("cancel").invoke(bean);
		}
		return ic.proceed();
	}
	
	private boolean checkUsuarioLogadoAuthForInterceptedAcaoDominio(DominioEnum dominio, AcaoEnum acao){
		
		for(PerfilAcaoDominio pad : usuarioLogado.getPerfil().getPerfilAcaoDominios()){
			if(pad.getDominio().getId().equals(dominio.getId()) && pad.getAcao().getId().equals(acao.getId())){
				return true;
			}
		}
		return false;
	}

}
