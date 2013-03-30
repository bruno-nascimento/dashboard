package br.com.bsitecnologia.dashboard.model;

public enum DominioEnum {
	
	DEMANDA(1, "Demanda"),
	CLIENTE(2, "Cliente"),
	STATUS(3, "Stauts"),
	TRANSICAO_STATUS(4, "Transição de status"),
	USUARIO(5, "Usuário"),
	TIPO_SERVICO(6, "Tipo de serviço"),
	CRITICIDADE(7, "Criticidade"),
	AREA_SOLICITANTE(8, "Área solicitante"),
	PERFIL(9, "Perfil"),
	TELEFONE(10, "Telefone"),
	TRANSICAO_STATUS_PERFIL(11, "Associação de perfil e transição de status"),
	PERFIL_ACAO_DOMINIO(12, "Associação de perfil, ação e domínio"),
	DOMINIO(13, "Domínio"),
	ACAO(14, "Ação");
	
	private DominioEnum(int id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	
	private int id;
	private String descricao;
	
	
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
