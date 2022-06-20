package kurierdienst.daten;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import kurierdienst.daten.Empfaenger;

/**
 * Die Klasse Paket erweitert die Superklasse Sendung um zwischen Paketen und Briefen zu unterscheiden. Es ist eine Entity um die 
 * dauerhafte Speicherung seiner Daten zu ermöglichen. Das Parameter Sperrgut stellt den unterschied zu der Klasse Brief da. 
 *  
 * @author xorca
 *
 */
@Entity(name = "Paket")
@DiscriminatorValue("PAKET")
public class Paket extends Sendung {
	
	@Column(name = "sperrgut")
	private boolean sperrgut;

	/**
	 * Der Paket-Konstruktor benötigt die gleichen Parameter wie die Superklasse Sendung und erweitert sie automatisch um das Parameter Sperrgut.
	 * 
	 * @param laenge Die Länge des Pakets max. 2000mm
	 * @param breite Die Breite des Pakets max. 2000mm
	 * @param hoehe Die Höhe des Pakets max. 2000mm
	 * @param gewicht Das Gewicht des Pakets max. 50000g
	 */
	public Paket(int laenge, int breite, int hoehe, int gewicht) {
		super(laenge, breite, hoehe, gewicht);
		if(super.getLaenge() >= 1000 || super.getBreite() >= 1000 || super.getHoehe() >= 1000) {
			this.sperrgut = true;
		} else {
			this.sperrgut = false;
		}
	}
	
	public Paket() {
		super();
	}

	public boolean isSperrgut() {
		return sperrgut;
	}

	public void setSperrgut(boolean sperrgut) {
		this.sperrgut = sperrgut;
	}

}
