package kurierdienst.daten;

/**
 * Die Klasse, die Datenbank Sendung ausließt und Informationen für den Kuriermanager aufbereitet. 
 * 
 * @author xorca
 *
 */
public class SendungManager {
	
	private int referenznummer;
	private char status;
	
	/**
	 * Kunstruktor zum generieren eines Objekts. 
	 * 
	 * @param referenznummer Eindeutige Identifikation für eine Sendung
	 * @param status Aktueller Status der Sendung
	 */
	public SendungManager(int referenznummer, char status) {
		this.referenznummer = referenznummer;
		this.status = status;
	}
	
	//Getter- und Settermethoden
	public int getReferenznummer() {
		return referenznummer;
	}

	public void setReferenznummer(int referenznummer) {
		this.referenznummer = referenznummer;
	}

	public String getStatus() {
		switch(this.status) {
		case 'a':
			return "Auftrag erhalten";
		case 'e':
			return "Im Lager";
		case 'i':
			return "In Zustellung";
		case 'z':
			return "Auftrag abgeschlossen";
		}
		return "Fehler im System";
	}

	public void setStatus(char status) {
		status = status;
	}
	
	

}
