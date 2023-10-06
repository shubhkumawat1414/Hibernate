package in.co.Hibernate5;

import java.util.HashMap;
import java.util.Map;



import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory != null) {
			return sessionFactory;
		}
	//1	Set Hibernate Properties
		 Map<String, Object> settings = new HashMap();
		settings.put("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
		settings.put("hibernate.connection.url","jdbc:mysql://localhost:3307/hibernate");
		settings.put("hibernate.connection.username", "root");
		settings.put("hibernate.connection.password", "root");
		settings.put("hibernate.show_sql", "true");
		settings.put("hibernate.hbm2ddl.auto", "update");

	
	
	// 2. Create registry builder 
	StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
	
	// 3.Set registryproperties
	registryBuilder.applySettings(settings);
	
	// 4. Create registry 
	registry = registryBuilder.build();
	
	// 5. Register entity class
	MetadataSources sources = new MetadataSources(registry);
			sources.addAnnotatedClass(User.class);
			Metadata metadata = sources.getMetadataBuilder().build();
	
	//6. Create session factory
			sessionFactory = metadata.getSessionFactoryBuilder().build();
			return sessionFactory;
			}
	//Destroy registry
	
	public static void Shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
	}
	
	}  

