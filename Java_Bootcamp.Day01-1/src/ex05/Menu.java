package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private final boolean isDev;
    private final Scanner scanner;
    TransactionsService service;

    public Menu(boolean isDev) {
        this.isDev = isDev;
        scanner = new Scanner(System.in);
        service = new TransactionsService();
    }

    private static void printUnpaired(Transaction[] checkTransactions) {
        System.out.println("Check results: " + checkTransactions.length);
        for (Transaction checkTransaction : checkTransactions) {
            System.out.println(checkTransaction.getRecipient() +
                    " has an unacknowledged transfer id = " + checkTransaction.getIdentifier() +
                    " from " + checkTransaction.getSender() +
                    " for " + checkTransaction.getTransferAmount());
        }
    }

    public void start() {
        while (true) {
            try {
                printMenu();
                switch (readInputCategory()) {
                    case addUser:
                        addUser();
                        break;
                    case viewUserBalances:
                        viewUserBalances();
                        break;
                    case performTransfer:
                        performTransfer();
                        break;
                    case viewAllTransactionsForUser:
                        viewAllTransactionsForUser();
                        break;
                    case removeTransferByID:
                        removeTransferByID();
                        break;
                    case checkTransferValidity:
                        checkTransferValidity();
                        break;
                    case finishExecution:
                        return;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void checkTransferValidity() {
        printUnpaired(service.checkTransactions());
    }

    private void removeTransferByID() {
        System.out.println("Enter a user ID and a transfer ID");
        int userId = getId();
        String uuidStr = scanner.next();
        UUID uuid = UUID.fromString(uuidStr);
        Transaction transaction = service.removeTransactionByTransactionIdAndUserId(uuid, userId);
        System.out.println("Transfer To " + transaction.getRecipient() + " " + transaction.getTransferAmount() + " removed");
    }

    private void viewAllTransactionsForUser() {
        System.out.println("Enter a user ID");
        User user = service.getUserById(getId());
        System.out.println(user.getName() + " " + user.getBalance());
        Transaction[] transactions = user.getList().toArray();
        for (Transaction transaction : transactions) {
            System.out.println("To " + transaction.getRecipient() +
                    " " + transaction.getTransferAmount() +
                    " with id = " + transaction.getIdentifier());
        }
    }

    private void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        service.transferTransaction(getId(), getId(), getBalance());
        System.out.println("The transfer is completed");
    }

    private void viewUserBalances() {
        System.out.println("Enter a user ID");
        User user = service.getUserById(getId());
        System.out.println(user.getName() + " " + user.getBalance());
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        User user = new User(scanner.next(), getBalance());
        service.addUser(user);
        System.out.println("User with id = " + user.getId() + " is added");
    }

    public void printMenu() {
        System.out.print("1. Add a user\n" +
                "2. View user balances\n" +
                "3. Perform a transfer\n" +
                "4. View all transactions for a specific user\n"
        );
        if (isDev) {
            System.out.println("5. DEV – remove a transfer by ID\n" +
                    "6. DEV – check transfer validity\n" +
                    "7. Finish execution");
        } else {
            System.out.println(
                    "7. Finish execution");
        }
    }

    public MenuCategory readInputCategory() {
        int input;
        input = getInput();
        switch (input) {
            case 1:
                return MenuCategory.addUser;
            case 2:
                return MenuCategory.viewUserBalances;
            case 3:
                return MenuCategory.performTransfer;
            case 4:
                return MenuCategory.viewAllTransactionsForUser;
            case 5:
                return MenuCategory.removeTransferByID;
            case 6:
                return MenuCategory.checkTransferValidity;
            case 7:
                return MenuCategory.finishExecution;
            default:
                throw new IllegalArgumentException("Error input: " + input);
        }
    }

    private int getInput() {
        int input;
        String inputStr = scanner.next();
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error input: " + inputStr);
        }
        if (input < 1 || input > 7) {
            throw new IllegalArgumentException("Error input: " + input);
        }
        if (!isDev && (input == 5 || input == 6)) {
            throw new IllegalArgumentException("Error input: " + input);
        }
        return input;
    }

    private int getBalance() {
        String balanceStr = scanner.next();
        int balance;
        try {
            balance = Integer.parseInt(balanceStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error input balance: " + balanceStr);
        }
        return balance;
    }

    private int getId() {
        String idStr = scanner.next();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error input id: " + idStr);
        }
        return id;
    }
}
