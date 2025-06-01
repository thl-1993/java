package game;

import com.beust.jcommander.JCommander;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Program {

    public static void main(String[] args) {
        try {
            Args jargs = parseArguments(args);
            Path propertiesFile = loadPropertiesFile(jargs);
            startGame(propertiesFile, jargs);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Args parseArguments(String[] args) {
        Args jargs = new Args();
        JCommander jCommander = new JCommander(jargs);
        jCommander.parse(args);
        return jargs;
    }

    private static Path loadPropertiesFile(Args jargs) throws FileNotFoundException {
        String propertiesFileName = "application-" + jargs.getProfile() + ".properties";
        try (InputStream inputStream = Program.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Properties file not found: " + propertiesFileName);
            }

            Path tempFile = Files.createTempFile("temp", ".properties");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return tempFile;
        } catch (Exception e) {
            throw new FileNotFoundException("Error loading properties file: " + e.getMessage());
        }
    }

    private static void startGame(Path propertiesFile, Args jargs) throws Exception {
        Game game = new Game(propertiesFile, jargs);
        game.startGame();
    }
}