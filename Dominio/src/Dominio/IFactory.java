/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dominio;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author itzel
 */
public interface IFactory {
    /*
    Método para crear el objeto correspondiente
    */
    public Cuadro crearCuadro(JPanel panel,Jugador jugador, Linea superior, 
            Linea inferior, Linea izquierda, Linea derecha);
    public Cuadro crearCuadro(JPanel panel,Jugador jugador);
    public Jugador crearJugador();
    public Jugador crearJugador(String nombre, int puntaje);
    public Jugador crearJugador(String nombre);
    public Linea crearLinea();
    public Linea crearLinea(Jugador jugador,JButton boton);
    public Partida crearPartida();
    public Partida crearPartida(Jugador jugadores[]);
    
}
