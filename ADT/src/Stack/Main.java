package Stack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentStack studentStack = new StudentStack();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student"); // Updated option
            System.out.println("4. Edit Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students");
            System.out.println("7. Exit");

            int option = -1;
            boolean validOption = false;

            while (!validOption) {
                System.out.print("Choose an option: ");
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (option >= 1 && option <= 7) {
                        validOption = true;
                    } else {
                        System.out.println("Invalid option. Please choose a number from 1 to 7.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number from 1 to 7.");
                    scanner.nextLine();
                }
            }

            switch (option) {
                case 1:
                    studentStack.addStudent();
                    break;
                case 2:
                    studentStack.displayStudents();
                    break;
                case 3: // Updated case
                    System.out.print("Enter student code to search: ");
                    String searchCode = scanner.nextLine();
                    studentStack.searchStudent(searchCode);
                    break;
                case 4:
                    System.out.print("Enter student code to edit: ");
                    String editCode = scanner.nextLine();
                    studentStack.editStudent(editCode);
                    break;
                case 5:
                    System.out.print("Enter student code to delete: ");
                    String deleteCode = scanner.nextLine();
                    studentStack.deleteStudent(deleteCode);
                    break;
                case 6:
                    studentStack.chooseSortingAlgorithm();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
