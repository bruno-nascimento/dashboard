package br.com.bsitecnologia.dashboard.infra.exceptions.customexceptions;

import br.com.bsitecnologia.dashboard.model.AcaoEnum;
import br.com.bsitecnologia.dashboard.model.DominioEnum;
import br.com.bsitecnologia.dashboard.model.Usuario;

public class UsuarioLogadoNotAuthorizedException extends RuntimeException{

	private static final long serialVersionUID = 3487496753156948091L;
	
	private DominioEnum dominio;
	private AcaoEnum acao;
	
	public UsuarioLogadoNotAuthorizedException(DominioEnum dominio, AcaoEnum acao) {
		this.dominio = dominio;
		this.acao = acao;
	}

	public DominioEnum getDominio() {
		return dominio;
	}

	public AcaoEnum getAcao() {
		return acao;
	}

	@Override
	public String getLocalizedMessage() {
		return String.format("Você não possui autorização para executar a ação: \"%s\" no domínio: \"%s\"", acao.getDescricao(), dominio.getDescricao());
	}
	
	
	
}
