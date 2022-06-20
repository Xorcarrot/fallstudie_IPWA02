package kurierdienst.daten;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import kurierdienst.daten.Empfaenger;
import kurierdienst.datenbank.EmpfaengerDAO;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

/**
 * Die Klasse Sendung ist die Superklasse von der Klasse Brief und Paket. Sie ist auch eine Entity und gibt für die beiden Unterklassen 
 * die Art des Tables vor. Auch stellt die Klasse Sendung mehrer Methoden für die Datenbanksuche zur Verfügung. 
 * 
 * @author xorca
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ArtDerSendung")
public class Sendung {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int referenznummer;
	@Column(name = "laenge")
	private int laenge;
	@Column(name = "breite")
	private int breite;
	@Column(name = "hoehe")
	private int hoehe;
	@Column(name = "gewicht")
	private int gewicht;
	/* 
	 * 'a' = Auftrag erhalten
	 * 'e' = entgegengenommen
	 * 'i' = in zustellung
	 * 'z' = zugestellt
	 */
	@Column(name = "status")
	private char status;
	@ManyToOne(fetch=FetchType.EAGER, targetEntity = Empfaenger.class)
	@JoinColumn(name= "empfaengerNummer")
	private Empfaenger emp;
	@Deprecated
	@Transient
	private Empfaenger sucheEmpfaenger;
	
	/**
	 * Konstruktor für eine Sendung. Die Referenznummer ist die eine eindeutig zuweisbare Zahl für Sendungen und wird automatisch generiert. 
	 * Das Parameter status wird bei jedem neu erzeugten Sendungs-Objekt automatisch auf 'a' gesetzt. 
	 * 	Die einzelnen char vom Parameter stellen folgende Information bereit:
	 * 		'a' = Auftrag erhalten
	 * 		'e' = entgegengenommen
	 * 		'i' = in Zustellung
	 * 		'z' = zugestellt
	 * 
	 * @param laenge Länge der Sendung
	 * @param breite Breite der Sendung
	 * @param hoehe Höhe der Sendung
	 * @param gewicht Gewicht der Sendung
	 */
	public Sendung(int laenge, int breite, int hoehe, int gewicht) {
		this.laenge = laenge;
		this.breite = breite;
		this.hoehe = hoehe;
		this.gewicht = gewicht;
		this.status = 'a';
	}
	
	/**
	 * Standart-Konstruktor für Sendung. 
	 */
	public Sendung() {
		
	}
	
	//Getter- und Settermethoden für die Klasse Sendung
	public int getLaenge() {
		return laenge;
	}
	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}
	public int getBreite() {
		return breite;
	}
	public void setBreite(int breite) {
		this.breite = breite;
	}
	public int getHoehe() {
		return hoehe;
	}
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}
	public int getGewicht() {
		return gewicht;
	}
	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	public int getReferenznummer() {
		return referenznummer;
	}
	public void setReferenznummer(int referenznummer) {
		this.referenznummer = referenznummer;
	}
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Empfaenger getEmp() {
		return emp;
	}

	public void setEmp(Empfaenger emp) {
		this.emp = emp;
	}
	
	//Nicht wichtig
	@Deprecated
	public Empfaenger getSucheEmpfaenger() {
		EmpfaengerDAO eDAO = new EmpfaengerDAO();
		Empfaenger e = eDAO.sucheEmpfaengerUberSendung(this.referenznummer);
		return e;
	}

	// Nicht wichtig
	@Deprecated
	public void setSucheEmpfaenger(Empfaenger sucheEmpfaenger) {
		this.sucheEmpfaenger = sucheEmpfaenger;
	}

	/**
	 * Diese Methode sucht anhand seiner eigenen Referenznummer nach seinem Empfänger.
	 * 
	 * @return Gibt den Empfänger dem die Sendung gehört zurück
	 */
	public Empfaenger searchEmp() {
		EmpfaengerDAO eDAO = new EmpfaengerDAO();
		Empfaenger e = eDAO.sucheEmpfaengerUberSendung(this.referenznummer);
		return e;
	}
	

}
