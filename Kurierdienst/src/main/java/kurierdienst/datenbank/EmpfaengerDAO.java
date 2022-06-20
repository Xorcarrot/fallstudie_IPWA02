package kurierdienst.datenbank;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import kurierdienst.daten.Brief;
import kurierdienst.daten.Empfaenger;
import kurierdienst.daten.Paket;
import kurierdienst.daten.Sendung;

/**
 * Diese Klasse übernimmt alle Aufgaben die für den Zugriff auf die Daten aus der Empfaenger-Datenbank sind, aber auch das Speichern eines neuen Empfängers 
 * mit der dazugehörigen Sendung.
 * 
 * @author xorca
 *
 */
public class EmpfaengerDAO {
	
	/**
	 * Diese Methode speichert den Empfänger mit der Sendung in der Datenbank. 
	 * 
	 * @param emp Ein Empfänger der auf der Datenbank hinterlegt werden soll
	 * @param pak Eine Sendung die mit dem Empfänger in Beziehung steht und auf der Datenbank gespeichert wird
	 */
	public void speichern(Empfaenger emp, Sendung pak) {  
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Empfaenger.class).addAnnotatedClass(Paket.class).addAnnotatedClass(Brief.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		session.save(emp);
		session.save(pak);
		
		tx.commit();
		
		session.close();
		sf.close();
	}
	
	/**
	 * Diese Methode durchsucht die Datenbank ob der Empfänger bereits vorhanden ist. Wenn das zutrift gibt sie die empfaengernummer zurück:
	 * 
	 * @param emp Empfänger der gesucht werden soll
	 * @return Die eindeutige identifizierbare empfaengernummer des Empfängers. Wenn er nicht vorhanden ist wird eine 0 zurückgegeben
	 */
	public int sucheEmpfaengerNummer(Empfaenger emp) {
		
		int empId = 0;
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Empfaenger.class).addAnnotatedClass(Paket.class).addAnnotatedClass(Brief.class).addAnnotatedClass(Sendung.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Empfaenger");
		List<Empfaenger> empfaenger = q.list();
		
		for(Empfaenger e : empfaenger) {
			if(emp.equals(e)) {
				empId = e.getEmpfaengerNummer();
			}
		}
		
		tx.commit();
		
		session.close();
		sf.close();
		
		return empId;
	}
	
	/**
	 * Diese Methode fügt einem bereits vorhandenen Empfänger eine neue Sendung hinzu.
	 * 
	 * @param sen Die Sendung die den Empfänger hinzugefügt wird
	 * @param id Die empfaengernummer des Empfängers 
	 */
	public void sendungUpdaten(Sendung sen, int id) {
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Empfaenger.class).addAnnotatedClass(Paket.class).addAnnotatedClass(Brief.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Empfaenger where empfaengerNummer=" + id);
		Empfaenger emp = (Empfaenger)q.uniqueResult();
		
		sen.setEmp(emp);
		emp.getSendung().add(sen);
		
		session.save(sen);
		
		tx.commit();
		
		session.close();
		sf.close();
	}
	
	/**
	 * Diese Methode sucht über die Referenznummer einer Sendung den dazugehörigen Empfänger.
	 * 
	 * @param referenznummer Die referenznummer der Sendung, bei der der Empfänger über die Datenbank gesucht wird
	 * @return Der gesuchte Empfänger wird rückgegeben
	 */
	public Empfaenger sucheEmpfaengerUberSendung(int referenznummer) {
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Empfaenger.class).addAnnotatedClass(Paket.class).addAnnotatedClass(Brief.class).addAnnotatedClass(Sendung.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Sendung where referenznummer= :num");
		q.setParameter("num", referenznummer);
		Sendung sen = (Sendung) q.uniqueResult();
		
		Empfaenger emp = sen.getEmp();
		
		tx.commit();
		
		session.close();
		sf.close();
		
		return emp;
	}

}
