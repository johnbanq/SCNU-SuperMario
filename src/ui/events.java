package ui;

class Event{
	
}

class GameStartEvent extends Event {

}

class GameRestartEvent extends Event {

}

interface EventListener{
	public void actionPerformed(Event event);
}
