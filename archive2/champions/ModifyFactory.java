package champions;

import java.util.ArrayList;
import java.util.HashMap;

public class ModifyFactory {

     //PYROMANCER

    private ArrayList<HashMap<String,Float>>pyromancerModifier;


     private void initPyromancer()
    {
        pyromancerModifier=new ArrayList<>();

         HashMap<String,Float>fireblastModifier;
         HashMap<String ,Float>igniteModifier;
         HashMap<String,Float>volcanicModifier;
        fireblastModifier=new HashMap<>();
        fireblastModifier.put("Rogue",0.8f);
        fireblastModifier.put("Knight",1.2f);
        fireblastModifier.put("Pyromancer",0.9f);
        fireblastModifier.put("Wizard",1.05f);

        pyromancerModifier.add(fireblastModifier);

        igniteModifier=new HashMap<>();
        igniteModifier.put("Rogue",0.8f);
        igniteModifier.put("Knight",1.2f);
        igniteModifier.put("Pyromancer",0.9f);
        igniteModifier.put("Wizard",1.05f);

        pyromancerModifier.add(igniteModifier);

        volcanicModifier=new HashMap<>();
        volcanicModifier.put("Volcanic",1.25f);

        pyromancerModifier.add(volcanicModifier);


    }

    public ArrayList<HashMap<String, Float>> getPyromancerModifier() {
        return pyromancerModifier;
    }

    //KNIGHT

    private ArrayList<HashMap<String,Float>> knightModifier;

     private void initKnight()
     {
         HashMap<String,Float> executeModifier;
         HashMap<String,Float>slamModifier;
         HashMap<String,Float>landsModifier;
         knightModifier=new ArrayList<>();

         executeModifier=new HashMap<>();
         executeModifier.put("Rogue",1.15f);
         executeModifier.put("Knight",1f);
         executeModifier.put("Pyromancer",1.1f);
         executeModifier.put("Wizard",0.8f);

         knightModifier.add(executeModifier);

         slamModifier=new HashMap<>();
         slamModifier.put("Rogue",0.8f);
         slamModifier.put("Knight",1.2f);
         slamModifier.put("Pyromancer",0.9f);
         slamModifier.put("Wizard",1.05f);

         knightModifier.add(slamModifier);

         landsModifier=new HashMap<>();
         landsModifier.put("Lands",1.15f);

         knightModifier.add(landsModifier);
     }
     public ArrayList<HashMap<String ,Float>> getKnightModifier()
     {
         return knightModifier;
     }

     //WIZARD

    private ArrayList<HashMap<String,Float>> wizardModifier;

     private void initWizard()
     {


         HashMap<String,Float> drainModifiers;
         HashMap<String,Float> deflectModifiers;
         HashMap<String,Float> desertModifiers;
         wizardModifier=new ArrayList<>();

         drainModifiers=new HashMap<>();
         drainModifiers.put("Rogue",0.8f);
         drainModifiers.put("Knight",1.2f);
         drainModifiers.put("Pyromancer",0.9f);
         drainModifiers.put("Wizard",1.05f);
         wizardModifier.add(drainModifiers);


         deflectModifiers=new HashMap<>();
         deflectModifiers.put("Rogue",1.2f);
         deflectModifiers.put("Knight",1.4f);
         deflectModifiers.put("Pyromancer",1.3f);
         deflectModifiers.put("Wizard",0f);
         wizardModifier.add(deflectModifiers);

         desertModifiers=new HashMap<>();
         desertModifiers.put("Desert",1.1f);
         wizardModifier.add(desertModifiers);

     }
     public ArrayList<HashMap<String,Float>> getWizardModifier()
     {
         return wizardModifier;
     }

     //ROGUE

    private ArrayList<HashMap<String,Float>> rogueModifier;

     private void initRogue()
     {
         HashMap<String,Float> backstabModifier;
         HashMap<String,Float> paralysisModifier;
         HashMap<String,Float> woodsModifier;

         rogueModifier=new ArrayList<>();

         backstabModifier=new HashMap<>();
         backstabModifier.put("Rogue",1.2f);
         backstabModifier.put("Knight",0.9f);
         backstabModifier.put("Pyromancer",1.25f);
         backstabModifier.put("Wizard",1.25f);
         rogueModifier.add(backstabModifier);

         paralysisModifier=new HashMap<>();
         paralysisModifier.put("Rogue",0.9f);
         paralysisModifier.put("Knight",0.8f);
         paralysisModifier.put("Pyromancer",1.2f);
         paralysisModifier.put("Wizard",1.25f);
         rogueModifier.add(paralysisModifier);

         woodsModifier=new HashMap<>();
         woodsModifier.put("Woods",1.15f);

         rogueModifier.add(woodsModifier);


     }

     public ArrayList<HashMap<String,Float>> getRogueModifier()
     {

         return rogueModifier;
     }

     private static ModifyFactory instance=null;

     private ModifyFactory()
     {
         initPyromancer();
         initKnight();
         initRogue();
         initWizard();

     }

     public static ModifyFactory getInstance()
     {
         if(instance==null)
             instance=new ModifyFactory();

         return instance;
     }


}
