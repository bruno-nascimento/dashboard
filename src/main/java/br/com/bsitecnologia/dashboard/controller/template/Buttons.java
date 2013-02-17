package br.com.bsitecnologia.dashboard.controller.template;

public enum Buttons {
	
	SAVE("Salvar"), EDIT("Editar"), DELETE("Deletar"), CANCEL("Cancelar");
	
	private Buttons(String label) {
		this.label = label;
	}
	
	private String label;
	
	public String getLabel(){
		return this.label;
	}

}
