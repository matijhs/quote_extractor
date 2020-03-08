import java.util.List;

public class Main {

    public static void main(String[] args) {
        QuoteExtractor qe = new QuoteExtractor();

        qe.loadSentences("src/main/resources/napoleon.txt");
//        qe.loadSentences("src/main/resources/appendixA.txt");
//        qe.loadSentences("src/main/resources/appendixB.txt");
//        qe.loadSentences("src/main/resources/appendixC.txt");
//        qe.loadSentences("src/main/resources/appendixD.txt");
//        qe.loadSentences("src/main/resources/appendixE.txt");
        qe.extract();
        qe.printQuotes();

    }


}
