package com.HermesProjects.practiceJava;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PracticeJava {

	public static void main(String args[]) {
	    
		//Ejercicio: obtener la primera palabra no repetida de la siguiente lista.
		String palabras[] = {"manzana","banana","manzana","naranja","pera","naranja"};
		
		//Modificar la siguiente linea asignando la funcion que desee perform/ejecutar 
		System.out.println(getFirstWordDiffOp9(palabras));
	    
	}
	public static String getFirstWordDiffOp1(String[] sourceToSearch) {
		boolean duplicated = false;
		String palabraEncontrada = null;
		String seedElem = null;
		for(int it = 0; it< sourceToSearch.length -1 ; it++) { // se ingresa a un ciclo
        	duplicated = false;
        	seedElem = sourceToSearch[it];
        	for(int j = it + 1; j< sourceToSearch.length; j++) { 
        		if(seedElem == sourceToSearch[j]){
        			duplicated = true;
        			break;
                }
        	} // Se consulta a la lista original.
        	if (!duplicated) {
        		palabraEncontrada = seedElem;
        		break;
        	}
        }
		return palabraEncontrada;
	}
	public static String getFirstWordDiffOp2(String[] sourceToSearch) {
		List<String> copy = Arrays.asList(sourceToSearch); 
		Iterator<String> nuevoIterador = copy.iterator();
		String palabraEncontrada = null, seedElem;
		boolean duplicated;
		int loop = 0;
		while(nuevoIterador.hasNext()) { // se ingresa a un ciclo
			duplicated = false;
        	seedElem = nuevoIterador.next();
			for(int j = loop + 1; j< sourceToSearch.length; j++) {
        		if(seedElem == sourceToSearch[j]){
        			duplicated = true;
        			break;
                }
        	} // Se consulta a la lista original.
			loop++;
        	if (!duplicated) {
        		palabraEncontrada = seedElem;
        		break;
        	}
		}
		return palabraEncontrada;
	}
	public static String getFirstWordDiffOp3(String[] sourceToSearch) {
		List<String> copy = Arrays.asList(sourceToSearch);
		Iterator<String> iteradorListaOriginal = copy.iterator();
		ListIterator<String> iteradorComparador = copy.listIterator(1);//Este iterador posee la ventaja de recorrer mediante un indice.
		String palabraEncontrada = null, seedElem;
		boolean duplicated;
		int loop = 0;
		while(iteradorListaOriginal.hasNext()) { //Se ingresa a un ciclo
			duplicated = false;
        	seedElem = iteradorListaOriginal.next();
        	while(iteradorComparador.hasNext()) {//Se recorre otro ciclo (semejante al anterior ciclo)
        		if(seedElem == iteradorComparador.next()){
        			duplicated = true;
        			break;
                }// En este bloque se comparan 2 elementos "copias" de la lista original.
        	}
			loop++;
			iteradorComparador = copy.listIterator(loop+1);
        	if (!duplicated) {
        		palabraEncontrada = seedElem;
        		break;
        	}
		}
		return palabraEncontrada;
	}
	public static String getFirstWordDiffOp4(String[] sourceToSearch) {
		List<String> copy = Arrays.asList(sourceToSearch);
		Iterator<String> iteradorListaOriginal = copy.iterator();
		String palabraObtenida = null;
		while(iteradorListaOriginal.hasNext()) { //Se itera en un ciclo
			palabraObtenida = iteradorListaOriginal.next();
			if(copy.indexOf(palabraObtenida) == copy.lastIndexOf(palabraObtenida)) // Se consulta a la lista "copy"
			{ 
				return copy.get(copy.indexOf(palabraObtenida)); // Se consulta a la lista "copy"
			}
		}
		return null;
	}
	public static String getFirstWordDiffOp5(String[] sourceToSearch) {
		// Map<String, Integer> conjunto = Collections.emptyMap();  // NOTE: No funciona
		Map<String, Integer> conjunto = new HashMap<String, Integer>();
		Integer posNoDuplicate = -1; //-1 = la palabra/clave tiene duplicado. 
		// Construyo el map conjunto
		for (String word : sourceToSearch) { //Se ingresa a un ciclo (de la lista original)
			posNoDuplicate++;
			if (conjunto.containsKey(word)) {
				conjunto.put(word, -1);
			}
			else {
				conjunto.put(word, posNoDuplicate);
			}
		} //Finaliza el ciclo.
		//Se ingresa a un ciclo del Mapa creado
		for (Map.Entry<String, Integer> entry : conjunto.entrySet()) {
			if(entry.getValue() != -1) {
				return entry.getKey();
			}
		}
		return null;

	}
	public static String getFirstWordDiffOp6(String[] sourceToSearch) {
		Map<String, Boolean> conjunto = new HashMap<String, Boolean>();
		for (String word : sourceToSearch) {//Se ingresa en un ciclo
			if(!conjunto.containsKey(word)) 
				conjunto.put(word, true);
			else 
				conjunto.remove(word);
		}
		return conjunto.keySet().stream().findFirst().get();
	} //NOTE: No funciona con cantidad impar de palabras repetidas.
	
	public static String getFirstWordDiffOp7(String[] sourceToSearch) {
		Map<String, Integer> conjunto = new HashMap<String, Integer>();
		List<String> copy = Arrays.asList(sourceToSearch);
		int pos = 0;
		for (String word : sourceToSearch) {//Se ingresa a un ciclo
			if(!conjunto.containsKey(word) && Collections.frequency(copy, word) == 1) // Se consulta a la lista "copy"
				conjunto.put(word, pos);//Se almacena la palabra
			pos++;
		}
		//Se obtiene el 1er elem del conjunto
		return conjunto.keySet().stream().findFirst().get();
	}
	
	public static String getFirstWordDiffOp8(String[] sourceToSearch) {
		List<String> copy = Arrays.asList(sourceToSearch);
		Iterator<String> iteradorListaOriginal = copy.iterator();
		String word = null;
		while(iteradorListaOriginal.hasNext()) {//Se ingresa a un ciclo
			word = iteradorListaOriginal.next();
			if(Collections.frequency(copy, word) == 1) // Se consulta a la lista "copy"
				return word;
		}
		return null;
	}
	
	public static String getFirstWordDiffOp8Alt(String[] sourceToSearch) {
		Map<Integer, Integer> conjunto = new HashMap<Integer, Integer>();
		List<String> copy = Arrays.asList(sourceToSearch);
		Iterator<String> iteradorListaOriginal = copy.iterator();
		int pos = 0; // pos in array
		int it = 0; //valor inicial, para generar indice
		while(iteradorListaOriginal.hasNext()) {//Se ingresa a un ciclo
			if(Collections.frequency(copy, iteradorListaOriginal.next()) == 1) {//Se consulta a la lista "copy"
				conjunto.put(it, pos); //Como palabra clave es el valor de iterador, el valor marca la posicion en array donde se encuentra.
				it++;
			}
			pos++;
		}//Finaliza el ciclo
		return (!conjunto.isEmpty() ? sourceToSearch[conjunto.get(0).intValue()] : null) ;
	}
	
	public static String getFirstWordDiffOp9(String[] sourceToSearch) {
		Map<String, Integer> conjunto = new HashMap<String, Integer>();
		List<String> copy = Arrays.asList(sourceToSearch);
		Iterator<String> iteradorListaOriginal = copy.iterator();
		
		// Comienzo a cargar todas las palabras en un mapa, e ir contando la cantidad de repeticiones de cada una.
		String listWord;
		int wordValue = 0;
		while(iteradorListaOriginal.hasNext()) {
			listWord = iteradorListaOriginal.next();
			if(conjunto.containsKey(listWord)) {
				conjunto.replace(listWord, wordValue, wordValue+1);
				// el numero se almacena en var wordValue para posteriormente incrementar su valor.
			}
			else {
				conjunto.put(listWord, 1);
			}
		}// Finalizado el ciclo
		// Obtengo la primera palabra sin repetición, a pertir del Mapa armado.
		for(Map.Entry<String, Integer> entry: conjunto.entrySet()) {
			if(entry.getValue().equals(1))
				return entry.getKey();
		}
		return null;
	}
}
