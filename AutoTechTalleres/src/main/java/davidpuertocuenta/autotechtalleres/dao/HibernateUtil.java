package davidpuertocuenta.autotechtalleres.dao;

import davidpuertocuenta.autotechtalleres.clases.Citas;
import davidpuertocuenta.autotechtalleres.clases.Talleres;
import davidpuertocuenta.autotechtalleres.clases.Usuarios;
import davidpuertocuenta.autotechtalleres.clases.Empleados;
import davidpuertocuenta.autotechtalleres.clases.Vehiculos;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Clase destinada a configuración para el gestor de datos
 * sql de hibernate.
 *
 * @author David Puerto Cuenca
 * @version 1.0
 */

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try (StandardServiceRegistry registry = new
                    StandardServiceRegistryBuilder().build();) {
                         
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Citas.class)
                        .addAnnotatedClass(Talleres.class)
                        .addAnnotatedClass(Empleados.class)
                        .addAnnotatedClass(Vehiculos.class)
                        .addAnnotatedClass(Usuarios.class)
                        .buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
