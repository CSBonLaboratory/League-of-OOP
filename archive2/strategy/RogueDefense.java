package strategy;

import champions.Champion;

public class RogueDefense extends Strategy {

    public void apply(Champion champion)
    {
        double bonusHp;

        bonusHp=Math.floor(champion.getCurrentHp()/2);

        champion.setCurrentHp(champion.getCurrentHp()+(float) bonusHp);

        changeModifiers(champion,-0.1f);


    }
}
