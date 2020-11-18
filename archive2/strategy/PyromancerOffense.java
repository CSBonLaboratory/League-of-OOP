package strategy;

import champions.Champion;

public class PyromancerOffense extends Strategy {

    public void apply(Champion champion)
    {
        double sacrificeHp;
        sacrificeHp=Math.floor(champion.getCurrentHp()/4);

        champion.setCurrentHp(champion.getCurrentHp()-(float)sacrificeHp);

        changeModifiers(champion,0.7f);

    }
}
