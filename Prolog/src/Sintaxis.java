
public class Sintaxis {
	
	static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	
	static boolean EstructuraBC(Lista L){
		Nodo aux = L.Primero;
		if(Character.isUpperCase(aux.datos.charAt(0))){
			System.out.print("El enunciado no puede tener mayusculas al inicio   ");
			return false;
		}
		if(!L.Ultimo.datos.equals(".")){
			System.out.print("Recuerde que el ultimo  elemento tiene que ser  '.'  ");
			return false;
		}
		if(Sintaxis.isNumeric(Character.toString(L.Primero.datos.charAt(0))) || !Character.isLetter(L.Primero.datos.charAt(0))){
			System.out.print("No puede contener numeros o signos al inicio del enunciado   ");
			return false;
		}
		while(aux != null){
			if(Character.isLetter(aux.datos.charAt(0)) && aux.siguiente.datos.equals(":") && aux.siguiente.siguiente.datos.equals("-")){
				aux=aux.siguiente.siguiente.siguiente;
				if(Proceso(aux)){
					return true;
				}
				else{
					return false;}
			}
			while(Character.isLetter(aux.datos.charAt(0)) ){
				aux=aux.siguiente;
			}
			if(!aux.datos.equals("(")){
				System.out.print("Recuerde abrir y cerrar parentisis kk   ");
				return false;
			}aux=aux.siguiente;
			if(!Character.isLetter(aux.datos.charAt(0))){
				return false;
			}
			while(Character.isLetter(aux.datos.charAt(0)) || aux.datos.equals(",")){
				aux=aux.siguiente;
			}
			if(!aux.datos.equals(")")){
				System.out.print("Recuerde cerrar y abrir los parentesis");
				return false;
			}
			aux=aux.siguiente;
			if(aux.datos.equals(".") && aux.siguiente==null){
				
				return true;
			}
			if(!aux.datos.equals(":") || !aux.siguiente.datos.equals("-")){
				System.out.print("Recuerde usar :- despues del consecuente");
				return false;
			}
			aux=aux.siguiente.siguiente;
			while(aux!=null){
				if(Proceso(aux)){
					return true;
				}return false;
			}
			break;
	}return true;		
	} 
	
	
static boolean Proceso(Nodo aux){
		boolean flag=true;
		while(aux!=null){
		if(aux.datos.equals("nl") || aux.datos.equals("fail")){
			if(!aux.siguiente.datos.equals(".")){
				aux=aux.siguiente.siguiente;
				Proceso(aux);}
			else if(aux.siguiente.siguiente==null){
				return true;
			}
			else{
				return false;}
		}
		if(!Character.isLetter(aux.datos.charAt(0))){
			System.out.print("Error de composicion");
			return false;
		}
		aux=aux.siguiente;
		if(!aux.datos.equals("(")){
			System.out.print("Recuerde abrir y cerrar parentisis segundo enunciado2    ");
			return false;
		}
		aux=aux.siguiente;
		if(aux.datos.equals("\"")){
			aux=aux.siguiente.siguiente;
			flag=false;
		}
		
		if(aux.datos.equals("\"")){
			flag=true;
		}
		aux=aux.siguiente;
		if(!aux.datos.equals(")")){
				System.out.print("Recuerde abrir y cerrar parentesis segundo enunciado    ");
				return false;
		}
		if(!flag){
			System.out.print("Error en comillas    ");
			return false;
		}
		aux=aux.siguiente;
		if(aux.datos.equals(".") && aux.siguiente==null){
			return true;}
		if(aux.datos.equals(",") || aux.siguiente.datos.equals(",") || aux.datos.equals(";") || aux.siguiente.datos.equals(";")){
			aux=aux.siguiente;
			Proceso(aux);
			}
		}
	return true;
	}
}

