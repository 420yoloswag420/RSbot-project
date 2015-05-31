package rangeguild;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Mouse;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class Talk extends Task<ClientContext> {

	int ironarrow = 884;
	int bronzearrow = 882;
	int instructor = 6070;
	
	Mouse mouse = new Mouse(ctx);
	
	public Talk(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {	
		// TODO Auto-generated method stub
		return ctx.inventory.select().id(ironarrow).count() == 0 && ctx.inventory.select().id(bronzearrow).count() == 0;
	}

	@Override
	public void execute() {
		
		Npc npc = ctx.npcs.select().id(instructor).nearest().poll();
		
		if(!npc.interacting().equals(ctx.players.local())){
			Condition.sleep(1000);
			npc.interact("Talk-to");
		}
		
		Condition.wait(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				ctx.widgets.component(231, 2).interact("Continue");
				return ctx.widgets.component(231, 2).visible();
			}
		}, 10, 50);
		
		Condition.sleep(2000);
		
		mouse.click(Random.nextInt(194, 332), Random.nextInt(388, 400), true);
		
		Condition.sleep(2000);
		
		mouse.click(Random.nextInt(143, 287), Random.nextInt(444, 455), true);
		
		Condition.sleep(2000);
		
		mouse.click(Random.nextInt(235, 378), Random.nextInt(444, 455), true);

		

	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Talking";
	}
}
