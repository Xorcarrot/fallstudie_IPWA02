package kurierdienst.datenbank;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import kurierdienst.daten.Brief;
import kurierdienst.daten.Empfaenger;
import kurierdienst.daten.Paket;
import kurierdienst.daten.Sendung;

/**
 * Stellt keine aktullen Methoden mehr zur Verfügung. Dafür ist die neu Klasse SendungDAO.java
 * 
 * @author xorca
 *
 */
public class BriefDAO {
	@Deprecated
	public void speichern(List<Brief> sen) {  
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Brief.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		session.save(sen);
		
		tx.commit();
		
		session.close();
		sf.close();
	}
	
	@Deprecated
	public Brief sucheNachBrief(int id) {
		
		Brief bri = null;
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Brief.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		bri = (Brief) session.get(Brief.class, id);
		
		tx.commit();
		sf.close();
		
		return bri;
	}

}
