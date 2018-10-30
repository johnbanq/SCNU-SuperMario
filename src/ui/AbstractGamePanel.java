package ui;

import java.util.LinkedList;

import javax.swing.JPanel;

import main.GameClient;
import sounds.SoundManager;
import ui.events.Event;
import ui.events.EventListener;

@SuppressWarnings("serial")
public abstract class AbstractGamePanel extends JPanel {
	// sounds
	private SoundManager sound_mgr;
	// event support
	private LinkedList<EventListener> listeners = new LinkedList<>();

	public AbstractGamePanel(SoundManager sound_mgr) {
		this.sound_mgr = sound_mgr;
	}

	// sounds
	public SoundManager getSoundManager() {
		return sound_mgr;
	}

	// event support
	public void addEventListener(EventListener listener) {
		listeners.addLast(listener);
	}

	public void removeEventListener(EventListener listener) {
		listeners.remove(listener);
	}

	protected void tellEvent(Event e) {
		for (EventListener l : listeners) {
			l.actionPerformed(e);
		}
	}

	// when install & uninstalled
	public abstract void removeFromGameClient(GameClient gc);

	public abstract void addToGameClient(GameClient gc);

}
