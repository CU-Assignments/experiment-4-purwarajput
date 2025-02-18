import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}

public class EmployeeManager {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add 2. Update 3. Remove 4. Search 5. Display 6. Exit");
            switch (scanner.nextInt()) {
                case 1: addEmployee(); break;
                case 2: updateEmployee(); break;
                case 3: removeEmployee(); break;
                case 4: searchEmployee(); break;
                case 5: displayEmployees(); break;
                case 6: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter ID, Name, Salary: ");
        employees.add(new Employee(scanner.nextInt(), scanner.next(), scanner.nextDouble()));
    }

    private static void updateEmployee() {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        for (Employee e : employees) {
            if (e.id == id) {
                System.out.print("New Name and Salary: ");
                e.name = scanner.next();
                e.salary = scanner.nextDouble();
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    private static void removeEmployee() {
        System.out.print("Enter ID to remove: ");
        int id = scanner.nextInt();
        employees.removeIf(e -> e.id == id);
    }

    private static void searchEmployee() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();
        employees.stream().filter(e -> e.id == id).findFirst().ifPresentOrElse(Employee::display, () -> System.out.println("Employee not found."));
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) System.out.println("No employees.");
        else employees.forEach(Employee::display);
    }
}
