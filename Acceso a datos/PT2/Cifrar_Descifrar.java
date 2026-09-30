import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cifrar_Descifrar {
    public static void main(String[] args) 
    {

        Scanner scanner = new Scanner(System.in);

        // Variables String que seran util
        // La primera es el archivo original, la segunda donde guardaremo el texto cifrado y el tercero donde se descifrara
        String entrada = "entrada.txt";
        String cifrado = "xifrat.txt";
        String descifrado = "desxifrat.txt";

        // Aqui ponemos la clave que nosotros queramos para el cifrado
        System.out.print("Introduce la clave del cifrado: ");
        int clave = scanner.nextInt();

        System.out.println("\n--- INICIO DEL CIFRADO ---");
        // Aqui llamaremos a nuestro metodo "cifrarArchivo" y le daremos las 3 variables que tenemos
        cifrarArchivo(entrada, cifrado, clave);

        System.out.println("\n--- INICI DEL DESCIFRADO ---");
        // Aqui hacemos lo mismo para poder descifrarlo
        descifrarArchivo(cifrado, descifrado, clave);

        // Una vez finalice el programa se cierra el "Scanner", ya que no se utilizara mas
        scanner.close();

        System.out.println("\nProceso completado.");
    }

    // =================================================
    // Metodo para cifrar
    // =================================================

    // Aqui lo que hace es leer el archivo de entrada (entrada.txt), lo cifra y crea el archivo de salida
    public static void cifrarArchivo(String entrada, String salida, int clave) 
    {
        // Aqui lo que hace es leerlo, se envuelve el "FileReader" en el "BufferedReader" y luego se guarda en la variable "br"
        // Para el "bw" se hace lo mismo, se abre para escribir, se envuelve y se guarda en la variable
        // Lo bueno es que, al ser un "try-with-resources" los archivos se cierran automaticamente cuando terminemos
        // De lo contrario, tendriamos que cerrarlo nosotros manualmente
        try (BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(salida))) 
        {
            // Sirve para guardar temporalmente cada linea del archivo
            String linea;
            // Contara la cantidad de lineas que contenga el archivo
            int numeroLinea = 1;

            // Aqui lo que hace es que, mientras haya una linea por leer el while continuara
            while ((linea = br.readLine()) != null) 
            {
                // Un mensaje que mostrara la linea que lleva cifrando
                System.out.println("Cifrando linea " + numeroLinea + "...");

                // Aqui invertimos la linea en la que se encuentre en ese momento
                String invertida = invertir(linea);

                // Aqui aplicamos el texto ya invertido para que se cifre y la "clave" que se usara como desplazamiento
                String cifrada = cesar(invertida, clave);

                // Escribimos el texto cifrado en "xifrat.txt"
                bw.write(cifrada);
                // Despues se añade un salto de linea 
                bw.newLine();

                // Hace que a la hora de mostrarlo en la terminal se vea como va de linea en linea
                numeroLinea++;
            }

            System.out.println("Cifrado completado.");
            System.out.println("Archivo creado: " + salida);

        } 
        // Esto es un mensaje que aparece cuando no existe el archivo
        catch (FileNotFoundException e) 
        {
            System.out.println("Error: no se encontro el archivo: " + e.getMessage());
        } 
        // Un mensaje que va relacionado con la lectura/escritura
        catch (IOException e) 
        {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    // =================================================
    // Metodo para descifrar
    // =================================================

    // Aqui recibe el archivo "xifrat.txt", el archivo descifrado "desxifrat.txt" y la clave que nosotros pusimos
    public static void descifrarArchivo(String entrada, String salida, int clave) 
    {
        // Aqui se hace lo mismo que en el metodo para cifrar pero en este caso se lee el archivo "xifrat.txt"
        try (BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(salida)))
        {

            String linea;
            int numeroLinea = 1;

            while ((linea = br.readLine()) != null) 
            {
                System.out.println("Descifrando linea " + numeroLinea + "...");

                // Aqui se descifrara el texto letra por letra dependiendo de la clave que le hayamos puesto
                String descifrada = cesar(linea, -clave);

                // Se vuelve a invertir el texto para que quede como el archivo original
                String original = invertir(descifrada);

                // Se escribe el texto descifrado en "desxifrat.txt"
                bw.write(original);
                bw.newLine();

                numeroLinea++;
            }

            System.out.println("Descifrado completado.");
            System.out.println("Archivo creado: " + salida);

        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Error: no se encontro el archivo: " + e.getMessage());
        } 
        catch (IOException e) 
        {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    // =================================================
    // Metodo para invertir una cadena
    // =================================================

    public static String invertir(String texto) 
    {
        // Crea un objeto que contiene lo que tenga "entrada.txt" y permite que se modifique facilmente
        // En resumen recibe el texto, lo invierte y vuelve a convertirlo en un String
        return new StringBuilder(texto).reverse().toString();
    }

    // =================================================
    // Metodo para aplicar el cifrado Cesar
    // =================================================

    // Aqui recibe nuestro texto invertido y su desplazamiento
    public static String cesar(String texto, int desplazamiento) 
    {
        // Crea una cadena vacia donde se metera el texto cifrado
        StringBuilder resultado = new StringBuilder();

        // Recorre todas las letras del texto
        for (int i = 0; i < texto.length(); i++) 
        {
            // Aqui le dara tal letra dependiendo de su posicion
            char caracter = texto.charAt(i);

            // Aqui realiza el desplazmiento Unicode
            // En "(char)" se interpreta este numero como una letra
            caracter = (char) (caracter + desplazamiento);

            // Se añade las letras al resultado
            resultado.append(caracter);
        }

        // Convertimos el StringBuilder en String y te da el resultado
        return resultado.toString();
    }
}
