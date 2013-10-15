 public class Consulta {

	 // Se establece las variable estaticas con las que se trabajara en el sistema
	static boolean Es_hecho, Es_regla;
	static Nodo Pred_Valido;
	static Lista Resultado = new Lista();
	static Lista BC = Lexico.BC;
	
	
	//Es el método principal que invocará a los mpetodos resultados para establecer si la consulta es verdadera o falsa
	static void Consultar (Lista L) {
		
		// Este condición se establece si el predicado unifica y contiene la misma ariedad y es un hecho.
		if (Buscar_BC(L, BC) && Es_hecho) {
			
			Resultado.InsertaBool("", true);
			if (!Es_regla){
				Validez();
			}
			Es_hecho = false;
			Es_regla = false;
		}
		
		// En esta condición entra si unifica contiene la misma aridad pero si es una regla
		else if (Buscar_BC(L, BC) && !Es_hecho) {
			
			Lista Pila = new Lista(); // Se crea una pila con la cual se va trabajar para determinar la validez de una regla
			Lista ListaInsertada = new Lista(); // Se crea una lista con la cual se va ir guardando cada uno de los predicados de la regla
			ListaInsertada.InsertaFinal(Pred_Valido.dato, Pred_Valido.valor); // Se inserta el primer predicado a la lista
			
			Nodo aux = Pred_Valido.Puntero; // Se recorre el nodo en el cual esta la regla que fur válida
			
			while (aux != null){ // Recorre el nodo donde esta la regla
				if (aux.dato.equals(":")){ 
					
					// Se inserta en la pila y luego se pone la lista insertada en null
					Pila.InsertaPila(ListaInsertada.Primero, false);					
					ListaInsertada.Primero =  null;
					aux = aux.siguiente;
				}
				else if (aux.dato.equals(",") || aux.dato.equals(";") || aux.dato.equals(".")) {
					
					Pila.InsertaPila(ListaInsertada.Primero, false);
					Pila.InsertaPila(aux, false);
					ListaInsertada.Primero =  null;
					
					//Resultado.InsertaBool(aux.dato, true);
					
				}
				else {
					ListaInsertada.InsertaFinal(aux.dato, aux.valor); // Se llena la lista ensertada con cada uno de los diferentes tokens de los hechos
				}			
				aux = aux.siguiente;
			}
			VerificarRegla (Pila, BC); // Se llama al método verificar regla para determinar la validez de la regla
		}
		else {
			Resultado.InsertaBool("", false); // Si no se cumple el hecho se inserta un false en la lista de resultado 
			if (!Es_regla){ // Si solo es un hecho se llama de de nuevo a validez
				Validez();
				
			}
			Es_regla = false;
		}
	}
	
	// Este método determina si se posee la aridad y la unificación funciona
	static boolean Buscar_BC (Lista L, Lista BC){
		
		Nodo aux_BC = BC.Primero;
		boolean valido = true;
		
		while (aux_BC != null) {
			
			if (aux_BC.dato.equals(L.Primero.dato) ){
				
				valido = true;
				Nodo auxBC_P = aux_BC.Puntero; 
				Nodo auxConsulta = L.Primero.siguiente;
				
				// Se recorre la lista donde esta la consulta y el predicado vpalido de Base de Conocimiento
				while (auxConsulta != null && auxBC_P != null && valido ) {
					
					// Si es parametro verifica si unifica
					if (!auxBC_P.dato.equals("(") && !auxBC_P.dato.equals(")") && !auxBC_P.dato.equals(",") && !auxBC_P.dato.equals(".")){    
						
						// si no unifica valido esta en false y sigue con el siguiente en la BC
						if (!unifica(auxBC_P, auxConsulta)) {
							valido = false;
						}
					}
					
					// con este else if se determina si es un hecho o una regla
					else if (auxBC_P.dato.equals(")") ) {
						if (auxBC_P.siguiente.dato.equals(".")) Es_hecho = true;
						else Es_regla = true;
						if (!auxConsulta.dato.equals(")")) valido = false; 
					}
					
					else if (auxConsulta.dato.equals(")") ) {
						if (!auxBC_P.dato.equals(")")) valido = false; 
					}
					auxBC_P = auxBC_P.siguiente;
					auxConsulta = auxConsulta.siguiente;
				}
				// Si es valido es el predicado en cual se va trabajar
				if (valido) break;
			}
			aux_BC = aux_BC.siguiente;
		}
		
		if (valido && aux_BC != null){
			Pred_Valido = aux_BC;
			return true;
		}
		else if (aux_BC == null) {
			return false;
		}
		else return false;
		
	}
	
	// Mpetod que determina si unifica y si la ariedad conside
	static boolean unifica (Nodo BC, Nodo Consulta) {
		
		char iBC = BC.dato.charAt(0); // Guarda la iniciales de los prametros que le entran
		char iCon = Consulta.dato.charAt(0);
		
		if (Character.isUpperCase(iBC) && Character.isUpperCase(iCon)) 
			Consulta.valor = BC.valor;
		
		else if (Character.isLowerCase(iBC) && Character.isLowerCase(iCon)) {
			if (BC.dato.equals(Consulta.dato)) {
				Consulta.valor = Consulta.dato;
				BC.valor = BC.dato; 
			}
			else return false;
		}
		
		else if (Character.isUpperCase(iBC) && Character.isLowerCase(iCon))
			BC.valor = Consulta.dato;
		
		else 
			Consulta.valor = BC.dato;
		
		return true;
	}
	
	// Método que verifica si la regla funciona
	static void VerificarRegla (Lista Pila, Lista BC){
		
	// Se recorre la pila y valida coada uno de los antecedentes
		
		Nodo predAux = Pila.Ultimo.anterior;
		while (predAux != Pila.Primero){
			
			Lista ListaAux = new Lista();
			Nodo aux = predAux.Puntero;
			
			if (aux.dato.equals(",") || aux.dato.equals(";") || aux.dato.equals(".") ){
				Resultado.InsertaBool(aux.dato, true);
			}
			else {
				
				while (aux != null){
					ListaAux.InsertaFinal(aux.dato, aux.valor);
					aux = aux.siguiente;
				}
				//ListaAux.Imprimir();
				if (ListaAux.Primero.dato.equals("write")){
					System.out.println(ListaAux.Primero.siguiente.siguiente.siguiente.dato);
					Resultado.InsertaBool("", true);
				}
				else if(ListaAux.Primero.dato.equals("nl")){
					System.out.println("");
					Resultado.InsertaBool("", true);
				}
				else if (ListaAux.Primero.dato.equals("fail")){
					Resultado.InsertaBool("", false);
				}
				else{
					Consultar(ListaAux);
				}
			}
			predAux = predAux.anterior;
		}
		Validez();
		
	}
	
	// recorre la alista resultado y determina si la regla se cumple o no
	static void Validez (){
		
		
		if (Resultado.VaciaLista()){ return;}
		
		if (Resultado.Primero.siguiente == null || Resultado.Primero.siguiente.dato.equals(".")){
			if(Resultado.Primero.cumple) System.out.println("YES");
			else System.out.println("NO");
			
		}
		
		else{
			if (Resultado.Primero.siguiente.siguiente == null) return;
			boolean resultado = Operar(Resultado.Primero.cumple, Resultado.Primero.siguiente.dato, Resultado.Primero.siguiente.siguiente.cumple);
			
			Nodo aux = Resultado.Primero.siguiente.siguiente.siguiente;
			while (aux != null ){
				
				if (!aux.dato.equals(""))resultado = Operar(resultado, aux.dato, aux.siguiente.cumple) ;
				aux = aux.siguiente;
			}
			if (resultado) System.out.println("YES");
			else System.out.println("NO");
		}
		Resultado.Primero = null;
		
		
	}
	
	// Método que determina se es un and o un or
	static boolean Operar(boolean ant, String ope, boolean suc){
		
		
		if (ope.equals(","))
			return ant && suc;
		
		if (ope.equals(";"))
			return ant || suc;
		return true;
	}
	
	
}