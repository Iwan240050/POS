package moonstationMulkowski;

public class DisposableDevice extends SpaceEquipment{
	private String usagePurpose;
	private boolean alreadyUsed;
	
	public DisposableDevice(String name, Engineer responsibleEngineer, int safetyLevel, int operatingHours, int id, String usagePurpose, boolean alreadyUsed, SpaceStation s) {
		super(name, responsibleEngineer, safetyLevel, operatingHours, id);
		this.usagePurpose = usagePurpose;
		this.alreadyUsed = alreadyUsed;
		s.addEquipment(this);
	}

	
	@Override
	public String toString() {
		return String.format("\n------------------------------\nDummes Gerät 3\n%s\nBenutzt für: %s\nBenutzt?: %b\n------------------------------\n\n", super.toString(), usagePurpose, alreadyUsed);
	}
	
	public boolean buyNewDevice() {
		return alreadyUsed = false;
	}
	
	public boolean useDevice() {
		if (alreadyUsed == false) {
			return alreadyUsed = true;
		} else {
			System.out.println("Das Werkzeug wurde schon benutzt");
			return alreadyUsed = true;
		}
	}
}
