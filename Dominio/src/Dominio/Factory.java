/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author itzel
 */
public class Factory implements IFactory{
    private Cuadro cuadro;
    private Jugador jugador;
    private Linea linea;
    private Partida partida;

    public Factory(){
        
    }
    
    @Override
    public Cuadro crearCuadro(JPanel panel, Jugador jugador, Linea superior, Linea inferior, Linea izquierda, Linea derecha) {
        cuadro = new Cuadro(panel, jugador, superior, inferior, izquierda, derecha);
        return cuadro;
    }

    @Override
    public Cuadro crearCuadro(JPanel panel, Jugador jugador) {
        cuadro = new Cuadro(panel, jugador);
        return cuadro;
    }

    @Override
    public Jugador crearJugador(String nombre, int puntaje) {
        jugador = new Jugador(nombre, puntaje);
        return jugador;
    }

    @Override
    public Jugador crearJugador(String nombre) {
        jugador = new Jugador(nombre);
        return jugador;
    }

    @Override
    public Linea crearLinea(Jugador jugador, JButton boton) {
        linea = new Linea(jugador, boton);
        return linea;
    }

    @Override
    public Partida crearPartida(Jugador[] jugadores) {
        partida = new Partida();
        partida.setJugadores(jugadores);
        return partida;
    }

    @Override
    public Jugador crearJugador() {
        jugador = new Jugador();
        return jugador;
    }

    @Override
    public Linea crearLinea() {
        linea = new Linea();
        return linea;
    }

    @Override
    public Partida crearPartida() {
        partida = new Partida();
        return partida;
    }
    
   
   
        
        
    

    
}
