import java.util.List;

public class Main {

    public static void main(String[] args) {
        QuoteExtractor qe = new QuoteExtractor();

        qe.loadSentences("src/main/resources/napoleon.txt");
        qe.printSentences();
        qe.extract();
        qe.printQuotes();

    }


}
