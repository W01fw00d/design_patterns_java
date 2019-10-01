package mediator;

import java.util.ArrayList;

class Participant {
	public int value;
	private Mediator mediator;

	public Participant(Mediator mediator) {
		this.value = 0;
		this.mediator = mediator;
		this.mediator.join(this);
	}

	public void say(int number) {
		this.mediator.broadcast(this, number);
	}

	public void listen(int number) {
		this.value += number;
	}
}

class Mediator {

	public ArrayList<Participant> participants;

	public Mediator() {
		this.participants = new ArrayList<Participant>();
	}

	public void broadcast(Participant emisor, int value) {
		for (Participant participant : participants) {
			if (!participant.equals(emisor)) {
				participant.listen(value);
			}
		}
	}

	public void join(Participant participant) {
		participants.add(participant);
	}
}

class Demo {
	public static void main(String[] args) throws Exception {
		Mediator mediator = new Mediator();
		Participant participant_a = new Participant(mediator);
		Participant participant_b = new Participant(mediator);

		System.out.println(participant_a.value);
		System.out.println(participant_b.value);
		System.out.println();
		participant_a.say(2);
		System.out.println(participant_a.value);
		System.out.println(participant_b.value);
		System.out.println();
		participant_b.say(4);
		System.out.println(participant_a.value);
		System.out.println(participant_b.value);
	}
}
