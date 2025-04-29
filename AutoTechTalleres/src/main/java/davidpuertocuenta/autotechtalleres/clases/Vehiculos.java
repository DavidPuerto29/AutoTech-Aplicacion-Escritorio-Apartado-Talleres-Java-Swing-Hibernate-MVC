/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.clases;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;


/**
 *
 * @author David Puerto Cuenca
 */
@NamedQuery(name = "get_todos_vehiculos_usuario", query = "FROM Vehiculos q Where q.cliente = :client ORDER BY q.matricula ASC ")
@NamedQuery(name = "get_todos_vehiculos", query = "FROM Vehiculos q ORDER BY q.matricula ASC ")
@NamedQuery(name = "get_vehiculo", query = "FROM Vehiculos q Where q.matricula = :identificacion ")
@Data
public class Vehiculos {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;
    @Column(unique = true)
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private String anoMatriculacion;
    @Column(unique = true)
    private String numeroBastidor;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "idCliente")
    private Usuarios cliente;
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Citas> citas;

    public Vehiculos(String matricula, String marca, String modelo, String color, String anoMatriculacion, String numeroBastidor, Usuarios cliente, List<Citas> citas) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anoMatriculacion = anoMatriculacion;
        this.numeroBastidor = numeroBastidor;
        this.cliente = cliente;
        this.citas = citas;
    }
}
