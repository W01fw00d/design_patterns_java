package chain_responsibility_query_subscriber;

enum CreatureType {
	GOBLIN("goblin"), GOBLIN_KING("goblin_king");

	private String name;

	CreatureType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
