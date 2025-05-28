/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.clases;

import jakarta.persistence.Column;
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

@NamedQuery(name = "get_empleados_username", query = "FROM Empleados p WHERE p.usuario = :username")
@NamedQuery(name = "get_empleados_dni", query = "FROM Empleados p WHERE p.dni = :dniCliente")
@NamedQuery(name = "get_empleados_login", query = "FROM Empleados p WHERE p.usuario = :username AND p.contrasena = :password")
@NamedQuery(name = "get_empleados", query = "FROM Empleados p WHERE p.usuario = :username")
@NamedQuery(name = "get_todos_empleados", query = "FROM Empleados q ORDER BY q.usuario ASC")
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Empleados{
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idEmpleado;
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

        @ManyToOne
        @JoinColumn(name  = "Taller")
        private Talleres taller;

        public Empleados(String usuario, String contrasena, String randomizador, String dni, String nombre, String apellidos, String correoElectronico, String numeroTelefono, String direccion, Talleres taller) {
            this.usuario = usuario;
            this.contrasena = contrasena;
            this.randomizador = randomizador;
            this.dni = dni;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.correoElectronico = correoElectronico;
            this.numeroTelefono = numeroTelefono;
            this.direccion = direccion;
            this.taller = taller;
        }

  
    
}
