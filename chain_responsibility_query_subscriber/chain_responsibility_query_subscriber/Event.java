package chain_responsibility_query_subscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<TArgs> {
	private int index = 0;
	private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

	public int subscribe(Consumer<TArgs> handler) {
		int i = index;
		handlers.put(index++, handler);
		return i;
	}

	public void unsubscribe(int key) {
		handlers.remove(key);
	}

	public void fire(TArgs args) {
		for (Consumer<TArgs> handler : handlers.values())
			handler.accept(args);
	}
}
