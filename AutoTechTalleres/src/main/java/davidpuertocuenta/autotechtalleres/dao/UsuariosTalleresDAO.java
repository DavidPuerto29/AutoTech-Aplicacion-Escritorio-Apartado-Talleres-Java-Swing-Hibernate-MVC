/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.dao;

import davidpuertocuenta.autotechtalleres.clases.UsuariosTalleres;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;


/**
 *
 * @author David Puerto Cuenca
 */
public class UsuariosTalleresDAO {
    public static void crearUsuarioTallerSql(UsuariosTalleres usuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
                session.persist(usuario);
                    session.getTransaction().commit();
        }
    }
    
    public static void eliminarUsuarioTallerSql(UsuariosTalleres usuario){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
                session.remove(usuario);
                    session.getTransaction().commit();
        }
    }
    
    public static UsuariosTalleres obtenerUsuarioTallerPorUsuarioSql(String usuario){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<UsuariosTalleres> q = session.createNamedQuery("get_usuarioTalleres_username", UsuariosTalleres.class);
                q.setParameter("username", usuario);
                    return (UsuariosTalleres) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static UsuariosTalleres obtenerUsuarioTallerPorDniSql(String dni){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<UsuariosTalleres> q = session.createNamedQuery("get_usuarioTalleres_dni", UsuariosTalleres.class);
                q.setParameter("dniCliente", dni);
                    return (UsuariosTalleres) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static UsuariosTalleres loginUsuarioTallerSql(String usuario,String contrasena){    //Creo que obsoleto - PUEDE Q SOBRE COMPROBAR A FINAL DE PROYECTO
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<UsuariosTalleres> q = session.createNamedQuery("get_usuarioTalleres_login", UsuariosTalleres.class);
                q.setParameter("username", usuario);
                    q.setParameter("password", contrasena);
                        return (UsuariosTalleres) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
     public static List<UsuariosTalleres> obtenerTodosUsuariosTalleresSql(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<UsuariosTalleres> q = session.createNamedQuery("get_todos_usuariosTalleres", UsuariosTalleres.class);
                return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
     public static boolean actualizarUsuarioTallerSql(UsuariosTalleres usuario){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
                session.merge(usuario);
                    session.getTransaction().commit();
                        return true;
        }
     }
     
     public static UsuariosTalleres obtenerUsuarioTallerSql(String usuario){  
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<UsuariosTalleres> q = session.createNamedQuery("get_usuarioTalleres", UsuariosTalleres.class);
                q.setParameter("username", usuario);
                    return (UsuariosTalleres) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
