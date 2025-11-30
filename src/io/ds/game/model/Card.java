package game.model;

public class Card {
    public int damage;
    public int heal;
    public int defense;
    public int DamageToSelf;

    public Card(int damage, int heal, int defense, int DamageToSelf){
        this.damage = damage;
        this.heal = heal;
        this.defense = defense;
        this.DamageToSelf = DamageToSelf;
    }

    public enum effect{
        Normal, Skip_Self, Skip_Next
    }

    public void play(Player self, Enemy enemy){

    }
}
