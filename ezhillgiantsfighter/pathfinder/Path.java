package pathfinder;

import org.powerbot.script.Tile;

import src.pathfinder.core.util.Structure;
import src.pathfinder.core.wrapper.PathNode;
import src.pathfinder.impl.Pathfinder;
import src.pathfinder.core.wrapper.TilePath;

public class Path {
	
	public static Tile toTile(int hash){
		return new Tile(Structure.TILE.getX(hash), Structure.TILE.getY(hash), Structure.TILE.getZ(hash));
	}
	
	public static Tile[] getTiles(int begin1, int begin2, int end1, int end2){
		TilePath tilePath = new Pathfinder().findPath(Structure.TILE.getHash(begin1, begin2, 0), Structure.TILE.getHash(end1, end2, 0), 1000, false);

		Tile[] path = new Tile[tilePath.size()];
		int i = 0;

		for(PathNode pn : tilePath){
			path[i] = toTile(pn.getHash());
			i++;
		}
		return path;
	}
}
