/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.awt.Color;
import java.util.Objects;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Equipo 4
 */
public class Jugador {
    public boolean jugando;
    public String nombre;
    public int puntaje;
    public Icon icono;
    public Color color;
    public boolean activo;
    /**
     * Método constructor vacío
     */
    public Jugador() {}

    /**
     * Método constructor que crea un Jugador a partir del nombre
     * 
     * @param nombre recibe el nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Método constructor que crea un Jugador a partir del nombre y el puntaje
     * 
     * @param nombre del jugador
     * @param puntaje del jugador
     */
    public Jugador(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }
    /**
     * Método que retorna el estado del jugador
     * 
     * @return true si el Jugador aún juega, false de lo contrario
     */
    public boolean isJugando() {
        return jugando;
    }
    /**
     * Asigna un valor al estado del jugador
     * 
     * @param jugando recibe el estado del jugador
     */
    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }
    /**
     * Método de acceso para obtener el nombre del jugador
     * 
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Método de acceso para asignar nombre al jugador
     * 
     * @param nombre que se le asignará al jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Método de acceso para obtener el puntaje del Jugador
     * 
     * @return puntaje del Jugador
     */
    public int getPuntaje() {
        return puntaje;
    }
    /**
     * Método de acceso para asignar puntaje al jugador
     * 
     * @param puntaje que se le asignará al jugador
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    /**
     * Método de acceso para obtener el ícono del jugador
     * 
     * @return ícono del jugador
     */
    public Icon getIcono() {
        return icono;
    }
    /**
     * Método de acceso para asignar ícono al jugador
     * 
     * @param icono del jugador
     */
    public void setIcono(Icon icono) {
        this.icono = icono;
    }
    /**
     * Método de acceso para obtener el color del jugador
     * 
     * @return color del jugador
     */

    public Color getColor() {
        return color;
    }
    /**
     * Método de acceso para asignar color al jugador
     * 
     * @param color que se asignará a jugador
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    /**
     * Método de acceso para retornar un String con los valores del Jugador
     * 
     * @return string con los valores del jugador
     */
    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + '}';
    }
    
}
