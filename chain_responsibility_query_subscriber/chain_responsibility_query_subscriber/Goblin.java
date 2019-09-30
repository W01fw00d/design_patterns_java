package chain_responsibility_query_subscriber;

import java.util.ArrayList;

class Goblin extends Creature {
	protected int base_attack = 1;
	protected int base_defense = 1;
	protected CreatureType type;

	public CreatureType getType() {
		return this.type;
	}

	private Game game;

	public Goblin(Game game) {
		this.game = game;
		this.type = CreatureType.GOBLIN;
	}

	@Override
	public int getAttack() {
		ArrayList<CreatureType> creature_types = new ArrayList<CreatureType>();
		creature_types.add(CreatureType.GOBLIN_KING);
		Query query = new Query(this, creature_types);
		game.queries.fire(query);

		return this.base_attack + query.result;
	}

	@Override
	public int getDefense() {
		ArrayList<CreatureType> creature_types = new ArrayList<CreatureType>();
		creature_types.add(CreatureType.GOBLIN);
		creature_types.add(CreatureType.GOBLIN_KING);
		Query query = new Query(this, creature_types);
		game.queries.fire(query);

		return this.base_defense + query.result;
	}
}
