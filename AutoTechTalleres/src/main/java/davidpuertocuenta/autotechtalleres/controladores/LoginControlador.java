/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.controladores;

import static davidpuertocuenta.autotechtalleres.cartografia.CifradoSHA256.verificarContraseña;
import davidpuertocuenta.autotechtalleres.clases.Usuarios;
import davidpuertocuenta.autotechtalleres.clases.UsuariosTalleres;
import static davidpuertocuenta.autotechtalleres.dao.UsuariosTalleresDAO.obtenerUsuarioTallerPorUsuarioSql;
import davidpuertocuenta.autotechtalleres.vistas.registro.RegistroUsuariosPaso1;
import davidpuertocuenta.autotechtalleres.vistas.talleres.VistaCitasTaller;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author David Puerto Cuenca
 */
public class LoginControlador {
    
    public boolean comprobacionAutenticacionUsuario(Usuarios usuario, String contrasena){
        if (usuario == null) {
            return false;
        }
        return verificarContraseña(contrasena, usuario.getRandomizador(), usuario.getContrasena());
    }
    
    public void iniciarSesionUsuarios(String usuario, char[] contrasena, JFrame vista){
        UsuariosTalleres usuarioTaller = obtenerUsuarioTallerPorUsuarioSql(usuario);
        
        if(comprobacionAutenticacionUsuario(usuarioTaller, String.valueOf(contrasena))){
            //Se limpia el array para aumentar la seguridad.
            java.util.Arrays.fill(contrasena, '\0');
                if(usuarioTaller.getTaller() != null){
                    VistaCitasTaller vct = new VistaCitasTaller(usuarioTaller.getTaller());
                        vct.setVisible(true);
                            vista.dispose();
                }else{
                    JOptionPane.showMessageDialog(vista, "Cuenta no activada. Por favor, contacte con el servicio técnico.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                }
        }else{
            //Se limpia el array para aumentar la seguridad.
            java.util.Arrays.fill(contrasena, '\0');
                JOptionPane.showMessageDialog(vista, "El usuario no ha sido encontrado, por favor compruebe los datos y vuelva a intentarlo.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void vistaRegistroUsuarios(JFrame vista){
        RegistroUsuariosPaso1 rg = new RegistroUsuariosPaso1();
            rg.setVisible(true);
                vista.dispose();
    }
   
}
