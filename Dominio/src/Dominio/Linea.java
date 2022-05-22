/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import javax.swing.JButton;



/**
 *
 * @author Equipo 4
 */
public class Linea{
    private JButton boton;
    private Jugador jugador;

    /**
     * Método constructor vacío
     */
    public Linea() {
    }
    /**
     * Método contructor que crea un Jugador a partir de un botón y un Jugador
     * 
     * @param jugador que "trazó" la línea
     * @param boton que representa la línea que se trazó
     */
    public Linea(Jugador jugador,JButton boton) {
        this.boton = boton;
        this.jugador = jugador;
    }
    /**
     * Método de acceso para obtener el objeto botón
     * 
     * @return objeto Button que representa el trazo de la línea
     */
    public JButton getBoton() {
        return boton;
    }
    /**
     * Método de acceso para obtener el Jugador que "trazó" la línea
     * 
     * @return el jugador que trazó la línea
     */
    public Jugador getJugador() {
        return jugador;
    }
    /**
     * Método de acceso para asignar un Jugador a la línea trazada
     * @param jugador 
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

}
