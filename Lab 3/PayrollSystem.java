//Base class:Employee
class Employee {
    private int employeeId;
    private String employeeName;
    private String designation;
    //constructor
    public Employee(int employeeId, String employeeName, String designation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }

    //getters for Employee attributes
    public int getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getDesignation() { return designation; }

    //default bonus calculation to be overridden
    public double calculateBonus() { return 0; }
    //display basic employee details
    public void displayInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Designation: " + designation);
    }
}

//derived class:HourlyEmployee
class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmployee(int employeeId, String employeeName, String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, designation);
        this.hourlyRate = hourlyRate > 0 ? hourlyRate : 0;
        this.hoursWorked = hoursWorked >= 0 ? hoursWorked : 0;
    }

    public double calculateWeeklySalary() { return hourlyRate * hoursWorked; }
    @Override
    public double calculateBonus() { return calculateWeeklySalary() * 0.1; }

    @Override
    public void displayInfo() {
        System.out.println("Hourly Employee Details:");
        super.displayInfo();
        System.out.printf("Hourly Rate: %.2f%n", hourlyRate);
        System.out.printf("Hours Worked: %d%n", hoursWorked);
        System.out.printf("Weekly Salary: %.2f%n", calculateWeeklySalary());
        System.out.printf("Bonus: %.2f%n%n", calculateBonus());
    }
}

//derived class:SalariedEmployee
class SalariedEmployee extends Employee {
    private double monthlySalary;
    public SalariedEmployee(int employeeId, String employeeName, String designation, double monthlySalary) {
        super(employeeId, employeeName, designation);
        this.monthlySalary = monthlySalary > 0 ? monthlySalary : 0;
    }
    public double calculateWeeklySalary() { return monthlySalary / 4; }
    @Override
    public double calculateBonus() { return calculateWeeklySalary() * 0.2; }
    @Override
    public void displayInfo() {
        System.out.println("Salaried Employee Details:");
        super.displayInfo();
        System.out.printf("Monthly Salary: %.2f%n", monthlySalary);
        System.out.printf("Weekly Salary: %.2f%n", calculateWeeklySalary());
        System.out.printf("Bonus: %.2f%n%n", calculateBonus());
    }
}

//further Derived class:ExecutiveEmployee
class ExecutiveEmployee extends SalariedEmployee {
    private double bonusPercentage;
    public ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage) {
        super(employeeId, employeeName, designation, monthlySalary);
        this.bonusPercentage = (bonusPercentage >= 0 && bonusPercentage <= 100) ? bonusPercentage : 0;
    }

    @Override
    public double calculateBonus() {
        return super.calculateWeeklySalary() * 4 * 12 * (bonusPercentage / 100);
    }

    @Override
    public void displayInfo() {
        System.out.println("Executive Employee Details:");
        System.out.printf("Employee ID: %d%n", getEmployeeId());
        System.out.printf("Employee Name: %s%n", getEmployeeName());
        System.out.printf("Designation: %s%n", getDesignation());
        System.out.printf("Monthly Salary: %.2f%n", getMonthlySalary());
        System.out.printf("Weekly Salary: %.2f%n", calculateWeeklySalary());
        System.out.printf("Bonus Percentage: %.2f%n", bonusPercentage);
        System.out.printf("Annual Bonus: %.2f%n", calculateBonus());
    }
    
    //getter for monthly salary to use in display info
    public double getMonthlySalary() {
        return super.calculateWeeklySalary() * 4;
    }
}

//main class to test the payroll system
public class PayrollSystem {
    public static void main(String[] args) {
        //create and display each employee's information
        new HourlyEmployee(1, "Varma", "Teaching Assistant", 20.5, 40).displayInfo();
        new SalariedEmployee(2, "Dimple", "Lecturer", 4000).displayInfo();
        new ExecutiveEmployee(3, "Harsha", "Head of Department", 8000, 15).displayInfo();
    }
}