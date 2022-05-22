/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Dominio.*;
import javax.swing.JOptionPane;
/**
 *
 * @author itzel
 */
public class Login extends javax.swing.JFrame {
    Factory fabrica = new Factory();
    Jugador jugador = fabrica.crearJugador();
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    //Constructor con un jugador en parámetros
    public Login(Jugador jugador) {
        initComponents();
        this.setLocationRelativeTo(null);
        //Iguala el jugador de parámetros a el jugador de la clase
        this.jugador=jugador;
        //Pone el nombre del jugador en parámetros
        campoNombre.setText(jugador.getNombre());
        //Pone el icono del jugador en parámetros
        labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource(jugador.getIcono().toString())));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        labelImagen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuConfiguracion = new javax.swing.JMenu();
        cambiarIcono = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Usuario");

        campoNombre.setToolTipText("");
        campoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreActionPerformed(evt);
            }
        });

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        menuConfiguracion.setText("Cambiar Icono");

        cambiarIcono.setText("Cambiar Icono");
        cambiarIcono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarIconoActionPerformed(evt);
            }
        });
        menuConfiguracion.add(cambiarIcono);

        jMenuBar1.add(menuConfiguracion);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresar)
                    .addComponent(btnCerrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombreActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        //Si el ícono es nulo se enviará un mensaje al usuario, avisándole que
        //se necesita proporcionar uno
        if(labelImagen.getIcon()==null){
            JOptionPane.showMessageDialog(null, "No se ha escogido un Icono de jugador", "Error", JOptionPane.ERROR_MESSAGE);
        //Si el nombre es nulo se enviará un mensaje al usuario, avisándole que
        //se necesita proporcionar uno
        }else if(campoNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No se ha introducido un nombre de jugador", "Error", JOptionPane.ERROR_MESSAGE);
        //Si no pasa ninguno de estos se agrega el  nombre al jugador y abre la 
        //pestaña principal y cierra la pestaña LogIn
        }else{
        jugador.setNombre(campoNombre.getText());
        Principal principal = new Principal(jugador);
        principal.setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_btnIngresarActionPerformed
    //Termina el proceso
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed
    //Abre la pestaña de iconos para que el usuario escoja un icono 
    //presonalizado
    private void cambiarIconoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarIconoActionPerformed
        Iconos iconos = new Iconos(this,true,jugador);
        iconos.setVisible(true);
        labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource(jugador.getIcono().toString())));
    }//GEN-LAST:event_cambiarIconoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JMenuItem cambiarIcono;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JMenu menuConfiguracion;
    // End of variables declaration//GEN-END:variables
}
