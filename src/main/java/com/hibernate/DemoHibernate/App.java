package com.hibernate.DemoHibernate;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
		con.addAnnotatedClass(Person.class);
		con.configure();
		// SessionFactory sessionFactory = new
		// AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = con.buildSessionFactory(reg);

		// save(session);
		//addPeople(sessionFactory);
		//fetchData(sessionFactory);
		//fetchPeople(sessionFactory);
		ObjectStateTest(sessionFactory);
	}

	public static void addPeople(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Random r = new Random();
		for(int i=1;i<=50;++i){
			Person person = new Person();
			person.setId(i);
			person.setName("Name"+i);
			person.setMark(r.nextInt(100));
			session.save(person);
		}
		
		session.getTransaction().commit();
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
		Query q1 = session.createQuery("from Person where id =1");
		// Alien alien = (Alien) session.get(Alien.class, 105);

		// System.out.println(alien);
		// Laptop laptop = (Laptop) session.get(Laptop.class, 23);
		//// System.out.println(laptop);
		//Person p = (Person) q1.uniqueResult();
		Person p = (Person) session.get(Person.class, 1);
		q1.setCacheable(true);
		System.out.println(p);

		session.getTransaction().commit();

		session.close();
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		// Alien alien2 = (Alien) session2.get(Alien.class, 105);
		// System.out.println(alien2);
		// Laptop laptop2 = (Laptop) session2.get(Laptop.class, 23);
		// System.out.println(laptop2);
		Query q2 = session2.createQuery("from Person where id =1");
		q2.setCacheable(true);
		//Person p2 = (Person) q2.uniqueResult();
		Person p2 = (Person) session2.get(Person.class, 1);

		System.out.println(p2);

		session2.getTransaction().commit();
		session2.close();
	}
	
	public static void fetchPeople(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		/*
		Query q1 = session.createQuery("from Person where mark > 50");
		List<Person> list = q1.list();
		for(Person p: list){
			System.out.println(p);
		}
		*/
		
		/*
		int num= 5;
		Query q2 = session.createQuery("select id, name, mark from Person where id = :num");
		q2.setParameter("num", num);
		Object[] obj = (Object[]) q2.uniqueResult();
		
		System.out.println(obj[0] + " : " + obj[1] + " : " + obj[2]);
		*/
		
		//SQL Query ( Native Query)
		SQLQuery sqlQuery = session.createSQLQuery("select name, mark from People where mark > 60");
		sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List people = sqlQuery.list();
		for(Object p: people){
			Map m = (Map)p;
			System.out.println(m.get("name") + " : " + m.get("mark"));
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	private static void ObjectStateTest(SessionFactory sessionFactory){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Person person = new Person();
		person.setId(101);
		person.setName("Ayah Hamdan");
		person.setMark(80);
		session.save(person);
		person.setMark(89);
		session.getTransaction().commit();
		person.setMark(80);person.setMark(80);
		session.close();
	}

}
