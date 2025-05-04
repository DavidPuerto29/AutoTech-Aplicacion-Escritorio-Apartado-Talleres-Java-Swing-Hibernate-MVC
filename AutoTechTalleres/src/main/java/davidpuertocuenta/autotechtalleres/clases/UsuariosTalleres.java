/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author David Puerto Cuenca
 */

@NamedQuery(name = "get_usuarioTalleres_username", query = "FROM UsuariosTalleres p WHERE p.usuario = :username")
@NamedQuery(name = "get_usuarioTalleres_dni", query = "FROM UsuariosTalleres p WHERE p.dni = :dniCliente")
@NamedQuery(name = "get_usuarioTalleres_login", query = "FROM UsuariosTalleres p WHERE p.usuario = :username AND p.contrasena = :password")
@NamedQuery(name = "get_usuarioTalleres", query = "FROM UsuariosTalleres p WHERE p.usuario = :username")
@NamedQuery(name = "get_todos_usuariosTalleres", query = "FROM UsuariosTalleres q ORDER BY q.usuario ASC")
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class UsuariosTalleres extends Usuarios{
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idEmpleado;
        
        @ManyToOne
        @JoinColumn(name  = "Taller")
        private Talleres taller;

    public UsuariosTalleres(String usuario, String contrasena, String randomizador, String dni, String nombre, String apellidos, String correoElectronico, String numeroTelefono, String direccion, boolean administrador, Talleres taller) {
        super(usuario, contrasena, randomizador, dni, nombre, apellidos, correoElectronico, numeroTelefono, direccion, administrador);
        this.taller = taller;
    }
    
}
