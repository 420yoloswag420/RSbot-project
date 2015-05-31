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
	
	Mouse<ClientContext> mouse = new Mouse<ClientContext>(ctx);
	
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

		if(ctx.inventory.select().id(bronzearrow).count() == 0 && ctx.inventory.select().id(ironarrow).count() == 0)
			npc.interact("Talk-to");
		
		
		Condition.wait(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				ctx.widgets.component(231, 2).interact("Continue");
				return ctx.widgets.component(231, 2).visible();
			}
		}, 10, 50);
		
		Condition.wait(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				ctx.widgets.widget(219).component(0).component(1).interact("Continue");
				return ctx.widgets.widget(219).component(0).component(1).visible();
			}
		}, 10, 50);
		
		Condition.wait(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				ctx.widgets.widget(217).component(2).interact("Continue");
				return ctx.widgets.widget(217).component(2).visible();
			}
		}, 10, 50);
		
		Condition.wait(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				ctx.widgets.component(231, 2).interact("Continue");
				return ctx.widgets.component(231, 2).visible();
			}
		}, 10, 50);
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Talking";
	}
}
