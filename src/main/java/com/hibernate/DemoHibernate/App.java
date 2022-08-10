package com.hibernate.DemoHibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/*
 * Hello world!
 *
 */
public class App {
	private static final Logger demoLogger = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) {
		System.out.println("Hello World!");

		// demoLogger.info("Start of App Class---005");
		// demoLogger.error("Start of App Class---0025");
		// demoLogger.trace("Start of App Clas---s00436");
		// demoLogger.debug("Start of App Clas---s0047");

		Configuration con = new Configuration();
		con.addAnnotatedClass(Alien.class);
		con.addAnnotatedClass(ex.class);
		con.addAnnotatedClass(Laptop.class);
		con.configure();
		// SessionFactory sessionFactory = new
		// AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = con.buildSessionFactory(reg);

		// save(session);
		fetchData(sessionFactory);
	}

	public static void save(Session session) {
		AlienName name = new AlienName();
		name.setFirstName("Ayah");
		name.setMiddleName("Khaled");
		name.setLastName("Hamdan");
		Alien alien = new Alien();
		alien.setAlienId(94);
		alien.setAlienName(name);
		alien.setColor("pink");

		Laptop laptop = new Laptop();
		laptop.setLaptopId(94);
		laptop.setLaptopName("Dell");
		alien.getLaptop().add(laptop);
		laptop.getAlien().add(alien);
		session.beginTransaction();
		session.save(alien);
		session.save(laptop);
		session.getTransaction().commit();
	}

	public static void fetchData(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q1 = session.createQuery("from ex where id =1");
		// Alien alien = (Alien) session.get(Alien.class, 105);

		// System.out.println(alien);
		// Laptop laptop = (Laptop) session.get(Laptop.class, 23);
		//// System.out.println(laptop);
		ex ex = (ex) q1.uniqueResult();
		// ex ex = (ex) session.get(ex.class, 1);
		q1.setCacheable(true);
		System.out.println(ex);

		session.getTransaction().commit();

		session.close();
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		// Alien alien2 = (Alien) session2.get(Alien.class, 105);
		// System.out.println(alien2);
		// Laptop laptop2 = (Laptop) session2.get(Laptop.class, 23);
		// System.out.println(laptop2);
		Query q2 = session2.createQuery("from ex where id =1");
		q2.setCacheable(true);
		ex ex2 = (ex) q2.uniqueResult();
		// ex ex2 = (ex) session2.get(ex.class, 1);

		System.out.println(ex2);

		session2.getTransaction().commit();
		session2.close();
	}

}
