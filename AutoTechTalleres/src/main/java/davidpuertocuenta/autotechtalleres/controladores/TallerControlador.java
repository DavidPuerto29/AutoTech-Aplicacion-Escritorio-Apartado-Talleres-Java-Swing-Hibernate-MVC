/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.controladores;

import davidpuertocuenta.autotechtalleres.clases.Citas;
import davidpuertocuenta.autotechtalleres.clases.Talleres;
import davidpuertocuenta.autotechtalleres.clases.Usuarios;
import davidpuertocuenta.autotechtalleres.clases.Vehiculos;
import static davidpuertocuenta.autotechtalleres.dao.VehiculosDAO.obtenerTodosVehiculosClienteSql;
import static davidpuertocuenta.autotechtalleres.dao.VehiculosDAO.obtenerVehiculoMatriculaSql;
import static davidpuertocuenta.autotechtalleres.dao.CitasDAO.obtenerTodasCitasTallerSql;
import davidpuertocuenta.autotechtalleres.vistas.login.LoginTalleres;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
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
    
    public void crearTablaCitasTaller(JTable tablaCitasVehiculo, Talleres taller){
        Object[] cabecera = new Object[]{"Número de cita","Fecha","Vehiculo","Estado De Cita"}; 
        DefaultTableModel miModelo = new DefaultTableModel(cabecera, 0){
            //Edicion de celdas deshabilida.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  
            }
        };
        tablaCitasVehiculo.setModel(miModelo);
        tablaCitasVehiculo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCitasVehiculo.getTableHeader().setReorderingAllowed(false);

            List<Citas> citas = new ArrayList(obtenerTodasCitasTallerSql(taller.getNumeroTaller()));
           
            for(Citas Cita : citas){
                Object[] fila = new Object[4];
                fila[0] = Cita.getNumeroCita();
                fila[1] = Cita.getFecha();
                fila[2] = Cita.getVehiculo().getMatricula();
                fila[3] = Cita.getEstadoCita();//TODO
                    miModelo.addRow(fila);
            } 
            
            //Dimensiones de la tabla.
            tablaCitasVehiculo.setRowHeight(40);
            TableColumn columnaNumeroCita = tablaCitasVehiculo.getColumn("Número de cita");
            columnaNumeroCita.setMinWidth(100);
            columnaNumeroCita.setMaxWidth(600);
            columnaNumeroCita.setPreferredWidth(300); 
            
            TableColumn columnaFecha = tablaCitasVehiculo.getColumn("Fecha");
            columnaFecha.setMinWidth(100);
            columnaFecha.setMaxWidth(600);
            columnaFecha.setPreferredWidth(300); 
            
            TableColumn columnaAnoVehiculo = tablaCitasVehiculo.getColumn("Vehiculo");
            columnaAnoVehiculo.setMinWidth(100);
            columnaAnoVehiculo.setMaxWidth(600);
            columnaAnoVehiculo.setPreferredWidth(300); 
            
            TableColumn columnaEstadoCita = tablaCitasVehiculo.getColumn("Estado De Cita");
            columnaEstadoCita.setMinWidth(100);
            columnaEstadoCita.setMaxWidth(600);
            columnaEstadoCita.setPreferredWidth(300); 
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaCitasVehiculo.getTableHeader().setResizingAllowed(false);
            //Usado para centrar el texto de las celdas.
            for (int i = 0; i < tablaCitasVehiculo.getColumnCount(); i++) {
                tablaCitasVehiculo.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    tablaCitasVehiculo.getColumnModel().getColumn(i).setResizable(false);
            }
    }
    
}
