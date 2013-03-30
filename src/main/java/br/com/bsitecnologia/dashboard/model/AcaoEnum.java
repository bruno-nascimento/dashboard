package br.com.bsitecnologia.dashboard.model;

public enum AcaoEnum {
	
	INSERIR(1, "Inserir", null), EDITAR(2, "Editar", null),
	EXCLUIR(3, "Excluir", null), LISTAR(4, "Listar", null),
	ALTERAR_SENHA(5, "Alterar senha", DominioEnum.USUARIO);

	private AcaoEnum(int id, String descricao, DominioEnum dominioEnum){
		this.id = id;
		this.descricao = descricao;
		this.dominioEnum = dominioEnum;
	}

	
	private final DominioEnum dominioEnum;
	private final String descricao;
	private final int id;
	
	
	public DominioEnum getDominioEnum() {
		return dominioEnum;
	}
	public String getDescricao() {
		return descricao;
	}
	public int getId() {
		return id;
	}

}
//INSERT INTO `dashboard`.`Acao` (`id`, `nome`, `dominio`) VALUES (1, "Inserir", null);
//INSERT INTO `dashboard`.`Acao` (`id`, `nome`, `dominio`) VALUES (2, "Editar", null);
//INSERT INTO `dashboard`.`Acao` (`id`, `nome`, `dominio`) VALUES (3, "Excluir", null);
//INSERT INTO `dashboard`.`Acao` (`id`, `nome`, `dominio`) VALUES (4, "Listar", null);
//INSERT INTO `dashboard`.`Acao` (`id`, `nome`, `dominio`) VALUES (5, "Alterar senha", 5);
