package hGFighter;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class Eat extends Task<ClientContext> {
	int lobster = 379;
	
	public Eat(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		// TODO Auto-generated method stub
		return ctx.combat.health() < 15 && ctx.inventory.select().id(lobster).count(true) > 1;
	}

	@Override
	public void execute() {
		Item item = ctx.inventory.select().id(lobster).first().poll();
		item.click();
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Eating";
	}

}
