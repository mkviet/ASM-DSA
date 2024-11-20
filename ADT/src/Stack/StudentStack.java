package Stack;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class StudentStack {
    private Stack<Student> stack;

    public StudentStack() {
        stack = new Stack<>();
    }
    
    // Method to add a student with sorting
    private boolean isDuplicateId(String studentCode) {
        for (Student student : stack) {
            if (student.getStudentCode().equals(studentCode)) {
                return true;
            }
        }
        return false;
    }
    
    // Method to add a student with sorting
public void addStudent() {
    Scanner scanner = new Scanner(System.in);
    int addedCount = 0;

    while (true) {
        try {
            System.out.println("Adding student " + (addedCount + 1) + ":");

            // Student ID Input
            String studentCode;
            while (true) {
                System.out.print("Enter studentID: ");
                studentCode = scanner.nextLine();

                // Check for duplicate ID
                if (isDuplicateId(studentCode)) {
                    System.out.println("Student ID already exists. Please enter a different ID.");
                    continue;
                }

                if (studentCode.matches("^[a-zA-Z0-9]+$")) {
                    break;
                } else {
                    System.out.println("Student code cannot be empty. Please enter a valid code.");
                }
            }

            // Student Name Input
            String name;
            while (true) {
                System.out.print("Enter student name: ");
                name = scanner.nextLine();
                if (name.matches("^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãạèéầẹêếễệềìíòóôọõốùúăặắđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀểỘỒỐỔỗờởởứửữựỳýỹ]+"
                        + "( [a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãạèéầẹêếễệềìíọòóôõốùúăặắđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀểỘỒỐỔỗờởởứửữựỳýỹ]+)*$")) {
                    break;
                } else {
                    System.out.println("Invalid name. Only letters and spaces are allowed.");
                }
            }

            // Student Score Input
            double score;
            while (true) {
                try {
                    System.out.print("Enter student score (0 to 10): ");
                    score = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    if (score >= 0 && score <= 10) break;
                    System.out.println("Score must be between 0 and 10. Please try again.");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric score.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            // Adding Student to Stack
            Student newStudent = new Student(name, studentCode, score);
            Stack<Student> tempStack = new Stack<>();
            while (!stack.isEmpty() && stack.peek().getScore() > score) {
                tempStack.push(stack.pop());
            }
            stack.push(newStudent);
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }

            addedCount++;
            System.out.print("Do you want to add another student? (y/n): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("y")) {
                break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    System.out.println("Student(s) added successfully.");
}


    // Method to display all students in the stack with ranking
    public void displayStudents() {
        if (stack.isEmpty()) {
            System.out.println("No students available. Please add students.");
            return;
        }

        System.out.println("All students in the stack:");
        for (Student student : stack) {
            System.out.println(student + " - Rank: " + student.getRank());
        }
    }

// Method to search for a student by studentCode
public void searchStudent(String studentCode) {
    Stack<Student> tempStack = new Stack<>();
    boolean found = false;

    // Traverse the stack to search for the student
    while (!stack.isEmpty()) {
        Student student = stack.pop();
        if (student.getStudentCode().equals(studentCode)) {
            System.out.println("Found student: " + student);
            found = true;
        }
        tempStack.push(student);
    }

    // Restore the original stack
    while (!tempStack.isEmpty()) {
        stack.push(tempStack.pop());
    }

    if (!found) {
        System.out.println("Student not found.");
    }
}


    // Method to sort students by score
    public enum SortField {
        SCORE, NAME, ID
    }

    public void bubbleSortStudents(SortField sortField, boolean sortAscending) {
        Student[] studentArray = stack.toArray(new Student[0]);

        for (int i = 0; i < studentArray.length - 1; i++) {
            for (int j = 0; j < studentArray.length - i - 1; j++) {
                boolean condition = sortAscending 
                    ? compareStudents(studentArray[j], studentArray[j + 1], sortField) > 0
                    : compareStudents(studentArray[j], studentArray[j + 1], sortField) < 0;

                if (condition) {
                    Student temp = studentArray[j];
                    studentArray[j] = studentArray[j + 1];
                    studentArray[j + 1] = temp;
                }
            }
        }

        stack.clear();
        for (Student student : studentArray) {
            stack.push(student);
        }

        System.out.println("Students sorted by Bubble Sort " 
                + sortField.toString().toLowerCase() 
                + " " + (sortAscending ? "ascending" : "descending"));
        displayStudents(); // Display sorted students
    }

    // Quick Sort method to sort by score, name, or ID and display results
    public void quickSortStudents(SortField sortField, boolean sortAscending) {
        Student[] studentArray = stack.toArray(new Student[0]);
        quickSortHelper(studentArray, 0, studentArray.length - 1, sortField, sortAscending);

        stack.clear();
        for (Student student : studentArray) {
            stack.push(student);
        }

        System.out.println("Students sorted by Quick Sort " 
                + sortField.toString().toLowerCase() 
                + " " + (sortAscending ? "ascending" : "descending"));
        displayStudents(); // Display sorted students
    }

    // Helper method for Quick Sort
    private void quickSortHelper(Student[] array, int low, int high, SortField sortField, boolean sortAscending) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, sortField, sortAscending);
            quickSortHelper(array, low, pivotIndex - 1, sortField, sortAscending);
            quickSortHelper(array, pivotIndex + 1, high, sortField, sortAscending);
        }
    }

    // Partition method for Quick Sort
    private int partition(Student[] array, int low, int high, SortField sortField, boolean sortAscending) {
        Student pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = sortAscending 
                ? compareStudents(array[j], pivot, sortField) <= 0
                : compareStudents(array[j], pivot, sortField) >= 0;

            if (condition) {
                i++;
                Student temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        Student temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    // Helper method to compare students based on sortField
    private int compareStudents(Student s1, Student s2, SortField sortField) {
        switch (sortField) {
            case SCORE:
                return Double.compare(s1.getScore(), s2.getScore());
            case NAME:
                return s1.getName().compareToIgnoreCase(s2.getName());
            case ID:
                return s1.getStudentCode().compareToIgnoreCase(s2.getStudentCode());
            default:
                throw new IllegalArgumentException("Invalid sort field.");
        }
    }

    // Menu to choose sorting algorithm
    public void chooseSortingAlgorithm() {
    if (stack.isEmpty()) {
        System.out.println("No students available to sort. Please add students first.");
        return;
    }

    Scanner scanner = new Scanner(System.in);

    try {
        System.out.println("Choose sorting algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Quick Sort");
        int algorithmChoice = scanner.nextInt();

        if (algorithmChoice != 1 && algorithmChoice != 2) {
            System.out.println("Invalid choice for sorting algorithm. Please enter 1 or 2.");
            return;
        }

        System.out.println("Choose sorting criterion:");
        System.out.println("1. Score");
        System.out.println("2. Name");
        System.out.println("3. ID");
        int fieldChoice = scanner.nextInt();

        SortField sortField;
        switch (fieldChoice) {
            case 1:
                sortField = SortField.SCORE;
                break;
            case 2:
                sortField = SortField.NAME;
                break;
            case 3:
                sortField = SortField.ID;
                break;
            default:
                System.out.println("Invalid choice for sorting criterion. Please enter 1, 2, or 3.");
                return;
        }

        System.out.println("Choose sorting order:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int orderChoice = scanner.nextInt();

        if (orderChoice != 1 && orderChoice != 2) {
            System.out.println("Invalid choice for sorting order. Please enter 1 or 2.");
            return;
        }

        boolean sortAscending = (orderChoice == 1);

        if (algorithmChoice == 1) {
            bubbleSortStudents(sortField, sortAscending);
        } else {
            quickSortStudents(sortField, sortAscending);
        }

    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number for each choice.");
        scanner.nextLine(); // Clear invalid input
    } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }
}


   // Method to edit student details
    public void editStudent(String studentCode) {
        Stack<Student> tempStack = new Stack<>();
        boolean found = false;
        Scanner scanner = new Scanner(System.in);

        while (!stack.isEmpty()) {
            Student student = stack.pop();
            if (student.getStudentCode().equals(studentCode)) {
                System.out.println("Editing student: " + student);

                System.out.print("Enter new name (leave blank to keep unchanged): ");
                String newName = scanner.nextLine();
                if (!newName.trim().isEmpty() && newName.matches("[a-zA-Z]+")) {
                    student.setName(newName);
                }

                System.out.print("Enter new score (enter -1 to keep unchanged): ");
                double newScore = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (newScore >= 0 && newScore <= 10) {
                    student.setScore(newScore);
                }

                found = true;
            }
            tempStack.push(student);
        }

        // Re-sort the stack after editing
        while (!tempStack.isEmpty()) {
            Student tempStudent = tempStack.pop();
            Stack<Student> sortedStack = new Stack<>();
            while (!stack.isEmpty() && stack.peek().getScore() > tempStudent.getScore()) {
                sortedStack.push(stack.pop());
            }
            stack.push(tempStudent);
            while (!sortedStack.isEmpty()) {
                stack.push(sortedStack.pop());
            }
        }

            if (!found) {
                System.out.println("Student ID not found.");
            } else {
                System.out.println("Student details updated successfully.");
            }
    }

    // Method to delete a student by code
    public void deleteStudent(String studentCode) {
        Stack<Student> tempStack = new Stack<>();
        boolean found = false;

        while (!stack.isEmpty()) {
            Student student = stack.pop();
            if (student.getStudentCode().equals(studentCode)) {
                found = true;
                System.out.println("Deleted student: " + student);
            } else {
                tempStack.push(student);
            }
        }

        // Restore the remaining students to the original stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
}
