import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileHandling {

    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file Path:");
        String filePath = scanner.nextLine();

        readFile(filePath);

        scanner.close();
    }

    public void readFile(String filePath) {
        try {
            FileReader file = new FileReader(filePath);

            BufferedReader fileReader = new BufferedReader(file);
            System.out.println("File Contents:\n");
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error Occured: " + e.getMessage());

        }

    }
}
