public class DialogueOption {
    private String text;
    private Dialogue nextDialogue;

    public DialogueOption(String text, Dialogue nextDialogue) {
        this.text = text;
        this.nextDialogue = nextDialogue;
    }
    public String getText() {
        return text;
    }
    public Dialogue getNextDialogue() {
        return nextDialogue;
    }
}