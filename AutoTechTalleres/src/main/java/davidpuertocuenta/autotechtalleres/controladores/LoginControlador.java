/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.controladores;

import static davidpuertocuenta.autotechtalleres.cartografia.CifradoSHA256.verificarContraseña;
import davidpuertocuenta.autotechtalleres.clases.Usuarios;
import static davidpuertocuenta.autotechtalleres.dao.UsuariosDAO.obtenerUsuarioPorUsuarioSql;
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
        Usuarios usuarios = obtenerUsuarioPorUsuarioSql(usuario);
        
        if(comprobacionAutenticacionUsuario(usuarios, String.valueOf(contrasena))){
            //Se limpia el array para aumentar la seguridad.
            java.util.Arrays.fill(contrasena, '\0');
               //VISTA TALLERES TODO
        }else{
            //Se limpia el array para aumentar la seguridad.
            java.util.Arrays.fill(contrasena, '\0');
                JOptionPane.showMessageDialog(vista, "El usuario no ha sido encontrado, por favor compruebe los datos y vuelva a intentarlo.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }
   
}
