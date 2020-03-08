public class QuotePersonPair {

    private String person;
    private String quote;

    public QuotePersonPair(String person, String quote) {
        if(person == null){
            person = "???";
        }
        this.person = person;
        this.quote = quote;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
