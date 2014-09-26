package aiprog.gui;

import java.awt.Graphics;


import javax.swing.JPanel;

public abstract class Canvas extends JPanel{
	protected int scale = 30;
        public Canvas() {
        }
        public void setScale(int scale){
        	this.scale = scale;
        }
        @Override
        protected synchronized void paintComponent(Graphics g) {
            super.paintComponent(g);
            renderGraphics();
        }
        protected abstract void renderGraphics();
        

}
