package br.com.rp.repository;

import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

public class GerarCPF {
	private ArrayList<Integer> listaAleatoria = new ArrayList<Integer>();
	private ArrayList<Integer> listaNumMultiplicados = null;

	public int geraNumAleatorio() {
		int numero = (int) (Math.random() * 10);

		return numero;
	}

	public ArrayList<Integer> geraCPFParcial() {
		for (int i = 0; i < 9; i++) {
			listaAleatoria.add(geraNumAleatorio());
		}

		return listaAleatoria;
	}

	public ArrayList<Integer> geraPrimeiroDigito() {
		listaNumMultiplicados = new ArrayList<Integer>();
		int primeiroDigito;
		int totalSomatoria = 0;
		int restoDivisao;
		int peso = 10;

		for (int item : listaAleatoria) {
			listaNumMultiplicados.add(item * peso);

			peso--;
		}

		for (int item : listaNumMultiplicados) {
			totalSomatoria += item;
		}

		restoDivisao = (totalSomatoria % 11);

		if (restoDivisao < 2) {
			primeiroDigito = 0;
		} else {
			primeiroDigito = 11 - restoDivisao;
		}

		listaAleatoria.add(primeiroDigito);

		return listaAleatoria;
	}

	public ArrayList<Integer> geraSegundoDigito() {
		listaNumMultiplicados = new ArrayList<Integer>();
		int segundoDigito;
		int totalSomatoria = 0;
		int restoDivisao;
		int peso = 11;

		for (int item : listaAleatoria) {
			listaNumMultiplicados.add(item * peso);

			peso--;
		}

		for (int item : listaNumMultiplicados) {
			totalSomatoria += item;
		}

		restoDivisao = (totalSomatoria % 11);

		if (restoDivisao < 2) {
			segundoDigito = 0;
		} else {
			segundoDigito = 11 - restoDivisao;
		}

		listaAleatoria.add(segundoDigito);

		return listaAleatoria;
	}

	public String geraCPF() {
		geraCPFParcial();
		geraPrimeiroDigito();
		geraSegundoDigito();

		String cpf = "";
		String texto = "";

		/*
		 * Aqui vamos concatenar todos os valores da lista em uma string Por que
		 * isso? Porque a formatacao que o ArrayList gera me impossibilitaria de
		 * usar a mascara, pois junto com os numeros gerados ele tambem gera
		 * caracteres especias. Ex.: lista com inteiros (de 1 a 5) [1 , 2 , 3 ,
		 * 4 , 5] Dessa forma o sistema geraria a excecao ParseException
		 */
		for (int item : listaAleatoria) {
			texto += item;
		}

		try {
			MaskFormatter mf = new MaskFormatter("###.###.###-##");
			mf.setValueContainsLiteralCharacters(false);
			cpf = mf.valueToString(texto);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return cpf;
	}
}
