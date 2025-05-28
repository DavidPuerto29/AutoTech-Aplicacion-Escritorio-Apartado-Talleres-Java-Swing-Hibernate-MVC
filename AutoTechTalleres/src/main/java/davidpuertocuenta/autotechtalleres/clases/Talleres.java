/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author David Puerto Cuenca
 */
@NamedQuery(name = "get_todos_talleres", query = "FROM Talleres q ORDER BY nombre ASC")
@NamedQuery(name = "get_taller", query = "FROM Talleres q Where q.numeroTaller = :identificacion ORDER BY nombre ASC")
@NamedQuery(name = "get_taller_cif", query = "FROM Talleres q Where q.cif = :identificacion ORDER BY nombre ASC")
@NamedQuery(name = "get_taller_nombre", query = "FROM Talleres q Where q.nombre = :identificacion ORDER BY nombre ASC")
@Entity @NoArgsConstructor @Data
public class Talleres {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroTaller;
    
    private String nombre;
    private String direccion;
    private String codigoPostal;
    private String telefono;
    private String cif;
    private String localidad;
    private Integer citasMaximas;

    public Talleres(String nombre, String direccion, String codigoPostal, String telefono, String cif, String localidad, Integer citasMaximas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.cif = cif;
        this.localidad = localidad;
        this.citasMaximas = citasMaximas;
    }
    
}
