package PT3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionVideojuegos 
{
    private static final String FICHERO = "PT3/videojuegos.dat";

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        // Cargamos los videojuegos que hay en el archivo
        ArrayList<Videojuego> videojuegos = cargarVideojuegos();

        int opcion;

        do 
        {
            System.out.println("\n==================================");
            System.out.println("       GESTION DE VIDEOJUEGOS");
            System.out.println("==================================");
            System.out.println("1. Agregar videojuego");
            System.out.println("2. Enlistar todos los videojuegos");
            System.out.println("3. Buscar videojuego por titulo");
            System.out.println("4. Actualizar videojuego");
            System.out.println("5. Eliminar videojuego");
            System.out.println("6. Salir");
            System.out.println("================================");
            System.out.print("Escoge una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) 
            {
                case 1:
                    agregarVideojuego(videojuegos, sc);
                    guardarVideojuegos(videojuegos);
                    break;

                case 2:
                    enlistarVideojuegos(videojuegos);
                    break;

                case 3:
                    buscarVideojuegos(videojuegos, sc);
                    break;

                case 4:
                    actualizarVideojuego(videojuegos, sc);
                    guardarVideojuegos(videojuegos);
                    break;

                case 5:
                    eliminarVideojuego(videojuegos, sc);
                    guardarVideojuegos(videojuegos);
                    break;

                case 6:
                    guardarVideojuegos(videojuegos);
                    System.out.println("Cambios guardados.");
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        } 
        while (opcion != 6);

        sc.close();
    }


    // ==========================================================
    // 1. AGREGAR VIDEOJUEGO
    // ==========================================================

    
    public static void agregarVideojuego(ArrayList<Videojuego> videojuegos, Scanner sc) 
    {
        System.out.println("\n--- Agregar videojuego ---");

        System.out.print("Titulo: ");
        String titulo = sc.nextLine();

        System.out.print("Genero: ");
        String genero = sc.nextLine();

        System.out.print("Anyo de lanzamiento: ");
        int anyoLanzamiento = sc.nextInt();
        sc.nextLine();

        System.out.print("Plataforma: ");
        String plataforma = sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();

        Videojuego videojuego = new Videojuego(titulo, genero, anyoLanzamiento, plataforma, precio);

        videojuegos.add(videojuego);

        System.out.println("Videojuego agregado correctamente.");
    }


    // ==========================================================
    // 2. ENLISTAR VIDEOJUEGOS
    // ==========================================================


    public static void enlistarVideojuegos(ArrayList<Videojuego> videojuegos) 
    {
        System.out.println("\n--- Lista de videojuegos ---");

        if (videojuegos.isEmpty()) 
        {
            System.out.println("No hay videojuegos guardados.");
            return;
        }

        for (int i = 0; i < videojuegos.size(); i++) 
        {
            System.out.println((i + 1) + ". " + videojuegos.get(i));
        }
    }


    // ==========================================================
    // 3. BUSCAR POR TITULO
    // ==========================================================


    public static void buscarVideojuegos(ArrayList<Videojuego> videojuegos, Scanner sc) 
    {
        System.out.println("\n--- Buscar videojuego ---");

        System.out.print("Escribe el titulo o una parte del titulo: ");
        String texto = sc.nextLine();

        boolean encontrado = false;

        for (Videojuego videojuego : videojuegos) 
        {
            if (videojuego.getTitulo().toLowerCase().contains(texto.toLowerCase())) 
            {
                System.out.println(videojuego);
                encontrado = true;
            }
        }

        if (!encontrado) 
        {
            System.out.println("No se encontro el/los videojuego/s.");
        }
    }


    // ==========================================================
    // 4. ACTUALIZAR VIDEOJUEGO
    // ==========================================================


    public static void actualizarVideojuego(ArrayList<Videojuego> videojuegos, Scanner sc) 
    {
        System.out.println("\n--- Actualizar videojuego ---");

        if (videojuegos.isEmpty()) 
        {
            System.out.println("No hay videojuegos por actualizar.");
            return;
        }

        // Se muestra los videojuegos como un numero
        enlistarVideojuegos(videojuegos);

        System.out.print("Selecciona el numero del videojuego: ");
        int numero = sc.nextInt();
        sc.nextLine();

        if (numero < 1 || numero > videojuegos.size()) 
        {
            System.out.println("Numero incorrecto.");
            return;
        }

        Videojuego videojuego = videojuegos.get(numero - 1);

        System.out.println("\nEscribe los nuevos datos:");

        System.out.print("Nuevo titulo: ");
        String titulo = sc.nextLine();

        System.out.print("Nuevo genero: ");
        String genero = sc.nextLine();

        System.out.print("Nuevo anyo de lanzamiento: ");
        int anyoLanzamiento = sc.nextInt();
        sc.nextLine();

        System.out.print("Nueva plataforma: ");
        String plataforma = sc.nextLine();

        System.out.print("Nuevo precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();

        videojuego.setTitulo(titulo);
        videojuego.setGenero(genero);
        videojuego.setAnyoLanzamiento(anyoLanzamiento);
        videojuego.setPlataforma(plataforma);
        videojuego.setPrecio(precio);

        System.out.println("Videojuego actualizado correctamente.");
    }


    // ==========================================================
    // 5. ELIMINAR VIDEOJUEGO
    // ==========================================================


    public static void eliminarVideojuego(ArrayList<Videojuego> videojuegos, Scanner sc) 
    {
        System.out.println("\n--- Eliminar videojuego ---");

        if (videojuegos.isEmpty()) 
        {
            System.out.println("No hay videojuegos para eliminar.");
            return;
        }

        enlistarVideojuegos(videojuegos);

        System.out.print("Selecciona el numero del videojuego que quieres eliminar: ");
        int numero = sc.nextInt();
        sc.nextLine();

        if (numero < 1 || numero > videojuegos.size()) 
        {
            System.out.println("Numero incorrecto.");
            return;
        }

        Videojuego eliminado = videojuegos.remove(numero - 1);

        System.out.println("Videojuego eliminado: " + eliminado.getTitulo());
    }


    // ==========================================================
    // CARGAR VIDEOJUEGOS
    // ==========================================================


    @SuppressWarnings("unchecked")
    public static ArrayList<Videojuego> cargarVideojuegos() 
    {
        ArrayList<Videojuego> videojuegos = new ArrayList<>();

        File archivo = new File(FICHERO);

        System.out.println("Buscando archivo en:");
        System.out.println(archivo.getAbsolutePath());

        // Si el fitxer encara no existeix, retornem una llista buida
        if (!archivo.exists()) 
        {
            System.out.println("El archivo NO existe.");
            return videojuegos;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) 
        {
            videojuegos = (ArrayList<Videojuego>) ois.readObject();
            System.out.println("Videojuegos cargados correctamente.");

        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Error cargando videojuegos: " + e.getMessage());
        }

        return videojuegos;
    }


    // ==========================================================
    // GUARDAR VIDEOJUEGOS
    // ==========================================================


    public static void guardarVideojuegos(ArrayList<Videojuego> videojuegos) 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO))) 
        {
            oos.writeObject(videojuegos);
            System.out.println("Datos guardados correctamente.");
        } 
        catch (IOException e) 
        {
            System.out.println("Error guardando videojuegos: " + e.getMessage());
        }
    }
}
