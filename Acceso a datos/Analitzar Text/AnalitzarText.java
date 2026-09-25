import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AnalitzarText {

    public static void main(String[] args) {

        int numCaracteres = 0;
        int numLineas = 0;
        int numPalabras = 0;

        boolean dentroPalabra = false;
        boolean hayContenido = false;
        boolean ultimoEraSalto = false;
        boolean ultimoEraCR = false;

        int[] frecuencia = new int[65536];

        // Abrimos un archivo llamado "text.txt"
        try (FileReader fr = new FileReader("text.txt")) 
        {

            int c;

            // Lee una letra del archivo y devuelve su valor como un int
            while ((c = fr.read()) != -1)
            {

                char caracter = (char) c;

                hayContenido = true;

                // Suma cada letra excpeto saltos de linea. Los espacios y tabulaciones si cuentan
                if (caracter != '\n' && caracter != '\r') 
                {
                    numCaracteres++;
                }

                // Contamos saltos de linea y usamos "ultimoEraCR" para no contar 2 veces el salto de Windows
                if (caracter == '\r') 
                {
                    numLineas++;
                    ultimoEraSalto = true;
                    ultimoEraCR = true;
                } 
                else if (caracter == '\n') 
                {
                    // Si el caracater anterior no era "\r", se suma una linea
                    if (!ultimoEraCR) 
                    {
                        numLineas++;
                    }

                    ultimoEraSalto = true;
                    ultimoEraCR = false;

                } 
                else 
                {
                    ultimoEraSalto = false;
                    ultimoEraCR = false;
                }

                // Cuenta las palabras
                if (caracter == ' ' || caracter == '\t' || caracter == '\n' || caracter == '\r') 
                {
                    dentroPalabra = false;
                } 
                else 
                {
                    if (!dentroPalabra) 
                    {
                        numPalabras++;
                        dentroPalabra = true;
                    }
                }

                // Cuenta la frecuencia de caracteres
                if (caracter != ' ' && caracter != '\t' && caracter != '\n' && caracter != '\r') 
                {
                    frecuencia[caracter]++;
                }
            }

            // Contamos la ultima linea si no acaba con salto
            if (hayContenido && !ultimoEraSalto) 
            {
                numLineas++;
            }

            // Buscamos la letra mas repetida
            int max = 0;
            char caracterMasRepetido = '\0';

            for (int i = 0; i < frecuencia.length; i++) 
            {

                if (frecuencia[i] > max) 
                {
                    max = frecuencia[i];
                    caracterMasRepetido = (char) i;
                }
            }

            // Mostramos resultados del programa
            System.out.println("Numero de letras: " + numCaracteres);

            System.out.println("Numero de lineas: " + numLineas);

            System.out.println("Numero de palabras: " + numPalabras);

            if (max > 0) 
            {
                System.out.println("Letra mas repetida: " + caracterMasRepetido);
            } 
            else 
            {
                System.out.println("No hay letras para contar.");
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("El archivo no existe.");
        } 
        catch (IOException e) 
        {
            System.out.println("Error durante la lectura del archivo.");
        } 
        catch (SecurityException e) 
        {
            System.out.println("No tienes permisos para acceder al archivo.");
        }
    }
}
