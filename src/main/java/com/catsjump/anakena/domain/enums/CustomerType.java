package com.catsjump.anakena.domain.enums;

public enum CustomerType {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private CustomerType(int cod, String descricao) {
	 this.cod = cod;
	 this.descricao = descricao;
	 }
	public int getCod() {
	 return cod;
	 }
	
	public String getDescricao() {
	 return descricao;
	 }
	
	public static CustomerType toEnum(Integer id) {
//static eh uma operacao possivel de ser instanciada sem executar objetos
	
	 if (id == null) {
		 return null;
	 }
	
	 for (CustomerType x : CustomerType.values()) {
		 if (id.equals(x.getCod())) {
			 return x;
	 }
	 }
	 throw new IllegalArgumentException("Id inválido " + id);
	 }
}