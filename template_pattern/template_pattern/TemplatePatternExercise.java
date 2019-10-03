package template_pattern;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

class Creature {
	public int attack, health;

	public Creature(int attack, int health) {
		this.attack = attack;
		this.health = health;
	}
}

abstract class CardGame {
	public int NO_WINNER = -1;

	public Creature[] creatures;

	public CardGame(Creature[] creatures) {
		this.creatures = creatures;
	}

	// returns -1 if no clear winner (both alive or both dead)
	public int combat(int creature1, int creature2) {
		Creature first = creatures[creature1];
		Creature second = creatures[creature2];
		hit(first, second);
		hit(second, first);
		
		if (first.health <= 0) {
			if (second.health <= 0) {
				return NO_WINNER;
			}
			return creature2;
		} else if (second.health <= 0) {
			return creature1;
		}

		return NO_WINNER;
	}

	// attacker hits other creature
	protected abstract void hit(Creature attacker, Creature other);
}

class TemporaryCardDamageGame extends CardGame {

	public TemporaryCardDamageGame(Creature[] creatures) {
		super(creatures);
	}

	@Override
	protected void hit(Creature attacker, Creature other) {
		if (attacker.attack >= other.health) {
			other.health = 0;
		}
	}
}

class PermanentCardDamageGame extends CardGame {

	public PermanentCardDamageGame(Creature[] creatures) {
		super(creatures);
	}

	@Override
	protected void hit(Creature attacker, Creature other) {
		other.health -= attacker.attack;
	}
}

public class TemplatePatternExercise {
	@Test
	public void TemporaryDamageTest() {
		Creature creature_a = new Creature(1, 2);
		Creature creature_b = new Creature(1, 3);
		
		// With temporary damage, no creature wins ever
		TemporaryCardDamageGame temp_dmg_game = new TemporaryCardDamageGame(
				new Creature[]{creature_a, creature_b}
		);
		assertEquals(
				temp_dmg_game.NO_WINNER,
				temp_dmg_game.combat(0, 1)
		);
		assertEquals(
				temp_dmg_game.NO_WINNER,
				temp_dmg_game.combat(0, 1)
		);
	}
	
	@Test
	public void PermanentDamageTest() {
		Creature creature_a = new Creature(1, 2);
		Creature creature_b = new Creature(1, 3);
		
		// With permanent damage, second creature will win at second combat
		PermanentCardDamageGame perm_dmg_game = new PermanentCardDamageGame(
				new Creature[]{creature_a, creature_b}
		);
		assertEquals(
				perm_dmg_game.NO_WINNER,
				perm_dmg_game.combat(0, 1)
		);
		assertEquals(
				1,
				perm_dmg_game.combat(0, 1)
		);
	}
}
