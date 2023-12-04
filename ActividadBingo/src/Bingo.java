import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Math;

public class Bingo {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] carton = new int[10];
        int[] numeros = new int[99];
        int numeroCarton;

        // generar un carton sin numero repeticos

        for (int i = 0; i < carton.length; i++) {
            boolean estaNumeroCarton;

            do {
                estaNumeroCarton = false;
                numeroCarton = (int) (Math.random() * 20) + 1;
                for (int k : carton) {
                    if (numeroCarton == k) {
                        estaNumeroCarton = true;
                        break;
                    }
                }

            } while (estaNumeroCarton);
            carton[i] = numeroCarton;
        }

        // mostrar el cartón
        for (int item : carton) {
            System.out.println(item);
        }

        System.out.println("Cuanto dinero quieres apostar");
        int apuesta = scanner.nextInt();
        System.out.println("En cuantos intentos crees que vas a necesitar ");
        int intentosApuesta = scanner.nextInt();
        int numerosSalidos = 0;

        int aciertos = 0;
        boolean cantadoBindo=false, lineaCantada = false;
        do {


            for (int i = 0; i < numeros.length; i++) {
                boolean estaNumeroBingo;
                int numeroBingo;
                do {
                    estaNumeroBingo = false;
                    numeroBingo = (int) (Math.random() * 20) + 1;
                    for (int k : numeros) {
                        if (numeroBingo == k) {
                            estaNumeroBingo = true;
                            break;
                        }
                    }

                } while (estaNumeroBingo);
                numerosSalidos++;
                numeros[i] = numeroBingo;

                for (int item : carton) {
                    if (item == numeroBingo) {
                        aciertos++;
                    }
                }
                if (aciertos == 5 && !lineaCantada) {
                    System.out.println("Linea!!");
                    lineaCantada = true;
                } else if (aciertos == 10) {
                    System.out.println("Bingo");
                    if (intentosApuesta == numerosSalidos) {
                        System.out.println("Enhorabuena, has acertado con todo");
                        System.out.println("Tu premios es: " + apuesta * 10);
                    }
                    cantadoBindo = true;
                    break;
                }

            }
            System.out.println("Jugando bingo");
        } while (!cantadoBindo);
    }
}
