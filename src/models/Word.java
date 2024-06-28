package models;

public class Word {
    private final String text;
    private final String hint;

    public Word(String text, String hint){
        this.text = text.toLowerCase();
        this.hint = hint;
    }

    public String getText() {
        return text;
    }

    public String getHint() {
        return hint;
    }
}
