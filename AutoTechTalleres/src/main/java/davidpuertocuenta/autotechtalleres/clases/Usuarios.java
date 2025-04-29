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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

/**
 *
 * @author David Puerto Cuenca
 */
@NamedQuery(name = "get_usuario_username", query = "FROM Usuarios p WHERE p.usuario = :username")
@NamedQuery(name = "get_usuario_dni", query = "FROM Usuarios p WHERE p.dni = :dniCliente")
@NamedQuery(name = "get_usuario_login", query = "FROM Usuarios p WHERE p.usuario = :username AND p.contrasena = :password")
@NamedQuery(name = "get_usuario", query = "FROM Usuarios p WHERE p.usuario = :username")
@NamedQuery(name = "get_todos_usuarios", query = "FROM Usuarios q ORDER BY q.usuario ASC")
@Data
public class Usuarios {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    @Column(unique = true)
    private String usuario;
    private String contrasena; 
    private String randomizador;   
    
    private String dni;
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String numeroTelefono;
    private String direccion;
    private boolean administrador;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Vehiculos> vehiculos;
    
    public Usuarios(String usuario, String contrasena, String randomizador, String dni, String nombre, String apellidos, String correoElectronico, String numeroTelefono, String direccion, boolean administrador) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.randomizador = randomizador;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.administrador = administrador;
    }
  
}
