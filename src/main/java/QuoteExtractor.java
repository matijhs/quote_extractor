import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * step1:
 * Break the input into sentences. Its tricky dealing with quotes, parentheticals and abbreviations.
 *
 * step2:
 * Extract quotes/persons from sentences
 *
 * step3:
 * Assign persons to quotes
 */
public class QuoteExtractor {

    private static final String LESS_STRICT_NAME_PATTERN = "[A-Z][a-z]*\\s[a-z]*";
    private static final String FULL_NAME_PATTERN = "[A-Z][a-záé'-]*\\s[A-Z][a-zA-Z]*";

    private List<QuotePersonPair> quotesList;
    private List<String> sentenceList;

    public QuoteExtractor() {
        this.quotesList = new ArrayList<>();
        this.sentenceList = new ArrayList<>();
    }

    public List<String> loadSentences(String filePath){
        try {
            this.sentenceList =  Utils.readSentences(filePath);
            return this.sentenceList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void extract(){
        for (int i = 0; i < sentenceList.size(); i++) {
            String sentence = sentenceList.get(i);
            extractQuotes(sentence);
        }
    }

    public int getDistance(String sentence, String person, String quote){
        int pIndex = sentence.indexOf(person);
        int qIndex = sentence.indexOf(quote);
        return Math.abs(qIndex-pIndex);
    }

    public String getPerson(String sentence){
        String person = Utils.findPersonName(sentence, FULL_NAME_PATTERN);
        return person;
    }

    public void extractQuotes(String sentence) {
        String[] quotes = null;
        String person = getPerson(sentence);
        for(Pair quoteChar : QuotingChars.getQuotingStyles()) {
            if(sentence.contains(quoteChar.getLeft().toString()) && sentence.contains(quoteChar.getRight().toString())) {
                quotes = StringUtils.substringsBetween(sentence, quoteChar.getLeft().toString(), quoteChar.getRight().toString());
                if (quotes != null) {
                    for (String q : quotes) {
                        if (Utils.notOneWordQuote(q)) {
                            quotesList.add(new QuotePersonPair(person, q));
                        }
                    }
                }
            }
        }
    }

    public void printSentences(){
        for(String s : sentenceList){
            System.out.println(s);
        }
    }

    public void printQuotes(){
        System.out.println("--------------");
        System.out.println("Quotes found:");
        for(QuotePersonPair qpp : quotesList){
            System.out.println(qpp.getPerson() + ": \"" + qpp.getQuote() + "\"");
        }
    }
}
