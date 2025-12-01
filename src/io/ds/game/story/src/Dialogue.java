
public class Dialogue {
    private String dialogueID;
    private String text;
    private DialogueOption[] options;
    private Dialogue nextDialogue;
    private Type type;

    public Dialogue(String dialogueID, String text, Type type) {
        this.dialogueID = dialogueID;
        this.text = text;
        this.type = type;
    }

    public void setOptions(DialogueOption[] options) {
        this.options = options;
    }

    public void setNextDialogue(Dialogue nextDialogue) {
        this.nextDialogue = nextDialogue;
    }

    public String getDialogueID() {
        return dialogueID;
    }

    public String getText() {
        return text;
    }

    public DialogueOption[] getOptions() {
        return options;
    }

    public Dialogue getNextDialogue() {
        return nextDialogue;
    }

    public Type getType() {
        return type;
    }
}