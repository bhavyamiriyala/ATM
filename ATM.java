import java.util.*;

public class ATM {
    // Inner class for User
    static class User {
        String userId = "Bhavya";
        String pin = "9398";
    }

    // Inner class for Transactions
    static class Transaction {
        String type;
        double amount;

        Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        public String toString() {
            return type + ": ₹" + amount;
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        List<Transaction> history = new ArrayList<>();
        double balance = 10000;
        double dummyReceiverBalance = 0;

        // Login
        System.out.println("Welcome to the ATM");
        System.out.print("Enter User ID: ");
        String enteredId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (!enteredId.equals(user.userId) || !enteredPin.equals(user.pin)) {
            System.out.println("Invalid credentials. Access denied.");
            return;
        }

        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (history.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        System.out.println("Transaction History:");
                        for (Transaction t : history) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmt = scanner.nextDouble();
                    if (withdrawAmt > 0 && withdrawAmt <= balance) {
                        balance -= withdrawAmt;
                        history.add(new Transaction("Withdraw", withdrawAmt));
                        System.out.println("Withdrawn ₹" + withdrawAmt);
                        
                    } else {
                        System.out.println("Insufficient balance or invalid amount.");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmt = scanner.nextDouble();
                    if (depositAmt > 0) {
                        balance += depositAmt;
                        history.add(new Transaction("Deposit", depositAmt));
                        System.out.println("Deposited ₹" + depositAmt);
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case 4:
                    System.out.print("Enter amount to transfer: ₹");
                    double transferAmt = scanner.nextDouble();
                    if (transferAmt > 0 && transferAmt <= balance) {
                        balance -= transferAmt;
                        dummyReceiverBalance += transferAmt;
                        history.add(new Transaction("Transfer", transferAmt));
                        System.out.println("Transferred ₹" + transferAmt + " to another account.");
                    } else {
                        System.out.println("Transfer failed. Check amount and balance.");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
