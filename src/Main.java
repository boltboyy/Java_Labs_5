import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> lines = FileManager.readLines("input.txt");
            List<List<String>> paragraphs = TextAligner.splitParagraphs(lines);
            int minWidth = TextAligner.maxWordLength(paragraphs) + TextAligner.INDENT;
            int width = readWidth(minWidth);
            String text = TextAligner.align(paragraphs, width);
            FileManager.write("output.txt", text);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
        System.out.println("Результат записан в output.txt");
    }
    public static int readWidth(int minWidth) throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while(true) {
            try {
                System.out.println("Введите ширину строки не меньше " + minWidth);
                String line = br.readLine();
                if (line == null){
                    throw new IOException("некорректные данные. ввод закрыт");
                }
                if(Integer.parseInt(line.trim()) < minWidth){
                    System.out.println("Введите корректные данные!");
                    continue;
                }
                return Integer.parseInt(line.trim());
            }catch(NumberFormatException numberFormatException){
                System.out.println("Введите число! " + numberFormatException.getMessage());
            }
        }
    }
}
