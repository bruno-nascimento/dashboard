package br.com.bsitecnologia.dashboard.infra.filters;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.bsitecnologia.dashboard.resources.qualifiers.ControleAcesso;

@ControleAcesso
@Interceptor
public class FuncionalidadeInterceptor {

	@AroundInvoke
	public Object manage(InvocationContext ic) throws Exception {
		return ic.proceed();
	}

}
