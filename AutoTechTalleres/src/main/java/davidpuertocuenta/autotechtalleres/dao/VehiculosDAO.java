/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.dao;

import davidpuertocuenta.autotechtalleres.clases.Usuarios;
import davidpuertocuenta.autotechtalleres.clases.Vehiculos;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author David Puerto Cuenca
 */
public class VehiculosDAO {
    
    public static void crearVehiculoSql(Vehiculos vehiculo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
                session.persist(vehiculo);
                    session.getTransaction().commit();
        }
    }
    
    public static List<Vehiculos> obtenerTodosVehiculosClienteSql(Usuarios cliente){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Vehiculos> q = session.createNamedQuery("get_todos_vehiculos_usuario", Vehiculos.class);
                q.setParameter("client", cliente);
                    return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static List<Vehiculos> obtenerTodosVehiculosSql(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Vehiculos> q = session.createNamedQuery("get_todos_vehiculos", Vehiculos.class);
                return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static void eliminarVehiculoSql(Vehiculos vehiculo){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
                session.remove(vehiculo);
                    session.getTransaction().commit();
        }
    }
    
    public static Vehiculos obtenerVehiculoMatriculaSql(String matricula){  
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Vehiculos> q = session.createNamedQuery("get_vehiculo", Vehiculos.class);
                q.setParameter("identificacion", matricula);
                    return (Vehiculos) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static void actualizarVehiculoSql(Vehiculos vehiculo){
        try(Session session = HibernateUtil.getSessionFactory().openSession();){
            session.getTransaction().begin();
                session.merge(vehiculo);
                    session.getTransaction().commit();
        }
    }
}
