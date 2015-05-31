package rangeguild;

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

@Script.Manifest(name="EZ RangeGuild", description="start at range guild near instructor. Make sure you have 1 iron arrow in your inventory.")
public class RangeGuild extends PollingScript<ClientContext> implements PaintListener {
	@SuppressWarnings("rawtypes")
	private List<Task> tasklist = new ArrayList<Task>();
	
	private int startRangeXp;
	public long startTime;
	
	@Override
    public void start() {
		tasklist.add(new Talk(ctx));
		tasklist.add(new Equip(ctx));
		tasklist.add(new Compete(ctx));
        
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
	    g.fillRect(10, 250, 2, 50); //left line of box
	    g.fillRect(10, 250, 250, 2); //top line of box
	    g.fillRect(260, 250, 2, 50); //right line of box
	    g.fillRect(10, 298, 251, 2); //bottom line of box
	 
    
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Current action: "+currentAction, 13, 265);
        g.drawString("Range xp gained:"+ (ctx.skills.experience(Skills.RANGE) - startRangeXp), 13, 289);
	}

}
