import java.util.Scanner;

class Node {
    String name, number, email;
    Node left, right;

    Node(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }
}

class BinaryTree {
    private Node root;

    public void insert(String name, String number, String email) {
        root = insertRec(root, name, number, email);
    }

    private Node insertRec(Node root, String name, String number, String email) {
        if (root == null) {
            return new Node(name, number, email);
        }

        int compareResult = name.compareTo(root.name);
        if (compareResult < 0) {
            root.left = insertRec(root.left, name, number, email);
        } else if (compareResult > 0) {
            root.right = insertRec(root.right, name, number, email);
        } else {
            System.out.println("Contact with this name already exists.");
        }

        return root;
    }

    public void search(String key) {
        Node result = searchRec(root, key);
        if (result != null) {
            displayContact(result);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private Node searchRec(Node root, String key) {
        if (root == null || root.name.equals(key)) {
            return root;
        }

        int compareResult = key.compareTo(root.name);
        if (compareResult < 0) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }

    public void delete(String key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, String key) {
        if (root == null) {
            return null;
        }

        int compareResult = key.compareTo(root.name);
        if (compareResult < 0) {
            root.left = deleteRec(root.left, key);
        } else if (compareResult > 0) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.name = minValue(root.right);
            root.right = deleteRec(root.right, root.name);
        }
        return root;
    }

    private String minValue(Node root) {
        String minValue = root.name;
        while (root.left != null) {
            minValue = root.left.name;
            root = root.left;
        }
        return minValue;
    }

    public void displayAll() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            displayContact(root);
            inorderTraversal(root.right);
        }
    }

    private void displayContact(Node contact) {
        System.out.println("Name: " + contact.name + "\tNumber: " + contact.number + "\tEmail: " + contact.email);
    }
}

public class PhoneBookApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree phoneBook = new BinaryTree();

        int choice;
        do {
            System.out.println("\n1. Search\n2. Insert\n3. Display All\n4. Delete\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name to search: ");
                    phoneBook.search(scanner.next());
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter number: ");
                    String number = scanner.next();
                    System.out.print("Enter email: ");
                    String email = scanner.next();
                    phoneBook.insert(name, number, email);
                    break;
                case 3:
                    phoneBook.displayAll();
                    break;
                case 4:
                    System.out.print("Enter name to delete: ");
                    phoneBook.delete(scanner.next());
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }
}
