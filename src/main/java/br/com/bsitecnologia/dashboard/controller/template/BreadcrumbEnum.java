package br.com.bsitecnologia.dashboard.controller.template;

public enum BreadcrumbEnum {
	
	HOME("Home", "/index?faces-redirect=true", "Ir para a página inicial"), 
	CARGO("Cargo", "/admin/cargo/cargo?faces-redirect=true", "Administração de cargos"),
	EQUIPE("Equipe", "/admin/equipe/equipe?faces-redirect=true", "Administração de equipes"), 
	STATUS("Status", "/admin/status/status?faces-redirect=true", "Administração de status"), 
	ATOR_EXTERNO("Ator externo", "/admin/atorexterno/atorexterno?faces-redirect=true", "Administração de atores externos"),
	IMPACTO("Impacto", "/admin/impacto/impacto?faces-redirect=true", "Administração de impactos"),
	TRANSICAO_STATUS("Transição de status", "/admin/transicaostatus/transicaostatus?faces-redirect=true", "Administração transições de status"),
	COLABORADOR("Colaborador", "/admin/colaborador/colaborador?faces-redirect=true", "Administração de colaboradores"), 
	PROJETO("Projeto", "/admin/projeto/projeto?faces-redirect=true", "Administração de projetos"), 
	IMPEDIMENTO("Impedimento", "/admin/impedimento/impedimento?faces-redirect=true", "Administração de impedimentos"), 
	USUARIO("Usuário", "/admin/usuario/usuario?faces-redirect=true", "Administração de usuários"), 
	RISCO("Risco", "/admin/risco/risco?faces-redirect=true", "Administração de riscos"); 
	
	BreadcrumbEnum(String name, String url, String tooltip){
		this.name = name;
		this.url = url;
		this.tooltip = tooltip;
	}
	
	private String name;
	private String url;
	private String tooltip;
	
	public String getName(){
		return this.name;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public String getTooltip(){
		return this.tooltip;
	}

}
