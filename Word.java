import java.util.Comparator;

public class Word {
    private String word_target, word_explain;   
    
    public String getTarget() {
        return this.word_target;
    }
    public String getExplain() {
        return this.word_explain;
    }

    public void setTarget(String tar) {
        this.word_target = tar;
    }
    public void setExplain(String exp) {
        this.word_explain = exp;
    }

    Word(String tar, String exp) {
        this.word_explain = exp;
        this.word_target = tar;
    }
    /*public static Comparator<Word> alphabetComp = new Comparator<Word>(){
        public int compare(Word w1, Word w2) {
            String en1 = w1.getTarget();
            String en2 = w2.getTarget();
            return en1.compareTo(en2);
        }

    };*/
    
    
}
