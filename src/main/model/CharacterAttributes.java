package model;


//Represents a user's character's attributes including health, attack, defense, speed
public class CharacterAttributes {
    private int health;
    private int attack;
    private int defense;
    private int speed;

    //EFFECTS: constructs the character's attributes
    public CharacterAttributes(int health, int attack, int defense, int speed) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }


    //MODIFIES: this
    //EFFECTS: adds the character attributes
    public void addCharacterAttributes(int health, int attack, int defense, int speed) {
        this.health += health;
        this.attack += attack;
        this.defense += defense;
        this.speed += speed;
    }

    //MODIFIES: this
    //EFFECTS: subtracts the character attributes
    public void removeCharacterAttributes(int health, int attack, int defense, int speed) {
        if (health >= this.health) {
            this.health = 0;
        } else if (health < this.health) {
            this.health -= health;
        }
        if (attack >= this.attack) {
            this.attack = 0;
        } else if (attack < this.attack) {
            this.attack -= attack;
        }
        if (defense >= this.defense) {
            this.defense = 0;
        } else if (defense < this.defense) {
            this.defense -= defense;
        }
        if (speed >= this.speed) {
            this.speed -= speed;
        } else if (speed < this.health) {
            this.speed -= speed;
        }
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
