package spells;

public class Ignite extends Spell {

    public Ignite(float calculatedDamage)
    {
        this.duration=2;
        this.damage=calculatedDamage;
        canMove=true;
    }
}
