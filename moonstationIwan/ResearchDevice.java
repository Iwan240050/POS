package moonstationMulkowski;

public class ResearchDevice extends SpaceEquipment implements Maintainable{
	private String researchField;
	private int dataOutputPerHour;
	
	public ResearchDevice(String name, Engineer responsibleEngineer, int safetyLevel, int operatingHours,int id, String researchField, int dataOutputPerHour, SpaceStation s) {
		super(name, responsibleEngineer, safetyLevel, operatingHours, id);
		this.researchField = researchField;
		this.dataOutputPerHour = dataOutputPerHour;
		s.addEquipment(this);
	}
	
	public int getDataOutput() {
		return dataOutputPerHour;
	}
	
	@Override
	public boolean needsMaintenance() {
		boolean rework = false;
		if (getOperatingHour() > 1000) rework = true;
		if (getSafetyLevel() >= 4) rework = true;
		if (dataOutputPerHour > 15) rework = true;
		return rework;
	}
	

	
	@Override
	public double calculateMaintenanceCost() {
		return 200 + getOperatingHour() * 0.4 + dataOutputPerHour * 10;
	}
	
	@Override
	public String toString() {
		return String.format("\n\n----Dummes Gerät 2----\n%s\nresearchField: %s\nDataOutput: %d\nMaintenanceCost: %f\n---------------------------\n\n\n", super.toString(), researchField, dataOutputPerHour, this.calculateMaintenanceCost());
	}
	
	public int compareTo(ResearchDevice r) {
		return Double.compare(this.calculateMaintenanceCost(), r.calculateMaintenanceCost());
	}
	
	public int calculateTotalDataOutput() {
		return dataOutputPerHour * this.getOperatingHour();
	}
	
	public void cleanDevice() {
		System.out.println("Das gerät wurde Überarbeitet erneuert und geputzt: ");
		 this.increaseSafetyLevel();
		 System.out.println("Das sicherheitslevel wurde erhöht\n Safety Level = " + this.getSafetyLevel());
	}
}
