package ui.components;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class AlphaLabel extends JLabel{

    private AlphaComposite cmp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1);
    private int alpha = 10;

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
    
    public int getAlpha() {
    	return alpha;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(cmp.derive(alpha==0?0:(0.1f*alpha)));
        super.paintComponent(g2d);
    }
}
