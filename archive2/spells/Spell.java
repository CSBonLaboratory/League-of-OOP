package spells;

public abstract class Spell {
    protected float damage;
    protected int duration;
    protected boolean canMove;

    public float getDamage()
    {
        return damage;
    }
    public boolean getCanMove()
    {
        return canMove;
    }
    public int getDuration()
    {
        return this.duration;
    }
    public void reduceDuration()
    {
        this.duration--;
    }




}
