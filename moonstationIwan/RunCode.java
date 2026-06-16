package moonstationMulkowski;

import java.util.Scanner;

public class RunCode {
	public static Scanner scan = new Scanner(System.in);
	public static SpaceStation s;
	
	public static void main(String[] args) {
		creator();
		welcome();
		//Scanner wird nicht geschlossen
	}
	
//	###################################################################################
//	CREATOR
//	###################################################################################
	public static void creator() { //irreführender Name daher ein creator ein Nomen ist, statt irgendwas beschreibt. Besser wär createObjects()
		s = new SpaceStation("IWANs beste Mondbasis");
		
		Engineer e1 = new Engineer("Herbert", "Mechatronik", 12);
		Engineer e2 = new Engineer("Gertrude", "IT", 167);
		Engineer e3 = new Engineer("IWAN", "Keinerlei Ausbildung", 16);
		
		ResearchDevice s1 = new ResearchDevice("Mars Rover Pro", e1, 3, 1200, 667, "Planetenforschung", 45, s);
		ResearchDevice s2 = new ResearchDevice("Quanten-Computer V2", e2, 5, 450, 668,"Quantencomputing", 850,s);
		ResearchDevice s3 = new ResearchDevice("BioScanner 3000", e3, 1, 3200, 669, "Genetik", 120,s);
		ResearchDevice s4 = new ResearchDevice("Drohnen-Prototyp X", e1, 2, 150, 670, "Autonome Systeme", 30, s);
		ResearchDevice s5 = new ResearchDevice("Zentrifuge Alpha", e3, 4, 890, 671, "Zellbiologie", 15, s);
		
		LifeSupportDevice l1 = new LifeSupportDevice("O2-Generator Alpha", e3, 5, 4500, 672, "Sauerstoff", true, s);
		LifeSupportDevice l2 = new LifeSupportDevice("Wasser-Recycler v4", e2, 4, 1200, 673, "Wasser", true, s);
		LifeSupportDevice l3 = new LifeSupportDevice("Heizmodul Gamma", e3, 3, 850, 674, "Wärme", false, s);
		LifeSupportDevice l4 = new LifeSupportDevice("Druckregler Main", e1, 5, 6000, 675, "Luftdruck", true, s);
		LifeSupportDevice l5 = new LifeSupportDevice("CO2-Scrubber", e2, 2, 300, 676, "Kohlendioxid-Filterung", false, s);
		
		DisposableDevice d1 = new DisposableDevice("Sauerstoff-Filter A", e1, 1, 0, 677, "Notfall-Sauerstoff", false, s);
		DisposableDevice d2 = new DisposableDevice("Test-Kit Medizin", e2, 2, 0, 678, "Blutanalyse", false, s);
		DisposableDevice d3 = new DisposableDevice("Hitzeschild-Kachel", e3, 5, 24, 679, "Wiedereintritt-Schutz", true, s);
		DisposableDevice d4 = new DisposableDevice("Müll-Kapsel 1", e1, 1, 12, 680, "Abfallentsorgung", true, s);
		DisposableDevice d5 = new DisposableDevice("Dichtungs-Ring", e2, 4, 0, 681, "Leck-Reparatur", false, s);
	}
	
	
//	###################################################################################
//	START
//	###################################################################################
	public static void welcome() {
		int action = 0;
		System.out.printf("------------------------\nWillkommen zu %s\n------------------------\n", s.getName());
		System.out.println("1. Das ganze Equipment drucken\n"
				+ "2. Das Sortierte Equipment drucken\n"
				+ "3. Ein gerät finden\n"
				+ "4. Remove by safetylevel\n"
				+ "5. Print Maintance needed\n"
				+ "6. Ingeneure drucken\n"
				+ "7. Ingeneur bearbeiten\n"
				+ "8. Disposable Device reparieren\n"
				+ "9. Device verwenden\n"
				+ "10. Operating hours erhöhen\n"
				+ "11. Gesamten Output in GB ausgeben\n"
				+ "12. Status printen\n"
				+ "13. Print by Maintenance cost\n"
				+ "0. Exit\n");
		while (true) {
			try {
				action = scan.nextInt();
				
				illagalIndexException(action); //Rechtschreibfehler Illagal, es ist illegal
				
				break;
			} catch (IllegalArgumentException e) {
				e.getMessage();
			} catch (Exception e) {
				System.out.println("Ungültige Eingabe!");
				scan.next();
			}
		}
		
		actions(action);
	}
	
//	###################################################################################
//	AKTIONEN
//	###################################################################################
	
	public static void actions(int action) { //Switch case wäre einfacher und besser geeignet
		if (action == 1) {
			s.printAllEquipment();
			welcome();
		} else if(action == 2) {
			s.printSortedEquipment();
			welcome();
		} else if (action == 3) {
			int key = checkKey();
			System.out.println(s.findByEquipmentId(key));
			welcome();
		} else  if (action == 4) {
			int safetyLevel = checkLevel();
			s.removeEquipmentBelowSafetyLevel(safetyLevel);
			welcome();
		} else if (action == 5) {
			s.printMaintenanceRequired();
			welcome();
		} else if (action == 6) {
			System.out.println("Geben Sie den Namen des Ingeneur ein");
			String name = scan.next();
			s.printByEngineer(name);
		} else if (action == 7) {
			reworkEngineer();
		} else if (action == 8) {
			System.out.println("Geben Sie den Namen für des Disposable Device ein: "); //verschieben in eigene Methode
			String name = scan.next();
			s.repairDisposableDevice(name);
			welcome();
		} else if (action == 9) {
			System.out.println("Geben Sie den Namen für des Disposable Device ein: "); //verschieben in eigene Methode
			String name = scan.next();
			s.useDisposableDevice(name);
			welcome();
		} else if (action == 10) {
			increaseHours();
			welcome();
		} else if (action == 11) {
			calcDataOutput();
			welcome();
		} else if (action == 12) {
			status();
			welcome();
		} else if (action == 13) {
			s.printByMaintainanceCost();
			welcome();
		} else if (action == 14) {
			cleanDevice();
			welcome();
		} else {
			System.out.println("Bis zum Nächsten mal");
		}
	}
	
//	###################################################################################
//	CLEAN DEVICE
//	###################################################################################
	public static void cleanDevice() {
		int key = checkKey();
		if (key == 0 || !(s.getMap().get(key) instanceof ResearchDevice)) {
			System.out.println("Den schlüssel gibt es nicht oder Das Objekt gehärt nicht der klasse an"); // "... Obejekt GEHÖRT nicht ..." immer mehr rechtschreib fehler, tsk tsk tsk
		} else {
			ResearchDevice researchDevice = (ResearchDevice) s.getMap().get(key);
			researchDevice.cleanDevice();
		}
	}
	
	
//	###################################################################################
//	STATUS
//	###################################################################################
	public static void status() {
		System.out.println("Geben Sie den Schlüssel ein: ");
		int key = checkKey();
		if (key == 0 || !(s.getMap().get(key) instanceof LifeSupportDevice)) {
			System.out.println("Den schlüssel gibt es nicht oder Das Objekt gehärt nicht der klasse an"); //Schon wieder dasselber
		} else {
			LifeSupportDevice lifeSupportDevice = (LifeSupportDevice) s.getMap().get(key);
			lifeSupportDevice.statusReport();
		}
	}
	
//	###################################################################################
//	GESAMMTEN OUTPUT IN GB
//	###################################################################################
	
	public static void calcDataOutput() {
		System.out.println("Geben Sie den Schlüssel ein: ");
		int key = checkKey();
		if (key == 0 || !(s.getMap().get(key) instanceof ResearchDevice)) {
			System.out.println("Den schlüssel gibt es nicht oder Das Objekt gehärt nicht der klasse an"); // Tsk Tsk Tsk
		} else {
			ResearchDevice researchDevice = (ResearchDevice) s.getMap().get(key);
			System.out.println(researchDevice.calculateTotalDataOutput());
		}
	}
	
	
//	###################################################################################
//	STUNDEN ERHÖHEN
//	###################################################################################
	public static void increaseHours() {
		int nr = checkNbr();
		int key = checkKey();
		if (key == 0) {
			System.out.println("Den schlüssel gibt es nicht");
		} else {
			s.getMap().get(key).increaseOperatingHours(nr);
			System.out.println("Anzahl der Stunden: " + s.getMap().get(key).getOperatingHour());
		}
	}
	
//	###################################################################################
//	ZAHL CHECKEN
//	###################################################################################
	public static int checkNbr() {
		int nr = 0;
		while (true) {
			try {
				System.out.println("Geben Sie die ANzahl der stunden ein: ");
				nr = scan.nextInt();
				checkNumberIsNegativ(nr);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Ungütlige Eingabe");
				scan.next();
			}
		}
		return nr;
	}
//	###################################################################################
//	INGENEUR ÜBERARBEITEN
//	###################################################################################
	
	public static void reworkEngineer() {
		System.out.println("Geben Sie den Namen des Ingeneurs ein den Sie bearbeiten wollen:");
		String name = scan.next();
		Engineer engineer = s.EngineerFinder(name);
		System.out.println("Geben Sie den Neuen Ausbildungs status ein: ");
		String title = scan.next();
		engineer.setBetterEducation(title);
		System.out.println(engineer);
		
	}
	
//	###################################################################################
//	LEVEL KONTROLLIEREN
//	###################################################################################
	public static int checkLevel() {
		int safetyLevel = 0;
		while (true) {
			try {
				System.out.println("Geben Sie ein mindest Sicherheitslevel an: ");
				safetyLevel = scan.nextInt();
				
				illegalLevelException(safetyLevel);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Ungültige Eingabe");
			}
		}
		return safetyLevel;	
	}
	
//	###################################################################################
//	SCHLÜSSEL KONTROLLIEREN
//	###################################################################################
	
	public static int checkKey() {
		int key = 0;
		while (true) {
			try {
				System.out.println("Geben Sie einen Schlüssel ein: ");
				key = scan.nextInt();
				
				break;
			} catch (Exception e) {
				System.out.println("Ungültige eingabe!");
				scan.next();
			}
		}
		
		if (s.getMap().containsKey(key)) {
			System.out.println("Schlüssel in der Map gefunden!");
			return key;
		} else {
			return 0;
		}
	}
	
//	###################################################################################
//	EXCEPTION
//	###################################################################################
	
	public static void illagalIndexException(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("Der Index ist zu klein!");
		} else if (index > 20) {
			throw new IllegalArgumentException("Der Index zu ist zu groß!");
		}
	}
	
	public static void illegalLevelException(int level) {
		if (level < 0) {
			throw new IllegalArgumentException("Das Level ist zu klein");
		} else if (level > 5) {
			throw new IllegalArgumentException("Das Level ist zu groß");
		}
	}
	
	public static void checkNumberIsNegativ(int nr) { // "Negative" nicht Negativ, in english please. he she it das s muss mit
		if (nr < 0) {
			throw new IllegalArgumentException("Die Zahl darf nicht negativ sein!");
		}
	}
}
