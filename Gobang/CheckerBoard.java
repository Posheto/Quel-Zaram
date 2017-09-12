package Gobang;

import java.awt.Font;
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

	//BackGroundPicture_width = 495
	//BackGroundPicture_height = 475
	
	//Upper left coordinates : 10, 78
	//Upper right coordinates : 369, 78
	//Lower left coordinates : 10, 437
	//Lower right coordinates : 369, 437
	
	int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	BufferedImage background = null;
	
	public CheckerBoard() {
		
		this.setTitle("Gobang Online");
		this.setSize(497, 502);
		this.setLocation((screen_width - 497)/2, (screen_height - 502)/2);
		this.addMouseListener(this);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try {
			background = ImageIO.read(new File("E:/My Program/Java/My Java Code/src/Image/background.jpg"));
			paint(this.getGraphics());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void paint(Graphics g) {
		
		g.drawImage(background, 1, 27, this);
		g.setFont(new Font("Consolas", Font.BOLD, 25));
		g.drawString("Game Info", 150, 60);
		g.setFont(new Font("Consolas", Font.BOLD, 12));
		g.drawString("Black's time : Unlimited", 30, 475);
		g.drawString("White's time : Unlimited", 252, 475);
		
		g.drawLine(11, 77, 370, 77);
		g.drawLine(11, 77, 11, 436);
		g.drawLine(11, 436, 370, 436);
		g.drawLine(370, 77, 370, 436);
		
		for (int i = 1; i < 19; i++) {
			g.drawLine(11, 77 + i*19, 370, 77 + i*19);
		}
		
		for (int i = 1; i < 19; i++) {
			g.drawLine(11 + i*19, 77, 11 + i*19, 436);
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
}
