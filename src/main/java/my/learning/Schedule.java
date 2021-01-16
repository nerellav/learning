package my.learning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Student {
	int arrivalTime;
	
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public static final Comparator<Student> ASCENDING_COMPARATOR = 
		    Comparator.comparing(Student::getArrivalTime);
	public static final Comparator<Student> DESCENDING_COMPARATOR = 
		    Comparator.comparing(Student::getArrivalTime).reversed();

	public Student(int arrivalTime) {
		super();
		this.arrivalTime = arrivalTime;
	}
	
	@Override
	public String toString() {
		return String.valueOf(arrivalTime);
	}
}

class Slot {
	
	public static final Comparator<Slot> ASCENDING_COMPARATOR = 
		    Comparator.comparing(Slot::getTime);
	
	public Slot(int time, List<Student> students) {
		super();
		this.time = time;
		this.students = students;
	}
	
	int time;
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	List<Student> students;
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Slot)) return false;
		
		Slot s = (Slot) o;
		
		if(s.time==time) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + time;
		return result;
	}
}

public class Schedule {
	
	static int n=90;
	static int m =25;
	
	static int availm =22;
	static int sparem =5;
	static int c =5;
	
	static List<Student> assignSlots (List<Student> students, List<Slot> slots) {
		List<Student> missedStudents = new ArrayList<>();
		for (Student student : students) {
			ArrayList<Slot> tempSlots = new ArrayList<Slot>(slots);
			do {
				Slot slot = getMinWaitTimeSlot(student, tempSlots);
				if (slot == null) {
					System.out.println("Couldn't find slot for student arrived at " + student.arrivalTime);
					missedStudents.add(student);
					break;
				}
				
				if (slot.students.size() < c) {
					slot.students.add(student);
					break;
				} else {
					tempSlots.remove(slot);
				}
			} while (true);
		}
		
		return missedStudents;
	}
	
	// First come first serve method
	static List<Slot> assignSlotsFCFS (List<Student> students, int sparem) {
		
		final int n = students.size();
		Collections.sort(students, Student.ASCENDING_COMPARATOR);
		
		List<Slot> slots = new ArrayList<>(sparem);
		int i = 0;
		for (int j = 0; j <=sparem; j++) {
			
			if (i >= n) {
				break;
			}
			List<Student> sl = students.subList(i, Math.min(n, i + c));
			
			slots.add(new Slot(sl.get(sl.size() -1).arrivalTime, new ArrayList<Student>(sl)));
			
			i = i + c;
		}
		
		return slots;
	}
	

	public static void main(String[] args) {

		List<Student> students = initArrivalTime(n);
		
		String allStudents = students.stream()
				.sorted(Student.ASCENDING_COMPARATOR)
				.map(Student::toString)
				.collect(Collectors.joining(", "));
		
		System.out.println(allStudents);

		List<Slot> slots = pickRandomSlots(students, availm);

		Collections.sort(students, Student.DESCENDING_COMPARATOR);

		List<Student> missedStudents = assignSlots(students, slots);
		
		String missed = missedStudents.stream()
				.sorted(Student.DESCENDING_COMPARATOR)
				.map(Student::toString)
				.collect(Collectors.joining(", "));

		System.out.println(missed);
			
		List<Slot> spareSlots = assignSlotsFCFS(missedStudents, sparem);
		
		slots.addAll(spareSlots);
		
		Collections.sort(slots, Slot.ASCENDING_COMPARATOR);
		
		int total = 0;
		int longestWaitTime = 0;
		Student mostAwaitedStudent = null;
		int totalWaitTime = 0;
		for (Slot slot : slots) {
			total += slot.students.size();
			System.out.println(slot.time + " " + slot.students.size());
			
			for (Student student: slot.students) {
				int studentwaitTime = slot.time - student.arrivalTime;
				totalWaitTime += studentwaitTime;
				
				if (studentwaitTime > longestWaitTime) {
					longestWaitTime = studentwaitTime;
					mostAwaitedStudent = student;
				}
			}
		}
		System.out.println(n - total);
		System.out.println("Total wait time: " + totalWaitTime + " Longest wait time: " + longestWaitTime + " for student arrived at " + mostAwaitedStudent.arrivalTime);
	}
	
	static Slot getMinWaitTimeSlot(Student student, List<Slot> slots) {
		Integer minWait = Integer.MAX_VALUE;
		Slot closest= null;
		
		for (Slot slot : slots) {
			if (slot.time >= student.arrivalTime) {

				int diff = slot.time - student.arrivalTime;
				if (diff < minWait) {
					minWait = diff;
					closest = slot;
				}
			}
		}
		
		return closest;
	}
	
	static List<Student> initArrivalTime(int n) {
		Random randMin = new Random();
		Random randHour = new Random();
		
		List<Student> arrivalTime = new ArrayList<>();

		// nextInt as provided by Random is exclusive of the top value so you need to add 1 
		// random.nextInt(max - min + 1) + min

		for (int i = 0; i < n; i++) {
			int randomM = randMin.nextInt((59 - 0) + 1) + 0;
			int randomH = randHour.nextInt((23 - 0) + 1) + 0;
			
			arrivalTime.add(new Student(randomH * 60 + randomM));
		}
		
		return arrivalTime;
	}
	
	public static List<Slot> pickRandomSlots(List<Student> list, int n) {
	    if (n > list.size()) {
	        n = list.size();
	    }
	    Random random = new Random();
	    
		return IntStream
	            .generate(() -> random.nextInt(list.size()))
	            .distinct()
	            .limit(n)
	            .mapToObj(i -> new Slot(list.get(i).arrivalTime, new ArrayList<Student>()))
	            .collect(Collectors.toList());
	}

}
