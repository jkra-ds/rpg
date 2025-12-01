import java.util.LinkedList;
import java.util.Queue;

public class Story {
    private Queue<Dialogue> dialogueQueue = new LinkedList<>();
    private Dialogue currentDialogue;

    public void addDialogue(Dialogue dialogue) {
        dialogueQueue.offer(dialogue);
    }
    public void startStory() {
        currentDialogue = dialogueQueue.poll();
    }
    public Dialogue getCurrentDialogue() {
        return currentDialogue;
    }
    public void nextDialogue() {
        if (currentDialogue != null) {
            if (currentDialogue.getNextDialogue() != null) {
                currentDialogue = currentDialogue.getNextDialogue();
            } else {
                currentDialogue = dialogueQueue.poll();
            }
        }
    }
    public void chooseOption(int index) {
        if (currentDialogue.getOptions() != null && index >= 0 && index < currentDialogue.getOptions().length) {
            currentDialogue = currentDialogue.getOptions()[index].getNextDialogue();
        }
    }
}
