package aiprog.gui;

import java.awt.Graphics;


import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Canvas extends JPanel{
	protected int scale = 30;
	protected int spacing = 0;
        public Canvas() {
        }
        public void setScale(int scale){
        	this.scale = scale;
        }
        public void setSpacing(int spacing){
        	this.spacing = spacing;
        }
        @Override
        protected synchronized void paintComponent(Graphics g) {
            super.paintComponent(g);
            renderGraphics(g);
        }
        protected abstract void renderGraphics(Graphics g);
        

}
