/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.controladores;

import davidpuertocuenca.autotech.vistas.registro.legal.TerminosYCondiciones;
import davidpuertocuenta.autotechtalleres.clases.UsuariosTalleres;
import davidpuertocuenta.autotechtalleres.vistas.login.LoginTalleres;
import davidpuertocuenta.autotechtalleres.vistas.registro.RegistroUsuariosPaso2;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author David Puerto Cuenca
 */
public class RegistroControlador {
    
    public void vistaLoginClientes(JFrame vista){
       LoginTalleres lgt = new LoginTalleres();
            lgt.setVisible(true);
                vista.dispose(); 
    }
    
    public void vistaLoginClientesFinalizarRegistro(JFrame vista){
       JOptionPane.showMessageDialog(vista, "Para finalizar el registro, contacte con el servicio técnico indicando los datos de su taller.", "Información", JOptionPane.INFORMATION_MESSAGE);
            LoginTalleres lgt = new LoginTalleres();
                 lgt.setVisible(true);
                     vista.dispose(); 
    }
    
    public void vistaRegistroPasoDos(JFrame vista, UsuariosTalleres usuario){
        RegistroUsuariosPaso2 rgc = new RegistroUsuariosPaso2(usuario);
                rgc.setVisible(true);
                    vista.dispose(); 
    }
    
    //Devuelve si los terminos han sido aceptados o no
    public boolean vistaTerminosCondiciones(JFrame vista){
        TerminosYCondiciones tyc = new TerminosYCondiciones(vista, true);
                tyc.setVisible(true);
                    return tyc.isAceptado();
    }
    
}
