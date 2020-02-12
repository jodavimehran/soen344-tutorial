package ca.concordia.soen344.template.junit.swingui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

class ProgressBar extends JPanel {
	public boolean fError= false;
	public int fTotal= 0;
	public int fProgress= 0;
	public int fProgressX= 0;
	public ProgressBar() {
		super();
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}
	private Color getStatusColor() {
		if (fError)
			return Color.red;
		return Color.green;
	}
	public void paintBackground(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0,0,getWidth(),getHeight());
	}
	public void paintComponent(Graphics g) {
		paintBackground(g);
		paintStatus(g);
	}
	public void paintStatus(Graphics g) {
		g.setColor(getStatusColor());
		Rectangle r= new Rectangle(0, 0, fProgressX, getBounds().height);
		g.fillRect(1, 1, r.width-1, r.height-2);
	}
	private void paintStep(int startX, int endX) {
		repaint(startX, 1, endX-startX, getBounds().height-2);
	}
	public void reset() {
		fProgressX= 1;
		fProgress= 0;
		fError= false;
		repaint();
	}
	public int scale(int value) {
		if (fTotal > 0)
			return Math.max(1, value*(getBounds().width-1)/fTotal);
		return value; 
	}
	public void setBounds(int x, int y, int w, int h) {
		super.setBounds(x, y, w, h);
		//setPreferredSize(new Dimension(20, 30));
		fProgressX= scale(fProgress);
	}
	public void start(int total) {
		fTotal= total;
		reset();
	}
	public void step(boolean successful) {
		fProgress++;
		int x= fProgressX;

		fProgressX= scale(fProgress);

		if (!fError && !successful) {
			fError= true;
			x= 1;
		}
		paintStep(x, fProgressX);
	}
}