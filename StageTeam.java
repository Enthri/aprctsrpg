import ui.*;
import ui.elem.*;

public class StageTeam extends Stage {
    private Dialog mainDialog;
    private Choice a;
    private Choice b;   
    
    @Override
    public void choiceClicked(Element elementVar) {
        Choice choiceObject = (Choice)elementVar;
        if(choiceObject.getLabel().equals("The Marina")){
            eventMarina();
            return;
        } else if(choiceObject.getLabel().equals("The Space Needle")){
            eventTower();
            return;
        }
        if(choiceObject.getLabel().equals("FIGHT")){
            nextStage();
        } else if(choiceObject.getLabel().equals("JOIN 'EM")){
            DataHandler.SHIELD = true;
            nextStage();
        }
    }
    
    @Override
    public void taskPerformed() {
    }
    
    @Override
    public void init() {
        a = new Choice("The Marina");
        b = new Choice("The Space Needle");
        mainDialog = new Dialog("");
        this.addElements(mainDialog, a, b);
        mainDialog.setText("Option 1: The Marina - to steal a boat and travel to the Pitt-Jolie Island", "Option 2: The Space Needle - to get a good look of the area");
    }
    
    public void eventMarina(){
        a.setLabel("FIGHT");
        b.setLabel("JOIN 'EM");
        mainDialog.setText("You're on your way to the marina when a SHIELD transport", "stops you.", "What do you do?");
    }
    
    public void eventTower(){
        a.setLabel("FIGHT");
        b.setLabel("JOIN 'EM");
        mainDialog.setText("You're on top of the Space Needle when a SHIELD ", "helicopter appears in front of you, ordering you to", "stand down", "What do you do?");
    }
}