package moonstationMulkowski;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class SpaceStation {
	private String name;
	private ArrayList<SpaceEquipment> equipmentList;
	private TreeSet<SpaceEquipment> sortedEquipment;
	private HashMap<Integer, SpaceEquipment> equipmentMap;
	
	public SpaceStation(String name) {
		this.name = name;
		equipmentList = new ArrayList<SpaceEquipment>();
		sortedEquipment = new TreeSet<SpaceEquipment>();
		equipmentMap = new HashMap<Integer, SpaceEquipment>();
	}
	
	private double getMaintenanceCost(SpaceEquipment s) {
		return ((Maintainable) s).calculateMaintenanceCost();
	}
	
	public void printByMaintainanceCost() {
		ArrayList<SpaceEquipment> maintableEquipment = new ArrayList<SpaceEquipment>();
		
		for (SpaceEquipment s: equipmentList) {
			if (s instanceof Maintainable) {
				maintableEquipment.add(s);
			}
		}
		
		maintableEquipment.sort(
				Comparator
//					.comparingDouble((SpaceEquipment s) -> ((Maintainable) s).calculateMaintenanceCost())
					.comparingDouble(this::getMaintenanceCost)
					.reversed()
					.thenComparing(SpaceEquipment::getName)
				);
		
		for (SpaceEquipment s : maintableEquipment) {
			System.out.println(s);
		}
	}
	
	public ArrayList<SpaceEquipment> getList() {
		return equipmentList;
	}
	
	public TreeSet<SpaceEquipment> getTreeSet() {
		return sortedEquipment;
	}
	
	public HashMap<Integer, SpaceEquipment> getMap() {
		return equipmentMap;
	}
	
	public String getName() {
		return name;
	}
	
	public void addEquipment(SpaceEquipment e) {
		equipmentList.add(e);
		sortedEquipment.add(e);
		equipmentMap.put(e.getEquipmentId(), e);
	}
	
	public void printAllEquipment() {
		for (SpaceEquipment e : equipmentList) {
			System.out.println(e);
		}
	}
	
	public void printSortedEquipment() {
		for (SpaceEquipment e: sortedEquipment) {
			System.out.println(e);
		}
	}
	
	public SpaceEquipment findByEquipmentId(int key) {
		if (equipmentMap.containsKey(key) == true) {
			return equipmentMap.get(key);
		}
		System.out.println("Es wurde nichts gefunden");
		return null;
	}
	
	public void removeEquipmentBelowSafetyLevel(int minSafeLevel) { // viel zu lang
		Iterator<SpaceEquipment> it = equipmentList.iterator();
		
		while (it.hasNext()) {
			SpaceEquipment element = it.next();
			if (element.getSafetyLevel() < minSafeLevel) {
				it.remove();
				System.out.println("Entfernt aus der Liste");
			}
		}
		
		Iterator<SpaceEquipment> it2 = sortedEquipment.iterator();
		
		while (it2.hasNext()) {
			SpaceEquipment element = it2.next();
			if (element.getSafetyLevel() < minSafeLevel) {
				it2.remove();
				System.out.println("Entfernt aus dem Set");
			}
		}
		
		
		Iterator<Integer> it3 = equipmentMap.keySet().iterator();
		
		while (it3.hasNext()) {
			int element = it3.next();
			if (equipmentMap.get(element).getSafetyLevel() < minSafeLevel) {
				it3.remove();
				System.out.println("Entfernt aus der Map");
			}
		}
		
		
	}
	
	public void printMaintenanceRequired() {
		for (int i = 0; i < equipmentList.size(); i++) {
			if (equipmentList.get(i) instanceof LifeSupportDevice) {
				LifeSupportDevice l = (LifeSupportDevice) equipmentList.get(i);
				if (l.needsMaintenance() == true) {
					System.out.println(l);
				}
			} else if (equipmentList.get(i) instanceof ResearchDevice) {
				ResearchDevice r = (ResearchDevice) equipmentList.get(i);
				if (r.needsMaintenance() == true) {
					System.out.println(r);
				}	
			}
		}
	}
	
	public void printSortedByMaintenanceCost() {
		System.out.println("LifeSupportDevice:");
		Collections.sort(equipmentList);
	}
	
	public void printByEngineer(String name) {
		for (int i = 0; i < equipmentList.size(); i++) {
			if (equipmentList.get(i).getEngName().equals(name)) {
				System.out.println(equipmentList.get(i));
			}
		}
	}
	
	public Engineer EngineerFinder(String name) {
		for (int i = 0; i < equipmentList.size(); i++) {
			if (equipmentList.get(i).getEngName().equals(name)) {
				return equipmentList.get(i).getEngineer();
			}
		}
		System.out.println("Wurde nicht gefunden");
		return null;
	}
	
	public void repairDisposableDevice(String name) {
		for (int i = 0; i < equipmentList.size(); i++) {
			if (equipmentList.get(i).getName().equals(name) && equipmentList.get(i) instanceof DisposableDevice) {
				DisposableDevice device = (DisposableDevice) equipmentList.get(i);
				device.buyNewDevice();
			}
		}
	}
	
	public void useDisposableDevice(String name) {
		for (int i = 0; i < equipmentList.size(); i++) {
			if (equipmentList.get(i).getName().equals(name) && (equipmentList.get(i) instanceof DisposableDevice)) {
				System.out.println("Aktiv");
				DisposableDevice device = (DisposableDevice) equipmentList.get(i);
				device.useDevice();
			}
		}
	}
	
}
