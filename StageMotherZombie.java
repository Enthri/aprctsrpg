import ui.*;
import ui.elem.*;
import util.*;

public class StageMotherZombie extends Stage {
	
	private DataHandler data;
	
	public StageMotherZombie(DataHandler dataVar) {
		data = dataVar;
	}
	
    private Text mainDialog;
    private Choice a;
    private EntityCreature Boss1;
    private EntityCreature Boss2;
    private EntityCreature Boss3;
    private EntityCreature MotherZombie;
    private int x = 0;
    
    @Override
    public void init() {
        a = new Choice("Continue");
        mainDialog = new Text("", 0, 0);
        this.add(mainDialog, a);
        Boss1 = new EntityCreature(100.0, 500.0);
        Boss2 = new EntityCreature(75.0, 750.0);
        Boss3 = new EntityCreature(150.0, 500.0);
        MotherZombie = new Mother(200.0, 1000.0);
        if(data.battleCompleted) {
            data.battleCompleted = false;
            if(x == 1) {
            	mainDialog.setText("You approach the second boss..");
            } else if(x == 2) {
            	mainDialog.setText("You approach the third boss..");
            } else if(x == 3) {
            	mainDialog.setText("The mother zombie approaches!");
            } else if(x == 4) {
            	mainDialog.setText("What's happening here?");
            }
            return;
        }
        mainDialog.setText("You approach the first boss...");
    }
    
    @Override
    public void choiceClicked(Element elementVar) {
        if(x == 0){
            data.prepareBattle(data.player, Boss1, this);
            this.setStage(0);
            x++;
            return;
        }
        if(x == 1){
            data.prepareBattle(data.player, Boss2, this);
            this.setStage(0);
            x++;
            return;
        }
        if(x == 2){
            data.prepareBattle(data.player, Boss3, this);
            this.setStage(0);
            x++;
            return;
        }
        if(x == 3){
            data.prepareBattle(data.player, MotherZombie, this);
            this.setStage(0);
            x++;
            return;
        }
        if(x == 4){
            nextStage();
        }
    }

	@Override
	public void taskPerformed() {
	}
}
