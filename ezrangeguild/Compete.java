package rangeguild;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Compete extends Task<ClientContext> {

	int bronzearrow = 882;
	int ironarrow = 884;
	
	
	public Compete(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		
		// TODO Auto-generated method stub
		return ctx.inventory.select().id(bronzearrow).count() > 0 || ctx.inventory.select().id(ironarrow).count() > 0;
	}

	
	@Override
	public void execute() {

		if(ctx.camera.pitch() > 0){
			ctx.camera.pitch(0);
			
			Condition.sleep(1000);
		}
	
		GameObject obj = ctx.objects.select().id(11663).nearest().poll();
		ctx.camera.turnTo(obj);

		obj.interact("Fire-at");
		Condition.sleep(2000);
		
		Condition.wait(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				ctx.widgets.component(325, 88).interact("Close");
				return ctx.widgets.component(325, 88).visible();
			}
		}, 10, 50);
		
		if(ctx.widgets.component(231, 2).visible()){
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					ctx.inventory.select().id(ironarrow).poll().click();
					return ctx.widgets.component(231, 2).visible();
				}
			}, 10, 50);
		}

	}
	

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Competing";
	}
}
