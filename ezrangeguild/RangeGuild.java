package rangeguild;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
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
	public static long startTime;
	
	@Override
    public void start() {
		tasklist.add(new Talk(ctx));
		tasklist.add(new Equip(ctx));
		tasklist.add(new Compete(ctx));
        
        startTime = System.currentTimeMillis();
        startRangeXp = ctx.skills.experience(Skills.RANGE);  
    }
	
	public String runTime(long i) {
		DecimalFormat nf = new DecimalFormat("00");
		long millis = System.currentTimeMillis() - i;
		long hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		long minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		long seconds = millis / 1000;
		return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
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
	
	public String perHour(int gained) {
        return formatNumber((int) ((gained) * 3600000D / (System.currentTimeMillis() - startTime)));
    }

	public String formatNumber(int start) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = start;
        if(i >= 1000000) {
            return nf.format((i / 1000000)) + "m";
        }
        if(i >=  1000) {
            return nf.format((i / 1000)) + "k";
        }
        return ""+start;
    }
	
	private Font font = new Font("Verdana", Font.PLAIN, 12);
	private String currentAction = "";
	
	@Override
	public void repaint(Graphics g) {
		int xpDif = ctx.skills.experience(Skills.RANGE) - startRangeXp;
				
		Color red = new Color(255, 150,150, 128 );
		g.setColor(red);
	    g.fillRect(10, 200, 200,100);
	    Color darkred = new Color(255, 50,50, 128 );
	    g.setColor(darkred);
	    g.fillRect(10, 200, 2, 100); //left line of box
	    g.fillRect(10, 200, 200, 2); //top line of box
	    g.fillRect(210, 200, 2, 100); //right line of box
	    g.fillRect(10, 298, 201, 2); //bottom line of box

		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Current action: "+currentAction, 20, 225);
        g.drawString("Range xp gained:"+ (ctx.skills.experience(Skills.RANGE) - startRangeXp), 20, 245);
        g.drawString("Experience /hr:  " + perHour(xpDif) + "(" + formatNumber(xpDif) + ")", 20, 265);
        g.drawString("Runtime: " + runTime(startTime), 20, 285);
	}

}
