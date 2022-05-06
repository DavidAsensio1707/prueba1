/*Ejercicio 1
Escribe un programa que dados dos números, uno real (base) y un entero
positivo (exponente), saque por pantalla todas las potencias con base el
numero dado y exponentes entre uno y el exponente introducido. No se deben
utilizar funciones de exponenciación. Por ejemplo, si introducimos el 2 y el 5,
se deberán mostrar 2^1, 2^2, 2^3, 2^4 y 2^5. */
import java.util.Scanner;
public class Ejercicio_1 {
    public static void main(String[] args){

        Scanner sc= new Scanner(System.in);
        System.out.println("Introduce el número base");
        double nBase=sc.nextDouble();
        System.out.println("Introduce la potencia");
        int nExponenteTeclado=sc.nextInt();
        for(int nExponente=1;nExponente<=nExponenteTeclado;nExponente++){
            int resultado=1;
            for(int j=1;j<nExponente;j++){
                resultado*=nBase;
            }
        System.out.println(nBase+"^"+nExponente+"="+ resultado);
        }    
        sc.close();
    }
}

