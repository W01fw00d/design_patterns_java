package chain_responsibility_query_subscriber;

import java.util.ArrayList;

class Query {
	public Creature creature;
	public ArrayList<CreatureType> creature_types;
	public int result;

	public Query(Creature creature, ArrayList<CreatureType> creature_types) {
		this.creature = creature;
		this.creature_types = creature_types;
	}
}
