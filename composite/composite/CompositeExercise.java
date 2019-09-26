package composite;

import java.util.*;
import java.util.stream.Collectors;

interface ValueContainer extends Iterable<Integer> {
	int sum();
}

class SingleValue implements ValueContainer {
	public int value;

	public SingleValue(int value) {
		this.value = value;
	}

	@Override
	public Iterator<Integer> iterator() {
		return this.iterator();
	}

	@Override
	public int sum() {
		return this.value;
	}
}

@SuppressWarnings("serial")
class ManyValues extends ArrayList<Integer> implements ValueContainer {
	@Override
	public int sum() {
		return this.stream().collect(Collectors.summingInt(i -> i));
	}
}

@SuppressWarnings("serial")
class MyList extends ArrayList<ValueContainer> {
	public MyList(Collection<? extends ValueContainer> c) {
		super(c);
	}

	public int sum() {
		return this.stream().collect(Collectors.summingInt(i -> i.sum()));
	}
}

class CompositeExerciseDemo {
	public static void main(String[] args) throws Exception {
		Collection<ValueContainer> values = new ArrayList<ValueContainer>();

		values.add(new SingleValue(1));
		
		ManyValues otherValues = new ManyValues();
		otherValues.add(2);
		otherValues.add(3);
		otherValues.add(4);
		values.add(otherValues);

		MyList myList = new MyList(values);

		System.out.println(myList.sum());

	}
}