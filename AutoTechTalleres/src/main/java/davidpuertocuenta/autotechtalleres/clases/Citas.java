/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.clases;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author David Puerto Cuenca
 */ 
@NamedQuery(name = "get_todas_citas_matricula", query = "FROM Citas q Where q.vehiculo.matricula = :matricula ORDER BY numeroCita ASC")
@NamedQuery(name = "get_todas_citas", query = "FROM Citas q")
@NamedQuery(name = "get_cita", query = "FROM Citas q Where q.numeroCita = :identificacion ")
@NamedQuery(name = "get_todas_citas_talleres", query = "FROM Citas q Where q.taller= :identificacion ")
@Data
public class Citas {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroCita;
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "idVehiculo")
    private Vehiculos vehiculo;
    @ManyToOne
    @JoinColumn(name = "taller", referencedColumnName = "numeroTaller")
    private Talleres taller;
    private int estadoCita;

    public Citas(Date fecha, Vehiculos vehiculo, Talleres taller, int estadoCita) {
        this.fecha = fecha;
        this.vehiculo = vehiculo;
        this.taller = taller;
        this.estadoCita = estadoCita;
    }

}
