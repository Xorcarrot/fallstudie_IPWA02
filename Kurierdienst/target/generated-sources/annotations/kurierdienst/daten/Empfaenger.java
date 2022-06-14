package kurierdienst.daten;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Die Klasse Empfaenger enthält alle wichtigen Parameter um eine Sendung an eine bestimmte Person versenden zu können. Sie ist eine Entity die in Beziehung mit 
 * der Klasse Sendung steht. 
 * 
 * @author xorca
 *
 */
@Entity
@Table
public class Empfaenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empfaengerNummer;
	@Column(name = "vorname")
	private String vorname;
	@Column(name = "nachname")
	private String nachname;
	@Column(name = "land")
	private String land;
	@Column(name = "wohnort")
	private String wohnort;
	@Column(name = "postleitzahl")
	private int postleitzahl;
	@Column(name = "adresse")
	private String adresse;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "emp", cascade = CascadeType.ALL, targetEntity = Sendung.class)
	private Set<Sendung> sendung;	
	
	/**
	 * Konstruktor für einen Empfaenger.
	 * 
	 * @param vorname Vorname eines Empfängers
	 * @param nachname Nachname eines Empfängers
	 * @param land Land des Empfängers
	 * @param wohnort Wohnort des Empfängers
	 * @param postleitzahl Postleitzahl des Empfängers
	 * @param adresse Adresse des Empfängers
	 */
	public Empfaenger(String vorname, String nachname, String land, String wohnort, int postleitzahl, String adresse) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.land = land;
		this.wohnort = wohnort;
		this.postleitzahl = postleitzahl;
		this.adresse = adresse;
		this.sendung = new HashSet<Sendung>();
	}
	
	/**
	 * Standart-Konstruktor
	 */
	public Empfaenger() {
		
	}

	//Nachfolgend die gesamten Getter- und Setter der Klasse
	public int getEmpfaengerNummer() {
		return empfaengerNummer;
	}

	public void setEmpfaengerNummer(int empfaengerNummer) {
		this.empfaengerNummer = empfaengerNummer;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getWohnort() {
		return wohnort;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	public int getPostleitzahl() {
		return postleitzahl;
	}

	public void setPostleitzahl(int postleitzahl) {
		this.postleitzahl = postleitzahl;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Set<Sendung> getSendung() {
		return sendung;
	}

	public void setSendung(Set<Sendung> sendung) {
		this.sendung = sendung;
	}
	
	
	//Nachfolgend die überschriebene hashCode und equals-Methode
	@Override
	public int hashCode() {
		return Objects.hash(adresse, land, nachname, postleitzahl, sendung, vorname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empfaenger other = (Empfaenger) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(land, other.land)
				&& Objects.equals(nachname, other.nachname) && postleitzahl == other.postleitzahl
				&& Objects.equals(vorname, other.vorname);
	}

	/**
	 * Methode die es ermöglicht eine weitere Sendung dem gleichen Empfänger hinzuzufügen. 
	 * 
	 * @param sen Sendung die mit dem Empfänger in einer Beziehung steht
	 */
	public void saveSendung(Sendung sen) {
		this.sendung.add(sen);
	}

}
