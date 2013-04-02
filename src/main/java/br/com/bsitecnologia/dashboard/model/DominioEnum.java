package br.com.bsitecnologia.dashboard.model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public enum DominioEnum {
	
	DEMANDA(1, "Demanda", "/dashboard/demandas.jsf?faces-redirect=true"),
	CLIENTE(2, "Cliente", "/admin/cliente/cliente.jsf?faces-redirect=true"),
	STATUS(3, "Stauts", "/admin/status/status.jsf?faces-redirect=true"),
	TRANSICAO_STATUS(4, "Transição de status", "/admin/transicaostatus/transicaostatus.jsf?faces-redirect=true"),
	USUARIO(5, "Usuário", "/admin/usuario/usuario.jsf?faces-redirect=true"),
	TIPO_SERVICO(6, "Tipo de serviço", "/admin/tiposervico/tiposervico.jsf?faces-redirect=true"),
	CRITICIDADE(7, "Criticidade", "/admin/criticidade/criticidade.jsf?faces-redirect=true"),
	AREA_SOLICITANTE(8, "Área solicitante", "/admin/areasolicitante/areasolicitante.jsf?faces-redirect=true"),
	PERFIL(9, "Perfil", "/admin/perfil/perfil.jsf?faces-redirect=true"),
	TELEFONE(10, "Telefone", ""),
	TRANSICAO_STATUS_PERFIL(11, "Associação de perfil e transição de status", "/admin/transicaostatusperfil/transicaostatusperfil.jsf?faces-redirect=true"),
	PERFIL_ACAO_DOMINIO(12, "Associação de perfil, ação e domínio", "/admin/perfilacaodominio/perfilacaodominio.jsf?faces-redirect=true"),
	CAMPO(13, "Campo", "/admin/campo/campo.jsf?faces-redirect=true");

	private DominioEnum(int id, String descricao, String url){
		this.id = id;
		this.descricao = descricao;
		this.url = url;
	}
	
	private String url;
	private int id;
	private String descricao;
	
	public static DominioEnum getDominioByUrl(String url){
		for(DominioEnum dominio : values()){
			if(dominio.getUrl().equals(url)){
				return dominio;				
			}
		}
		return null;
	}
	
	public String getUrl() {
		return url;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (1, 'Demanda');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (2, 'Cliente');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (3, 'Status');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (4, 'Transição de Status');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (5, 'Usuário');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (6, 'Tipo de Serviço');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (7, 'Criticidade');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (8, 'Área Solicitante');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (9, 'Perfil');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (10, 'Telefone');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (11, 'Associação de Perfil e Transição de Status');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (12, 'Associação de Perfil, Ação e Dominio');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (13, 'Dominio');
//INSERT INTO `dashboard`.`Dominio` (`id`, `nome`) VALUES (14, 'Ação');
