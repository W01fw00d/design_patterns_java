package chain_responsibility_query_subscriber;

class GoblinKing extends Goblin {
	public GoblinKing(Game game) {
		super(game);
		this.type = CreatureType.GOBLIN_KING;
		this.base_attack = 3;
		this.base_defense = 3;
	}
}
