/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package davidpuertocuenta.autotechtalleres;

import davidpuertocuenta.autotechtalleres.dao.HibernateUtil;
import davidpuertocuenta.autotechtalleres.vistas.login.LoginTalleres;
import javax.swing.JOptionPane;

/**
 *
 * @author David Puerto Cuenca
 */
public class AutoTechTalleres {

    public static void main(String[] args) {
        if(HibernateUtil.getSessionFactory() != null){ 
            LoginTalleres lt = new LoginTalleres();
                lt.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "No ha sido posible establecer conexión con el servidor.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
}
