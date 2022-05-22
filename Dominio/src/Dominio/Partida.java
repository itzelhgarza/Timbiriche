/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;


/**
 *
 * @author Equipo 4
 */
public class Partida {
    public Jugador jugadores[];
    public enumeradores estado;

    /**
     * Método de acceso que obtiene el arreglo de los jugadores que conforman la
     * partida
     * 
     * @return arreglo de jugadores
     */
    public Jugador[] getJugadores() {
        return jugadores;
    }
    /**
     * Método de acceso para asignar valor al arreglo de jugadores
     * 
     * @param jugadores recibe un arreglo de jugadores
     */
    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }
    /**
     * Método de acceso para obtener el estado de la partida
     * 
     * @return estado de la partida
     */
    public enumeradores getEstado() {
        return estado;
    }
    /**
     * Método de acceso para asignar un estado a la partida
     * 
     * @param estado 
     */
    public void setEstado(enumeradores estado) {
        this.estado = estado;
    }
    
    
}
