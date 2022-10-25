package in.co.rays.pojo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class testUser {
	public static void main(String[] args) {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testGet();
		// testList();
		// testCreateQueryWhere();
		// testCreateQueryToGetSomeColumn();
		// testOrderBy();
		// testAggregateFuntion();
		// testGroupBy();
		// testCriteria();
		// testProjection();
		// testCriteriaConditionAND();
		// testCriteriaConditionOR();
		// testFirstLevelCache();
		//testSecondlevelCache();
		//testMerge();

	}

	private static void testMerge() {
		/* to run merge we have to go to User.hbm.xml file and comment out this (<cache usage="read-only"/> )*/
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		User u = (User) s.get(User.class, 1);
		System.out.println(u.getFname());
		s.close();
		u.setFname("Ravi");
		Session s1 = sf.openSession();
		Transaction tx = s1.beginTransaction();
		s1.merge(u);
		tx.commit();
		s1.close();
		
    }

	private static void testSecondlevelCache() {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Query q = s.createQuery("from User");// createQuery is SELECT HQL Statement.
		List<User> l = q.list();
		Iterator<User> it = l.iterator();

		User u;
		while (it.hasNext()) {
			u = it.next();
			System.out.print(u.getId() + "\t");
			System.out.print(u.getFname() + "\t");
			System.out.print(u.getLname() + "\t");
			System.out.print(u.getUserName());
			System.out.println();

		}
		s.close(); /*
					 * if do not close session object here then it will get data from database and
					 * not from firt level cache
					 */
		sf.close(); /*
					 * we will close session factory object here so now it will clear data from
					 * first level cache
					 */
		System.out.println("\n");

		User u1;
		Iterator<User> it1 = l.iterator();
		while (it1.hasNext()) {
			u1 = it1.next();
			System.out.print(u1.getId() + "\t");
			System.out.print(u1.getFname() + "\t");
			System.out.print(u1.getLname() + "\t");
			System.out.print(u1.getUserName());
			System.out.println();
		}

		// s.close();

	}

	private static void testFirstLevelCache() {

		/*
		 * in this level when we create object of session it will first get data from
		 * DATABASE then without closing session object second time it will get data
		 * from "First Level Cache".
		 */
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Query q = s.createQuery("from User");// createQuery is SELECT HQL Statement.
		List<User> l = q.list();
		Iterator<User> it = l.iterator();

		User u;
		while (it.hasNext()) {
			u = it.next();
			System.out.print(u.getId() + "\t");
			System.out.print(u.getFname() + "\t");
			System.out.print(u.getLname() + "\t");
			System.out.print(u.getUserName());
			System.out.println();

		}
		s.close();/*
					 * session object is closed now the second time it will get data from first
					 * level cacheFF
					 */
		System.out.println("\n");
		/*
		 * if we close session object here that is (s.close();) and then we have to
		 * create session object again then it will get data both time directly from
		 * DATABASE
		 */
		User u1;
		Iterator<User> it1 = l.iterator();
		while (it1.hasNext()) {
			u1 = it1.next();
			System.out.print(u1.getId() + "\t");
			System.out.print(u1.getFname() + "\t");
			System.out.print(u1.getLname() + "\t");
			System.out.print(u1.getUserName());
			System.out.println();
		}

	}

	private static void testCriteriaConditionOR() {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Criteria c = s.createCriteria(User.class);
		c.add(Restrictions.or(Restrictions.like("fname", "r%"), Restrictions.eq("id", 3)));

		List<User> l = c.list();
		Iterator<User> it = l.iterator();

		User u;
		while (it.hasNext()) {
			u = it.next();

			System.out.print(u.getId() + "\t");
			System.out.print(u.getFname() + "\t");
			System.out.print(u.getLname() + "\t");
			System.out.print(u.getUserName());
			System.out.println();
		}
		s.close();

	}

	private static void testCriteriaConditionAND() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Criteria c = s.createCriteria(User.class);
		c.add(Restrictions.like("fname", "r%"));
		c.add(Restrictions.eq("id", 8));
		List<User> l = c.list();
		Iterator<User> it = l.iterator();

		User u;
		while (it.hasNext()) {
			u = it.next();

			System.out.print(u.getId() + "\t");
			System.out.print(u.getFname() + "\t");
			System.out.print(u.getLname() + "\t");
			System.out.print(u.getUserName());
			System.out.println();
		}
		s.close();

	}

	private static void testProjection() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Criteria c = s.createCriteria(User.class);
		ProjectionList p = Projections.projectionList();
		p.add(Projections.property("id"));
		p.add(Projections.property("fname"));
		p.add(Projections.property("lname"));
		p.add(Projections.property("userName"));
		c.setProjection(p);
		List l = c.list();

		Iterator it = l.iterator();

		Object[] o;
		while (it.hasNext()) {

			o = (Object[]) it.next();
			Integer id = (Integer) o[0];
			String fname = (String) o[1];
			String lname = (String) o[2];
			String userName = (String) o[3];
			System.out.println(id + "\t" + fname + "\t" + lname + "\t" + userName);
		}
		s.close();
	}

	private static void testCriteria() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Criteria c = s.createCriteria(User.class);
		List<User> l = c.list();
		Iterator<User> it = l.iterator();

		User u;
		while (it.hasNext()) {
			u = it.next();
			System.out.print(u.getId() + "\t");
			System.out.print(u.getFname() + "\t");
			System.out.print(u.getLname() + "\t");
			System.out.print(u.getUserName());
			System.out.println();
		}
		s.close();
	}

	private static void testGroupBy() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Query q = s.createQuery("select u.fname from User u");// createQuery is SELECT HQL Statement.
		List l = q.list();
		Iterator it = l.iterator();
		Object[] c;
		while (it.hasNext()) {
			c = (Object[]) it.next();
			String st = (String) c[0];
			System.out.println(st);
//           	 Object in =  c[1];
//           	 System.out.println(in);

		}
		s.close();
	}

	private static void testAggregateFuntion() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Query q = s.createQuery("select count(*) from User");// createQuery is SELECT HQL Statement.
		List l = q.list();
		Object r = l.get(0);
		System.out.println(r);
		s.close();

	}

	private static void testOrderBy() {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Query q = s.createQuery("from User where fname like'r%' order by fname");// createQuery is SELECT HQL Statement.
		List l = q.list();
		Iterator it = l.iterator();

		User u;
		System.out.println("ID\tName");
		while (it.hasNext()) {

			u = (User) it.next();
			System.out.println(u.getFname());
		}
		s.close();

	}

	private static void testCreateQueryToGetSomeColumn() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Query q = s.createQuery("select u.id, u.fname from User u ");// createQuery is SELECT HQL Statement.
		List l = q.list();
		Iterator it = l.iterator();

		Object[] c;
		System.out.println("ID\tName");
		while (it.hasNext()) {

			c = (Object[]) it.next();

			Integer id = (Integer) c[0];
			String st = (String) c[1];
			System.out.println(id + "\t" + st);
		}
		s.close();

	}

	private static void testCreateQueryWhere() {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Query q = s.createQuery("From User where fname like'r%'");// createQuery is SELECT HQL Statement.
		List<User> l = q.list();
		Iterator<User> it = l.iterator();

		User u;
		while (it.hasNext()) {
			u = it.next();
			System.out.print(u.getId() + "\t" + u.getFname() + "\t" + u.getLname() + "\t" + u.getUserName());
			System.out.println();
		}
		s.close();

	}

	private static void testList() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		Query q = s.createQuery("from User");// createQuery is SELECT HQL Statement.
		List<User> l = q.list();
		Iterator<User> it = l.iterator();

		User u;
		while (it.hasNext()) {
			u = it.next();
			System.out.print(u.getId() + "\t");
			System.out.print(u.getFname() + "\t");
			System.out.print(u.getLname() + "\t");
			System.out.print(u.getUserName());
			System.out.println();
		}
		s.close();

	}

	private static void testGet() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();// Load Factory
		Session s = sf.openSession(); // Create Session
		User u = (User) s.get(User.class, 1);
		System.out.print(u.getId() + "\t");
		System.out.print(u.getFname() + "\t");
		System.out.print(u.getLname() + "\t");
		System.out.print(u.getUserName());

		s.close();

	}

	private static void testDelete() {
		User u = new User();
		u.setId(2);
		u.setFname("shubam");
		u.setLname("sirota");
		u.setUserName("123");

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.delete(u);
		t.commit();
		s.close();

	}

	private static void testUpdate() {

		User u = new User();
		u.setId(3);
		u.setFname("shubam");
		u.setLname("sirota");
		u.setUserName("123");

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.update(u);
		t.commit();
		s.close();

	}

	private static void testAdd() {
		User u = new User();
		u.setFname("ravi");
		u.setLname("joshi");
		u.setUserName("joshiRavi@096");

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.save(u);
		t.commit();
		s.close();

	}

}
