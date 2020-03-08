import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
    private static final String SAID_PATTERN = "said";

    /**
     * Sentences can end with .?! or a quote as well.
     */
    private static final String END_OF_SENTENCE = "(?<=[.!?][" +
            QuotingChars.doubleQuote.getRight() +
            QuotingChars.latinQuote.getRight() +
            QuotingChars.singleQuote.getRight() + " ])\\s*";
    private CopyOnWriteArraySet<Object> persons;

    public static ArrayList<String> readSentences(String filePath) throws IOException {
        Scanner text = new Scanner(new FileReader(filePath)).useDelimiter(END_OF_SENTENCE);
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        while ((text.hasNext()))
        {
            line = text.next();
            lines.add(line);
        }
        text.close();
        return lines;
    }

    public static String findPersonName(String sentence, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(sentence);
        if(matcher.find()){
           return matcher.group(0);
        }
        return "???";
    }

    /**
     * Do not consider as a quote if its a one word
     */
    public static boolean notOneWordQuote(String quote) {
        return (quote.split("\\s").length) != 1;
    }

    public static List<String> tokenize(String str, String delimiter) {
        return Collections.list(new StringTokenizer(str, delimiter)).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

}
