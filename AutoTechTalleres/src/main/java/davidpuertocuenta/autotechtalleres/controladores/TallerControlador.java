/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.controladores;

import davidpuertocuenta.autotechtalleres.clases.Citas;
import davidpuertocuenta.autotechtalleres.clases.Talleres;
import davidpuertocuenta.autotechtalleres.clases.Usuarios;
import davidpuertocuenta.autotechtalleres.clases.Vehiculos;
import static davidpuertocuenta.autotechtalleres.dao.CitasDAO.actualizarCitaSql;
import static davidpuertocuenta.autotechtalleres.dao.CitasDAO.obtenerCitaPorNumeroSql;
import static davidpuertocuenta.autotechtalleres.dao.VehiculosDAO.obtenerTodosVehiculosClienteSql;
import static davidpuertocuenta.autotechtalleres.dao.CitasDAO.obtenerTodasCitasTallerSql;
import davidpuertocuenta.autotechtalleres.vistas.login.LoginTalleres;
import davidpuertocuenta.autotechtalleres.vistas.talleres.dialogCambiarEstadoCita;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
    
    public void crearTablaVehiculos(JTable tablaVehiculos, Usuarios usuario){
        Object[] cabecera = new Object[]{"Matrícula", "Marca", "Modelo", "Año De Matriculación", "Color", "Citas Reservadas", "Número De Bastidor"}; 
        DefaultTableModel miModelo = new DefaultTableModel(cabecera, 0){
            //Edicion de celdas deshabilida.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  
            }
        };
        tablaVehiculos.setModel(miModelo);
        tablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVehiculos.getTableHeader().setReorderingAllowed(false);

            List<Vehiculos> vehiculos = new ArrayList(obtenerTodosVehiculosClienteSql(usuario));
           
            for(Vehiculos Vehiculo : vehiculos){
                Object[] fila = new Object[7];
                fila[0] = Vehiculo.getMatricula();
                fila[1] = Vehiculo.getMarca();
                fila[2] = Vehiculo.getModelo();
                fila[3] = Vehiculo.getAnoMatriculacion();
                fila[4] = Vehiculo.getColor();
                fila[5] = Vehiculo.getCitas().size();
                fila[6] = Vehiculo.getNumeroBastidor();
                    miModelo.addRow(fila);
            }
         
            //Dimensiones de la tabla.
            tablaVehiculos.setRowHeight(40);
            TableColumn columnaMatricula = tablaVehiculos.getColumn("Matrícula");
            columnaMatricula.setMinWidth(100);
            columnaMatricula.setMaxWidth(600);
            columnaMatricula.setPreferredWidth(300); 
            
            TableColumn columnaMarca = tablaVehiculos.getColumn("Marca");
            columnaMarca.setMinWidth(100);
            columnaMarca.setMaxWidth(600);
            columnaMarca.setPreferredWidth(300); 
            
            TableColumn columnaModelo = tablaVehiculos.getColumn("Modelo");
            columnaModelo.setMinWidth(100);
            columnaModelo.setMaxWidth(600);
            columnaModelo.setPreferredWidth(300); 
            
            TableColumn columnaAnoMatriculacion = tablaVehiculos.getColumn("Año De Matriculación");
            columnaAnoMatriculacion.setMinWidth(100);
            columnaAnoMatriculacion.setMaxWidth(600);
            columnaAnoMatriculacion.setPreferredWidth(300); 
            
            TableColumn columnaColor = tablaVehiculos.getColumn("Color");
            columnaColor.setMinWidth(100);
            columnaColor.setMaxWidth(600);
            columnaColor.setPreferredWidth(300); 
            
            TableColumn columnaCitas = tablaVehiculos.getColumn("Citas Reservadas");
            columnaCitas.setMinWidth(100);
            columnaCitas.setMaxWidth(600);
            columnaCitas.setPreferredWidth(300);
            
            TableColumn columnaBastidor = tablaVehiculos.getColumn("Número De Bastidor");
            columnaBastidor.setMinWidth(100);
            columnaBastidor.setMaxWidth(600);
            columnaBastidor.setPreferredWidth(300); 
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaVehiculos.getTableHeader().setResizingAllowed(false);
            //Usado para centrar el texto de las celdas.
            for (int i = 0; i < tablaVehiculos.getColumnCount(); i++) {
                tablaVehiculos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    tablaVehiculos.getColumnModel().getColumn(i).setResizable(false);
            }
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
        tablaCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
         
            //Dimensiones de la tabla.
            tablaCitas.setRowHeight(40);
            TableColumn columnaNumeroCita = tablaCitas.getColumn("Numero De Cita");
            columnaNumeroCita.setMinWidth(100);
            columnaNumeroCita.setMaxWidth(600);
            columnaNumeroCita.setPreferredWidth(300); 
            
            TableColumn columnaFecha = tablaCitas.getColumn("Fecha");
            columnaFecha.setMinWidth(100);
            columnaFecha.setMaxWidth(600);
            columnaFecha.setPreferredWidth(300); 
           
            TableColumn columnaDescripcionr = tablaCitas.getColumn("Descripción");
            columnaDescripcionr.setMinWidth(100);
            columnaDescripcionr.setMaxWidth(600);
            columnaDescripcionr.setPreferredWidth(300); 
            
            TableColumn columnaMatricula = tablaCitas.getColumn("Matrícula");
            columnaMatricula.setMinWidth(100);
            columnaMatricula.setMaxWidth(600);
            columnaMatricula.setPreferredWidth(300);
            
            TableColumn columnaCliente = tablaCitas.getColumn("Cliente");
            columnaCliente.setMinWidth(100);
            columnaCliente.setMaxWidth(600);
            columnaCliente.setPreferredWidth(300);
            
            TableColumn columnaEstado = tablaCitas.getColumn("Estado");
            columnaEstado.setMinWidth(100);
            columnaEstado.setMaxWidth(600);
            columnaEstado.setPreferredWidth(300);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaCitas.getTableHeader().setResizingAllowed(false);
            //Usado para centrar el texto de las celdas.
            for (int i = 0; i < tablaCitas.getColumnCount(); i++) {
                tablaCitas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    tablaCitas.getColumnModel().getColumn(i).setResizable(false);
            }
    }
    
    public void cargarEstadoCitaComboBox(JComboBox comboBoxEstadoCita, Citas cita){
        comboBoxEstadoCita.removeAllItems(); 
            comboBoxEstadoCita.addItem("Pendiente"); 
            comboBoxEstadoCita.addItem("En proceso"); 
            comboBoxEstadoCita.addItem("Listo para recoger"); 
            comboBoxEstadoCita.addItem("Finalizada"); 
                comboBoxEstadoCita.setSelectedIndex(cita.getEstadoCita());
                            //Se añade uno para evitar que el JComboBox se descuadre debido al array.
                           comboBoxEstadoCita.setSelectedIndex(cita.getEstadoCita() - 1);
    }
    
    public void modificarEstadoCita(Citas cita, JComboBox comboBoxEstadoCita, JDialog vista){
        cita.setEstadoCita(comboBoxEstadoCita.getSelectedIndex() - 1);
            actualizarCitaSql(cita);
                vista.dispose();
    }
    
    public void vistaDialogCamiarEstado(JFrame vista, JTable tablaCitasTaller){
        dialogCambiarEstadoCita ec = new dialogCambiarEstadoCita(vista, true, obtenerCitaPorNumeroSql((Long) tablaCitasTaller.getValueAt(tablaCitasTaller.getSelectedRow(), 0)));
            ec.setVisible(true);
    }
    
}
