package strategy;

import champions.Champion;

public class PyromancerDefense extends Strategy {

    public void apply(Champion champion)
    {
        double bonusHp;

        bonusHp=Math.floor(champion.getCurrentHp()/3);

        champion.setCurrentHp(champion.getCurrentHp()+(float) bonusHp);

        changeModifiers(champion,-0.3f);

    }

}
