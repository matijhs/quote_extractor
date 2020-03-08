import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuotingChars {

    public static Pair latinQuote = Pair.of("“", "”");
    // naive handling of abbreviations for words like Printers' Ink
    public static Pair singleQuote = Pair.of(" \'", "\' ");
    public static Pair doubleQuote = Pair.of("\"", "\"");
    public static Pair colon = Pair.of(":", ".");

    public static List<Pair> getQuotingStyles(){
        return new ArrayList<>(Arrays.asList(latinQuote, singleQuote, doubleQuote));
    }

}
