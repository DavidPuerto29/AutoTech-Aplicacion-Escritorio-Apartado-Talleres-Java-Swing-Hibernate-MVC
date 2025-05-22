/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package davidpuertocuenta.autotechtalleres;

import com.formdev.flatlaf.FlatLightLaf;
import davidpuertocuenta.autotechtalleres.dao.HibernateUtil;
import davidpuertocuenta.autotechtalleres.vistas.login.LoginTalleres;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author David Puerto Cuenca
 */
public class AutoTechTalleres {

    public static void main(String[] args) {
        try {
            // Establece FlatLaf con tema claro
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("No se pudo iniciar FlatLaf");
        }
        
        if(HibernateUtil.getSessionFactory() != null){ 
            LoginTalleres lt = new LoginTalleres();
                lt.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "No ha sido posible establecer conexión con el servidor.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
}
