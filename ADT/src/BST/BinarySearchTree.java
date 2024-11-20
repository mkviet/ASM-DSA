package BST;

import java.util.Scanner;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    // Method to add a student
    public void add() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        String studentCode;
        while (true) {
            System.out.print("Enter student code: ");
            studentCode = scanner.nextLine();
            if (!studentCode.trim().isEmpty()) break;
            System.out.println("Student code cannot be empty. Please enter a valid code.");
        }

        double score;
        while (true) {
            try {
                System.out.print("Enter student score: ");
                score = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (score >= 0) break;
                System.out.println("Score cannot be negative. Please enter a valid score.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a numeric score.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Insert the student into the tree
        Student student = new Student(name, studentCode, score);
        insert(student);
        System.out.println("Student added successfully.");
    }

    // Insert method
    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) {
            return new TreeNode(student);
        }
        if (student.studentCode.compareTo(node.student.studentCode) < 0) {
            node.left = insertRec(node.left, student);
        } else if (student.studentCode.compareTo(node.student.studentCode) > 0) {
            node.right = insertRec(node.right, student);
        } else {
            System.out.println("Duplicate student code; cannot insert.");
        }
        return node;
    }

    // Search for a student by studentCode
    public void searchStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student code to search: ");
        String studentCode = scanner.nextLine();

        Student student = search(studentCode);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    public Student search(String studentCode) {
        return searchRec(root, studentCode);
    }

    private Student searchRec(TreeNode node, String studentCode) {
        if (node == null) {
            return null;
        }
        if (studentCode.equals(node.student.studentCode)) {
            return node.student;
        } else if (studentCode.compareTo(node.student.studentCode) < 0) {
            return searchRec(node.left, studentCode);
        } else {
            return searchRec(node.right, studentCode);
        }
    }

    // In-order Traversal
    public void inorderTraversal() {
        inorderRec(root);
    }

    private void inorderRec(TreeNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.student);
            inorderRec(node.right);
        }
    }

    // Pre-order Traversal
    public void preorderTraversal() {
        preorderRec(root);
    }

    private void preorderRec(TreeNode node) {
        if (node != null) {
            System.out.println(node.student);
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    // Post-order Traversal
    public void postorderTraversal() {
        postorderRec(root);
    }

    private void postorderRec(TreeNode node) {
        if (node != null) {
            postorderRec(node.left);
            postorderRec(node.right);
            System.out.println(node.student);
        }
    }

    // Delete a student
    public void delete(String studentCode) {
        root = deleteRec(root, studentCode);
    }

    private TreeNode deleteRec(TreeNode node, String studentCode) {
        if (node == null) {
            System.out.println("Student not found.");
            return node;
        }
        if (studentCode.compareTo(node.student.studentCode) < 0) {
            node.left = deleteRec(node.left, studentCode);
        } else if (studentCode.compareTo(node.student.studentCode) > 0) {
            node.right = deleteRec(node.right, studentCode);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Node with two children: Get the inorder successor (smallest in the right subtree)
            node.student = minValue(node.right);
            node.right = deleteRec(node.right, node.student.studentCode);
        }
        return node;
    }

    private Student minValue(TreeNode node) {
        Student minStudent = node.student;
        while (node.left != null) {
            minStudent = node.left.student;
            node = node.left;
        }
        return minStudent;
    }

    // Update a student
    public void update(String studentCode, String newName, double newScore) {
    Student student = search(studentCode);
    if (student != null) {
        student.name = newName;
        student.score = newScore;

        // Determine rank based on the updated score
        if (newScore >= 0 && newScore <= 5.0) {
            student.rank = "Fail";
        } else if (newScore > 5.0 && newScore <= 6.5) {
            student.rank = "Medium";
        } else if (newScore > 6.5 && newScore <= 7.5) {
            student.rank = "Good";
        } else if (newScore > 7.5 && newScore <= 9.0) {
            student.rank = "Very Good";
        } else if (newScore > 9.0 && newScore <= 10.0) {
            student.rank = "Excellent";
        }

        System.out.println("Student updated successfully.");
    } else {
        System.out.println("Student not found.");
    }
}

    // TreeNode class for the BST
    private static class TreeNode {
        Student student;
        TreeNode left, right;

        TreeNode(Student student) {
            this.student = student;
            this.left = this.right = null;
        }
    }
}
