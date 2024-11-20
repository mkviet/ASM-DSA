

package BST;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        int numStudents = 0;

        // Loop to ensure valid number of students is entered
        while (true) {
            try {
                System.out.print("Enter the number of students: ");
                numStudents = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                if (numStudents <= 0) {
                    System.out.println("Number of students must be greater than 0. Please try again.");
                    continue;
                }
                break; // Valid input; exit loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        
        
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            
            String studentCode = "";
            while(true){
                System.out.print("Student Code: ");
                studentCode = scanner.nextLine();
                if(!studentCode.matches("^[a-zA-Z0-9]+$")) {
                    System.out.println("Invalid Student Code. "
                            + "It should contain only letters and numbers. Please enter a valid code.");
                }else{
                    break;
                }
            }
            
            
            double score = 0;
            while(true){
                try {
                    System.out.print("Score: ");
                    score = scanner.nextDouble();
                    scanner.nextLine();
                    if(score < 0) {
                        System.out.println("Score cannot be negative. Please enter a valid score. ");
                        continue;
                    }
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Invalid input. Please enter a valid number for the score.");
                    scanner.nextLine();
                }
            }
            
            bst.insert(new Student(name, studentCode, score));
        }

        // Menu for operations
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. In-order Traversal");
            System.out.println("3. Pre-order Traversal");
            System.out.println("4. Post-order Traversal");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. Search Student");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (option) {
                case 1:
                    addStudent(bst, scanner);
                    break;
                case 2:
                    System.out.println("\nIn-order Traversal:");
                    bst.inorderTraversal();
                    break;
                case 3:
                    System.out.println("\nPre-order Traversal:");
                    bst.preorderTraversal();
                    break;
                case 4:
                    System.out.println("\nPost-order Traversal:");
                    bst.postorderTraversal();
                    break;
                case 5: // Update Student
                    System.out.print("Enter Student Code to update: ");
                    String updateCode = scanner.nextLine();
                    System.out.print("Enter new Name: ");
                    String newName = scanner.nextLine();
                    double newScore = 0;
                    while (true) {
                        try {
                            System.out.print("Enter new Score: ");
                            newScore = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline
                            if (newScore < 0) {
                                System.out.println("Score cannot be negative. Please enter a valid score.");
                                continue;
                            }
                            break; // Valid input; exit loop
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for the score.");
                            scanner.nextLine(); // Clear the invalid input
                        }
                    }
                    bst.update(updateCode, newName, newScore);
                    break;
                case 6: // Delete Student
                    System.out.print("Enter Student Code to delete: ");
                    String deleteCode = scanner.nextLine();
                    bst.delete(deleteCode);
                    break;
                case 7: // Search Student
                    System.out.print("Enter Student Code to search: ");
                    String searchCode = scanner.nextLine();
                    Student foundStudent = bst.search(searchCode);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 8: // Exit
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addStudent(BinarySearchTree bst, Scanner scanner) {
        System.out.println("Enter details for new student:");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        String studentCode = ""; // Initialize studentCode variable
        while (true) {
            try {
                System.out.print("Student Code: ");
                studentCode = scanner.nextLine();
                if (studentCode.trim().isEmpty()) {
                    throw new IllegalArgumentException("Student Code cannot be empty. Please enter a valid code.");
                }
                break; // Valid input; exit loop
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        double score = 0; // Initialize score variable
        while (true) {
            try {
                System.out.print("Score: ");
                score = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline
                if (score < 0) {
                    System.out.println("Score cannot be negative. Please enter a valid score.");
                    continue;
                }
                break; // Valid input; exit loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the score.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        // Insert student into BST
        bst.insert(new Student(name, studentCode, score));
        System.out.println("Student added successfully.");
    }
}
