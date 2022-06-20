package kurierdienst.daten;

import java.util.Set;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
/**
 * Die Klasse Brief erweitert die Superklasse Sendung um zwischen Paketen und Briefen zu unterscheiden. Es ist eine Entity um die 
 * dauerhafte Speicherung seiner Daten zu ermöglichen. 
 *  
 * @author xorca
 *
 */
@Entity(name = "Brief")
@DiscriminatorValue("BRIEF")
public class Brief extends Sendung {
	
	/**
	 * Der Brief-Konstruktor benötigt die gleichen Parameter wie die Superklasse Sendung.
	 * 
	 * @param laenge Die Länge des Briefes max. 297mm.
	 * @param breite Die Breite des Briefes max. 210mm.
	 * @param hoehe Die Höhe des Briefes max. 50mm.
	 * @param gewicht Das Gewicht des Briefes max. 2000g. 
	 */
	public Brief(int laenge, int breite, int hoehe, int gewicht) {
		super(laenge, breite, hoehe, gewicht);
	}   
	
	public Brief() {
		super();
	}

}
