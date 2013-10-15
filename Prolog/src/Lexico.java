import java.util.Scanner;

public class Lexico {
	static void AnalisisL(){
			Scanner sc = new Scanner(System.in);
			String n;
			System.out.print("Introduzca una regla o hecho a la base de conocimiento: ");
			n = sc.nextLine();
			
			String num = "";
			String letra = "";
			String esp = "";
			boolean unir = false, error = false;
			
			Lista L = new Lista();
			for (int i=0; i<n.length(); i++) {
				
				String c = Character.toString(n.charAt(i));
				
				System.out.println(c);
				
				
				if (c.matches("[0-9]*") && !unir){
					num = num + c;
				}
				
				else {
					/**
					if (c.equals("(") || c.equals(")") || c.equals("+") || c.equals("-") || c.equals("^") || c.equals("*") || c.equals("/") || c.equals("{") || c.equals("}") || c.equals(";") || c.equals(":") || c.equals(".") || c.equals("<") || c.equals(">") || c.equals("=") || c.equals(",")){
						L.InsertaFinal(c.toString());}
					else{ error = true;
						System.out.println ("ERRORRRRR");}
					**/
					if (num != ""){
						L.InsertaFinal(num.toString());
						num = "";
					}
		
					if((c.matches("[a-z]*") || c.matches("[A-Z]*")) && (!unir) ){
						System.out.println("fdede");
						letra = letra + c;
					} 
					else {
						if (letra != ""){
							L.InsertaFinal(letra.toString());
							letra = "";
						}
						
						
						if (c.equals("\"") & (unir)){
							L.InsertaFinal(esp.toString());
							esp = "";
							unir = false;
						}
						
						if (unir){
							esp = esp + c;
						}
						
						else {
							if (c.equals("\"") && (!unir)){
								unir = true;
								L.InsertaFinal(c.toString());
							}	
						}
						
					}
				}
				
			}
			
			if (num != ""){
				L.InsertaFinal(num.toString());
			}
			if (letra != ""){
				L.InsertaFinal(letra.toString());
			}
			//System.out.print(L.Ultimo.datos);
			//System.out.print(L.Primero.siguiente.datos);
			//System.out.print(L.Primero.siguiente.siguiente.datos);
			L.Imprimir();
			if (!error){
				if(Sintaxis.EstructuraBC(L)){
					System.out.print("true");
				}else{
					System.out.print("false");
				}
			}
			else System.out.println("No es válido, existe un error léxico");
			
			
		
	}
	
}
