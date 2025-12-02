package game.util;

import java.io.*;

public static class SaveLoad {

    void save (Player player, String profileName) throws IOException {
        FileOutputStream output = new FileOutputStream(profileName);
        ObjectOutputStream objectOutput = new ObjectOutputStream(output);
        objectOutput.writeObject(player);
        System.out.println("Progress Saved!");

    }
    Player load (String profileName) throws IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream(profileName);
        ObjectInputStream inputStream = new ObjectInputStream(input);
        Player player = (Player) inputStream.readObject();
        System.out.println(STR."Loading \{profileName} prpgress");
        return player;
    }

}
public static class Player implements Serializable{

}

public  static void main(String[] args) {

}
