package observable;

import static org.junit.Assert.assertEquals;
import java.io.Closeable;
import java.io.IOException;
import org.junit.Test;
import java.util.*;
import java.util.function.Consumer;

class Event<TArgs> {
	private int count = 0;
	private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

	public Subscription addHandler(Consumer<TArgs> handler) {
		int i = count;
		handlers.put(count++, handler);
		return new Subscription(this, i);
	}

	public void fire(TArgs args) {
		for (Consumer<TArgs> handler : handlers.values())
			handler.accept(args);
	}

	public class Subscription implements AutoCloseable {
		private Event<TArgs> event;
		private int id;

		public Subscription(Event<TArgs> event, int id) {
			this.event = event;
			this.id = id;
		}

		@Override
		public void close() /* throws Exception */
		{
			event.handlers.remove(id);
		}
	}
}

class RatEventArgs
{
  public Object source;

  public RatEventArgs(Object source)
  {
    this.source = source;
  }
}

class Game {
	private ArrayList<Rat> rats = new ArrayList<Rat>();
	public Event<RatEventArgs> ratAdded = new Event<>();
	public Event<RatEventArgs> ratRemoved = new Event<>();
	
	public void addRat(Rat rat) {
		rats.add(rat);
		rat.setAttack(rats.size());

		ratAdded.fire(new RatEventArgs(this));

		rat.rat_added_sub = this.ratAdded.addHandler(rat_args -> rat.ratAdded(rat_args));
		rat.rat_removed_sub = this.ratRemoved.addHandler(rat_args -> rat.ratRemoved(rat_args));		
	}
	
	public void removeRat(Rat rat) {
		rats.remove(rat);

		ratRemoved.fire(new RatEventArgs(rat));
	}
}

class Rat implements Closeable {
	private Game game;
	public int attack = 1;
	
	public Event<RatEventArgs>.Subscription rat_added_sub;
	public Event<RatEventArgs>.Subscription rat_removed_sub;		

	public Rat(Game game) {
		this.game = game;
		this.game.addRat(this);
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public void ratAdded(RatEventArgs rat_args) {
		System.out.println("Rat added!");
		if (!rat_args.source.equals(this)) {
			System.out.println("It's not me, so Attack augmented!");
			attack++;
		}
	}
	
	public void ratRemoved(RatEventArgs rat_args) {
		System.out.println("Rat removed!");
		if (!rat_args.source.equals(this)) {
			System.out.println("It's not me, so Attack diminished!");
			attack--;
		}
	}

	@Override
	public void close() throws IOException {
		rat_added_sub.close();
		rat_removed_sub.close();
		this.game.removeRat(this);
	}
}

public class ObservableExercise {

	@Test
	public void exampleTest() {
		Game game = new Game();
		
		Rat rat_a = new Rat(game);
		assertEquals(1, rat_a.attack);
		
		Rat rat_b = new Rat(game);
		assertEquals(2, rat_a.attack);
		assertEquals(2, rat_b.attack);
		
		try {
			rat_b.close();
			assertEquals(1, rat_a.attack);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// If we remove the last rat, no attack reduction message should appear
		try {
			rat_a.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}