import java.math.BigDecimal;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Demo account: change PIN/balance if you want
        Account acc = new Account("1234567890", "1234", new BigDecimal("1000.00"));

        System.out.println("Welcome to Simple ATM");
        System.out.println("Account: " + acc.getMaskedAccount());

        // ----- Authentication (3 attempts) -----
        boolean authed = false;
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Enter PIN: ");
            String pin = sc.nextLine().trim();
            if (acc.authenticate(pin)) {
                authed = true;
                break;
            }
            System.out.println("Incorrect PIN. Attempts left: " + (2 - attempts));
        }
        if (!authed) {
            System.out.println("Too many attempts. Card blocked.");
            sc.close();
            return;
        }

        // ----- Main Menu -----
        boolean running = true;
        while (running) {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Balance: " + acc.getBalance());
                    break;
                case "2":
                    BigDecimal dep = promptAmount(sc, "Enter deposit amount: ");
                    try {
                        acc.deposit(dep);
                        System.out.println("Deposited. New balance: " + acc.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":
                    BigDecimal wd = promptAmount(sc, "Enter withdrawal amount: ");
                    try {
                        if (acc.withdraw(wd)) {
                            System.out.println("Please collect cash. New balance: " + acc.getBalance());
                        } else {
                            System.out.println("Insufficient balance.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Last transactions:");
                    for (String h : acc.getHistory()) System.out.println(" - " + h);
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Thank you for using Simple ATM.");
        sc.close();
    }

    private static BigDecimal promptAmount(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            try {
                BigDecimal amt = new BigDecimal(s);
                if (amt.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Amount must be positive.");
                    continue;
                }
                return amt;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number, e.g., 250 or 250.50");
            }
        }
    }
}
