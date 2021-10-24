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
    
}
