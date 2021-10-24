public class DictionaryCommandline {
    DictionaryManagement man = new DictionaryManagement();
    public void showAllWords() {
        int n = man.dict.Word.size();
        System.out.println("No\t| English\t\t| Vietnamese");
        for(int i = 0; i< n; i++ ){
            Word wrd = man.dict.Word.get(i);
            String s = i+1 + "\t| " + wrd.getTarget() + "\t\t\t| " + wrd.getExplain();
            System.out.println(s);
        }
    }
    public void dictionaryBasic() {
        man.insertFromCommandline();
        this.showAllWords();
    }
}
