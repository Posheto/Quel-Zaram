package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JTextField tf1, tf2;
	String gameinfo1, gameinfo2, blacktime, whitetime;
	int[][] points = new int[15][15];
	int[][] specialpoints = new int[15][15];
	
	public Board(JTextField tf1, JTextField tf2, String gameinfo1, String gameinfo2, String blacktime, String whitetime, int[][] points, int[][] specialpoints) {
		this.tf1 = tf1;
		this.tf2 = tf2;
		this.gameinfo1 = gameinfo1;
		this.gameinfo2 = gameinfo2;
		this.blacktime = blacktime;
		this.whitetime = whitetime;
		this.points = points;
		this.specialpoints = specialpoints;
	}
	
	public void changeInfo(String gameinfo1, String gameinfo2, String blacktime, String whitetime) {
		this.gameinfo1 = gameinfo1;
		this.gameinfo2 = gameinfo2;
		this.blacktime = blacktime;
		this.whitetime = whitetime;
	}

	public void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		tf1.setText(gameinfo1);
		tf2.setText(gameinfo2);
		
		BufferedImage bi = new BufferedImage(500, 430, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Consolas", Font.BOLD, 12));
		g.drawString("Black's time : " + blacktime, 55, 400);
		g.drawString("White's time : " + whitetime, 277, 400);
		
		Graphics2D rim = (Graphics2D)g;
		rim.setColor(Color.BLACK);
		rim.setStroke(new BasicStroke(3.0f));
		rim.drawLine(82, 31, 417, 31);
		rim.drawLine(82, 31, 82, 366);
		rim.drawLine(82, 366, 417, 366);
		rim.drawLine(417, 31, 417, 366);
		rim.setStroke(new BasicStroke(1.0f));
		
		for (int i = 1; i < 14; i++) {
			g.drawLine(82, 31 + i*24, 417, 31 + i*24);
		}
		for (int i = 1; i < 14; i++) {
			g.drawLine(82 + i*24, 31, 82 + i*24, 366);
		}
		
		for (int i = 1; i <= 15; i++) {
			g.drawString(String.valueOf(i), 60, 13 + i*24);
			g.drawString(String.valueOf(i), 52 + i*24, 25);
		}
		
		g.fillOval(150, 100, 8, 8);
		g.fillOval(342, 100, 8, 8);
		g.fillOval(150, 292, 8, 8);
		g.fillOval(342, 292, 8, 8);
		g.fillOval(246, 196, 8, 8);
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (specialpoints[i][j] == 1) {
					g.setColor(Color.RED);
					g.fillOval(70 + j*24, 20 + i*24, 22, 22);
				}
			}
		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (points[i][j] == 1) {
					g.setColor(Color.BLACK);
					g.fillOval(71 + j*24, 21 + i*24, 20, 20);
				} else if (points[i][j] == 2) {
					g.setColor(Color.WHITE);
					g.fillOval(71 + j*24, 21 + i*24, 20, 20);
					if (specialpoints[i][j] == 0) {
						g.setColor(Color.BLACK);
						g.drawOval(71 + j*24, 21 + i*24, 20, 20);
					}
				}
			}
		}
		
		graphics.drawImage(bi, 0, 0, this);
		
	}
	
}
