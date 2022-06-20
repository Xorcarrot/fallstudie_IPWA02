package kurierdienst.daten;

/**
 * Klasse die dafür dient um die Informationen einer Sendung in kompakter schreibweiße für den Kurier auszugeben.
 *  
 * @author xorca
 *
 */
public class SendungAbholung {
	
	private int referenznummer;
	private String abmessungen;
	private int gewicht;
	private String sperrgut;
	
	/**
	 * Konstruktor ähnlich wie bei einer Sendung, mit dem Unterschied das er die Parameter laenge, breite und hoehe zu einem String zusammenführt. 
	 * Weiters wird das Parameter sperrgut mit "Ja" oder "Nein" ersetzt. 
	 * 
	 * @param referenznummer Eindeutig identifiziarbare Nummer für eine Sendung.
	 * @param laenge Länge der Sendung 
	 * @param breite Breite der Sendung
	 * @param hoehe Höhe der Sendung
	 * @param gewicht Gewicht der Sendung
	 */
	public SendungAbholung(int referenznummer, int laenge, int breite, int hoehe, int gewicht) {
		this.referenznummer = referenznummer;
		this.abmessungen = laenge + "x" + breite + "x" + hoehe;
		this.gewicht = gewicht;
		if (laenge >= 1000 || breite >= 1000 || hoehe >= 1000) {
			this.sperrgut = "Ja";
		} else {
			this.sperrgut = "Nein";
		}
	}
	
	/**
	 * Standart-Konstruktor
	 */
	public SendungAbholung() {
		
	}

	public int getReferenznummer() {
		return referenznummer;
	}

	public void setReferenznummer(int referenznummer) {
		this.referenznummer = referenznummer;
	}

	public String getAbmessungen() {
		return abmessungen;
	}

	public void setAbmessungen(String abmessungen) {
		this.abmessungen = abmessungen;
	}

	public int getGewicht() {
		return gewicht;
	}

	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}

	public String getSperrgut() {
		return sperrgut;
	}

	public void setSperrgut(String sperrgut) {
		this.sperrgut = sperrgut;
	}

}
