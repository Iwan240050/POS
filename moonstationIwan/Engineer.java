package moonstationMulkowski;

public class Engineer {
	private String name;
	private String spezialisation;
	private int experienceYears;
	private String title;
	
	public Engineer (String name, String spezialisation, int experienceYears) {
		this.name = name;
		this.spezialisation = spezialisation;
		this.experienceYears = experienceYears;
		title = "Lehrling";
	}
	
	public String setBetterEducation(String title) {
		return this.title = title;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format("\n---------------------------\nEngineer\nName: %s\nErfahrung: %d\nSpezialisierung: %s\nAusbildung: %s\n---------------------------\n", name, experienceYears, spezialisation, title);
	}
}
