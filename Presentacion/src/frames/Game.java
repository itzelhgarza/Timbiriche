/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import Dominio.Factory;
import Dominio.Cuadro;
import Dominio.Jugador;
import Dominio.Linea;
import Dominio.Partida;
import static Dominio.enumeradores.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Equipo 2
 * prueba git
 */
public class Game extends javax.swing.JFrame {
    //Instancia a la clase Fábrica
    Factory fabrica = new Factory();
    
    //Se crea un jugador central para señalar que está jugando
    Jugador jugadorUsuario;
    //Contador para turnos
    int turno=0;
    //Arreglo de jugadores
    Jugador jugadores[];
    //jugador comodín para la creación de instancias futuras
    Jugador comodin = fabrica.crearJugador("Oscar30871", 2);
    //comodin.crearJugador("Oscar30871", 2);
    //Jugador comodin = new Jugador("Oscar30871", 2);
    
    //Creación de instancia partida
    Partida partida = fabrica.crearPartida();
    //Arreglo de lineas horizontales
    ArrayList<Linea> lineasHorizontales = new ArrayList();
    //Arreglo de lineas verticales
    ArrayList<Linea> lineasVerticales = new ArrayList();
    //Arreglo de cuadros
    ArrayList<Cuadro> cuadros = new ArrayList();
    //Arreglo de cuadros
    ArrayList<JPanel> paneles = new ArrayList();
    //Arreglo de etiquetas que muestran quien sigue en turno
    JLabel turnos[]=new JLabel[4];
    //Contador de jugadores activos
    int jugadoresActivos = 0;
    /**
     * Log de eventos
     */
    JFrame ventana_Log;
    JTextArea area_Log;
    JPanel contenedor_AreaLog;
    JScrollPane scroll;
    public void interfaceLog() {
        ventana_Log = new JFrame("- Log -");
        area_Log = new JTextArea(10, 12);
        scroll = new JScrollPane(area_Log);
        area_Log.setEnabled(false);
        contenedor_AreaLog = new JPanel();
        contenedor_AreaLog.setLayout(new GridLayout(1, 1));
        contenedor_AreaLog.add(scroll);

        ventana_Log.add(contenedor_AreaLog, BorderLayout.NORTH);
        ventana_Log.setSize(300, 220);
        ventana_Log.setVisible(true);
        ventana_Log.setResizable(false);
        ventana_Log.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public String mensajeClicLinea(Jugador jugador) {
        String mensaje = "";
        mensaje = "El jugador " + jugador.getNombre() + " ha trazado una linea";
        return mensaje;
    }
    public String llenoUnCuadro(Jugador jugador){
        String mensaje = "";
        mensaje = "El jugador " + jugador.getNombre() + " ha completado un cuadro";
        return mensaje;
    }
    public void mostrarMensaje(String mensaje) {
        area_Log.append(mensaje + "\n");
    }
    /**
     * Creates new form Game
     * @param jugadores
     * @param tablero
     */
    public Game(Jugador jugadores[]) {
        initComponents();
        interfaceLog();
        //Se centra la pantalla
        this.setLocationRelativeTo(null);
        //El jugador principal se iguala al jugador de esta misma clase
        this.jugadores = jugadores;
        //El jugador que obtendrá la primera acción será el jugador principal
        this.jugadorUsuario = this.jugadores[0];
        //se agregan los jugadores a partida
        partida = fabrica.crearPartida(jugadores);
        //partida.setJugadores(jugadores);
        //Se asigna la partida al estado "En curso"
        partida.setEstado(EN_CURSO);
        //Asigna colores a cada jugador por defecto
        this.jugadores[0].setColor(Color.BLUE);
        this.jugadores[1].setColor(Color.CYAN);
        this.jugadores[2].setColor(Color.ORANGE);
        this.jugadores[3].setColor(Color.GREEN);
        //El panel de cada jugador se establecerá por su color por defecto
        panelJugador1.setBackground(Color.BLUE);
        panelJugador2.setBackground(Color.CYAN);
        panelJugador3.setBackground(Color.ORANGE);
        panelJugador4.setBackground(Color.GREEN);
        
        //Se cambiara por gris el color de los jugadores y del panel en caso de
        //estar inactivos
        for (int i = 0; i < jugadores.length; i++) {
            if(jugadores[i].isActivo() == false){
                jugadores[i].setColor(Color.GRAY);
                switch(i){
                    case 0:
                        panelJugador1.setBackground(Color.GRAY);
                        botonCambiarColor.setEnabled(false);
                        break;
                    case 1:
                        panelJugador2.setBackground(Color.GRAY);
                        botonCambiarColor1.setEnabled(false);
                        break;
                    case 2:
                        panelJugador3.setBackground(Color.GRAY);
                        botonCambiarColor2.setEnabled(false);
                        break;
                    case 3:
                        panelJugador4.setBackground(Color.GRAY);
                        botonCambiarColor3.setEnabled(false);
                        break;
                }
            }
        }
        //Se asigna cada una de las etiquetas al arreglo de etiquetas
        turnos[0]=labelTurno1;
        turnos[1]=labelTurno2;
        turnos[2]=labelTurno3;
        turnos[3]=labelTurno4;
        
        //Se hace visible la etiqueta 1 y las demás desaparecerán
        turnos[0].setVisible(false);
        turnos[1].setVisible(false);
        turnos[2].setVisible(false);
        turnos[3].setVisible(false);        
        for (int i = 0; i < jugadores.length; i++) {
            if(jugadores[i].isActivo()){
                turnos[i].setVisible(true);
                turno = i;
                jugadorUsuario = jugadores[i];
                System.out.println("Jugador seleccionado:"+jugadores[i].getNombre());
                break;
            }
        }
        for (int i = 0; i < jugadores.length; i++) {
            if(jugadores[i].isActivo()){
                jugadoresActivos++;
            }            
        }
        //Desaparece los botones que no serán requeridos en la partida dependiendo
        //del número de jugadores
        switch(jugadoresActivos){
            case 2:
                btnV41.setVisible(false);
                btnV42.setVisible(false);
                btnV43.setVisible(false);
                btnV44.setVisible(false);
                btnV45.setVisible(false);
                btnH51.setVisible(false);
                btnH52.setVisible(false);
                btnH53.setVisible(false);
                btnH54.setVisible(false); 
                
                btnV31.setVisible(false);
                btnV32.setVisible(false);
                btnV33.setVisible(false);
                btnV34.setVisible(false);
                btnV35.setVisible(false);
                btnH41.setVisible(false);
                btnH42.setVisible(false);
                btnH43.setVisible(false);
                btnH44.setVisible(false);  
                
            case 3:
                btnV41.setVisible(false);
                btnV42.setVisible(false);
                btnV43.setVisible(false);
                btnV44.setVisible(false);
                btnV45.setVisible(false);
                btnH51.setVisible(false);
                btnH52.setVisible(false);
                btnH53.setVisible(false);
                btnH54.setVisible(false);  
        }
         
        //Se asignan los nombre y los icono a los jugadores
        txtNombre1.setText(jugadores[0].getNombre());
        labelIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource(this.jugadores[0].getIcono().toString())));
        txtNombre2.setText(jugadores[1].getNombre());
        labelIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/avatar2R.png")));
        txtNombre3.setText(jugadores[2].getNombre());
        labelIcon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/avatar3R.png")));
        txtNombre4.setText(jugadores[3].getNombre());
        labelIcon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/avatar1R.png")));
        //Botones añadidos a la lista
        //Se asignarán los botones que están de manera Horizontal primero a la 
        int aux;
        switch (jugadoresActivos) {
            case 2:
                for (int i = 0; i < 8; i++) {
                    cuadros.add(fabrica.crearCuadro(paneles.get(i), comodin, lineasHorizontales.get(i), lineasHorizontales.get((i + 4)), new Linea(), new Linea()));
                }
                aux = 0;
                for (int i = 0; i < 8; i++) {
                    if (i == 4) {
                        aux++;
                    }
                    cuadros.get(i).setIzquierda(lineasVerticales.get(i + aux));
                    cuadros.get(i).setDerecha(lineasVerticales.get(i + 1 + aux));
                }
                break;
            case 3:
                for (int i = 0; i < 12; i++) {
                    cuadros.add(fabrica.crearCuadro(paneles.get(i), comodin, lineasHorizontales.get(i), lineasHorizontales.get((i + 4)), new Linea(), new Linea()));
                }
                aux = 0;
                for (int i = 0; i < 12; i++) {
                    if (i == 4 || i == 8) {
                        aux++;
                    }
                    cuadros.get(i).setIzquierda(lineasVerticales.get(i + aux));
                    cuadros.get(i).setDerecha(lineasVerticales.get(i + 1 + aux));
                }
                break;
            case 4:
                for (int i = 0; i < 16; i++) {
                    cuadros.add(fabrica.crearCuadro(paneles.get(i), comodin, lineasHorizontales.get(i), lineasHorizontales.get((i + 4)), new Linea(), new Linea()));
                }
                aux = 0;
                for (int i = 0; i < 16; i++) {
                    if (i == 4 || i == 8 || i == 12) {
                        aux++;
                    }
                    cuadros.get(i).setIzquierda(lineasVerticales.get(i + aux));
                    cuadros.get(i).setDerecha(lineasVerticales.get(i + 1 + aux));
                }
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbandonar = new javax.swing.JButton();
        panelTablero = new javax.swing.JPanel();
        btnH11 = new javax.swing.JButton();
        btnH12 = new javax.swing.JButton();
        btnH13 = new javax.swing.JButton();
        btnH14 = new javax.swing.JButton();
        btnV11 = new javax.swing.JButton();
        btnV12 = new javax.swing.JButton();
        btnV13 = new javax.swing.JButton();
        btnV14 = new javax.swing.JButton();
        btnV15 = new javax.swing.JButton();
        btnV21 = new javax.swing.JButton();
        btnH21 = new javax.swing.JButton();
        btnV22 = new javax.swing.JButton();
        btnH22 = new javax.swing.JButton();
        btnV23 = new javax.swing.JButton();
        btnH23 = new javax.swing.JButton();
        btnV24 = new javax.swing.JButton();
        btnH24 = new javax.swing.JButton();
        btnV25 = new javax.swing.JButton();
        btnV31 = new javax.swing.JButton();
        btnH31 = new javax.swing.JButton();
        btnV32 = new javax.swing.JButton();
        btnH32 = new javax.swing.JButton();
        btnV33 = new javax.swing.JButton();
        btnH33 = new javax.swing.JButton();
        btnV34 = new javax.swing.JButton();
        btnH34 = new javax.swing.JButton();
        btnV35 = new javax.swing.JButton();
        btnH41 = new javax.swing.JButton();
        btnV41 = new javax.swing.JButton();
        btnH42 = new javax.swing.JButton();
        btnV42 = new javax.swing.JButton();
        btnH43 = new javax.swing.JButton();
        btnV43 = new javax.swing.JButton();
        btnH44 = new javax.swing.JButton();
        btnV44 = new javax.swing.JButton();
        btnV45 = new javax.swing.JButton();
        btnH51 = new javax.swing.JButton();
        btnH52 = new javax.swing.JButton();
        btnH53 = new javax.swing.JButton();
        btnH54 = new javax.swing.JButton();
        Panel11 = new javax.swing.JPanel();
        Panel12 = new javax.swing.JPanel();
        Panel13 = new javax.swing.JPanel();
        Panel21 = new javax.swing.JPanel();
        Panel14 = new javax.swing.JPanel();
        Panel22 = new javax.swing.JPanel();
        Panel23 = new javax.swing.JPanel();
        Panel24 = new javax.swing.JPanel();
        Panel34 = new javax.swing.JPanel();
        Panel31 = new javax.swing.JPanel();
        Panel32 = new javax.swing.JPanel();
        Panel33 = new javax.swing.JPanel();
        Panel41 = new javax.swing.JPanel();
        Panel43 = new javax.swing.JPanel();
        Panel42 = new javax.swing.JPanel();
        Panel44 = new javax.swing.JPanel();
        panelJugador1 = new javax.swing.JPanel();
        labelIcon1 = new javax.swing.JLabel();
        botonCambiarColor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JLabel();
        txtPuntuacion1 = new javax.swing.JLabel();
        labelPuntaje1 = new javax.swing.JLabel();
        panelJugador2 = new javax.swing.JPanel();
        labelIcon2 = new javax.swing.JLabel();
        botonCambiarColor1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPuntuacion2 = new javax.swing.JLabel();
        txtNombre2 = new javax.swing.JLabel();
        labelPuntaje2 = new javax.swing.JLabel();
        panelJugador3 = new javax.swing.JPanel();
        labelIcon3 = new javax.swing.JLabel();
        botonCambiarColor2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPuntuacion3 = new javax.swing.JLabel();
        txtNombre3 = new javax.swing.JLabel();
        labelPuntaje3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelJugador4 = new javax.swing.JPanel();
        labelIcon4 = new javax.swing.JLabel();
        botonCambiarColor3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPuntuacion4 = new javax.swing.JLabel();
        txtNombre4 = new javax.swing.JLabel();
        labelPuntaje4 = new javax.swing.JLabel();
        labelTurno1 = new javax.swing.JLabel();
        labelTurno2 = new javax.swing.JLabel();
        labelTurno3 = new javax.swing.JLabel();
        labelTurno4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setName("Game"); // NOI18N

        btnAbandonar.setText("Abandonar partida");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });

        panelTablero.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout Panel11Layout = new javax.swing.GroupLayout(Panel11);
        Panel11.setLayout(Panel11Layout);
        Panel11Layout.setHorizontalGroup(
            Panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel11Layout.setVerticalGroup(
            Panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel12Layout = new javax.swing.GroupLayout(Panel12);
        Panel12.setLayout(Panel12Layout);
        Panel12Layout.setHorizontalGroup(
            Panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel12Layout.setVerticalGroup(
            Panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel13Layout = new javax.swing.GroupLayout(Panel13);
        Panel13.setLayout(Panel13Layout);
        Panel13Layout.setHorizontalGroup(
            Panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel13Layout.setVerticalGroup(
            Panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel21Layout = new javax.swing.GroupLayout(Panel21);
        Panel21.setLayout(Panel21Layout);
        Panel21Layout.setHorizontalGroup(
            Panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel21Layout.setVerticalGroup(
            Panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel14Layout = new javax.swing.GroupLayout(Panel14);
        Panel14.setLayout(Panel14Layout);
        Panel14Layout.setHorizontalGroup(
            Panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel14Layout.setVerticalGroup(
            Panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel22Layout = new javax.swing.GroupLayout(Panel22);
        Panel22.setLayout(Panel22Layout);
        Panel22Layout.setHorizontalGroup(
            Panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel22Layout.setVerticalGroup(
            Panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel23Layout = new javax.swing.GroupLayout(Panel23);
        Panel23.setLayout(Panel23Layout);
        Panel23Layout.setHorizontalGroup(
            Panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel23Layout.setVerticalGroup(
            Panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel24Layout = new javax.swing.GroupLayout(Panel24);
        Panel24.setLayout(Panel24Layout);
        Panel24Layout.setHorizontalGroup(
            Panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel24Layout.setVerticalGroup(
            Panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel34Layout = new javax.swing.GroupLayout(Panel34);
        Panel34.setLayout(Panel34Layout);
        Panel34Layout.setHorizontalGroup(
            Panel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel34Layout.setVerticalGroup(
            Panel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel31Layout = new javax.swing.GroupLayout(Panel31);
        Panel31.setLayout(Panel31Layout);
        Panel31Layout.setHorizontalGroup(
            Panel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel31Layout.setVerticalGroup(
            Panel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel32Layout = new javax.swing.GroupLayout(Panel32);
        Panel32.setLayout(Panel32Layout);
        Panel32Layout.setHorizontalGroup(
            Panel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel32Layout.setVerticalGroup(
            Panel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel33Layout = new javax.swing.GroupLayout(Panel33);
        Panel33.setLayout(Panel33Layout);
        Panel33Layout.setHorizontalGroup(
            Panel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel33Layout.setVerticalGroup(
            Panel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel41Layout = new javax.swing.GroupLayout(Panel41);
        Panel41.setLayout(Panel41Layout);
        Panel41Layout.setHorizontalGroup(
            Panel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel41Layout.setVerticalGroup(
            Panel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel43Layout = new javax.swing.GroupLayout(Panel43);
        Panel43.setLayout(Panel43Layout);
        Panel43Layout.setHorizontalGroup(
            Panel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel43Layout.setVerticalGroup(
            Panel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel42Layout = new javax.swing.GroupLayout(Panel42);
        Panel42.setLayout(Panel42Layout);
        Panel42Layout.setHorizontalGroup(
            Panel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel42Layout.setVerticalGroup(
            Panel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel44Layout = new javax.swing.GroupLayout(Panel44);
        Panel44.setLayout(Panel44Layout);
        Panel44Layout.setHorizontalGroup(
            Panel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel44Layout.setVerticalGroup(
            Panel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelTableroLayout = new javax.swing.GroupLayout(panelTablero);
        panelTablero.setLayout(panelTableroLayout);
        panelTableroLayout.setHorizontalGroup(
            panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableroLayout.createSequentialGroup()
                .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTableroLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addComponent(btnV11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(Panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnV12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btnV13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnV14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnV15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addComponent(btnV21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnV22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btnV23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(Panel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnV24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnV25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTableroLayout.createSequentialGroup()
                                        .addComponent(btnH21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(btnH23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTableroLayout.createSequentialGroup()
                                        .addComponent(btnH31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(btnH33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTableroLayout.createSequentialGroup()
                                        .addComponent(btnH41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH42, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(btnH43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH44, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTableroLayout.createSequentialGroup()
                                        .addComponent(btnH11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(btnH13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTableroLayout.createSequentialGroup()
                                        .addComponent(btnH51, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH52, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(btnH53, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnH54, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addComponent(btnV31, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(Panel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnV32, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btnV33, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnV34, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(Panel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnV35, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelTableroLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnV41, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Panel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnV42, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Panel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnV43, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Panel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnV44, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(Panel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnV45, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        panelTableroLayout.setVerticalGroup(
            panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableroLayout.createSequentialGroup()
                .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTableroLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnH14)
                            .addComponent(btnH13)
                            .addComponent(btnH12)
                            .addComponent(btnH11))
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnV12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnV13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnV14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnV11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnV15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(Panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnH24)
                            .addComponent(btnH23)
                            .addComponent(btnH22)
                            .addComponent(btnH21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnV22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnV23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnV24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnV21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnV25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnH34)
                            .addComponent(btnH33)
                            .addComponent(btnH32)
                            .addComponent(btnH31)))
                    .addGroup(panelTableroLayout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(Panel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTableroLayout.createSequentialGroup()
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnV32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnV33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnV34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnV31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnV35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnH44)
                            .addComponent(btnH43)
                            .addComponent(btnH42)
                            .addComponent(btnH41))
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelTableroLayout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(Panel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelTableroLayout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(Panel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelTableroLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(btnV41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableroLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Panel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnV42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnV43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnV45, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnV44, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelTableroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Panel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnH54)
                            .addComponent(btnH53)
                            .addComponent(btnH52)
                            .addComponent(btnH51)))
                    .addComponent(Panel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lineasHorizontales.add(new Linea(comodin,btnH11));

        btnH11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 0);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH12));

        btnH12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 1);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH13));

        btnH13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 2);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH14));

        btnH14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 3);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV11));

        btnV11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 0);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV12));

        btnV12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 1);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV13));

        btnV13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 2);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV14));

        btnV14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 3);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV15));

        btnV15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 4);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV21));

        btnV21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 5);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH21));

        btnH21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 4);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV22));

        btnV22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 6);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH22));

        btnH22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 5);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV23));

        btnV23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 7);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH23));

        btnH23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 6);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV24));

        btnV24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 8);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH24));

        btnH24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 7);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV25));

        btnV25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 9);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV31));

        btnV31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 10);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH31));

        btnH31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 8);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV32));

        btnV32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 11);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH32));

        btnH32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 9);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV33));

        btnV33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 12);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH33));

        btnH33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 10);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV34));

        btnV34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 13);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH34));

        btnH34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 11);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV35));

        btnV35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 14);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH41));

        btnH41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 12);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV41));

        btnV41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 15);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH42));

        btnH42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 13);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV42));

        btnV42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 16);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH43));

        btnH43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 14);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV43));

        btnV43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 17);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH44));

        btnH44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 15);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV44));

        btnV44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 18);
            }
        });
        lineasVerticales.add(new Linea(comodin,btnV45));

        btnV45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoVertical(jugadorUsuario, 19);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH51));

        btnH51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 16);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH52));

        btnH52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 17);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH53));

        btnH53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 18);
            }
        });
        lineasHorizontales.add(new Linea(comodin,btnH54));

        btnH54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClikeadoHorizontal(jugadorUsuario, 19);
            }
        });
        paneles.add(Panel11);
        paneles.add(Panel12);
        paneles.add(Panel13);
        paneles.add(Panel21);
        paneles.add(Panel14);
        paneles.add(Panel22);
        paneles.add(Panel23);
        paneles.add(Panel24);
        paneles.add(Panel34);
        paneles.add(Panel31);
        paneles.add(Panel32);
        paneles.add(Panel33);
        paneles.add(Panel41);
        paneles.add(Panel43);
        paneles.add(Panel42);
        paneles.add(Panel44);

        panelJugador1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        botonCambiarColor.setText("Color");
        botonCambiarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarColorActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre:");

        jLabel3.setText("Puntos:");

        labelPuntaje1.setText("0");

        javax.swing.GroupLayout panelJugador1Layout = new javax.swing.GroupLayout(panelJugador1);
        panelJugador1.setLayout(panelJugador1Layout);
        panelJugador1Layout.setHorizontalGroup(
            panelJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJugador1Layout.createSequentialGroup()
                .addComponent(labelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCambiarColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelJugador1Layout.createSequentialGroup()
                        .addGroup(panelJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelJugador1Layout.createSequentialGroup()
                                .addComponent(labelPuntaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPuntuacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelJugador1Layout.setVerticalGroup(
            panelJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJugador1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(labelPuntaje1))
                    .addComponent(txtPuntuacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(botonCambiarColor)
                .addContainerGap())
        );

        panelJugador2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        botonCambiarColor1.setText("Color");
        botonCambiarColor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarColor1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Puntos:");

        jLabel4.setText("Nombre:");

        labelPuntaje2.setText("0");

        javax.swing.GroupLayout panelJugador2Layout = new javax.swing.GroupLayout(panelJugador2);
        panelJugador2.setLayout(panelJugador2Layout);
        panelJugador2Layout.setHorizontalGroup(
            panelJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJugador2Layout.createSequentialGroup()
                .addComponent(labelIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCambiarColor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelJugador2Layout.createSequentialGroup()
                        .addGroup(panelJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelJugador2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(labelPuntaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPuntuacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelJugador2Layout.setVerticalGroup(
            panelJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIcon2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJugador2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJugador2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(panelJugador2Layout.createSequentialGroup()
                        .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPuntuacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPuntaje2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(botonCambiarColor1)
                .addContainerGap())
        );

        panelJugador3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        botonCambiarColor2.setText("Color");
        botonCambiarColor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarColor2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Puntos:");

        jLabel6.setText("Nombre:");

        labelPuntaje3.setText("0");

        javax.swing.GroupLayout panelJugador3Layout = new javax.swing.GroupLayout(panelJugador3);
        panelJugador3.setLayout(panelJugador3Layout);
        panelJugador3Layout.setHorizontalGroup(
            panelJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJugador3Layout.createSequentialGroup()
                .addComponent(labelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCambiarColor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelJugador3Layout.createSequentialGroup()
                        .addGroup(panelJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelJugador3Layout.createSequentialGroup()
                                .addComponent(labelPuntaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPuntuacion3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelJugador3Layout.setVerticalGroup(
            panelJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIcon3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJugador3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJugador3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(panelJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(labelPuntaje3)))
                    .addGroup(panelJugador3Layout.createSequentialGroup()
                        .addComponent(txtNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPuntuacion3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(botonCambiarColor2)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jugadores");
        jLabel1.setToolTipText("");

        panelJugador4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        botonCambiarColor3.setText("Color");
        botonCambiarColor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarColor3ActionPerformed(evt);
            }
        });

        jLabel9.setText("Puntos:");

        jLabel8.setText("Nombre:");

        labelPuntaje4.setText("0");

        javax.swing.GroupLayout panelJugador4Layout = new javax.swing.GroupLayout(panelJugador4);
        panelJugador4.setLayout(panelJugador4Layout);
        panelJugador4Layout.setHorizontalGroup(
            panelJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJugador4Layout.createSequentialGroup()
                .addComponent(labelIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCambiarColor3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelJugador4Layout.createSequentialGroup()
                        .addGroup(panelJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelJugador4Layout.createSequentialGroup()
                                .addComponent(labelPuntaje4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(txtPuntuacion4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 33, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelJugador4Layout.setVerticalGroup(
            panelJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIcon4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJugador4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJugador4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(panelJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(labelPuntaje4)))
                    .addGroup(panelJugador4Layout.createSequentialGroup()
                        .addComponent(txtNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPuntuacion4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(botonCambiarColor3)
                .addContainerGap())
        );

        labelTurno1.setText("¡Es tu turno!");

        labelTurno2.setText("¡Es tu turno!");

        labelTurno3.setText("¡Es tu turno!");

        labelTurno4.setText("¡Es tu turno!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTurno2)
                    .addComponent(labelTurno3)
                    .addComponent(labelTurno4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelJugador4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelJugador3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelJugador2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelJugador1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(labelTurno1)
                            .addGap(58, 58, 58)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addComponent(btnAbandonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTurno1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTurno2)
                        .addGap(3, 3, 3)
                        .addComponent(panelJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(labelTurno3)
                        .addGap(2, 2, 2)
                        .addComponent(panelJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTurno4)
                        .addGap(4, 4, 4)
                        .addComponent(panelJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbandonar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonarActionPerformed
        //Se cierra la ventana y se termina el programa
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].setPuntaje(0);            
        }
        ventana_Log.dispose();
        this.dispose();
        Principal principal = new Principal(jugadores[0]);
        principal.setVisible(true);
    }//GEN-LAST:event_btnAbandonarActionPerformed

    private void botonCambiarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarColorActionPerformed
        //Se abre la pestaña de Settings para escoger el color deseado del 
        //jugador 1
        Settings settings = new Settings(this,true,jugadores[0]);
        settings.setVisible(true);
        //Se le asigna el color escogido a el panel y todos los botones y 
        //paneles de el tablero
        panelJugador1.setBackground(jugadores[0].getColor());
        coloresBotones(jugadores[0]);
    }//GEN-LAST:event_botonCambiarColorActionPerformed

    private void botonCambiarColor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarColor1ActionPerformed
        //Se abre la pestaña de Settings para escoger el color deseado del 
        //jugador 2
        Settings settings = new Settings(this,true,jugadores[1]);
        settings.setVisible(true);
        //Se le asigna el color escogido a el panel y todos los botones y 
        //paneles de el tablero
        panelJugador2.setBackground(jugadores[1].getColor());
        coloresBotones(jugadores[1]);
    }//GEN-LAST:event_botonCambiarColor1ActionPerformed

    private void botonCambiarColor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarColor2ActionPerformed
        //Se abre la pestaña de Settings para escoger el color deseado del 
        //jugador 3
        Settings settings = new Settings(this,true,jugadores[2]);
        settings.setVisible(true);
        //Se le asigna el color escogido a el panel y todos los botones y 
        //paneles de el tablero
        panelJugador3.setBackground(jugadores[2].getColor());
        coloresBotones(jugadores[2]);
    }//GEN-LAST:event_botonCambiarColor2ActionPerformed

    private void botonCambiarColor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarColor3ActionPerformed
        //Se abre la pestaña de Settings para escoger el color deseado del 
        //jugador 4
        Settings settings = new Settings(this,true,jugadores[3]);
        settings.setVisible(true);
        //Se le asigna el color escogido a el panel y todos los botones y 
        //paneles de el tablero
        panelJugador4.setBackground(jugadores[3].getColor());
        coloresBotones(jugadores[3]);
    }//GEN-LAST:event_botonCambiarColor3ActionPerformed

   //Método que cambia de color a todos las líneas y páneles relacionados con 
    //el jugador por el color del mísmo jugador
    public void coloresBotones(Jugador jugador){
        //Itera por la lista de lineas
        for (int x = 0; x < 20; x++) {
            //Si la linea vertical contiene como atributo al jugador, este lo cambiará a
            //su color
            if(lineasVerticales.get(x).getJugador().equals(jugador)){
                lineasVerticales.get(x).getBoton().setBackground(jugador.getColor());
            }
            //Si la linea horizontal contiene como atributo al jugador, este lo cambiará a
            //su color
            if(lineasHorizontales.get(x).getJugador().equals(jugador)){
                lineasHorizontales.get(x).getBoton().setBackground(jugador.getColor());
            }
        }
        //Itera por la lista de cuadros
        for (int y = 0; y < cuadros.size(); y++) {
            //So es nulo no hará nada
            if(cuadros.get(y).getJugador()==null){
            //Si el cuadro contiene como atributo al jugador, este lo cambiará a
            //su color
            }else if(cuadros.get(y).getJugador().equals(jugador)){
                cuadros.get(y).getPanel().setBackground(jugador.getColor());
            }
        }
    }
    //Cuando un boton sea clickeado, se enviará la posición del botón en la 
    //lista y el Jugador que lo oprimió
    public void botonClikeadoVertical(Jugador jugador,int numero){
        //Estableceremos que el jugador no pintó
        boolean pinto = false;
        //Se asignará a la línea el jugador como atributo, se le cambiará de 
        //color al botón de la misma línea y se desactivará para que no pueda
        //ser pulsado de nuevo
        lineasVerticales.get(numero).setJugador(jugador);
        lineasVerticales.get(numero).getBoton().setBackground(jugador.getColor());
        lineasVerticales.get(numero).getBoton().setEnabled(false);
        //Itera por la lista de cuadros
        mostrarMensaje(mensajeClicLinea(jugador));
        for (int j = 0; j < cuadros.size(); j++) {
            //Si el cuadro tiene todas sus líneas coloreadas y no tiene color
            //se pintará del color del jugador actual
            if(cuadros.get(j).cuadroCompleto() && cuadros.get(j).getPanel().
                    getBackground().toString().equals("javax.swing.plaf.ColorUIResource[r=238,g=238,b=238]")){
                cuadros.get(j).getPanel().setBackground(jugador.getColor());
                cuadros.get(j).setJugador(jugador);
                //Se ejecutará el método que suma un puntaje al jugador enviado
                puntaje(jugador);
                //marcará que si pinto el jugador
                pinto=true;
            }
        }
        //Si el jugador no pinto, pasará turno al siguiente jugador
        if(pinto==false){
            siguienteTurno();
        }
        //Si el método identifica que ya no hay más cuadrados por llenar 
        //terminará la partida y anunciará al ganador, por último se saldrá 
        //del sistema
        if(finPartida()){
            partida.setEstado(TERMINADO);
            System.exit(0);
        }
        
    }
    public void botonClikeadoHorizontal(Jugador jugador,int numero){
        //Estableceremos que el jugador no pintó
        boolean pinto = false;
        //Se asignará a la línea el jugador como atributo, se le cambiará de 
        //color al botón de la misma línea y se desactivará para que no pueda
        //ser pulsado de nuevo
        lineasHorizontales.get(numero).setJugador(jugador);
        lineasHorizontales.get(numero).getBoton().setBackground(jugador.getColor());
        lineasHorizontales.get(numero).getBoton().setEnabled(false);
        //Itera por la lista de cuadros
        mostrarMensaje(mensajeClicLinea(jugador));
        for (int j = 0; j < cuadros.size(); j++) {
            //Si el cuadro tiene todas sus líneas coloreadas y no tiene color
            //se pintará del color del jugador actual
            if(cuadros.get(j).cuadroCompleto() && cuadros.get(j).getPanel().
                    getBackground().toString().equals("javax.swing.plaf.ColorUIResource[r=238,g=238,b=238]")){
                cuadros.get(j).getPanel().setBackground(jugador.getColor());
                cuadros.get(j).setJugador(jugador);
                //Se ejecutará el método que suma un puntaje al jugador enviado
                puntaje(jugador);
                //marcará que si pinto el jugador
                pinto=true;
            }
        }
        //Si el jugador no pinto, pasará turno al siguiente jugador
        if(pinto==false){
            siguienteTurno();
        }
        //Si el método identifica que ya no hay más cuadrados por llenar 
        //terminará la partida y anunciará al ganador, por último se saldrá 
        //del sistema
        if(finPartida()){
            partida.setEstado(TERMINADO);
            System.exit(0);
        }
        
    }
    //Este método regresa si la partida a terminado o no cuando ningún cuadro 
    //este sin colorear
    public boolean finPartida(){
        //Itera sobre la lista de cuadros
        for (int j = 0; j < cuadros.size()+1; j++) {
            //Si la lista llego a su final Creará a un jugador ganador
            if(j==cuadros.size()){
                Jugador ganador=jugadores[0];
                for (int i = 1; i < jugadores.length; i++) {
                    if(ganador.getPuntaje()<jugadores[i].getPuntaje()){
                        ganador=jugadores[i];
                    }
                }
                //Desplegará un mensaje con el nombre y el puntaje indicando que
                //jugador ganó
                JOptionPane.showMessageDialog(null, "El jugador "+ganador.getNombre()+" con "+ganador.getPuntaje()+" puntos ha ganado", "Fin de la Partida", JOptionPane.OK_OPTION);
                return true;
            //Si se encuentra un cuadro sin completar regresará que no se ha
            //terminado la partida
            }else if(!cuadros.get(j).cuadroCompleto()){
                return false;
            }
        }
        return false;
    }
    //Este método suma al jugador 1 punto a su puntaje y actualiza los puntajes de 
    //todos los jugadores
    public void puntaje(Jugador jugador){
        jugador.setPuntaje(jugador.getPuntaje()+1);
        labelPuntaje1.setText(String.valueOf(jugadores[0].getPuntaje()));
        labelPuntaje2.setText(String.valueOf(jugadores[1].getPuntaje()));
        labelPuntaje3.setText(String.valueOf(jugadores[2].getPuntaje()));
        labelPuntaje4.setText(String.valueOf(jugadores[3].getPuntaje()));
        mostrarMensaje(llenoUnCuadro(jugador));
    }

    //Este método realiza la procedencia de turnos entre jugadores y
    //permite ver las etiquetas del turno correspondiente
    
      
    public void siguienteTurno(){
        switch (turno) {
            //Si es 0 i será 1 y hará visible la etiqueta del jugador 2
            case 0:
                if(jugadores[1].isActivo()){
                    turno = 1;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(true);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(false);
                }else if(jugadores[2].isActivo()){
                    turno = 2;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(true);
                    turnos[3].setVisible(false);
                }else{
                    turno = 3;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(true);
                }
                break;
            //Si es 1 i será 2 y hará visible la etiqueta del jugador 3
            case 1:
                if(jugadores[2].isActivo()){
                    turno = 2;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(true);
                    turnos[3].setVisible(false);
                }else if(jugadores[3].isActivo()){
                    turno = 3;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(true);
                }else{
                    turno = 0;
                    turnos[0].setVisible(true);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(false);
                }
                break;
            //Si es 2 i será 3 y hará visible la etiqueta del jugador 4
            case 2:
                if(jugadores[3].isActivo()){
                    turno = 3;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(true);
                }else if(jugadores[0].isActivo()){
                    turno = 0;
                    turnos[0].setVisible(true);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(false);
                }else{
                    turno = 1;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(true);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(false);
                }
                break;
            //Si es 3 i será 0 y hará visible la etiqueta del jugador 1
            case 3:
                if(jugadores[0].isActivo()){
                    turno = 0;
                    turnos[0].setVisible(true);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(false);
                }else if(jugadores[1].isActivo()){
                    turno = 1;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(true);
                    turnos[2].setVisible(false);
                    turnos[3].setVisible(false);
                }else{
                    turno = 2;
                    turnos[0].setVisible(false);
                    turnos[1].setVisible(false);
                    turnos[2].setVisible(true);
                    turnos[3].setVisible(false);
                }
                break;
            default:
                break;
        }
        //El jugadorUsuario se pasará al siguiente jugador
        jugadorUsuario=jugadores[turno];
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel11;
    private javax.swing.JPanel Panel12;
    private javax.swing.JPanel Panel13;
    private javax.swing.JPanel Panel14;
    private javax.swing.JPanel Panel21;
    private javax.swing.JPanel Panel22;
    private javax.swing.JPanel Panel23;
    private javax.swing.JPanel Panel24;
    private javax.swing.JPanel Panel31;
    private javax.swing.JPanel Panel32;
    private javax.swing.JPanel Panel33;
    private javax.swing.JPanel Panel34;
    private javax.swing.JPanel Panel41;
    private javax.swing.JPanel Panel42;
    private javax.swing.JPanel Panel43;
    private javax.swing.JPanel Panel44;
    private javax.swing.JButton botonCambiarColor;
    private javax.swing.JButton botonCambiarColor1;
    private javax.swing.JButton botonCambiarColor2;
    private javax.swing.JButton botonCambiarColor3;
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JButton btnH11;
    private javax.swing.JButton btnH12;
    private javax.swing.JButton btnH13;
    private javax.swing.JButton btnH14;
    private javax.swing.JButton btnH21;
    private javax.swing.JButton btnH22;
    private javax.swing.JButton btnH23;
    private javax.swing.JButton btnH24;
    private javax.swing.JButton btnH31;
    private javax.swing.JButton btnH32;
    private javax.swing.JButton btnH33;
    private javax.swing.JButton btnH34;
    private javax.swing.JButton btnH41;
    private javax.swing.JButton btnH42;
    private javax.swing.JButton btnH43;
    private javax.swing.JButton btnH44;
    private javax.swing.JButton btnH51;
    private javax.swing.JButton btnH52;
    private javax.swing.JButton btnH53;
    private javax.swing.JButton btnH54;
    private javax.swing.JButton btnV11;
    private javax.swing.JButton btnV12;
    private javax.swing.JButton btnV13;
    private javax.swing.JButton btnV14;
    private javax.swing.JButton btnV15;
    private javax.swing.JButton btnV21;
    private javax.swing.JButton btnV22;
    private javax.swing.JButton btnV23;
    private javax.swing.JButton btnV24;
    private javax.swing.JButton btnV25;
    private javax.swing.JButton btnV31;
    private javax.swing.JButton btnV32;
    private javax.swing.JButton btnV33;
    private javax.swing.JButton btnV34;
    private javax.swing.JButton btnV35;
    private javax.swing.JButton btnV41;
    private javax.swing.JButton btnV42;
    private javax.swing.JButton btnV43;
    private javax.swing.JButton btnV44;
    private javax.swing.JButton btnV45;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelIcon1;
    private javax.swing.JLabel labelIcon2;
    private javax.swing.JLabel labelIcon3;
    private javax.swing.JLabel labelIcon4;
    private javax.swing.JLabel labelPuntaje1;
    private javax.swing.JLabel labelPuntaje2;
    private javax.swing.JLabel labelPuntaje3;
    private javax.swing.JLabel labelPuntaje4;
    private javax.swing.JLabel labelTurno1;
    private javax.swing.JLabel labelTurno2;
    private javax.swing.JLabel labelTurno3;
    private javax.swing.JLabel labelTurno4;
    private javax.swing.JPanel panelJugador1;
    private javax.swing.JPanel panelJugador2;
    private javax.swing.JPanel panelJugador3;
    private javax.swing.JPanel panelJugador4;
    private javax.swing.JPanel panelTablero;
    private javax.swing.JLabel txtNombre1;
    private javax.swing.JLabel txtNombre2;
    private javax.swing.JLabel txtNombre3;
    private javax.swing.JLabel txtNombre4;
    private javax.swing.JLabel txtPuntuacion1;
    private javax.swing.JLabel txtPuntuacion2;
    private javax.swing.JLabel txtPuntuacion3;
    private javax.swing.JLabel txtPuntuacion4;
    // End of variables declaration//GEN-END:variables
}
