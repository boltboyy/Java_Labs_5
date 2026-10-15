import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<String> readLines(String fileName) throws IOException{
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))){
            String line;
            while((line = br.readLine()) != null){
                lines.add(line);
            }
        }
        return lines;
    }
    public static void write(String fileName, String text) throws IOException{
        Files.writeString(Path.of(fileName), text, StandardCharsets.UTF_8);
    }

}
