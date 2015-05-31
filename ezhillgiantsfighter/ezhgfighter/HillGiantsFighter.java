package hGFighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Skills;

@Script.Manifest(name="EZ HillGiantsFighter", description="Start at varrock east bank, and make sure you have a brass key in your inventory or bank and sufficient lobsters.")
public class HillGiantsFighter extends PollingScript<ClientContext> implements PaintListener {
	@SuppressWarnings("rawtypes")
	private List<Task> tasklist = new ArrayList<Task>();
	
	public enum CurrentState {
	    WalkToHG, FightHG, DoBanking, WalkToBank
	}
	
	private int startRangeXp;
	public long startTime;
	
	@Override
    public void start() {
		tasklist.add(new Eat(ctx));
        tasklist.add(new Bank(ctx));
        tasklist.add(new WalkToBank(ctx));
        tasklist.add(new WalkToHG(ctx));
        tasklist.add(new Combat(ctx));
        
        startTime = System.currentTimeMillis();
        startRangeXp = ctx.skills.experience(Skills.RANGE);
    }
	
	@Override
	public void poll() {
		// TODO Auto-generated method stub
		for(@SuppressWarnings("rawtypes") Task task : tasklist){
		
			if(task.activate()){
				currentAction = task.description();
				task.execute();
			}
		}
	}
	
	private Font font = new Font("Verdana", Font.PLAIN, 12);
	private String currentAction = "";
	
	@Override
	public void repaint(Graphics g) {
		Color red = new Color(255, 150,150, 128 );
		g.setColor(red);
	    g.fillRect(10, 250, 250, 50);
	    Color darkred = new Color(255, 50,50, 128 );
	    g.setColor(darkred);
	    g.fillRect(10, 250, 2, 50); //left line
	    g.fillRect(10, 250, 250, 2); //top line
	    g.fillRect(260, 250, 2, 50); //right line
	    g.fillRect(10, 298, 251, 2); //bottom line
	 
    
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Current action: "+currentAction, 13, 265);
        g.drawString("Range xp gained:"+ (ctx.skills.experience(Skills.RANGE) - startRangeXp), 13, 289);
	}

}
