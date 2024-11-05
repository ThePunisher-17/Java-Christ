public class PayrollSystemTest {
    public static void main(String[] args) {
        //create an array of Employee references to store different types of employees
        Employee[] employees = new Employee[3];

        //instantiate HourlyEmployee
        employees[0] = new HourlyEmployee(1, "Varma", "Teaching Assistant", 20.5, 40);
        
        //instantiate SalariedEmployee
        employees[1] = new SalariedEmployee(2, "Dimple", "Lecturer", 4000.0);
        
        //instantiate ExecutiveEmployee
        employees[2] = new ExecutiveEmployee(3, "Harsha", "Head of Department", 8000.0, 15.0);

        //calculate total payroll
        double totalPayroll = 0;

        //display detailed information for each employee and calculate total payroll
        for (Employee employee : employees) {
            employee.displayInfo();
            totalPayroll += employee instanceof HourlyEmployee 
                ? ((HourlyEmployee) employee).calculateWeeklySalary() 
                : ((SalariedEmployee) employee).calculateWeeklySalary();
            System.out.println();
        }

        //display total payroll
        System.out.println("Total Payroll: " + totalPayroll);
    }
}