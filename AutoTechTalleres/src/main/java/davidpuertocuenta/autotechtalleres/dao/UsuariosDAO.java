/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.dao;

import davidpuertocuenta.autotechtalleres.clases.Usuarios;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;


/**
 *
 * @author David Puerto Cuenca
 */
public class UsuariosDAO {
    public static void crearUsuarioSql(Usuarios usuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
                session.persist(usuario);
                    session.getTransaction().commit();
        }
    }
    
    public static void eliminarUsuarioSql(Usuarios usuario){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
                session.remove(usuario);
                    session.getTransaction().commit();
        }
    }
    
    public static Usuarios obtenerUsuarioPorUsuarioSql(String usuario){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuarios> q = session.createNamedQuery("get_usuario_username", Usuarios.class);
                q.setParameter("username", usuario);
                    return (Usuarios) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static Usuarios obtenerUsuarioPorDniSql(String dni){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuarios> q = session.createNamedQuery("get_usuario_dni", Usuarios.class);
                q.setParameter("dniCliente", dni);
                    return (Usuarios) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static Usuarios loginUsuarioSql(String usuario,String contrasena){    //Creo que obsoleto - PUEDE Q SOBRE COMPROBAR A FINAL DE PROYECTO
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuarios> q = session.createNamedQuery("get_usuario_login", Usuarios.class);
                q.setParameter("username", usuario);
                    q.setParameter("password", contrasena);
                        return (Usuarios) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
     public static List<Usuarios> obtenerTodosUsuariosSql(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuarios> q = session.createNamedQuery("get_todos_usuarios", Usuarios.class);
                return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
     public static boolean actualizarUsuarioSql(Usuarios usuario){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
                session.merge(usuario);
                    session.getTransaction().commit();
                        return true;
        }
     }
     
     public static Usuarios obtenerUsuarioSql(String usuario){  
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuarios> q = session.createNamedQuery("get_usuario", Usuarios.class);
                q.setParameter("username", usuario);
                    return (Usuarios) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
