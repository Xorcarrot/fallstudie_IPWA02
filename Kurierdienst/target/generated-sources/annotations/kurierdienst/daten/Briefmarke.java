package kurierdienst.daten;

/**
 * Die Klasse Briefmarke hält die Information für die Preisgestaltung zum versenden von Paketen. Auch bietet sie Methoden zum berechnen
 * der Kosten für Sendung und Methoden zum automatischen Ausgeben textbasierter Lösungen.  
 * 
 * @author xorca
 *
 */
public class Briefmarke {
	
	private float markeS = 0.85f;
	private float markeM = 1.35f;
	private float markeL = 2.75f;
	private float markeXL = 4.3f;
	
	private float sperrgutZuschlagLeicht = 20.2f;
	private float sperrgutZuschlagSchwer = 24.24f;
	
	/**
	 * Bei dieser Methode wird ein Brief übergeben der anhand seiner Abmessung und seinem Gewicht katigorisiert wird. Es gibt die Möglichkeit
	 * das er in keine der maximal Vorgeschriebenen Dimensionen passt, somit folgt die Preisabrechnung nach den Paketgebühren. 
	 * 
	 * @param bri Stellt die Informationen der Abmessung und Gewicht bereit. 
	 * @return Es wird eine float Zahl rückgegeben die den Preis wiederspiegel. 
	 */
	public float preisBerechnenBrief(Brief bri) {
		if(bri.getGewicht() <= 20 && bri.getHoehe() <= 5) {
			return 0.85f;
		} else if(bri.getGewicht() <= 75 && bri.getHoehe() <= 5) {
			return 1.35f;
		} else {
			if(bri.getHoehe() <= 25) {
				return 2.75f;
			} else if(((bri.getLaenge()*2)+(bri.getBreite()*2)) <= 900) {
				return 4.3f;
			} else {
				Paket pak = new Paket(bri.getLaenge(), bri.getBreite(), bri.getHoehe(), bri.getGewicht());
				return this.preisBerechnenPaket(pak);
			}
		}
	}
	
	/**
	 * Bei dieser Methode wird ein Paket übergeben der anhand seinem Gewicht und dem Parameter Sperrgut katigorisiert wird. 
	 * 
	 * @param pak Stellt die Informationen des Gewichts und ob es sich um ein Sperrgut handelt bereit. 
	 * @return Es wird eine float Zahl rückgegeben die den Preis wiederspiegelt. 
	 */
	public float preisBerechnenPaket(Paket pak) {
		float kosten= 0f;
		
		if(pak.getGewicht() <= 1000) {
			kosten = kosten + 5f;
		} else if(pak.getGewicht() <= 2000) {
			kosten = kosten + 5.80f;
		} else if(pak.getGewicht() <= 5000) {
			kosten = kosten + 7f;
		} else if(pak.getGewicht() <= 10000) {
			kosten = kosten + 10f;
		} else if(pak.getGewicht() <= 15000) {
			kosten = kosten + 13f;
		} else {
			kosten = kosten + 16f;
		}
		
		if(pak.isSperrgut() && pak.getGewicht() <= 2000) {
			kosten = kosten + sperrgutZuschlagLeicht;
		} else if(pak.isSperrgut()) {
			kosten = kosten + sperrgutZuschlagSchwer;
		}
		
		return kosten;
	}
	
	/**
	 * Diese Methode wandelt die Kosten einer Sendung in die Anzahl von Briefmarken um. Dieser String beinhaltet die Information welche
	 * Briefmarken und wieviele davon für die Sendung benötigt werden. 
	 * 
	 * @param preis Die float-Zahl die den Preis der Sendung darstellt. 
	 * @return Ein String der die Information über die Briefmarken enthält. 
	 */
	public String anzahlBriefmarken(float preis) {
		float restpreis = preis;
		
		int markeXL = 0;
		int markeL = 0;
		int markeM = 0;
		int markeS = 0;
		
		while (restpreis >= this.markeXL) {
			restpreis = restpreis - this.markeXL;
			markeXL++;
		}
		while (restpreis >= this.markeL) {
			restpreis = restpreis - this.markeL;
			markeL++;
		}
		while (restpreis >= this.markeM) {
			restpreis = restpreis - this.markeM;
			markeM++;
		}
		while (restpreis >= this.markeS) {
			restpreis = restpreis - this.markeS;
			markeS++;
		}
		if(restpreis > 0 && markeS > 0) {
			markeS--;
			if((restpreis + this.markeS) > this.markeM) {
				markeS++;
				markeS++;
			} else {
				markeM++;
			}
		} else if (restpreis >0) {
			markeS++;
		}
		return " Für den Versand brauchen Sie: " + markeXL + " Marke(n) um 4,30€ , " + markeL + " Marke(n) um 2,75€ , " + markeM + " Marke(n) um 1,35€ und " + markeS + " Marke(n) um 0,85€.";
	}
	
	/**
	 * Diese Methode wandelt eine float-Zahl in einen String um. 
	 * 
	 * @param preis Kosten der Sendung. 
	 * @return String mit Information über Gesamtkosten. 
	 */
	public String preisAusgeben(float preis) {
		String goldDigger = Float.toString(preis);
		goldDigger = goldDigger.replace('.', ',');
		return "Die Gesamtkosten betragen sich auf: " + goldDigger + "€.";
	}

}
