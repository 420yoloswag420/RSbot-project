package rangeguild;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class Equip extends Task<ClientContext> {

	int bronzearrow = 882;
	int ironarrow = 884;
	
	
	public Equip(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		
		// TODO Auto-generated method stub
		return ctx.inventory.select().id(bronzearrow).count() > 0;
	}
	
	@Override
	public void execute() {
		Condition.sleep(2000);
		ctx.inventory.select().id(bronzearrow).poll().click();
	}
	

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Equiping";
	}
}
