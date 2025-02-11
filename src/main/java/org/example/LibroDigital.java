package org.example;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LibroDigital")
public class LibroDigital extends Libro {
    @Column(name = "formato")
    private String formato;

    public LibroDigital(String titulo, String autor, String genero, String isbn, int anioPublicacion, String formato) {
        super(titulo, autor, genero, isbn, anioPublicacion);
        this.formato = formato;
    }

    public LibroDigital() {
        super();
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return super.toString() + ", formato='" + formato + '\'' + '}';
    }
}

