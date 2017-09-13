package Gobang;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CheckerBoard extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	int x, y;
	int[][] points = new int[15][15];
	int[][] specialpoints = new int[15][15];
	boolean isGameOn = true;
	boolean isBlack = true;
	String gameinfo = "Let's start the game !";
	
	BufferedImage background = null;
	
	public CheckerBoard() {
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				points[i][j] = 0;
				specialpoints[i][j] = 0;
			}
		}
		
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
		
		Graphics2D rim = (Graphics2D)g;
		
		g.setColor(Color.BLACK);
		g.drawImage(background, 1, 27, this);
		g.setFont(new Font("Consolas", Font.ITALIC, 15));
		g.drawString(gameinfo, 150, 60);
		g.setFont(new Font("Consolas", Font.BOLD, 12));
		g.drawString("Black's time : Unlimited", 30, 475);
		g.drawString("White's time : Unlimited", 252, 475);
		
		rim.setColor(Color.BLACK);
		rim.setStroke(new BasicStroke(3.0f));
		rim.drawLine(23, 89, 358, 89);
		rim.drawLine(23, 89, 23, 424);
		rim.drawLine(23, 424, 358, 424);
		rim.drawLine(358, 89, 358, 424);
		rim.setStroke(new BasicStroke(1.0f));
		
		for (int i = 1; i < 15; i++) {
			g.drawLine(23, 89 + i*24, 358, 89 + i*24);
		}
		for (int i = 1; i < 15; i++) {
			g.drawLine(23 + i*24, 89, 23 + i*24, 424);
		}
		
		g.fillOval(91, 158, 8, 8);
		g.fillOval(283, 158, 8, 8);
		g.fillOval(91, 350, 8, 8);
		g.fillOval(283, 350, 8, 8);
		g.fillOval(187, 254, 8, 8);
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (specialpoints[i][j] == 1) {
					g.setColor(Color.RED);
					g.fillOval(12 + j*24, 78 + i*24, 22, 22);
				}
			}
		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (points[i][j] == 1) {
					g.setColor(Color.BLACK);
					g.fillOval(13 + j*24, 79 + i*24, 20, 20);
				} else if (points[i][j] == 2) {
					g.setColor(Color.WHITE);
					g.fillOval(13 + j*24, 79 + i*24, 20, 20);
					if (specialpoints[i][j] == 0) {
						g.setColor(Color.BLACK);
						g.drawOval(13 + j*24, 79 + i*24, 20, 20);
					}
				}
			}
		}
		
	}
	
	private boolean checkWin() {
		int color = points[x][y];
		int VerticallyCount = -1;
		int HorizontalCount = -1;
		int LeftDiagonalCount = -1;
		int RightDiagonalCount = -1;
		int i;
		i = 0;
		while ((x + i < 15) && (points[x + i][y] == color)) {
			VerticallyCount++;
			specialpoints[x + i][y] = 1;
			i++;
		}
		i = 0;
		while ((x - i >= 0) && (points[x - i][y] == color)) {
			VerticallyCount++;
			specialpoints[x - i][y] = 1;
			i++;
		}
		if (VerticallyCount >= 5) {
			return true;
		} else {
			for (i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					specialpoints[i][j] = 0;
				}
			}
		}
		i = 0;
		while ((y + i < 15) && (points[x][y + i] == color)) {
			HorizontalCount++;
			specialpoints[x][y + i] = 1;
			i++;
		}
		i = 0;
		while ((y - i >= 0) && (points[x][y - i] == color)) {
			HorizontalCount++;
			specialpoints[x][y - i] = 1;
			i++;
		}
		if (HorizontalCount >= 5) {
			return true;
		} else {
			for (i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					specialpoints[i][j] = 0;
				}
			}
		}
		i = 0;
		while ((y + i < 15) && (x - i >= 0) && (points[x - i][y + i] == color)) {
			LeftDiagonalCount++;
			specialpoints[x - i][y + i] = 1;
			i++;
		}
		i = 0;
		while ((y - i >= 0) && (x + i < 15) && (points[x + i][y - i] == color)) {
			LeftDiagonalCount++;
			specialpoints[x + i][y - i] = 1;
			i++;
		}
		if (LeftDiagonalCount >= 5) {
			return true;
		} else {
			for (i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					specialpoints[i][j] = 0;
				}
			}
		}
		i = 0;
		while ((y + i < 15) && (x + i < 15) && (points[x + i][y + i] == color)) {
			RightDiagonalCount++;
			specialpoints[x + i][y + i] = 1;
			i++;
		}
		i = 0;
		while ((y - i >= 0) && (x - i >= 0) && (points[x - i][y - i] == color)) {
			RightDiagonalCount++;
			specialpoints[x - i][y - i] = 1;
			i++;
		}
		if (RightDiagonalCount >= 5) {
			return true;
		} else {
			for (i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					specialpoints[i][j] = 0;
				}
			}
		}
		specialpoints[x][y] = 1;
		return false;
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		x = e.getY();
		y = e.getX();
		if (y >= 11 && y <= 370 && x >= 77 && x <=436 && isGameOn) {
			y = (y - 11) / 24;
			x = (x - 77) / 24;
			if (points[x][y] == 0) {
				if (isBlack) {
					gameinfo = "Black moves in (" + (x + 1) + ", " + (y + 1) + ")";
					points[x][y] = 1;
					isBlack = false;
				} else {
					gameinfo = "White moves in (" + (x + 1) + ", " + (y + 1) + ")";
					points[x][y] = 2;
					isBlack = true;
				}
				if (this.checkWin()) {
					JOptionPane.showMessageDialog(this, "The match is end! " + (isBlack ? "White side win!" : "Black side win!"));
					gameinfo = isBlack ? "Congratulations on white's win" : "Congratulations on black's win";
					isGameOn = false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Location has been occupied");
			}
			this.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
}
