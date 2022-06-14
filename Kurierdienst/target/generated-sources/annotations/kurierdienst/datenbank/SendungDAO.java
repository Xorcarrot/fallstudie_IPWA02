package kurierdienst.datenbank;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.web.util.NestedServletException;

import kurierdienst.daten.Brief;
import kurierdienst.daten.Empfaenger;
import kurierdienst.daten.Paket;
import kurierdienst.daten.Sendung;

/**
 * Diese Klasse übernimmt die Suche nach Sendungen in der Datenbank
 * 
 * @author xorca
 *
 */
public class SendungDAO {
	
	/**
	 * Diese Methode sucht in der Datenbank nach der Sendung die mit der eingegeben Referenznummer übereinstimmt.
	 * 
	 * @param id Referenznummer der gesuchten Sendung
	 * @return Die gesuchte Sendung wird rückgegeben
	 * @throws NestedServletException Fehler der auftritt wenn eine falsche Referenznummer eingegeben wird
	 * @throws NullPointerException Fehler der auftritt wenn eine falschen Referenznummer eingegeben wird
	 */
	public Sendung sucheNachPaket (int id) throws NestedServletException, NullPointerException {
		
		Sendung pak = null;
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Paket.class).addAnnotatedClass(Brief.class).addAnnotatedClass(Sendung.class).addAnnotatedClass(Empfaenger.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Sendung where referenznummer=" + id);
		pak = (Sendung)q.uniqueResult();
		
		tx.commit();
		session.close();
		sf.close();
		
		return pak;
	}
	
	/**
	 * Diese Methode gibt eine Liste aller Sendung die in der Datenbank vorhanden sind zurück.
	 * 
	 * @return List mit allen in der Datenbank vorhanden Sendungen
	 */
	public List<Sendung> allePaketeSuchen() {
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Paket.class).addAnnotatedClass(Brief.class).addAnnotatedClass(Sendung.class).addAnnotatedClass(Empfaenger.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Sendung");
		List<Sendung> senList = q.list();
		
		tx.commit();
		session.close();
		sf.close();
		
		return senList;
	}
	
	/**
	 * Diese Methode schaltet den Status der, über die Referenznummer angegeben Sundung, eins weiter. 'a'->'e'->'i'->'z'
	 * 
	 * @param id Referenznummer bei der, der Status weitergeschaltet werden soll
	 */
	public void statusWeiterschalten(int id) {
		
		char neuerStatus;
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Paket.class).addAnnotatedClass(Brief.class).addAnnotatedClass(Sendung.class).addAnnotatedClass(Empfaenger.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query qStatus = session.createQuery("select status from Sendung where referenznummer = :num");
		qStatus.setParameter("num", id);
		char status = (char) qStatus.uniqueResult();
		
		if(status == 'a') {
			neuerStatus = 'e';
		} else if (status == 'e') {
			neuerStatus = 'i';
		} else {
			neuerStatus = 'z';
		}
		
		Query q = session.createQuery("update Sendung set status = :sta where referenznummer = :num");
		q.setParameter("sta", neuerStatus);
		q.setParameter("num", id);
		q.executeUpdate();
		
		tx.commit();
		session.close();
		sf.close();
	}

}
