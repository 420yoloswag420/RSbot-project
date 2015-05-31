package hGFighter;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import pathfinder.Path;

public class WalkToBank extends Task<ClientContext> {
	int lobster = 379;
	int ironarrow = 884;
	Tile bank = new Tile(3183, 3438);
	public Tile saveSpot1 = new Tile(3115, 9829);

	public WalkToBank(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		// TODO Auto-generated method stub
		return ctx.inventory.select().id(lobster).count(true) < 2;
	}

	@Override
	public void execute() {
		
		if(ctx.movement.distance(new Tile(3116, 9851)) == 1){
			GameObject ladder = ctx.objects.select().id(17385).nearest().poll();
			ladder.interact("Climb-up");
		} else if(ctx.movement.distance(new Tile(3116, 3451)) == 1){
			GameObject door = ctx.objects.select().id(1804).nearest().poll();
			
			if(door.inViewport()){
            	ctx.camera.angleTo(Random.nextInt(-360, 360));
				ctx.camera.pitch(Random.nextInt(-100, 100));
            	door.interact("Open");
			} else {
	    		ctx.camera.turnTo(door, 36);
	    	}
		} else if(ctx.movement.distance(saveSpot1) >= 1){
			ctx.movement.step(ctx.movement.closestOnMap(new Tile(3116, 9851)));
		}else {
			ctx.movement.newTilePath(Path.getTiles(3115, 3449,3184, 3440)).traverse();
		}
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Walking to bank";
	}

}
