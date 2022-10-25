package in.co.rays.hibernate5;

	import java.util.Iterator;
	import java.util.List;

	import javax.persistence.Query;
	import javax.persistence.criteria.CriteriaBuilder;
	import javax.persistence.criteria.CriteriaQuery;
	import javax.persistence.criteria.Root;

	import org.hibernate.HibernateException;
	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.Transaction;

	public class testUser {

		public static void main(String[] args) throws Exception {
			
		 // testAdd();
		  // testUpdate();
		  // testDelete();
		   testGet();
		  //testList();
			//testNamedQuery();
		   
		}

		private static void testNamedQuery() {
			SessionFactory sf = UserHibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			Query query = session.getNamedQuery("allUser");
			List<User> list = query.getResultList();
			for (User user : list) {
			System.out.println(user.getId());
			System.out.println(user.getFname());
			System.out.println(user.getLname());
			System.out.println(user.getPwd());
			System.out.println(user.getUserName());
			}
			
		}

		private static void testList() {
			
			SessionFactory sf = UserHibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query =
			builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root);
			Query q = session.createQuery(query);
			List<User> user = q.getResultList();
		    Iterator<User> list = user.iterator();
		    System.out.println();
		    System.out.println("ID\tFirstName\tLastName\tPassword\t UserName");
			while (list.hasNext()) {
				User u = list.next();
				
				System.out.println(u.getId()+"\t"+u.getFname() +"\t\t"+ u.getLname()+"\t\t"+u.getPwd()+"\t\t"+u.getUserName());
			
				
			
			}
			
		}

		private static void testGet() throws Exception {
				SessionFactory sf = UserHibernateUtil.getSessionFactory();
				Session session = sf.openSession();
				User u = session.get(User.class, 1);
				System.out.println(u.getId());
				System.out.println(u.getFname());
				System.out.println(u.getLname());
				System.out.println(u.getUserName());
				System.out.println(u.getPwd());
				session.close();
				sf.close();
				System.out.println();
				System.out.println("this time data is get from SECOND-LEVEL-CACHE");
				System.out.println();
				
				System.out.println(u.getId());
				System.out.println(u.getFname());
				System.out.println(u.getLname());
				System.out.println(u.getUserName());
				System.out.println(u.getPwd());
				
				UserHibernateUtil.shutdown();
		}

		private static void testDelete() {
			SessionFactory sf = UserHibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			Transaction tx = null;
			try {
			      tx = session.beginTransaction();
			      User user = new User();
			      user.setId(4);
			      session.delete(user);
			      tx.commit();
			      System.out.println("Deleted...!!!");
			}
			catch (HibernateException e) {
			tx.rollback();
			} 
			finally {
			session.close();
			UserHibernateUtil.shutdown();
			}
			
			
		}

		private static void testUpdate() throws Exception {
			
				SessionFactory sf = UserHibernateUtil.getSessionFactory();
				Session session = sf.openSession();
				Transaction tx = null;
				try {
				tx = session.beginTransaction();
				User user = new User();
				user.setId(2);
				user.setUserName("Vijaysharma@123");
				user.setFname("Vijay");
				user.setLname("Sharma");
				user.setPwd("123457");
			
				session.update(user);
				tx.commit();
				System.out.println("Updated...!!!");
				} 
				catch (HibernateException e) {
				   tx.rollback();
				}
				finally {
				   session.close();
				  UserHibernateUtil.shutdown();
				}
				
			
			
		}

		public static void testAdd() throws Exception {

			SessionFactory sf = UserHibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			Transaction tx = null;

			try {
				tx = session.beginTransaction();
				User user = new User();
				// user.setId(1);
				user.setFname("Ravi");
				user.setLname("Patidar");
				user.setPwd("123458");
				user.setUserName("rvpatidar12@gmail.com");
				
				
				session.save(user);
				tx.commit();
				System.out.println("done...!!!");
				
			} 
			catch (HibernateException e) {
				tx.rollback();
			} 
			finally {
				session.close();
				UserHibernateUtil.shutdown();
			}
		
		}
}



