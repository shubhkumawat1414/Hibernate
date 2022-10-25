package in.co.rays.Association.OneToMany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class testAssociation {
	
	public static void main(String[] args) {
		
		AuctionItem i = new AuctionItem();
		i.setDescription("Audi");
		
		Bid b1 = new Bid();
		b1.setAmount(200);
		
		Bid b2 = new Bid();
		b2.setAmount(300);
		
		Bid b3 = new Bid();
		b3.setAmount(100);
		
		Set<Bid> s = new  HashSet<Bid>();
		s.add(b1);
		s.add(b2);
		s.add(b3);
		
		i.setBids(s);
		
		SessionFactory sf =new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		session.save(i);
		t.commit();
		sf.close();
		System.out.println("One to Many done...!!!");
	}

}
