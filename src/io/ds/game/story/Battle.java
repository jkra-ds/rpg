import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

abstract class Character {
    String name;
    int hp;
    int attack;

    public Character(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    public abstract void attack(Character target);

    @Override
    public String toString() {
        return name + " (HP: " + hp + ")";
    }
}

class Player extends Character {
    public Player(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void attack(Character target) {
        target.takeDamage(attack);
        System.out.println(name + " attacks " + target.name + " for " + attack + " damage!");
    }
}

class Enemy extends Character {
    public Enemy(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void attack(Character target) {
        target.takeDamage(attack);
        System.out.println(name + " attacks " + target.name + " for " + attack + " damage!");
    }
}

public class Battle {

    private BattleQueue battleQueue = new BattleQueue(); // Circular queue for current fighting characters
    private RemainingEnemyQueue remainingEnemyQueue = new RemainingEnemyQueue(); // Waiting enemies queue
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    // Circular Queue
    class BattleQueue {
        Character[] queue = new Character[3];
        int front = 0, rear = -1, size = 0;

        void enqueue(Character character) {
            if (size == 3) return;
            rear = (rear + 1) % 3;
            queue[rear] = character;
            size++;
        }

        Character dequeue() {
            if (size == 0) return null;
            Character c = queue[front];
            front = (front + 1) % 3;
            size--;
            return c;
        }

        Character nextTurn() {
            Character c = dequeue();
            enqueue(c);
            return c;
        }

        Character peek() {
            return size == 0 ? null : queue[front];
        }

        Character[] toArray() {
            return queue;
        }
    }

    // Normal queue: backup enemies
    class RemainingEnemyQueue {
        Queue<Enemy> queue = new LinkedList<>();

        void enqueue(Enemy enemy) {
            queue.add(enemy);
        }

        Enemy dequeue() {
            return queue.poll();
        }

        boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public Battle() {
        setupGame();
        startBattle();
    }

    void setupGame() {
        System.out.println("Enter Player Name: ");
        player = new Player(scanner.nextLine(), 100, 20);

    }

    void startBattle() {
        System.out.println("\n=== Battle Start! ===");

        while (!player.isDead()) {
            Character current = battleQueue.peek();

            if (current instanceof Player) {
                playerTurn();
            } else {
                enemyTurn(current);
            }

            checkDeaths();
            battleQueue.nextTurn();
        }

        System.out.println("\nGAME OVER! You lost.");
    }

    void playerTurn() {
        System.out.println("\nYour Turn! Choose enemy to attack:");
        displayEnemies();

        System.out.print("Enter target index (1 or 2): ");
        int targetIndex = scanner.nextInt();
        scanner.nextLine();

        Character target = battleQueue.toArray()[targetIndex];

        if (target != null && !target.isDead()) {
            player.attack(target);
        } else {
            System.out.println("Invalid target! Turn skipped!");
        }
    }

    void enemyTurn(Character enemy) {
        System.out.println("\n" + enemy.name + "'s Turn:");
        enemy.attack(player);
    }

    void displayEnemies() {
        Character[] arr = battleQueue.toArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != null && !arr[i].isDead()) {
                System.out.println(i + ": " + arr[i]);
            }
        }
    }

    void checkDeaths() {
        Character[] arr = battleQueue.toArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != null && arr[i].isDead()) {
                System.out.println(arr[i].name + " died!");
                arr[i] = null;
                battleQueue.size--;

                if (!remainingEnemyQueue.isEmpty()) {
                    Enemy next = remainingEnemyQueue.dequeue();
                    System.out.println(next.name + " enters the battle!");
                    battleQueue.enqueue(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Battle();
    }
}
