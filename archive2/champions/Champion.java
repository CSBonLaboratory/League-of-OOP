package champions;

import angels.Angel;
import greatMagician.GreatMagician;
import strategy.Strategy;
import spells.Spell;

import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class Champion {

    protected float currentHp;
    protected float maxHp;
    protected int hpPerLevel;
    protected int xp;
    protected int row;
    protected int column;
    protected int level;
    protected Spell effect=null;
    protected ArrayList<HashMap<String,Float>> bonuses;
    protected Strategy strategy=null;
    protected int id;
    protected GreatMagician subject;



    public boolean canMove() {
        if (effect == null)
            return true;
        return effect.getCanMove();
    }



    public void giveXP(int loserLevel)
    {
        int max;
        max=200-(this.level-loserLevel)*40;
        if(max>0)
            this.xp=this.xp+max;

        this.levelUp();


    }
    public void setXp(int bonusXp)
    {
        this.xp=bonusXp;
    }
    public void levelUp()
    {
        if(this.xp>=250)
        {
           float experience;
           int newLevel;
           experience=this.xp-250;
           experience=experience/50f;
           newLevel=((int)Math.floor(experience))+1;

           this.maxHp=this.maxHp+(newLevel-this.level)*this.hpPerLevel;
           this.currentHp=this.maxHp;
           this.level=newLevel;


        }
    }

    public Champion(int x,int y,ArrayList<HashMap<String,Float>> bonuses,int id)
    {
        this.xp=0;
        this.row=x;
        this.column=y;
        this.level=0;
        GreatMagician magicianInstance=GreatMagician.getInstance();
        this.subject=magicianInstance;
        this.id=id;

        this.bonuses=new ArrayList<>();    // Deep-copy al structurii ce memoreaza modificatorii rasa initiali

        HashMap<String,Float> first=new HashMap<>();
        first.put("Pyromancer",bonuses.get(0).get("Pyromancer"));
        first.put("Knight",bonuses.get(0).get("Knight"));
        first.put("Rogue",bonuses.get(0).get("Rogue"));
        first.put("Wizard",bonuses.get(0).get("Wizard"));

        this.bonuses.add(first);



        HashMap<String,Float> second=new HashMap<>();

        second.put("Pyromancer",bonuses.get(1).get("Pyromancer"));
        second.put("Knight",bonuses.get(1).get("Knight"));
        second.put("Rogue",bonuses.get(1).get("Rogue"));
        second.put("Wizard",bonuses.get(1).get("Wizard"));

        this.bonuses.add(second);



    }
    public float getCurrentHp()
    {
        return this.currentHp;

    }
    public Spell getEffect()
    {
        return this.effect;
    }
    public void setCurrentHp(float newCurrentHp)
    {
        this.currentHp=newCurrentHp;
    }
    public float getMaxHp(){
        return maxHp;
    }
    public int getXp()
    {
        return xp;
    }
    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }
    public int getLevel()
    {
        return level;
    }
    public Strategy getStrategy(){return strategy;}
    public void setRow(int x)
    {
        this.row=x;
    }
    public void setColumn(int y)
    {
        this.column=y;
    }
    public ArrayList<HashMap<String,Float>> getBonuses()
    {
        return bonuses;
    }
    public void setBonuses(ArrayList<HashMap<String,Float>> modifiers)
    {
        this.bonuses=modifiers;
    }
    public int getId()
    {
        return id;
    }





    public float findModifier(HashMap<String,Float> modifier,Champion victim)
    {
        if(victim instanceof Pyromancer)
            return modifier.get("Pyromancer");
        if(victim instanceof Knight)
            return modifier.get("Knight");
        if(victim instanceof Wizard)
            return modifier.get("Wizard");
        if(victim instanceof Rogue)
            return modifier.get("Rogue");

        return 0f;


    }

    abstract public void chooseStrategy();

    public void applyStrategy()
    {
        this.strategy.apply(this);
    }


    public void deleteEffect()
    {
            this.effect=null;
    }



    abstract public float firstAbility();
    abstract public float secondAbility();
    abstract public float accept(Champion attacker);
    abstract public float attack(Champion victim);
    abstract public void acceptAngel(Angel angel);
    public void updateLevels(int oldLevel,Champion champion)
    {
        GreatMagician greatMagician=GreatMagician.getInstance();

        String notification ="";

        if(champion instanceof Pyromancer)
            notification="Pyromancer "+champion.id+" reached level ";
        if(champion instanceof Knight)
            notification="Knight "+champion.id+" reached level ";
        if(champion instanceof Rogue)
            notification="Rogue "+champion.id+" reached level ";
        if(champion instanceof Wizard)
            notification="Wizard "+champion.id+" reached level ";



        for(int i=oldLevel+1;i<=champion.getLevel();i++)
        {
            greatMagician.getNotifications().add(notification+i);
        }



    }
    public void update(int win,int lose,Champion loser)
    {
        String playerNotification= "";
        GreatMagician magician=GreatMagician.getInstance();
        if(loser instanceof Pyromancer)
            playerNotification="Player Pyromancer "+ lose +" was killed by ";
        if(loser instanceof Knight)
            playerNotification="Player Knight "+ lose +" was killed by ";
        if(loser instanceof Rogue)
            playerNotification="Player Rogue "+ lose + " was killed by ";
        if(loser instanceof Wizard)
            playerNotification="Player Wizard "+ lose +" was killed by ";

        if(this instanceof Pyromancer)
            playerNotification=playerNotification+"Pyromancer "+win;
        if(this instanceof Knight)
            playerNotification=playerNotification+"Knight "+win;
        if(this instanceof Rogue)
            playerNotification=playerNotification+"Rogue "+win;
        if(this instanceof Wizard)
            playerNotification=playerNotification+"Wizard "+win;

        magician.getNotifications().add(playerNotification);


    }

    public void notifyKillUpdates(int win,int lose)
    {
        subject.updateChampionsKills(win,lose);
    }
    public void notifyLevelUp(int oldLevel,int id)
    {
        subject.updateChampionsLevels(oldLevel,id);
    }




}
