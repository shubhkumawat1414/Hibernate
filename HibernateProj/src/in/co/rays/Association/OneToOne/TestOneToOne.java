package in.co.rays.Association.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestOneToOne {
	
	public static void main(String[] args) {
		
		Address a = new Address();
		a.setCity("Shujalpur");
		a.setState("Madhya Pradesh");
		
		Employee e = new Employee();
		e.setFname("Ravi");
		e.setLname("Patidar");
	    e.setAddress(a);
		
		SessionFactory sf =new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		session.save(e);
		t.commit();
		sf.close();
		System.out.println("Inserted");
	}

}
