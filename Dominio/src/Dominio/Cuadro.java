/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import javax.swing.JPanel;


/**
 *
 * @author Equipo 4
 */
public class Cuadro{
    private final JPanel panel;
    private Linea superior;
    private Linea inferior;
    private Linea izquierda;
    private Linea derecha;
    private Jugador jugador;
    private boolean estado;
    private Factory factory;
    

    /**
     * Método constructor de Cuadro
     * 
     * @param panel recibe el panel, el cual será rellenado de un color una vez sea completado
     * @param jugador recibe el Jugador que completó el cuadro
     * @param superior recibe la línea superior que forma el cuadro
     * @param inferior recibe la línea inferior que forma el cuadro
     * @param izquierda recibe la línea izquierda que foma el cuadro
     * @param derecha recibe la línea derecha que forma el cuadro
     */
    public Cuadro(JPanel panel,Jugador jugador, Linea superior, 
            Linea inferior, Linea izquierda, Linea derecha) {
        this.panel = panel;
        this.superior = superior;
        this.inferior = inferior;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.jugador = jugador;
    }

    /**
     * Constructor de Cuadro que recibe panel, jugador, coordenada x y coordenada y
     * Este es utilizado cuando el cuadro aún no está completo
     * 
     * @param panel recibe el panel, el cual será rellenado de un color una vez sea completado
     * @param jugador recibe el Jugador que completó el cuadro
     */
    public Cuadro(JPanel panel,Jugador jugador) {
        this.panel = panel;
        this.jugador = jugador;
    }

    /**
     * Regresa el valor de la línea superior del cuadro
     * 
     * @return linea superior
     */
    public Linea getSuperior() {
        return superior;
    }
/**
     * Regresa el valor de la línea inferior del cuadro
     * 
     * @return linea inferior
     */
    public Linea getInferior() {
        return inferior;
    }

    /**
     * Regresa el valor de la línea izquierda del cuadro
     *
     * @return linea izquierda
     */
    public Linea getIzquierda() {
        return izquierda;
    }
    /**
     * Regresa el valor de la línea derecha del cuadro
     * 
     * @return derecha
     */
    public Linea getDerecha() {
        return derecha;
    }
    /**
     * Regresa el panel del cuadro
     * 
     * @return panel
     */
    public JPanel getPanel() {
        return panel;
    }
    /**
     * Regresa el Jugador al que pertenezca el cuadro completado
     * 
     * @return jugador
     */
    public Jugador getJugador() {
        return jugador;
    }
    /**
     * Asigna un Jugador al cuadro una vez sea completado
     * 
     * @param jugador 
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    /**
     * Asigna la línea superior proporcionada en parametros
     * @param superior 
     */
    public void setSuperior(Linea superior) {
        this.superior = superior;
    }
    /**
     * Asigna la línea inferior proporcionada en parametros
     * @param inferior 
     */
    public void setInferior(Linea inferior) {
        this.inferior = inferior;
    }
    /**
     * Asigna la línea izquierda proporcionada en parámetros
     * @param izquierda 
     */
    public void setIzquierda(Linea izquierda) {
        this.izquierda = izquierda;
    }
    /**
     * Asigna la línea derecha proporcionada en parametros
     * @param derecha 
     */
    public void setDerecha(Linea derecha) {
        this.derecha = derecha;
    }
    /**}
     * Asigna el estado proporcionado en parametros booleano
     * @param estado 
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    /**
     * Método que checa si el cuadro ha sido completado
     * 
     * @return true si se completó, false si está incompleto
     */
    public boolean cuadroCompleto(){
        return  derecha.getBoton().isEnabled() == false
                && izquierda.getBoton().isEnabled() == false
                && superior.getBoton().isEnabled() == false
                && inferior.getBoton().isEnabled()==false;
    }
   
}