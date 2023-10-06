package in.co.Hibernate5;

import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class testAdd {
	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testGet();
		testList();
	}

	private static void testAdd() {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session session = factory.openSession();

		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			User u = new User();

			u.setFname("utkarsh");
			u.setLname("verma");
			u.setUserName("uverma");
			u.setPwd("utkarsh8");

			session.save(u);
			System.out.println(u.getId());
			System.out.println(u.getFname());
			tx.commit();

		} catch (HibernateException e) {
			tx.rollback();
		} finally {

		}

		session.close();

		HibernateUtil.Shutdown();

	}

	public static void testUpdate() throws Exception {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			User user = new User();
			user.setId(1);
			user.setFname("Shreya");

			session.update(user);
			tx.commit();

		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.Shutdown();
		}

	}

	private static void testDelete() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			User user = new User();
			user.setId(2);
			session.delete(user);
			tx.commit();

		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.Shutdown();
		}

	}

	private static void testGet() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		User u = (User) session.get(User.class, 3);

		System.out.println(u.getId());
		System.out.println(u.getFname());
		System.out.println(u.getLname());
		System.out.println(u.getUserName());
		System.out.println(u.getPwd());

	}

	private static void testList() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<User> query = builder.createQuery(User.class);
		
		Root<User> root = query.from(User.class);
		
		query.select(root);
		
		Query q = session.createQuery(query);
		
		List<User> user = q.getResultList();
		
		Iterator<User> list = user.iterator();
		
		while (list.hasNext()) {
			
			User u = list.next();
			System.out.println(u.getId());
			System.out.println(u.getFname());
			System.out.println(u.getLname());
			System.out.println(u.getUserName());
			System.out.println(u.getPwd());
			
		}
		

	}

}
