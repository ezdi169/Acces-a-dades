import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.NodeList; 
import java.io.File;

public class LeerZoo 
{
    public static void main(String[] args) 
    {
        try
        {
            // Se carga el archivo XML
            File archivo = new File("PT4/ZooVirtual.xml");

            // Creamos un lector XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Leemos el documento
            Document documento = builder.parse(archivo);

            // Obtenemos todos los elementos de animal
            // BONUS: Muestra el numero total de los animales que hay en el Zoo
            NodeList animales = documento.getElementsByTagName("animal");
            System.out.println("El numero total de animales son: " + animales.getLength());
            System.out.println();

            // Recorremos todos los animales
            for(int i = 0; i < animales.getLength(); i++)
            {
                Element animal = (Element) animales.item(i);

                // Obtenemos el atributo id
                String id = animal.getAttribute("id");

                // A partir de aqui cogemos el resto de atributos que posee cada animal
                String nombre = animal.getElementsByTagName("nombre").item(0).getTextContent();
                String especie = animal.getElementsByTagName("especie").item(0).getTextContent();
                String alimento = animal.getElementsByTagName("alimento").item(0).getTextContent();
                String edad = animal.getElementsByTagName("edad").item(0).getTextContent();

                // Aqui mostramos los datos por pantalla
                // BONUS: Solo muestra los animales que coman carne
                if(alimento.equalsIgnoreCase("Carne"))
                {
                    System.out.println("Animal #" + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Especie: " + especie);
                    System.out.println("Alimento: " + alimento);
                    System.out.println("Edad: " + edad);
                    System.out.println();
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
        }
    }
}
