package chain_responsibility_query_subscriber;

import java.util.ArrayList;
import java.util.List;

class Game {
	public List<Creature> creatures = new ArrayList<>();
	public Event<Query> queries = new Event<>();
	private int token;

	public Game() {
		super();

		token = this.queries.subscribe(query -> {
			query.result = this.countCreaturesByTypes(query.creature, query.creature_types);
		});
	}

	private int countCreaturesByTypes(Creature requester_creature, ArrayList<CreatureType> creature_types) {
		int result = 0;

		for (Creature creature : this.creatures) {
			if (this.isTargetedCreature(creature, requester_creature, creature_types)) {
				result++;
			}
		}

		return result;
	}

	private boolean isTargetedCreature(Creature creature, Creature requester_creature,
			ArrayList<CreatureType> creature_types) {
		return creature != requester_creature
				&& creature_types.stream().anyMatch(creature_type -> creature.getType().equals(creature_type));
	}

}
