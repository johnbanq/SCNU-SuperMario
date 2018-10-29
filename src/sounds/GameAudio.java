package sounds;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.*; 


public class GameAudio 
{
	protected String name;
	protected InputStream in ;
	protected AudioStream as ;
	protected ContinuousAudioDataStream cas ;
	
	public GameAudio(String name)
	{
		this.name=name;
		try {
			switch(name)
			
			{
			
			case "���" : in = new FileInputStream(new File("src\\Aduio\\���.wav"));break;
			case "������" : in = new FileInputStream(new File("src\\Aduio\\������.wav"));break;
			case "ˮ��" : in = new FileInputStream(new File("src\\Aduio\\ˮ��.wav"));break;
			case "ʧ��" : in = new FileInputStream(new File("src\\Aduio\\ʧ��.wav"));break;
			case "���" : in = new FileInputStream(new File("src\\Aduio\\��̤.wav"));break;
			case "ײ����" : in = new FileInputStream(new File("src\\Aduio\\ײ����.wav"));break;
			case "��ף" : in = new FileInputStream(new File("src\\Aduio\\��ף.wav"));break;
			case "������" : in = new FileInputStream(new File("src\\Aduio\\���1.wav"));break;
			}
			as = new AudioStream(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start()
	{
		AudioPlayer.player.start(as);
	}
	
	public void stop()
	{
		AudioPlayer.player.stop(as);
	}
	
	public void continuousPlay()
	{
		try {
			cas = new ContinuousAudioDataStream(as.getData());
			AudioPlayer.player.start(cas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void continuousStop()
	{
		AudioPlayer.player.stop(cas);
	}
}