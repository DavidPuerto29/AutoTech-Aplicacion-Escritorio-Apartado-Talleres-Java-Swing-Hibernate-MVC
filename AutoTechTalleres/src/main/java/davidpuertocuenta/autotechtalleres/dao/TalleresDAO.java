/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.dao;

import davidpuertocuenta.autotechtalleres.clases.Talleres;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author David Puerto Cuenca
 */
public class TalleresDAO {
    public static List<Talleres> obtenerTodosTalleresSql(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Talleres> q = session.createNamedQuery("get_todos_talleres", Talleres.class);
                return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public static void eliminarTallerSql(Talleres taller){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
                session.remove(taller);
                    session.getTransaction().commit();
        }
    }
   
}
