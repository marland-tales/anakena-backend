package com.catsjump.anakena.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String s) {
//metodo para desencoda um paramentro recebido - o parametro pode ser uma string contendo um nome com espaco, por exemplo
		try {
			return URLDecoder.decode(s, "UTF-8");
//funcao do java para desencoda uma string
		} 
		catch (UnsupportedEncodingException e) {
			return "";
//catch tratando excecao na funcao java URLDecoder e returnando uma string vazia ""
		}
	}	

	public static List<Integer> decodeIntList(String s) {
		String[] vet = s.split(",");
//split eh uma funcao que pega a string e recorta conforme o caracter informado
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<vet.length; i++) {
//for para percorrer o vetor iniciando em 0, enquanto i for menor que vet.lenght e incrementando a cada item
			list.add(Integer.parseInt(vet[i]));
//convertendo o elemento da posicao i para inteiro e o resultado da conversao atribuir a lista de numeros inteiros
		}
		return list;
//LAMBDA
//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}