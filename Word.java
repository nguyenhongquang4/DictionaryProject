public class Word {
    private String word_target;
    private String word_explain;
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
    String getWord_target() {
        return word_target;
    }
    String getWord_explain() {
        return word_explain;
    }
}