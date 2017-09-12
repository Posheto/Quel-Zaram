package Gobang;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class CheckerBoard extends JFrame implements MouseListener {

	int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	BufferedImage background = null;
	
	public CheckerBoard() {
		
		this.setTitle("Gobang Online");
		this.setSize(500, 500);
		this.setLocation((screen_width - 500)/2, (screen_height - 500)/2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try {
			background = ImageIO.read(new File("E:/My Program/Java/My Java Code/src/Image/background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
