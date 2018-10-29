package ui;

class Event{
	
}

class GameStartedEvent extends Event {

}

interface EventListener{
	public void actionPerformed(Event event);
}
