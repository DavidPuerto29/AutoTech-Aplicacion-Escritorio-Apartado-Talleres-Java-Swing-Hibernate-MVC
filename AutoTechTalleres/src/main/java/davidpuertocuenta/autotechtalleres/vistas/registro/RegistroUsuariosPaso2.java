/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.vistas.registro;

import davidpuertocuenta.autotechtalleres.clases.Empleados;
import davidpuertocuenta.autotechtalleres.controladores.RegistroControlador;
import static davidpuertocuenta.autotechtalleres.dao.EmpleadosDAO.crearEmpleadoSql;
import static davidpuertocuenta.autotechtalleres.dao.UsuariosDAO.obtenerUsuarioPorDniSql;
import static davidpuertocuenta.autotechtalleres.util.Estilos.aplicarEstiloBoton;
import static davidpuertocuenta.autotechtalleres.util.Estilos.aplicarEstiloTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;


/**
 *
 * @author David Puerto Cuenca
 */
public class RegistroUsuariosPaso2 extends javax.swing.JFrame {
    private ImageIcon iconoError = new ImageIcon(getClass().getResource("/icons/error_prov.png"));
    private Empleados empleado;
    private RegistroControlador controlador = new RegistroControlador();
    /**
     * Creates new form RegistroClientesView2
     */
    public RegistroUsuariosPaso2() {
        initComponents();
        this.setLocationRelativeTo(null);
        reiniciarEtiquetas();
    }
    
    public RegistroUsuariosPaso2(Empleados usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        //Estilos FrontEnd
        aplicarEstiloBoton(botonCancelar);
        aplicarEstiloBoton(botonFinalizar);
        aplicarEstiloTextField(fieldNombre);
        aplicarEstiloTextField(fieldApellidos);
        aplicarEstiloTextField(fieldDni);
        aplicarEstiloTextField(fieldTelefono);
        aplicarEstiloTextField(fieldDireccion);
        
        reiniciarEtiquetas();
        this.empleado = usuario;
        
        //Listener para poder pasar al siguiente paso pulsando enter desde los textFields.
         ActionListener finalizarRegistroListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarRegistro();
            }
         };

        fieldNombre.addActionListener(finalizarRegistroListener);
        fieldApellidos.addActionListener(finalizarRegistroListener);
        fieldDni.addActionListener(finalizarRegistroListener);
        fieldTelefono.addActionListener(finalizarRegistroListener);
        fieldDireccion.addActionListener(finalizarRegistroListener);
        
    }

     private void reiniciarEtiquetas(){
        textoErrorDni.setText(" ");
        textoErrorDni.setIcon(null);
        textoErrorNombre.setText(" ");
        textoErrorNombre.setIcon(null);
        textoErrorApellidos.setText(" ");
        textoErrorApellidos.setIcon(null); 
        textoErrorTelefono.setText(" ");
        textoErrorTelefono.setIcon(null); 
        textoErrorDireccion.setText(" ");
        textoErrorDireccion.setIcon(null);
        this.revalidate(); 
        this.repaint(); 
    }
     
    private boolean registrarCliente(){
        reiniciarEtiquetas();
        boolean formatoCorrecto = true;
        
        //Comprobación de que el dni no esta ya en uso.
        if(obtenerUsuarioPorDniSql(fieldDni.getText()) != null){
            formatoCorrecto = false;
                textoErrorDni.setText("Dni ya en uso.");
                    textoErrorDni.setIcon(iconoError);  
        }
        
        //Comprobación de que el dni tenga el formato correcto.
        if (!fieldDni.getText().matches("^[0-9]{8}[A-Za-z]$")) {
            formatoCorrecto = false;
                textoErrorDni.setIcon(iconoError);  
                    textoErrorDni.setText("Debe introducir un dni valido.");
        }
        
        //Comprobación de que el dni no este vacio.
        if(fieldDni.getText().isEmpty()){
            formatoCorrecto = false;
                textoErrorDni.setIcon(iconoError);  
                    textoErrorDni.setText("Debe introducir un dni.");
        }
        
        //Comprobación de que el nombre no este vacio.
        if(fieldNombre.getText().isEmpty()){
            formatoCorrecto = false;
                textoErrorNombre.setIcon(iconoError);  
                    textoErrorNombre.setText("Debe introducir un nombre.");
        }
        
        //Comprobación de que los apellidos no esten vacios.
        if(fieldApellidos.getText().isEmpty()){
            formatoCorrecto = false;
                textoErrorApellidos.setIcon(iconoError);  
                    textoErrorApellidos.setText("Debe introducir los apellidos.");
        }
          
        //Comprobación de que el teléfono tenga el formato correcto. (123546789)
        if (!fieldTelefono.getText().trim().matches("\\d{9}") && !fieldTelefono.getText().trim().isEmpty()) {
            formatoCorrecto = false;
                textoErrorTelefono.setVisible(true);
                    textoErrorTelefono.setText("El formato no es el correcto.");         
        }
        
        //Comprobación de que el teléfono sean números y no letras.
        try {
            Integer.parseInt(fieldTelefono.getText()); 
        }catch (NumberFormatException e) {
            formatoCorrecto = false;
                textoErrorTelefono.setIcon(iconoError);  
                    textoErrorTelefono.setText("El teléfono no puede contener letras.");
        } 
        
        //Comprobación de que el teléfono no este vacio.
        if(fieldTelefono.getText().isEmpty()){
            formatoCorrecto = false;
                textoErrorTelefono.setIcon(iconoError);  
                    textoErrorTelefono.setText("Debe introducir un teléfono.");
        }
        
        //Comprobación de que la dirección no este vacio.
        if(fieldDireccion.getText().isEmpty()){
            formatoCorrecto = false;
                textoErrorDireccion.setIcon(iconoError);  
                    textoErrorDireccion.setText("Debe introducir una dirección.");
        }
        
        if(formatoCorrecto){
            empleado.setNombre(fieldNombre.getText());
            empleado.setApellidos(fieldApellidos.getText());
            empleado.setDni(fieldDni.getText().toUpperCase());
            empleado.setDireccion(fieldDireccion.getText());
            empleado.setNumeroTelefono(fieldTelefono.getText());
                        crearEmpleadoSql(empleado);
                                return true;
        }else{
            return false;
        }
    }
    
    private void finalizarRegistro(){
        if(registrarCliente()){
            controlador.vistaLoginClientesFinalizarRegistro(this);
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

        formularioRegistroPaso2 = new javax.swing.JPanel();
        fieldTelefono = new javax.swing.JTextField();
        fieldDireccion = new javax.swing.JTextField();
        textoErrorDireccion = new javax.swing.JLabel();
        textoErrorTelefono = new javax.swing.JLabel();
        fieldDni = new javax.swing.JTextField();
        textoErrorDni = new javax.swing.JLabel();
        fieldApellidos = new javax.swing.JTextField();
        textoErrorApellidos = new javax.swing.JLabel();
        textoErrorNombre = new javax.swing.JLabel();
        fieldNombre = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        labelApellidos = new javax.swing.JLabel();
        labelDni = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        labelDireccion = new javax.swing.JLabel();
        botonFinalizar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        labelIniciarSesion = new javax.swing.JLabel();
        fondoPantalla = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autotech - Talleres");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        formularioRegistroPaso2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fieldTelefono.setToolTipText("");
        formularioRegistroPaso2.add(fieldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 250, 40));

        fieldDireccion.setToolTipText("");
        formularioRegistroPaso2.add(fieldDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 250, 40));

        textoErrorDireccion.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorDireccion.setText("Debe introducir una dirección.");
        formularioRegistroPaso2.add(textoErrorDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 500, 250, -1));

        textoErrorTelefono.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorTelefono.setText("Debe introducir un teléfono.");
        formularioRegistroPaso2.add(textoErrorTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 250, -1));

        fieldDni.setToolTipText("");
        formularioRegistroPaso2.add(fieldDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 250, 40));

        textoErrorDni.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorDni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorDni.setText("El formato no es correcto.");
        formularioRegistroPaso2.add(textoErrorDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 250, -1));

        fieldApellidos.setToolTipText("");
        formularioRegistroPaso2.add(fieldApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 250, 40));

        textoErrorApellidos.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorApellidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorApellidos.setText("Debe introducir un apellido.");
        formularioRegistroPaso2.add(textoErrorApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 250, -1));

        textoErrorNombre.setForeground(new java.awt.Color(255, 0, 0));
        textoErrorNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error_prov.png"))); // NOI18N
        textoErrorNombre.setText("Debe introducir un nombre.");
        formularioRegistroPaso2.add(textoErrorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 250, -1));

        fieldNombre.setToolTipText("");
        fieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNombreActionPerformed(evt);
            }
        });
        formularioRegistroPaso2.add(fieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 250, 40));

        labelNombre.setForeground(new java.awt.Color(255, 255, 255));
        labelNombre.setText("Nombre");
        formularioRegistroPaso2.add(labelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 250, -1));

        labelApellidos.setForeground(new java.awt.Color(255, 255, 255));
        labelApellidos.setText("Apellidos");
        formularioRegistroPaso2.add(labelApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 250, -1));

        labelDni.setForeground(new java.awt.Color(255, 255, 255));
        labelDni.setText("Dni");
        formularioRegistroPaso2.add(labelDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 250, -1));

        labelTelefono.setForeground(new java.awt.Color(255, 255, 255));
        labelTelefono.setText("Teléfono");
        formularioRegistroPaso2.add(labelTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 250, -1));

        labelDireccion.setForeground(new java.awt.Color(255, 255, 255));
        labelDireccion.setText("Dirección");
        formularioRegistroPaso2.add(labelDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 250, -1));

        botonFinalizar.setText("Finalizar");
        botonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFinalizarActionPerformed(evt);
            }
        });
        formularioRegistroPaso2.add(botonFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 100, 30));

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });
        formularioRegistroPaso2.add(botonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 550, 100, 30));

        labelIniciarSesion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        labelIniciarSesion.setText("AutoTech - Paso 2 de 2: Crear tu cuenta");
        formularioRegistroPaso2.add(labelIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 450, 40));

        getContentPane().add(formularioRegistroPaso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 520, 620));
        formularioRegistroPaso2.setBackground(new java.awt.Color(0, 0, 0, 120));

        fondoPantalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stiles/fondo_formularios.jpg"))); // NOI18N
        fondoPantalla.setOpaque(true);
        getContentPane().add(fondoPantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 720));
        formularioRegistroPaso2.setBackground(new java.awt.Color(0, 0, 0, 120));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNombreActionPerformed

    private void botonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFinalizarActionPerformed
         finalizarRegistro();
    }//GEN-LAST:event_botonFinalizarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        controlador.vistaLoginClientes(this);
    }//GEN-LAST:event_botonCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroUsuariosPaso2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuariosPaso2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuariosPaso2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuariosPaso2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroUsuariosPaso2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonFinalizar;
    private javax.swing.JTextField fieldApellidos;
    private javax.swing.JTextField fieldDireccion;
    private javax.swing.JTextField fieldDni;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldTelefono;
    private javax.swing.JLabel fondoPantalla;
    private javax.swing.JPanel formularioRegistroPaso2;
    private javax.swing.JLabel labelApellidos;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelDni;
    private javax.swing.JLabel labelIniciarSesion;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel textoErrorApellidos;
    private javax.swing.JLabel textoErrorDireccion;
    private javax.swing.JLabel textoErrorDni;
    private javax.swing.JLabel textoErrorNombre;
    private javax.swing.JLabel textoErrorTelefono;
    // End of variables declaration//GEN-END:variables
}
