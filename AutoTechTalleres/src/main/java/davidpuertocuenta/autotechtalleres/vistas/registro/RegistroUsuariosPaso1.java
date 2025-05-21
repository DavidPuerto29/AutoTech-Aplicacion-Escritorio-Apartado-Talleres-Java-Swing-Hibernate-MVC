/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.vistas.registro;

import static davidpuertocuenta.autotechtalleres.cartografia.CifradoSHA256.cifrarContraseña;
import static davidpuertocuenta.autotechtalleres.cartografia.CifradoSHA256.generarRandomizador;
import davidpuertocuenta.autotechtalleres.clases.UsuariosTalleres;
import davidpuertocuenta.autotechtalleres.controladores.RegistroControlador;
import static davidpuertocuenta.autotechtalleres.dao.UsuariosDAO.obtenerUsuarioPorUsuarioSql;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


/**
 *
 * @author David Puerto Cuenca
 */
public class RegistroUsuariosPaso1 extends javax.swing.JFrame {
    private UsuariosTalleres usuario;
    private boolean aceptacionTerminos = false;
    private RegistroControlador controlador = new RegistroControlador();
    /**
     * Creates new form RegistroClientesView1
     */
    public RegistroUsuariosPaso1() {
        initComponents();
        reiniciarEtiquetas();
        setExtendedState(RegistroUsuariosPaso1.MAXIMIZED_BOTH);
        
        //Listener para poder pasar al siguiente paso pulsando enter desde los textFields.
         ActionListener finalizarRegistroListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarRegistro();
            }
         };

        fieldUsuario.addActionListener(finalizarRegistroListener);
        fieldCorreo.addActionListener(finalizarRegistroListener);
        fieldContrasena.addActionListener(finalizarRegistroListener);
        fieldContrasenaVerificar.addActionListener(finalizarRegistroListener);
    }

     private void reiniciarEtiquetas(){
        textoErrorUsuario.setVisible(false);
        textoErrorContrasena.setVisible(false);
        textoErrorContrasena1.setVisible(false);
        textoErrorCorreoElectronico.setVisible(false);
        textoErrorTerminos.setVisible(false);
        this.revalidate(); 
        this.repaint(); 
    }
    
    private boolean registrarCliente(){
        reiniciarEtiquetas();
        boolean formatoCorrecto = true;
        
        //Comprobación de que el usuario no esta ya en uso.
        if(obtenerUsuarioPorUsuarioSql(fieldUsuario.getText()) != null){
            formatoCorrecto = false;
                textoErrorUsuario.setText("Usuario ya en uso.");
                    textoErrorUsuario.setVisible(true);            
        }
        
        //Comprobación de que el campo usuario no esta vacio.
        if(fieldUsuario.getText().isEmpty()){
            formatoCorrecto = false;
                textoErrorUsuario.setText("Debe introducir un usuario.");
                    textoErrorUsuario.setVisible(true);
        }
        
        //Comprobación de que las dos contraseñas coincidan
        if(!Arrays.equals(fieldContrasena.getPassword(), fieldContrasenaVerificar.getPassword())){
           formatoCorrecto = false;
               textoErrorContrasena.setText("Las contraseñas no coinciden.");
                    textoErrorContrasena.setVisible(true);
                        textoErrorContrasena1.setText("Las contraseñas no coinciden.");
                            textoErrorContrasena1.setVisible(true);
                    
        }
        
        //Comprobación de que las dos contraseñas no esten vacias.
        if(String.valueOf(fieldContrasena.getPassword()).isEmpty() && String.valueOf(fieldContrasenaVerificar.getPassword()).isEmpty()){
           formatoCorrecto = false;
               textoErrorContrasena.setText("Debe introducir una contraseña.");
                    textoErrorContrasena.setVisible(true); 
                        textoErrorContrasena1.setText("Debe introducir una contraseña.");
                            textoErrorContrasena1.setVisible(true); 
        }
        
        //Comprobación de que el correo electronico tenga el formato requerido.
        if (!fieldCorreo.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            formatoCorrecto = false;
                textoErrorCorreoElectronico.setVisible(true);
                    textoErrorCorreoElectronico.setText("Introduzca un correo electrónico valido.");
        }
        
        //Comprobación de que el correo electronico no este vacio.
        if(fieldCorreo.getText().isEmpty()){
            formatoCorrecto = false;
                textoErrorCorreoElectronico.setVisible(true);
                    textoErrorCorreoElectronico.setText("Debe introducir un correo electrónico.");
        }
        
        //Comprobación de que el usuario haya aceptado los terminos.
        if(aceptacionTerminos == false){
            textoErrorTerminos.setVisible(true);
        }
        
        if(formatoCorrecto){
            String randormizador = generarRandomizador();
            char[] contasenaChar = fieldContrasena.getPassword();
                //Se guardan los datos obtenidos en la variable global.
                usuario = new UsuariosTalleres(fieldUsuario.getText(),cifrarContraseña(String.valueOf(contasenaChar), randormizador),randormizador,null, null, null, fieldCorreo.getText(), null, null, false,null);
                    //Se limpia el array para aumentar la seguridad.
                    java.util.Arrays.fill(contasenaChar, '\0');    
                        return true;
        }else{
            return false;
        }
    }
    
    private void finalizarRegistro(){
        if(registrarCliente() && aceptacionTerminos == true){
            controlador.vistaRegistroPasoDos(this, usuario);
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
        java.awt.GridBagConstraints gridBagConstraints;

        fieldUsuario = new javax.swing.JTextField();
        textoErrorUsuario = new javax.swing.JLabel();
        fieldContrasena = new javax.swing.JPasswordField();
        fieldContrasenaVerificar = new javax.swing.JPasswordField();
        textoErrorContrasena = new javax.swing.JLabel();
        fieldCorreo = new javax.swing.JTextField();
        textoErrorCorreoElectronico = new javax.swing.JLabel();
        labelUsuario1 = new javax.swing.JLabel();
        labelContrasena = new javax.swing.JLabel();
        labelCorreoElectronico = new javax.swing.JLabel();
        textoErrorContrasena1 = new javax.swing.JLabel();
        botonCancelar = new javax.swing.JButton();
        botonContinuar = new javax.swing.JButton();
        checkTerminosYCondiciones = new javax.swing.JCheckBox();
        textoErrorTerminos = new javax.swing.JLabel();
        labelContrasena2 = new javax.swing.JLabel();
        labelIniciarSesion = new javax.swing.JLabel();
        fondoCabecera = new javax.swing.JLabel();
        fondoLogin = new javax.swing.JLabel();
        fondoPantalla = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro Empleado Paso 1");
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        fieldUsuario.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.ipadx = 186;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 920, 0, 0);
        getContentPane().add(fieldUsuario, gridBagConstraints);

        textoErrorUsuario.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorUsuario.setText("Usuario ya en uso.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 920, 0, 0);
        getContentPane().add(textoErrorUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.ipadx = 186;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 920, 0, 0);
        getContentPane().add(fieldContrasena, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.ipadx = 186;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 920, 0, 0);
        getContentPane().add(fieldContrasenaVerificar, gridBagConstraints);

        textoErrorContrasena.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorContrasena.setText("Las contraseñas no coinciden.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 920, 0, 0);
        getContentPane().add(textoErrorContrasena, gridBagConstraints);

        fieldCorreo.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.ipadx = 186;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 920, 0, 0);
        getContentPane().add(fieldCorreo, gridBagConstraints);

        textoErrorCorreoElectronico.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorCorreoElectronico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorCorreoElectronico.setText("Debe introducir un correo electrónico.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 20;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 920, 0, 0);
        getContentPane().add(textoErrorCorreoElectronico, gridBagConstraints);

        labelUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        labelUsuario1.setText("Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 920, 0, 0);
        getContentPane().add(labelUsuario1, gridBagConstraints);

        labelContrasena.setForeground(new java.awt.Color(255, 255, 255));
        labelContrasena.setText("Contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 920, 0, 0);
        getContentPane().add(labelContrasena, gridBagConstraints);

        labelCorreoElectronico.setForeground(new java.awt.Color(255, 255, 255));
        labelCorreoElectronico.setText("Correo Electronico");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 920, 0, 0);
        getContentPane().add(labelCorreoElectronico, gridBagConstraints);

        textoErrorContrasena1.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorContrasena1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorContrasena1.setText("Las contraseñas no coinciden.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 920, 0, 0);
        getContentPane().add(textoErrorContrasena1, gridBagConstraints);

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 0, 0, 0);
        getContentPane().add(botonCancelar, gridBagConstraints);

        botonContinuar.setText("Continuar");
        botonContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonContinuarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 44, 0, 0);
        getContentPane().add(botonContinuar, gridBagConstraints);

        checkTerminosYCondiciones.setBackground(new java.awt.Color(0, 0, 0));
        checkTerminosYCondiciones.setForeground(new java.awt.Color(255, 255, 255));
        checkTerminosYCondiciones.setText("Términos y condiciones de uso");
        checkTerminosYCondiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTerminosYCondicionesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 0, 0, 0);
        getContentPane().add(checkTerminosYCondiciones, gridBagConstraints);

        textoErrorTerminos.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorTerminos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorTerminos.setText("Debe aceptar los términos.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = -2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        getContentPane().add(textoErrorTerminos, gridBagConstraints);

        labelContrasena2.setForeground(new java.awt.Color(255, 255, 255));
        labelContrasena2.setText("Contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 920, 0, 0);
        getContentPane().add(labelContrasena2, gridBagConstraints);

        labelIniciarSesion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        labelIniciarSesion.setText("[●○] Paso 1: Crear cuenta(Con mas estilo)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 30;
        gridBagConstraints.ipadx = 76;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(130, 840, 0, 0);
        getContentPane().add(labelIniciarSesion, gridBagConstraints);

        fondoCabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stiles/cabecera_prov.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 28;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = -1276;
        gridBagConstraints.ipady = -82;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(130, 830, 0, 0);
        getContentPane().add(fondoCabecera, gridBagConstraints);

        fondoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stiles/fondo_login_prov .jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 29;
        gridBagConstraints.gridheight = 18;
        gridBagConstraints.ipadx = -2484;
        gridBagConstraints.ipady = -3282;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(90, 780, 0, 0);
        getContentPane().add(fondoLogin, gridBagConstraints);

        fondoPantalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stiles/fondo_prov.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 31;
        gridBagConstraints.gridheight = 19;
        gridBagConstraints.ipadx = 220;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(fondoPantalla, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        controlador.vistaLoginClientes(this);
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonContinuarActionPerformed
        finalizarRegistro();
    }//GEN-LAST:event_botonContinuarActionPerformed

    private void checkTerminosYCondicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTerminosYCondicionesActionPerformed
        if(controlador.vistaTerminosCondiciones(this)){
            checkTerminosYCondiciones.setSelected(true);
                aceptacionTerminos = true;
                    textoErrorTerminos.setVisible(false);
        }else{
            checkTerminosYCondiciones.setSelected(false);
                aceptacionTerminos = false;
        }
    }//GEN-LAST:event_checkTerminosYCondicionesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuariosPaso1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuariosPaso1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuariosPaso1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuariosPaso1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroUsuariosPaso1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonContinuar;
    private javax.swing.JCheckBox checkTerminosYCondiciones;
    private javax.swing.JPasswordField fieldContrasena;
    private javax.swing.JPasswordField fieldContrasenaVerificar;
    private javax.swing.JTextField fieldCorreo;
    private javax.swing.JTextField fieldUsuario;
    private javax.swing.JLabel fondoCabecera;
    private javax.swing.JLabel fondoLogin;
    private javax.swing.JLabel fondoPantalla;
    private javax.swing.JLabel labelContrasena;
    private javax.swing.JLabel labelContrasena2;
    private javax.swing.JLabel labelCorreoElectronico;
    private javax.swing.JLabel labelIniciarSesion;
    private javax.swing.JLabel labelUsuario1;
    private javax.swing.JLabel textoErrorContrasena;
    private javax.swing.JLabel textoErrorContrasena1;
    private javax.swing.JLabel textoErrorCorreoElectronico;
    private javax.swing.JLabel textoErrorTerminos;
    private javax.swing.JLabel textoErrorUsuario;
    // End of variables declaration//GEN-END:variables
}
