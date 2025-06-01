
package ex00;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        try (WriterFileRes writer = new WriterFileRes("src/ex00/test/text.txt")) {
            Reader reader = new Reader("src/ex00/test/signatures.txt");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter file names (type '42' to exit):");
            String input;

            while (!(input = scanner.next()).equals("42")) {
                String result = reader.readSignatureFile(input);
                writer.writeToFile(result);
                System.out.println("PROCESS " + result);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(-1);
        }
    }
}

