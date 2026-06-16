package moonstationMulkowski;

public class LifeSupportDevice extends SpaceEquipment implements Maintainable{
	private String supportedRecource;
	private boolean criticalSystem;
	
	public LifeSupportDevice(String name, Engineer responsibleEngineer, int safetyLevel, int operatingHours, int id, String supportedRecource, boolean crititicalSystem, SpaceStation s) {
		super(name, responsibleEngineer, safetyLevel, operatingHours, id);
		this.supportedRecource = supportedRecource;
		this.criticalSystem = crititicalSystem;
		s.addEquipment(this);
	}
	
	@Override
	public boolean needsMaintenance() {
		boolean rework = false;
		if (getOperatingHour() > 800) rework = true;
		if (getSafetyLevel() >= 4) rework = true;
		if (criticalSystem == true) rework = true;
		return rework;
	}
	
	@Override
	public double calculateMaintenanceCost() {
		if (criticalSystem == true) {
			return 500 + getOperatingHour()*0.8 + 1000;
		} 
		return 500 + getOperatingHour()*0.8;
	}
	@Override
	public String toString() {
		return String.format("\n\n----Dummes Gerät 1----\n%s\nsupportedRecourcess: %s\nCritical System: %b\nMaintenanceCost: %f\n---------------------------\n\n\n", super.toString(), supportedRecource, criticalSystem, this.calculateMaintenanceCost());
	}
	
	public int compareTo(LifeSupportDevice l) {
		return Double.compare(this.calculateMaintenanceCost(), l.calculateMaintenanceCost());
	}
	
	public void statusReport() {
		if (criticalSystem == true && needsMaintenance() == true) {
			System.out.println("WARNUNG DAS SYSTEM IST INSTABIL");
		} else {
			System.out.println("System Stabil");
		}
	}
}
