/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.dao;

import davidpuertocuenta.autotechtalleres.clases.Empleados;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;


/**
 *
 * @author David Puerto Cuenca
 */
public class EmpleadosDAO {
    public static void crearEmpleadoSql(Empleados empleado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
                session.persist(empleado);
                    session.getTransaction().commit();
        }
    }
    
    public static void eliminarEmpleadoSql(Empleados empleado){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
                session.remove(empleado);
                    session.getTransaction().commit();
        }
    }
    
    public static Empleados obtenerEmpleadoPorUsuarioSql(String empleado){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Empleados> q = session.createNamedQuery("get_usuarioTalleres_username", Empleados.class);
                q.setParameter("username", empleado);
                    return (Empleados) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static Empleados obtenerEmpleadorPorDniSql(String dni){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Empleados> q = session.createNamedQuery("get_usuarioTalleres_dni", Empleados.class);
                q.setParameter("dniCliente", dni);
                    return (Empleados) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static Empleados loginEmpleadoSql(String empleado,String contrasena){    //Creo que obsoleto - PUEDE Q SOBRE COMPROBAR A FINAL DE PROYECTO
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Empleados> q = session.createNamedQuery("get_usuarioTalleres_login", Empleados.class);
                q.setParameter("username", empleado);
                    q.setParameter("password", contrasena);
                        return (Empleados) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
     public static List<Empleados> obtenerTodosEmpleadoSql(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Empleados> q = session.createNamedQuery("get_todos_usuariosTalleres", Empleados.class);
                return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
     public static boolean actualizarEmpleadoSql(Empleados empleado){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
                session.merge(empleado);
                    session.getTransaction().commit();
                        return true;
        }
     }
     
     public static Empleados obtenerEmpleadoSql(String empleado){  
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Empleados> q = session.createNamedQuery("get_usuarioTalleres", Empleados.class);
                q.setParameter("username", empleado);
                    return (Empleados) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
