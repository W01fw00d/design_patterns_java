package chain_responsibility;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

// This implementation can be improved using events and subscribers.
// Goblin should ask the game for the number of goblins and king goblins
abstract class Creature {
	public abstract int getAttack();

	public abstract int getDefense();
	
	public abstract String getName();
}

class Goblin extends Creature {
	protected int base_attack = 1;
	protected int base_defense = 1;
	protected String name;
	
	public String getName() {
		return this.name;
	}

	private Game game;

	public Goblin(Game game) {
		this.game = game;
		this.name = "goblin";
	}

	@Override
	public int getAttack() {
		int result = this.base_attack;
		
		for (Creature creature : game.creatures) {
			  try {
				result += getGoblinAttackBonus(creature);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	private int getGoblinAttackBonus(Creature creature) throws ClassNotFoundException {
		if (
				creature != this &&
				creature.getName().equals("globin_king") 
		) {
			return 1;
		}

		return 0;
	}

	@Override
	public int getDefense() {
		int result = this.base_defense;
		
		for (Creature creature : game.creatures) {
			  result += getGoblinDefenseBonus(creature);
		}

		return result;
	}
	
	private int getGoblinDefenseBonus(Creature creature) {
		if (
				creature != this &&
				(creature.getClass() == this.getClass() || this.getClass().isAssignableFrom(creature.getClass()))
		) {
			return 1;
		}

		return 0;
	}
}

class GoblinKing extends Goblin {
	public GoblinKing(Game game) {
		super(game);
		this.name = "globin_king";
		this.base_attack = 3;
		this.base_defense= 3; 
	}
}

enum Statistic {
	ATTACK, DEFENSE
}

class Game {
	public List<Creature> creatures = new ArrayList<>();
}

class ChainResponsibility {
	@Test
	public void Test() {
		Game game = new Game();
		Goblin goblin = new Goblin(game);
		GoblinKing goblin_king = new GoblinKing(game);
		game.creatures.add(goblin);
		game.creatures.add(goblin_king);
		assertEquals(2, goblin.getAttack());
		assertEquals(2, goblin.getDefense());
	}
}
