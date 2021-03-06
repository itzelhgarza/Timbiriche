/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import Dominio.Factory;
import Dominio.Jugador;
import interfaces.ICliente;
import java.util.Set;





/**
 *
 * @author itzel
 */
public class Lobby extends javax.swing.JFrame {
    //Instancia de la fábrica
    Factory fabrica = new Factory();
    
    private static Lobby instance;
    //Se crea a 4 jugadores para su uso futuro
    Jugador jugador1;
    Jugador jugador2= fabrica.crearJugador();
    Jugador jugador3= fabrica.crearJugador();
    Jugador jugador4= fabrica.crearJugador();
    //Se crea una lista con 4 espacios para los 4 jugadores
    Jugador jugadores[] = new Jugador[4];

    //Se inicializan las siguiente varaibles
    private int votos;
    private String ip;
    private int port;
    private ICliente sck;
    
    /**
     * Creates new form Lobby
     */
    public Lobby(){}
    public Lobby(Jugador jugador) {
        initComponents();
        btnEmpezar.setEnabled(false);
        //Se centra la pantalla
        this.setLocationRelativeTo(null);
        //Se establece el jugador del usuario al
        this.jugador1=jugador;
        //Se asignan los jugadores al arreglo
        jugadores[0]=jugador1;
        jugadores[1]=jugador2;
        jugadores[2]=jugador3;
        jugadores[3]=jugador4;
        //Se establecen los nombres a cada jugador
        jugadores[1].setNombre("Ian");
        jugadores[2].setNombre("Fabián");
        jugadores[3].setNombre("David");
        //Se ilusta el icono y el nombre de cada jugador en la pantalla
        labelIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource(jugador1.getIcono().toString())));
        labelNombre1.setText(jugador1.getNombre());
        labelIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/avatar2R.png")));
        labelNombre2.setText(jugadores[1].getNombre());
        labelIcon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/avatar3R.png")));
        labelNombre3.setText(jugadores[2].getNombre());
        labelIcon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/avatar1R.png")));
        labelNombre4.setText(jugadores[3].getNombre());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        btnEmpezar = new javax.swing.JButton();
        panelLobbyJugador1 = new javax.swing.JPanel();
        labelIcon1 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        labelNombre1 = new javax.swing.JLabel();
        btnVotar0 = new javax.swing.JButton();
        panelLobbyJugador2 = new javax.swing.JPanel();
        labelIcon2 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        labelNombre2 = new javax.swing.JLabel();
        btnVotar1 = new javax.swing.JButton();
        panelLobbyJugador3 = new javax.swing.JPanel();
        labelIcon3 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        labelNombre3 = new javax.swing.JLabel();
        btnVotar2 = new javax.swing.JButton();
        panelLobbyJugador4 = new javax.swing.JPanel();
        labelIcon4 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        labelNombre4 = new javax.swing.JLabel();
        btnVotar3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelVotos = new javax.swing.JLabel();
        contenedor_AreaLog = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        area_TextLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setName("Lobby"); // NOI18N

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnEmpezar.setText("Empezar partida");
        btnEmpezar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpezarActionPerformed(evt);
            }
        });

        panelLobbyJugador1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label1.setText("Nombre:");

        btnVotar0.setText("Votar Empezar Partida");
        btnVotar0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotar0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLobbyJugador1Layout = new javax.swing.GroupLayout(panelLobbyJugador1);
        panelLobbyJugador1.setLayout(panelLobbyJugador1Layout);
        panelLobbyJugador1Layout.setHorizontalGroup(
            panelLobbyJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLobbyJugador1Layout.createSequentialGroup()
                .addComponent(labelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLobbyJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLobbyJugador1Layout.createSequentialGroup()
                        .addComponent(label1)
                        .addGap(2, 2, 2)
                        .addComponent(labelNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnVotar0))
                .addContainerGap())
        );
        panelLobbyJugador1Layout.setVerticalGroup(
            panelLobbyJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLobbyJugador1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLobbyJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVotar0)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        panelLobbyJugador2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label2.setText("Nombre:");

        btnVotar1.setText("Votar Empezar Partida");
        btnVotar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLobbyJugador2Layout = new javax.swing.GroupLayout(panelLobbyJugador2);
        panelLobbyJugador2.setLayout(panelLobbyJugador2Layout);
        panelLobbyJugador2Layout.setHorizontalGroup(
            panelLobbyJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLobbyJugador2Layout.createSequentialGroup()
                .addComponent(labelIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLobbyJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLobbyJugador2Layout.createSequentialGroup()
                        .addComponent(label2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNombre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLobbyJugador2Layout.createSequentialGroup()
                        .addComponent(btnVotar1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLobbyJugador2Layout.setVerticalGroup(
            panelLobbyJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIcon2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLobbyJugador2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLobbyJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVotar1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelLobbyJugador3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label3.setText("Nombre:");

        btnVotar2.setText("Votar Empezar Partida");
        btnVotar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLobbyJugador3Layout = new javax.swing.GroupLayout(panelLobbyJugador3);
        panelLobbyJugador3.setLayout(panelLobbyJugador3Layout);
        panelLobbyJugador3Layout.setHorizontalGroup(
            panelLobbyJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLobbyJugador3Layout.createSequentialGroup()
                .addComponent(labelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLobbyJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLobbyJugador3Layout.createSequentialGroup()
                        .addComponent(label3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNombre3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLobbyJugador3Layout.createSequentialGroup()
                        .addComponent(btnVotar2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLobbyJugador3Layout.setVerticalGroup(
            panelLobbyJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIcon3, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
            .addGroup(panelLobbyJugador3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLobbyJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVotar2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelLobbyJugador4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label4.setText("Nombre:");

        btnVotar3.setText("Votar Empezar Partida");
        btnVotar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLobbyJugador4Layout = new javax.swing.GroupLayout(panelLobbyJugador4);
        panelLobbyJugador4.setLayout(panelLobbyJugador4Layout);
        panelLobbyJugador4Layout.setHorizontalGroup(
            panelLobbyJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLobbyJugador4Layout.createSequentialGroup()
                .addComponent(labelIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLobbyJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLobbyJugador4Layout.createSequentialGroup()
                        .addComponent(label4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNombre4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLobbyJugador4Layout.createSequentialGroup()
                        .addComponent(btnVotar3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLobbyJugador4Layout.setVerticalGroup(
            panelLobbyJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIcon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLobbyJugador4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLobbyJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4))
                .addGap(18, 18, 18)
                .addComponent(btnVotar3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Jugadores Que Votaron:");

        labelVotos.setText("0");

        scroll.setEnabled(false);

        area_TextLog.setEditable(false);
        area_TextLog.setColumns(20);
        area_TextLog.setRows(5);
        scroll.setViewportView(area_TextLog);

        javax.swing.GroupLayout contenedor_AreaLogLayout = new javax.swing.GroupLayout(contenedor_AreaLog);
        contenedor_AreaLog.setLayout(contenedor_AreaLogLayout);
        contenedor_AreaLogLayout.setHorizontalGroup(
            contenedor_AreaLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedor_AreaLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        contenedor_AreaLogLayout.setVerticalGroup(
            contenedor_AreaLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedor_AreaLogLayout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelLobbyJugador3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelLobbyJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelLobbyJugador4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelLobbyJugador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contenedor_AreaLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEmpezar)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelVotos)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLobbyJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelLobbyJugador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelLobbyJugador4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLobbyJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(labelVotos))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEmpezar)
                            .addComponent(btnSalir)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contenedor_AreaLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        //Se abre la pantalla principal si se sale de la ventana Lobby con el 
        //jugador principal
        Principal principal = new Principal(jugador1);
        principal.setVisible(true);
        //Se cierra la pestaña
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEmpezarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpezarActionPerformed
        Game game = new Game(jugadores);
        game.setVisible(true);
        //Se cierra la pestaña Lobby
        this.dispose();
    }//GEN-LAST:event_btnEmpezarActionPerformed
    public void desbloquearEmpezar(){
        if (labelVotos.getText().equals("2")||labelVotos.getText().equals("3")||labelVotos.getText().equals("4")) {
            //Se abre la pestaña Game
            btnEmpezar.setEnabled(true);
        }else{
            btnEmpezar.setEnabled(false);
        }
    }
    private void btnVotar0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotar0ActionPerformed
        // TODO add your handling code here:
        if (this.btnVotar0.getText().equals("Votar Empezar Partida")) {
            votos++;
            labelVotos.setText(String.valueOf(votos));
            mostrarMensaje("Un jugador " + jugadores[0].getNombre() + " ha votado");
            this.btnVotar0.setText("Cancelar Voto");
            jugadores[0].setActivo(true);
            
        } else if (this.btnVotar0.getText().equals("Cancelar Voto")) {
            votos--;
            labelVotos.setText(String.valueOf(votos));
            mostrarMensaje("Un jugador " + jugadores[0].getNombre() + " ha cancelado su voto");
            this.btnVotar0.setText("Votar Empezar Partida");
            jugadores[0].setActivo(false);
        }
        desbloquearEmpezar();
    }//GEN-LAST:event_btnVotar0ActionPerformed

    private void btnVotar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotar1ActionPerformed
        // TODO add your handling code here:
        if (this.btnVotar1.getText().equals("Votar Empezar Partida")) {
            votos++;
            labelVotos.setText(String.valueOf(votos));
            mostrarMensaje("Un jugador "+jugadores[1].getNombre()+" ha votado");
            this.btnVotar1.setText("Cancelar Voto");
            jugadores[1].setActivo(true);
        }else if (this.btnVotar1.getText().equals("Cancelar Voto")) {
            votos--;
            labelVotos.setText(String.valueOf(votos));
            mostrarMensaje("Un jugador "+jugadores[1].getNombre()+" ha cancelado su voto");
            this.btnVotar1.setText("Votar Empezar Partida");
            jugadores[1].setActivo(false);
        }
        desbloquearEmpezar();
    }//GEN-LAST:event_btnVotar1ActionPerformed

    private void btnVotar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotar2ActionPerformed
        // TODO add your handling code here:
        if (this.btnVotar2.getText().equals("Votar Empezar Partida")) {
            votos++;
            labelVotos.setText(String.valueOf(votos));
            mostrarMensaje("Un jugador "+jugadores[2].getNombre()+" ha votado");
            this.btnVotar2.setText("Cancelar Voto");
            jugadores[2].setActivo(true);
        }else if (this.btnVotar2.getText().equals("Cancelar Voto")) {
            votos--;
            labelVotos.setText(String.valueOf(votos));
            mostrarMensaje("Un jugador "+jugadores[2].getNombre()+" ha cancelado su voto");
            this.btnVotar2.setText("Votar Empezar Partida");
            jugadores[2].setActivo(false);
        }
        desbloquearEmpezar();
    }//GEN-LAST:event_btnVotar2ActionPerformed

    private void btnVotar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotar3ActionPerformed
        // TODO add your handling code here:
        if (this.btnVotar3.getText().equals("Votar Empezar Partida")) {
            votos++;
            labelVotos.setText(String.valueOf(votos));
            mostrarMensaje("Un jugador "+jugadores[3].getNombre()+" ha votado");
            this.btnVotar3.setText("Cancelar Voto");
            jugadores[3].setActivo(true);
        }else if (this.btnVotar3.getText().equals("Cancelar Voto")) {
            votos--;
            labelVotos.setText(String.valueOf(votos));
            mostrarMensaje("Un jugador "+jugadores[3].getNombre()+" ha cancelado su voto");
            this.btnVotar3.setText("Votar Empezar Partida");
            jugadores[3].setActivo(false);
        }
        desbloquearEmpezar();
    }//GEN-LAST:event_btnVotar3ActionPerformed
    
    //Obtiene una instancia de lobby
    public static Lobby getInstance() {
        if (instance == null) {
            instance = new Lobby();
        }
        return instance;
    }
    //Escribe el mensaje puesto en parámetros dentro de la caja de mensajes
    public void mostrarMensaje(String mensaje){
        area_TextLog.append(mensaje+"\n");
    }
    //Suma los votos y los muestra en la pantalla

    //Reinicia los votos en pantalla
    public void reiniciarVotos(){
        votos=0;
    }
    //Crea una conexión con el servidor donde se mandan los datos de el jugador 
    //que ingresa gracias a la aportación de su ip y puerto
    public boolean ejecutarConexion(Jugador jugador, String ip, int port) {
        this.jugador1 = jugador;

        this.ip = ip;
        this.port = port;

        if (sck.conectarAlServidor(this.ip, this.port)) {
            System.out.println("Conectado con exito");
            sck.enviarAlServidor(this.jugador1);
            sck.escucharAlServidor();
            return true;
        } else {
            System.out.println("No se pudo conectar con el servidor");
            return false;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_TextLog;
    private javax.swing.JButton btnEmpezar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVotar0;
    private javax.swing.JButton btnVotar1;
    private javax.swing.JButton btnVotar2;
    private javax.swing.JButton btnVotar3;
    private javax.swing.JPanel contenedor_AreaLog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel labelIcon1;
    private javax.swing.JLabel labelIcon2;
    private javax.swing.JLabel labelIcon3;
    private javax.swing.JLabel labelIcon4;
    private javax.swing.JLabel labelNombre1;
    private javax.swing.JLabel labelNombre2;
    private javax.swing.JLabel labelNombre3;
    private javax.swing.JLabel labelNombre4;
    private javax.swing.JLabel labelVotos;
    private javax.swing.JPanel panelLobbyJugador1;
    private javax.swing.JPanel panelLobbyJugador2;
    private javax.swing.JPanel panelLobbyJugador3;
    private javax.swing.JPanel panelLobbyJugador4;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables


}
