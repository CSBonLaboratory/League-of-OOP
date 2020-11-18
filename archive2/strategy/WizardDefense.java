package strategy;

import champions.Champion;

public class WizardDefense extends Strategy{

    public void apply(Champion champion)
    {
        double bonusHp;

        bonusHp=Math.floor(champion.getCurrentHp()/5);

        champion.setCurrentHp(champion.getCurrentHp()+(float)bonusHp);

        changeModifiers(champion,-0.2f);
    }
}
