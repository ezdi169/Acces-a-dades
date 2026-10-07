package PT3;

public class Videojuego 
{
    private String titulo;
    private String genero;
    private int anyoLanzamiento;
    private String plataforma;
    private double precio;

    // Constructor
    public Videojuego(String titulo, String genero, int anyoLanzamiento, String plataforma, double precio) 
    {
        this.titulo = titulo;
        this.genero = genero;
        this.anyoLanzamiento = anyoLanzamiento;
        this.plataforma = plataforma;
        this.precio = precio;
    }

    // Getters
    public String getTitulo() 
    {
        return titulo;
    }

    public String getGenero() 
    {
        return genero;
    }

    public int GetAnyoLanzamiento() 
    {
        return anyoLanzamiento;
    }

    public String getPlataforma() 
    {
        return plataforma;
    }

    public double getPrecio() 
    {
        return precio;
    }

    // Setters
    public void setTitulo(String titulo) 
    {
        this.titulo = titulo;
    }

    public void setGenero(String genero) 
    {
        this.genero = genero;
    }

    public void setAnyoLanzamiento(int anyoLanzamiento) 
    {
        this.anyoLanzamiento = anyoLanzamiento;
    }

    public void setPlataforma(String plataforma) 
    {
        this.plataforma = plataforma;
    }

    public void setPrecio(double precio) 
    {
        this.precio = precio;
    }

    // toString
    @Override
    public String toString() 
    {
        return "Titulo: " + titulo + " | Genero: " + genero + " | Lanzamiento: " + anyoLanzamiento + " | Plataforma: " + plataforma + " | Precio: " + precio + " €";
    }
}
