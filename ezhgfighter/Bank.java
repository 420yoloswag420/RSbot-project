package hGFighter;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class Bank extends Task<ClientContext> {
	int[] bankerIds = {2898, 2897};
	int brassKey = 983;
	int lobster = 379;
	
	public Bank(ClientContext ctx) {
		super(ctx);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return (ctx.inventory.select().count() < 2 || ctx.inventory.select().id(lobster).count(true) < 2 || ctx.inventory.select().id(brassKey).count(true) == 0) && ctx.movement.distance(new Tile(3184, 3440)) > 0 &&  ctx.movement.distance(new Tile(3184, 3440)) < 5;
	}

	@Override
	public void execute() {
		
		Npc banker = ctx.npcs.select().id(bankerIds).nearest().poll();
        if (banker.inViewport()){
            banker.interact("Bank");
            Condition.sleep(3000);
            if (ctx.bank.opened()) {
            	if(ctx.inventory.select().count() > 0)
            		ctx.bank.depositInventory();
            	
                if(ctx.bank.select().id(brassKey).count() > 0 && ctx.inventory.select().id(brassKey).count() == 0)
                    ctx.bank.withdraw(brassKey, 1);
                else
                	System.out.println("No brass key found...");
                
                
                if(ctx.bank.select().id(lobster).count() > 0 && ctx.inventory.select().id(lobster).count() == 0)
                    ctx.bank.withdraw(lobster, 27);
                
                ctx.bank.close();
            } else {
            	banker.interact("Bank");
            }
        } else { 
        	ctx.camera.turnTo(banker);
            ctx.movement.step(banker);
            Condition.sleep(1500);
        }

	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Banking";
	}

}
