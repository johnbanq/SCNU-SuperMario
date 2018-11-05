package ui.components;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class BlinkingLabel extends JLabel{

    private AlphaComposite cmp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1);
    private int alpha = 10;
    private boolean should_tick = true;
    private boolean should_add_alpha=false;

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
    
    public int getAlpha() {
    	return alpha;
    }
    
    public void setShouldTick(boolean v) {
    	this.should_tick = v;
    }
    
    public boolean getShouldTick() {
    	return should_tick;
    }

    @Override
    protected void paintComponent(Graphics g) {
    	//to let the shining effect moves to the next frame
    	if(should_tick) {
        	if(alpha==10) {
    			should_add_alpha = false;
    		}else if(alpha == 0) {
    			should_add_alpha = true;
    		}
    		
    		if(should_add_alpha) {
    			alpha++;
    		}else {
    			alpha--;
    		}
    	}
    	
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(cmp.derive(alpha==0?0:(0.1f*alpha)));
        super.paintComponent(g2d);
    }
}
