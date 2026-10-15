import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TextAligner {
    public static final int INDENT = 4;
    public static List<List<String>> splitParagraphs(List<String> lines){
        List<List<String>> result = new ArrayList<>();
        for (String line: lines){
            if(line.trim().equals("")) continue;
            if(line.charAt(0) == ' ' || line.charAt(0) == '\t' || result.isEmpty()) {
                result.add(new ArrayList<>());
            }
            StringTokenizer tokenizer = new StringTokenizer(line);
            while(tokenizer.hasMoreTokens()){
                result.get(result.size() - 1).add(tokenizer.nextToken());
            }
        }
        return result;
    }
    public static int maxWordLength(List<List<String>> paragraphs){
        int max = 0;
        for(int i = 0; i< paragraphs.size(); i++){
            for(int j=0; j< paragraphs.get(i).size(); j++){
                if(max < paragraphs.get(i).get(j).length()) max = paragraphs.get(i).get(j).length();
            }
        }
        return max;
    }
    public static String justifyLine(List<String> words, int width){
        int totalLetters = 0;
        for(String str: words){
            totalLetters = totalLetters + str.length();
        }
        int spaces = width - totalLetters;
        int gaps = words.size() - 1;
        if(gaps == 0) return words.get(0) + " ".repeat(spaces);
        StringBuilder sb = new StringBuilder();
        int k;
        for(int i = 0; i < words.size() - 1; i++){
            sb.append(words.get(i));
            k = spaces / gaps;
            sb.append(" ".repeat(k));
            spaces = spaces - k;
            gaps--;
        }
        sb.append(words.get(words.size() - 1));
        return sb.toString();
    }
    public static String alignParagraph(List<String> words, int width){
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(INDENT));
        int lineWidth = width - INDENT;
        List<String> current = new ArrayList<>();
        int currentLength = 0;
        for (String word: words){
            if(current.isEmpty()){
                current.add(word);
                currentLength = word.length();
            }
            else if(currentLength + 1 + word.length() <= lineWidth){
                current.add(word);
                currentLength = currentLength + 1 + word.length();
            }
            else{
                sb.append(justifyLine(current, lineWidth));
                sb.append("\n");
                lineWidth = width;
                current = new ArrayList<>();
                current.add(word);
                currentLength = word.length();
            }
        }
        sb.append(String.join(" ", current));
        return sb.toString();
    }
    public static String align(List<List<String>> paragraphs, int width){
        List<String> aligned = new ArrayList<>();
        for(List<String> paragraph: paragraphs){
            aligned.add(alignParagraph(paragraph, width));
        }
        return String.join("\n", aligned);
    }
}
