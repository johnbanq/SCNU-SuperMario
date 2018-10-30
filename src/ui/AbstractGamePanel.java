package ui;

import java.util.LinkedList;

import javax.swing.JPanel;

import main.GameClient;
import ui.events.Event;

@SuppressWarnings("serial")
public abstract class AbstractGamePanel extends JPanel {
	//event support
	private LinkedList<EventListener> listeners = new LinkedList<>();
	public void addEventListener(EventListener listener) {
		listeners.addLast(listener);
	}
	public void removeEventListener(EventListener listener) {
		listeners.remove(listener);
	}
	protected void tellEvent(Event e) {
		for(EventListener l:listeners) {
			l.actionPerformed(e);
		}
	}
	
	public abstract void removeFromGameClient(GameClient gc);
	public abstract void addToGameClient(GameClient gc);
}
