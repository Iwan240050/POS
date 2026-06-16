package moonstationMulkowski;

import java.util.Comparator;

public abstract class SpaceEquipment implements Comparable<SpaceEquipment>{
	static int countEquipmentId = 0;
	private int equipmentId;
	private String name;
	private Engineer responsibleEngineer;
	private int safetyLevel;
	private int operatingHours;
	
	
	public SpaceEquipment (String name, Engineer responsibleEngineer, int safetyLevel, int operatingHours, int id) {
		this.name = name;
		this.responsibleEngineer = responsibleEngineer;
		this.safetyLevel = safetyLevel;
		this.operatingHours = operatingHours;
		this.equipmentId = id;
		countEquipmentId ++;
	}
	
	public void getDisplayInformation() {
		System.out.printf("[%d], %s - Safety Level: %d", equipmentId, name, safetyLevel);
	}
	
	public Engineer getEngineer() {
		return responsibleEngineer;
	}
	
	public int getSafetyLevel() {
		return safetyLevel;
	}
	
	public int increaseOperatingHours(int hours) {
		return operatingHours += hours;
	}
	
	public String getName() {
		return name;
	}
	
	public int getEquipmentId() {
		return equipmentId;
	}
	
	public int getOperatingHour() {
		return operatingHours;
	}
	
	public int increaseSafetyLevel() {
		 if (safetyLevel >= 5) {
			 System.out.println("Das Gerät funktioniert einwandfrei!");
		 } else {
			 safetyLevel += 1;
		 }
		 return safetyLevel;
	}
	
	public String getEngName() {
		return responsibleEngineer.getName();
	}
	
	@Override
	public String toString() {
		return String.format("countEquipmentIds: %d\nequipmentI: %d\nName: %s\nresponsibleEngineer: %s\nsafetyLevel: %d\noperatingHours: %d", countEquipmentId, equipmentId, name, responsibleEngineer.getName(), safetyLevel, operatingHours);
	}
	
	@Override
	public int compareTo(SpaceEquipment se1) {
		return Comparator
				.comparingInt(SpaceEquipment::getSafetyLevel)
				.thenComparing(SpaceEquipment::getName)
				.thenComparingInt(SpaceEquipment::getEquipmentId)
				.compare(this, se1);
	}

}
