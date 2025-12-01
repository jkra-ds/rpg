

import java.util.LinkedList;
import java.util.Queue;


public class Main {
    public static void main(String[] args) {
        Story story = new Story();
        Dialogue intro = new Dialogue("scene-1","Mao ni first scene...", Type.DIALOG);

        Dialogue choiceScene = new Dialogue("scene-2","Tas next... asa ka muadto?", Type.CHOICE);

        DialogueOption opt1 = new DialogueOption("Sa left!", null);
        DialogueOption opt2 = new DialogueOption("Sa right!", null);

        choiceScene.setOptions(new DialogueOption[]{opt1, opt2});
        intro.setNextDialogue(choiceScene);
        story.addDialogue(intro);
        story.addDialogue(choiceScene);
        story.startStory();

        System.out.println(story.getCurrentDialogue().getText());
        story.nextDialogue();
        System.out.println(story.getCurrentDialogue().getText());
    }
}