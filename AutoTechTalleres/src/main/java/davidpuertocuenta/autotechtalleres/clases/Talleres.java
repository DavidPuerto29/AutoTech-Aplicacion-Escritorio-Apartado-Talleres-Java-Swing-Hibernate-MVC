/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.clases;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author David Puerto Cuenca
 */
@NamedQuery(name = "get_todos_talleres", query = "FROM Talleres q ")
@NoArgsConstructor
@Data
@Entity
public class Talleres {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroTaller;
    
    private String nombre;
    private String direccion;
    private String codigoPostal;
    private String teléfono;
    private String cif;
    private String localidad;
    
    @OneToMany(mappedBy = "UsuariosTalleres", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UsuariosTalleres> usuarios;
    
    public Talleres(String nombre, String direccion, String codigoPostal, String teléfono, String cif, String localidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.teléfono = teléfono;
        this.cif = cif;
        this.localidad = localidad;
    }
    
}
