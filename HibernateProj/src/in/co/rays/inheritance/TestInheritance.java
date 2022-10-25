package in.co.rays.inheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestInheritance {
	
	public static void main(String[] args) {
		
	    CreditCard c = new CreditCard();
	    c.setId(1);
	    c.setAmount(25000);
	    c.setPaymentType("cheque");
	    c.setCreditCardType("Visa");
	    
	    
	    Cheque ch = new Cheque();
	    ch.setId(2);
	    ch.setAmount(30000);
	    c.setPaymentType("cheque");
	    ch.setChequeNumber(21548763);
	    ch.setBankName("BOI");
	    
	    SessionFactory sf =new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		session.save(c);
		session.save(ch);
		t.commit();
		session.close();
		
		System.out.println("Inserted");

	}

}
