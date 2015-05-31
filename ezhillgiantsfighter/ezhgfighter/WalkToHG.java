package hGFighter;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import pathfinder.Path;

public class WalkToHG extends Task<ClientContext> {

	public Tile saveSpot1 = new Tile(3115, 9829);
	int lobster = 379;
	int ironarrow = 884;
	public Tile dungeonDoorOutside = new Tile(3115, 3449);
	public Tile dungeonDoorInside = new Tile(3115, 3450);

	
	public WalkToHG(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
	
		// TODO Auto-generated method stub
		return ctx.inventory.select().count() > 2 && ctx.inventory.select().id(lobster).count(true) > 1 && ctx.movement.distance(saveSpot1) == -1;
	}


	@Override
	public void execute() {
		
		if(ctx.movement.distance(dungeonDoorOutside) > 1 || ctx.movement.distance(dungeonDoorOutside) == -1)
			ctx.movement.newTilePath(Path.getTiles(3182, 3440, 3115, 3449)).traverse();
		
		
		if (ctx.movement.distance(dungeonDoorOutside) == 1){

			GameObject door = ctx.objects.select().id(1804).nearest().poll();
	
			if(door.inViewport()){

				Condition.wait(new Callable<Boolean>() {
	                @Override
	                public Boolean call() throws Exception {
	                	ctx.camera.angleTo(Random.nextInt(-360, 360));
						ctx.camera.pitch(Random.nextInt(-100, 100));
	                	door.interact("Open");
	                	
	                    return ctx.movement.distance(dungeonDoorOutside) > 1;
	                }
	            }, 600, 5);
			} else {
        		ctx.camera.turnTo(door, 36);
        	}
		}
		
		if(ctx.movement.distance(dungeonDoorInside) == 1){
			GameObject ladder = ctx.objects.select().id(17384).nearest().poll();
			
			if(ladder.inViewport()){

				Condition.wait(new Callable<Boolean>() {
	                @Override
	                public Boolean call() throws Exception {
	                	ctx.camera.angleTo(Random.nextInt(-360, 360));
						ctx.camera.pitch(Random.nextInt(-100, 100));
						ladder.interact("Climb-down");
	                	
	                    return ctx.movement.distance(dungeonDoorInside) > 1;
	                }
	            }, 600, 5);
			} else {
	    		ctx.camera.turnTo(ladder, 36);
	    	}
		} 
		
		if(ctx.movement.distance(saveSpot1) != -1){
			ctx.movement.step(ctx.movement.closestOnMap(saveSpot1));
		}
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Walking to hill giants";
	}

}
