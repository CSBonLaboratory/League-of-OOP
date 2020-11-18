package champions;

import greatMagician.GreatMagician;

public class ChampionFactory {


    private static ChampionFactory instance=null;

    private ChampionFactory(){}

    public static ChampionFactory getInstance()
    {
        if(instance==null)
            instance=new ChampionFactory();
        return instance;
    }

    public Champion getChampion(char type,int row,int column,int id)
    {
        ModifyFactory modifyInstance=ModifyFactory.getInstance();


        switch (type)
        {
            case 'P':
                return new Pyromancer(row,column,modifyInstance.getPyromancerModifier(),id);
            case 'K':
                return new Knight(row,column,modifyInstance.getKnightModifier(),id);
            case 'R':
                return new Rogue(row,column,modifyInstance.getRogueModifier(),id);
            case 'W':
                return new Wizard(row,column,modifyInstance.getWizardModifier(),id);




        }
        return null;
    }
}
