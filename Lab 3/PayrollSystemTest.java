public class PayrollSystemTest {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];

        employees[0] = new HourlyEmployee(1, "Pratik", "Teaching Assistant", 20.5, 40);
        
        employees[1] = new SalariedEmployee(2, "Dimple", "Lecturer", 4000.0);
        
        employees[2] = new ExecutiveEmployee(3, "Harsha", "Head of Department", 8000.0, 15.0);

        double totalPayroll = 0;

        for (Employee employee : employees) {
            employee.displayInfo();
            totalPayroll += employee instanceof HourlyEmployee ? ((HourlyEmployee) employee).calculateWeeklySalary() : ((SalariedEmployee) employee).calculateWeeklySalary();
            System.out.println();
        }

        System.out.println("Total Payroll: " + totalPayroll);
    }
}