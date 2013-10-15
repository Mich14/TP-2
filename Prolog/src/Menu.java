import java.util.Scanner; // importa la libreria Scanner que es propia de java
// crea la clase menu, la que se encargara de mostrar en pantalla las funciones principales a realizar //
public class Menu { 
	
	Menu () {
	System.out.println("Digite 1 para Inserta en la Base de conocimiento o 2 para modo consulta"); // imprime en pantalla las opciones que se le daran al usuario.
	Scanner sc = new Scanner(System.in);
	String opcion;
	opcion = sc.nextLine();
	if (opcion.equals("1")){ // captura la opcion 1 del menu para asi realizarla (inserta en la base de conocimiento)
		Lexico.AnalisisL(1);
	}
	else if (opcion.equals("2")){ // captura la opcion 2 del menu y la realiza (Modo consulta)
		Lexico.AnalisisL(2);
	}
	else { // En caso de no selecionar niguna de las dos opciones le imprime un error.
		System.out.println("No se digito una opción válida");
		new Menu();
	}
}
}