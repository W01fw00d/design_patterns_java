package chain_responsibility_query_subscriber;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class Tests {
	@Test
	public void Test() {
		Game game = new Game();
		Goblin goblin_a = new Goblin(game);
		Goblin goblin_b = new Goblin(game);
		GoblinKing goblin_king = new GoblinKing(game);

		// Goblin creature starts with attack=1, defense=1
		game.creatures.add(goblin_a);
		assertEquals(1, goblin_a.getAttack());
		assertEquals(1, goblin_a.getDefense());

		// Goblin gets +1 defense for each goblin, even a king
		game.creatures.add(goblin_b);
		assertEquals(1, goblin_a.getAttack());
		assertEquals(2, goblin_a.getDefense());

		// Goblin gets an additional +1 attack for each king
		game.creatures.add(goblin_king);
		assertEquals(2, goblin_a.getAttack());
		assertEquals(3, goblin_a.getDefense());

	}
}
