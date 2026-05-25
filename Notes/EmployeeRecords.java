record Employee(String name, String designation, double salary) {} 

public class EmployeeRecords {
	public static void main(String[] args){
		Employee empRecord_1 = new Employee("Jack Ryan", "Analyst", 540_000);
		Employee empRecord_2 = new Employee("John Price", "Lead Strategist", 870_000);
		Employee empRecord_3 = new Employee("Marcus Halloway", "Senior Cybersecurity Analyst", 1_000_000);
		
		System.out.println(empRecord_1.name() + ": " + empRecord_1.designation() + ": " + empRecord_1.salary());
		System.out.println(empRecord_2.name() + ": " + empRecord_2.designation() + ": " + empRecord_2.salary());
		System.out.println(empRecord_3.name() + ": " + empRecord_3.designation() + ": " + empRecord_3.salary());
	}
	
}