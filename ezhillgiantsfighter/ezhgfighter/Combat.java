package hGFighter;

import java.util.Iterator;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Actor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.Player;

public class Combat extends Task<ClientContext> {

	public Tile saveSpot1 = new Tile(3115, 9829);
	public int[] giants = {2102, 2103, 2098, 2101};
	public int killed = 0;
	int lobster = 379;
	
	public Combat(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		
		// TODO Auto-generated method stub
		return ctx.movement.distance(saveSpot1) >= 1 && ctx.inventory.select().id(lobster).count(true) > 1;
	}
	
	public boolean validId(int id){
		for (int i = 0; i < giants.length; i++){
		   if(giants[i] == id){
		      return true;
		  }
		}
		
		return false;
	}
	
	public final Filter<Npc> GIANTSFILTER = new Filter<Npc>() {
		@Override
		public boolean accept(Npc npc) {
			return !npc.inCombat() && validId(npc.id()) && !npc.interacting().valid();
		}
	};

	@Override
	public void execute() {
		
		Npc npc = ctx.npcs.select().select(GIANTSFILTER).nearest(ctx.players.local().tile()).poll();
		Npc target = null;
		if(ctx.players.local().interacting().health() == 0){
			target = null;
			killed++;
			Condition.sleep(500);
		}
		
		if(!npc.inCombat() && !ctx.players.local().inCombat() && !ctx.players.local().interacting().valid()){
			Condition.sleep(256);
			npc.interact("Attack");
			target = npc;
			Condition.sleep(548);
		}
		
		if(ctx.players.local().inCombat() && target != null){
	
			if(ctx.players.local().animation() == -1 && !ctx.players.local().interacting().valid()){
				target.interact("Attack");
			}
			
			Condition.sleep(168);
		}
	
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "In combat";
	}
}
