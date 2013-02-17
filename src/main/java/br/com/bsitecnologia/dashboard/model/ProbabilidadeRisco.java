package br.com.bsitecnologia.dashboard.model;


public enum ProbabilidadeRisco {
	
	IRRELEVANTE("Irrelevante", 1), MUITO_BAIXO("Muito baixo", 10),
	BAIXO("Baixo", 25), MEDIO("Médio", 50),
	ALTO("Alto", 75), IMINENTE("Iminente",90);
	
	private final String nome;
	private final Integer probabilidade;

	private ProbabilidadeRisco(String nome, Integer probabilidade) {
		this.nome = nome;
		this.probabilidade = probabilidade;
	}

	public String getNome() {
		return nome;
	}

	public Integer getProbabilidade() {
		return probabilidade;
	}
	
	public static ProbabilidadeRisco getProbabilidadeRiscoByProbabilidade(Integer probabilidade){
		for (ProbabilidadeRisco probabilidadeRisco : ProbabilidadeRisco.values()) {
			if(probabilidade.equals(probabilidadeRisco.getProbabilidade())){
				return probabilidadeRisco;
			}
		}
		return null;
	}

}
