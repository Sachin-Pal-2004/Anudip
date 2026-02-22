package ATM_Machine;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AtmSystem {

    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== ATM MACHINE =====");
            System.out.println("1. Create Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Delete Account");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    Account acc1 = authenticateAccount();
                    if (acc1 != null) acc1.showDetails();
                    break;

                case 3:
                    Account acc2 = authenticateAccount();
                    if (acc2 != null) {
                        System.out.print("Enter amount to deposit: ");
                        double amount = sc.nextDouble();
                        acc2.deposit(amount);
                        System.out.println("✅ Deposit Successful!");
                    }
                    break;

                case 4:
                    Account acc3 = authenticateAccount();
                    if (acc3 != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = sc.nextDouble();
                        if (acc3.withdraw(amount)) {
                            System.out.println("✅ Withdrawal Successful!");
                        } else {
                            System.out.println("❌ Insufficient Balance!");
                        }
                    }
                    break;

                case 5:
                    Account acc4 = authenticateAccount();
                    if (acc4 != null) {
                        accounts.remove(acc4);
                        System.out.println("✅ Account Deleted Successfully!");
                    }
                    break;

                case 6:
                    System.out.println("Thank You for Using ATM!");
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // CREATE ACCOUNT WITH AUTO NUMBER
    public static void createAccount() {

        int accNo = generateAccountNumber();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        System.out.print("Set 4-digit PIN: ");
        String pin = sc.next();

        accounts.add(new Account(accNo, name, balance, pin));

        System.out.println("✅ Account Created Successfully!");
        System.out.println("🎉 Your Account Number is: " + accNo);
    }

    // AUTO ACCOUNT NUMBER GENERATOR
    public static int generateAccountNumber() {

        Random random = new Random();
        int accNo;

        while (true) {
            accNo = 100000 + random.nextInt(900000);

            boolean exists = false;

            for (Account acc : accounts) {
                if (acc.getAccountNumber() == accNo) {
                    exists = true;
                    break;
                }
            }

            if (!exists) return accNo;
        }
    }

    // AUTHENTICATION
    public static Account authenticateAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter PIN: ");
        String enteredPin = sc.next();

        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo &&
                acc.getPin().equals(enteredPin)) {
                return acc;
            }
        }

        System.out.println("❌ Invalid Account Number or PIN!");
        return null;
    }
}