package gobang;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CheckerBoard extends JFrame implements MouseListener, ActionListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	int x, y;
	int[][] points = new int[15][15];
	int[][] specialpoints = new int[15][15];
	boolean isGameOn = false;
	boolean isBlack = true;
	String gameinfo = "Welcome";
	BufferedImage background = null;
	int maxTime = 0;
	int blackTime = 0;
	int whiteTime = 0;
	String blacktime = "Unlimited";
	String whitetime = "Unlimited";
	Thread thread = new Thread(this);
	
	private JButton start = new JButton("Start");
	private JButton settings = new JButton("Settings");
	private JButton info = new JButton("Info");
	private JButton surrender = new JButton("Surrender");
	private JButton withdraw = new JButton("Withdraw");
	private JButton exit = new JButton("Exit");
	
	@SuppressWarnings("deprecation")
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
		this.setLayout(null);
		
		try {
			background = ImageIO.read(new File("resource/Image/background.png"));
			paint(this.getGraphics());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		start.addActionListener(this);
		settings.addActionListener(this);
		info.addActionListener(this);
		surrender.addActionListener(this);
		withdraw.addActionListener(this);
		exit.addActionListener(this);
		this.add(start);
		this.add(settings);
		this.add(info);
		this.add(surrender);
		this.add(withdraw);
		this.add(exit);
		start.setBounds(383, 52 ,100 ,30);
		settings.setBounds(383, 102 ,100 ,30);
		info.setBounds(383, 152 ,100 ,30);
		surrender.setBounds(383, 253 ,100 ,30);
		withdraw.setBounds(383, 302 ,100 ,30);
		exit.setBounds(383, 352 ,100 ,30);
		
		thread.start();
		thread.suspend();
		
	}

	public void paint(Graphics graphics) {
		
		BufferedImage bi = new BufferedImage(497, 502, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		
		g.setColor(Color.BLACK);
		g.drawImage(background, 3, 26, this);
		g.setFont(new Font("Consolas", Font.ITALIC, 20));
		g.drawString(gameinfo, 100, 60);
		g.setFont(new Font("Consolas", Font.BOLD, 12));
		g.drawString("Black's time : " + blacktime, 30, 475);
		g.drawString("White's time : " + whitetime, 252, 475);
		
		Graphics2D rim = (Graphics2D)g;
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
		
		graphics.drawImage(bi, 0, 0, this);
		
	}
	
	private boolean checkWin() {
		int color = points[x][y];
		int VerticallyCount;
		int HorizontalCount;
		int LeftDiagonalCount;
		int RightDiagonalCount;
		VerticallyCount = this.checkCount(1, 0, color);
		if (VerticallyCount >= 5) {
			specialpoints[x][y] = 1;
			return true;
		} else {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					specialpoints[i][j] = 0;
				}
			}
		}
		HorizontalCount = this.checkCount(0, 1, color);
		if (HorizontalCount >= 5) {
			specialpoints[x][y] = 1;
			return true;
		} else {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					specialpoints[i][j] = 0;
				}
			}
		}
		LeftDiagonalCount = this.checkCount(1, -1, color);
		if (LeftDiagonalCount >= 5) {
			specialpoints[x][y] = 1;
			return true;
		} else {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					specialpoints[i][j] = 0;
				}
			}
		}
		RightDiagonalCount = this.checkCount(1, 1, color);
		if (RightDiagonalCount >= 5) {
			specialpoints[x][y] = 1;
			return true;
		} else {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					specialpoints[i][j] = 0;
				}
			}
		}
		specialpoints[x][y] = 1;
		return false;
	}
	
	private int checkCount(int xChange, int yChange, int color) {
		int count = 1;
		int tempX = xChange;
		int tempY = yChange;
		while ((x + xChange < 15) && (y + yChange < 15) && (y + yChange >= 0) && (points[x + xChange][y + yChange] == color)) {
			count++;
			specialpoints[x + xChange][y + yChange] = 1;
			if (xChange != 0) {
				xChange++;
			}
			if (yChange > 0) {
				yChange++;
			} else if (yChange < 0) {
				yChange--;
			}
		}
		xChange = tempX;
		yChange = tempY;
		while ((x - xChange >= 0) && (y - yChange >= 0) && (y - yChange < 15) && (points[x - xChange][y - yChange] == color)) {
			count++;
			specialpoints[x - xChange][y - yChange] = 1;
			if (xChange != 0) {
				xChange++;
			}
			if (yChange > 0) {
				yChange++;
			} else if (yChange < 0) {
				yChange--;
			}
		}
		return count;
	}
	
	@SuppressWarnings("deprecation")
	private void restart() {
		blackTime = maxTime;
		whiteTime = maxTime;
		blacktime = (blackTime / 3600) + ":" + 
				(blackTime / 60 - blackTime / 3600 * 60) + ":" +
				(blackTime - blackTime / 60 * 60);
		whitetime = (whiteTime / 3600) + ":" + 
				(whiteTime / 60 - whiteTime / 3600 * 60) + ":" +
				(whiteTime - whiteTime / 60 * 60);
		gameinfo = "Game has been restarted";
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				points[i][j] = 0;
				specialpoints[i][j] = 0;
			}
		}
		isGameOn = true;
		isBlack = true;
		start.setText("Restart");
		thread.resume();
		this.repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	@SuppressWarnings("deprecation")
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
					gameinfo = "Congratulations!";
					isGameOn = false;
					thread.suspend();;
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

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == start) {
			if (isGameOn) {
				int result = JOptionPane.showConfirmDialog(this, "Restart the game ?");
				if (result == 0) {
					this.restart();
				}
			} else {
				for (int i = 0; i < 15; i++) {
					for (int j = 0; j < 15; j++) {
						points[i][j] = 0;
						specialpoints[i][j] = 0;
					}
				}
				blackTime = maxTime;
				whiteTime = maxTime;
				isGameOn = true;
				gameinfo = "Game start !";
				start.setText("Restart");
			}
		} else if (target == settings) {
			String input = JOptionPane.showInputDialog("Please enter a time limit.(Unit: minutes)"
					+ "\n\n"
					+ "0 means no time limit");
			try {
				maxTime = Integer.parseInt(input)*60;
				if (maxTime < 0) {
					JOptionPane.showMessageDialog(this, "Please enter a positive integer");
				} else {
					blackTime = maxTime;
					whiteTime = maxTime;
					int result = JOptionPane.showConfirmDialog(this, "Time limit updated, restart now ?");
					if (result == 0) {
						this.restart();
					}
				}
			} catch (NumberFormatException el) {
				JOptionPane.showMessageDialog(this, "Please enter a pure number");
			}
		} else if (target == info) {
			JOptionPane.showMessageDialog(this, "Black plays first, and players alternate in placing a stone of their color on an empty intersection."
					+ " \n"
					+ " The winner is the first player to get an unbroken row of five stones horizontally, vertically, or diagonally.");
		} else if (target == surrender) {
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to surrender?");
			if (result == 0) {
				isGameOn = false;
				start.setText("Start");
				JOptionPane.showMessageDialog(this, (isBlack ? "Black " : "White ") + "side choose death ! Game over !");
			}
		} else if (target == withdraw) {
			if (points[x][y] != 0) {
				gameinfo = (isBlack ? "White" : "Black") + "side take back a move";
				points[x][y] = 0;
				specialpoints[x][y] = 0;
				isBlack = !isBlack;
			} else {
				gameinfo = "You can only take back one move";
			}
		} else if (target == exit) {
			int result = JOptionPane.showConfirmDialog(this, "Exit now ? " + (isGameOn ? "You will lose this Game !" : ""));
			if (result == 0) {
				System.exit(0);
			}
		}
		this.repaint();
	}

	@SuppressWarnings("deprecation")
	public void run() {
		if (maxTime > 0) {
			while (true) {
				if (maxTime == 0) {
					blacktime = "Unlimited";
					whitetime = "Unlimited";
				} else {
					blacktime = (blackTime / 3600) + ":" + 
							(blackTime / 60 - blackTime / 3600 * 60) + ":" +
							(blackTime - blackTime / 60 * 60);
					whitetime = (whiteTime / 3600) + ":" + 
							(whiteTime / 60 - whiteTime / 3600 * 60) + ":" +
							(whiteTime - whiteTime / 60 * 60);
					if (isBlack) {
						blackTime--;
						if (blackTime < 0) {
							JOptionPane.showMessageDialog(this, "Black side overtime, white side wins");
							isGameOn = false;
							start.setText("Start");
							gameinfo = "Welcome";
							thread.stop();
						}
					} else {
						whiteTime--;
						if (whiteTime < 0) {
							JOptionPane.showMessageDialog(this, "White side overtime, black side wins");
							isGameOn = false;
							start.setText("Start");
							gameinfo = "Welcome";
							thread.stop();
						}
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.repaint();
			}
		}
	}
	
}
