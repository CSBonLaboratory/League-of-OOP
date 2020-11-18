package spells;

public class Paralisys extends Spell {

    public Paralisys(float calculatedDamage)
    {
        this.damage=calculatedDamage;
        this.duration=3;
        this.canMove=false;
    }
}
