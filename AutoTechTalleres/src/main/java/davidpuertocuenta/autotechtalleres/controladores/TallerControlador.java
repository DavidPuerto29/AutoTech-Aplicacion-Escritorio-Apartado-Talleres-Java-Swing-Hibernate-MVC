/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.controladores;

import davidpuertocuenta.autotechtalleres.clases.Citas;
import davidpuertocuenta.autotechtalleres.clases.Talleres;
import static davidpuertocuenta.autotechtalleres.util.Estilos.aplicarEstiloTablas;
import static davidpuertocuenta.autotechtalleres.dao.CitasDAO.actualizarCitaSql;
import static davidpuertocuenta.autotechtalleres.dao.CitasDAO.eliminarCitaSql;
import static davidpuertocuenta.autotechtalleres.dao.CitasDAO.obtenerCitaPorNumeroSql;
import static davidpuertocuenta.autotechtalleres.dao.CitasDAO.obtenerTodasCitasTallerSql;
import davidpuertocuenta.autotechtalleres.vistas.login.LoginTalleres;
import davidpuertocuenta.autotechtalleres.vistas.talleres.DialogCambiarEstadoCita;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author David Puerto Cuenca
 */
public class TallerControlador {
    
    public void cerrarSesion(JFrame vista){
        if(JOptionPane.showOptionDialog(vista, "¿Desea cerrar sesíon?", "Cerrar Sesíon", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No"},"No") == JOptionPane.YES_OPTION){
            LoginTalleres login = new LoginTalleres();
                login.setVisible(true);
                    vista.dispose();
        }
    }
    
    //Requerido para que la opción de cerrar sesión aparezca a la derecha de la pantalla.    
    public void colocarCerrarSesion(JMenuBar jMenuBar1, JMenu jMenu5){
        jMenuBar1.remove(jMenu5);
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(jMenu5);
    }
    
    public void crearTablaCitasTaller(JTable tablaCitas, Talleres taller){
                Object[] cabecera = new Object[]{"Numero De Cita", "Fecha", "Descripción", "Matrícula", "Cliente", "Estado"}; 
        DefaultTableModel miModelo = new DefaultTableModel(cabecera, 0){
            //Edicion de celdas deshabilida.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  
            }
        };
        tablaCitas.setModel(miModelo);
        tablaCitas.getTableHeader().setReorderingAllowed(false);
                
            List<Citas> citas = new ArrayList(obtenerTodasCitasTallerSql(taller));
           
            for(Citas cita : citas){
                Object[] fila = new Object[6];
                fila[0] = cita.getNumeroCita();
                fila[1] = cita.getFecha();
                fila[2] = cita.getDescripcion();
                fila[3] = cita.getVehiculo().getMatricula();
                fila[4] = cita.getVehiculo().getCliente().getNombre();
                switch(cita.getEstadoCita()){
                    case 1 -> fila[5] = "Pendiente ";
                    case 2 -> fila[5] = "En proceso ";
                    case 3 -> fila[5] = "Listo para recoger ";
                    case 4 -> fila[5] = "Finalizada  ";
                        default -> fila[5] = "Error";
                }
                    miModelo.addRow(fila);
            }
         
           aplicarEstiloTablas(tablaCitas);
    }
    
    public void cargarEstadoCitaComboBox(JComboBox comboBoxEstadoCita, Citas cita){
        comboBoxEstadoCita.removeAllItems(); 
            comboBoxEstadoCita.addItem("Pendiente"); 
            comboBoxEstadoCita.addItem("En proceso"); 
            comboBoxEstadoCita.addItem("Listo para recoger"); 
            comboBoxEstadoCita.addItem("Finalizada"); 
                            //Se añade uno para evitar que el JComboBox se descuadre debido al array.
                           comboBoxEstadoCita.setSelectedIndex(cita.getEstadoCita() - 1);
    }
    
    public void modificarEstadoCita(Citas cita, JComboBox comboBoxEstadoCita, JDialog vista){
        cita.setEstadoCita(comboBoxEstadoCita.getSelectedIndex() + 1);
            actualizarCitaSql(cita);
                vista.dispose();
    }
    
    public void vistaDialogCamiarEstado(JFrame vista, JTable tablaCitasTaller){
        try{
            DialogCambiarEstadoCita ec = new DialogCambiarEstadoCita(vista, true, obtenerCitaPorNumeroSql((Long) tablaCitasTaller.getValueAt(tablaCitasTaller.getSelectedRow(), 0)));
                ec.setVisible(true);
        }catch (ArrayIndexOutOfBoundsException e){
              JOptionPane.showMessageDialog(vista, "Debe seleccionar una cita de la lista.", "Información", JOptionPane.INFORMATION_MESSAGE);
         }
    }
    
    public void eliminarCita(JTable tablaCitasTaller, JFrame vista, Talleres taller){
        try{
            Citas cita = obtenerCitaPorNumeroSql((Long) tablaCitasTaller.getValueAt(tablaCitasTaller.getSelectedRow(), 0));
                if(cita == null){
                    JOptionPane.showMessageDialog(vista, "La cita seleccionada no ha sido encontrada.", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                    if(JOptionPane.showOptionDialog(vista, "¿Esta seguro de realizar esta opción?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No"},"No") == JOptionPane.YES_OPTION){
                        eliminarCitaSql(cita);        
                    }
         }catch (ArrayIndexOutOfBoundsException e){
              JOptionPane.showMessageDialog(vista, "Debe seleccionar una cita de la lista.", "Información", JOptionPane.INFORMATION_MESSAGE);
         }
        //Siempre al finalizar actualiza la tabla.
        crearTablaCitasTaller(tablaCitasTaller, taller);
    }

}
