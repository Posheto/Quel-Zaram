package gobang;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CheckerBoard extends JFrame implements ActionListener, MouseListener, Runnable {

	private static final long serialVersionUID = 1L;
	
	int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	String gameinfo1 = "Not logged in";
	String gameinfo2;
	int x, y;
	int[][] points = new int[15][15];
	int[][] specialpoints = new int[15][15];
	boolean isGameOn = false;
	boolean isBlack = true;
	int maxTime = 0;
	int blackTime = 0;
	int whiteTime = 0;
	String blacktime = "Unlimited";
	String whitetime = "Unlimited";
	Thread thread = new Thread(this);
	
	JMenuBar jmb;
	JMenu fuc, set, help, mode, manman, manmac;
	JMenuItem login, sta, wit, sur, logout, exit, manblack, manwhite, macblack, macwhite, time, info;
	JTextField tf1, tf2;
	JTextArea ta1;
	
	@SuppressWarnings("deprecation")
	public CheckerBoard() {
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				points[i][j] = 0;
				specialpoints[i][j] = 0;
			}
		}
		
		jmb = new JMenuBar();
		
		fuc = new JMenu("<html><u>F</u>uction</html>");
		fuc.setMnemonic('f');
		set = new JMenu("<html><u>S</u>ettings</html>");
		set.setMnemonic('s');
		help = new JMenu("<html><u>H</u>elp</html>");
		help.setMnemonic('h');
		mode = new JMenu("Mode");
		manman = new JMenu("Man-man");
		manmac = new JMenu("Man-machine");
		login = new JMenuItem("Log in");
		login.addActionListener(this);
		sta = new JMenuItem("Start");
		sta.addActionListener(this);
		wit = new JMenuItem("Withdraw");
		wit.addActionListener(this);
		sur = new JMenuItem("Surrender");
		sur.addActionListener(this);
		logout = new JMenuItem("Log out");
		logout.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		manblack = new JMenuItem("Choose black");
		manblack.addActionListener(this);
		manwhite = new JMenuItem("Choose white");
		manwhite.addActionListener(this);
		macblack = new JMenuItem("Choose black");
		macblack.addActionListener(this);
		macwhite = new JMenuItem("Choose white");
		macwhite.addActionListener(this);
		time = new JMenuItem("Time limit");
		time.addActionListener(this);
		info = new JMenuItem("Info");
		info.addActionListener(this);
		tf1 = new JTextField(gameinfo1);
		tf2 = new JTextField(gameinfo2);
		ta1 = new JTextArea();
		
		jmb.add(fuc);
		jmb.add(set);
		jmb.add(help);
		fuc.add(login);
		fuc.add(sta);
		fuc.add(wit);
		fuc.add(wit);
		fuc.add(sur);
		fuc.add(logout);
		fuc.add(exit);
		set.add(mode);
		set.add(time);
		mode.add(manman);
		mode.add(manmac);
		manman.add(manblack);
		manman.add(manwhite);
		manmac.add(macblack);
		manmac.add(macwhite);
		help.add(info);
		
		tf1.setEditable(false);
		tf1.setFont(new Font("宋体", Font.BOLD, 12));
		tf1.setHorizontalAlignment(SwingConstants.CENTER);
		tf2.setEditable(false);
		tf2.setFont(new Font("宋体", Font.BOLD, 12));
		tf2.setHorizontalAlignment(SwingConstants.CENTER);
		ta1.setEditable(false);
		ta1.setBackground(new Color(220, 191, 157));;
		ta1.addMouseListener(this);
		
		this.setJMenuBar(jmb);
		this.add(tf1, "North");
		this.add(ta1, "Center");
		this.add(tf2, "South");
		
		this.setTitle("五子棋大战 V1.0");
		this.setSize(500, 500);
		this.setLocation((screen_width - 500)/2, (screen_height - 500)/2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		thread.start();
		thread.suspend();
		
	}

	public void paint(Graphics graphics) {
		
		super.paint(graphics);
		
		tf1.setText(gameinfo1);
		tf2.setText(gameinfo2);
		
		BufferedImage bi = new BufferedImage(497, 502, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Consolas", Font.BOLD, 12));
		g.drawString("Black's time : " + blacktime, 45, 455);
		g.drawString("White's time : " + whitetime, 267, 455);
		
		Graphics2D rim = (Graphics2D)g;
		rim.setColor(Color.BLACK);
		rim.setStroke(new BasicStroke(1.0f));
		rim.drawLine(82, 101, 417, 101);
		rim.drawLine(82, 101, 82, 436);
		rim.drawLine(82, 436, 417, 436);
		rim.drawLine(417, 101, 417, 436);
		rim.setStroke(new BasicStroke(1.0f));
		
		for (int i = 1; i < 14; i++) {
			g.drawLine(82, 101 + i*24, 417, 101 + i*24);
		}
		for (int i = 1; i < 14; i++) {
			g.drawLine(82 + i*24, 101, 82 + i*24, 436);
		}
		
		for (int i = 1; i <= 15; i++) {
			g.drawString(String.valueOf(i), 60, 83 + i*24);
			g.drawString(String.valueOf(i), 52 + i*24, 95);
		}
		
		g.fillOval(150, 170, 8, 8);
		g.fillOval(342, 170, 8, 8);
		g.fillOval(150, 362, 8, 8);
		g.fillOval(342, 362, 8, 8);
		g.fillOval(246, 266, 8, 8);
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (specialpoints[i][j] == 1) {
					g.setColor(Color.RED);
					g.fillOval(70 + j*24, 90 + i*24, 22, 22);
				}
			}
		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (points[i][j] == 1) {
					g.setColor(Color.BLACK);
					g.fillOval(71 + j*24, 91 + i*24, 20, 20);
				} else if (points[i][j] == 2) {
					g.setColor(Color.WHITE);
					g.fillOval(71 + j*24, 91 + i*24, 20, 20);
					if (specialpoints[i][j] == 0) {
						g.setColor(Color.BLACK);
						g.drawOval(71 + j*24, 91 + i*24, 20, 20);
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
		gameinfo2 = "Game has been restarted";
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				points[i][j] = 0;
				specialpoints[i][j] = 0;
			}
		}
		isGameOn = false;
		isBlack = true;
		sta.setText("Start");
		thread.suspend();
		this.repaint();
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == login) {
			
		} else if (target == sta) {
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
				gameinfo2 = "Game start !";
				sta.setText("Restart");
				thread.resume();
			}
		} else if (target == wit) {
			if (points[x][y] != 0) {
				gameinfo2 = (isBlack ? "White" : "Black") + "side take back a move";
				points[x][y] = 0;
				specialpoints[x][y] = 0;
				isBlack = !isBlack;
			} else {
				gameinfo2 = "You can only take back one move";
			}
		} else if (target == sur) {
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to surrender?");
			if (result == 0) {
				isGameOn = false;
				sta.setText("Start");
				JOptionPane.showMessageDialog(this, (isBlack ? "Black " : "White ") + "side choose death ! Game over !");
			}
		} else if (target == logout) {
			
		} else if (target == exit) {
			int result = JOptionPane.showConfirmDialog(this, "Exit now ? " + (isGameOn ? "You will lose this Game !" : ""));
			if (result == 0) {
				System.exit(0);
			}
		} else if (target == manblack) {
			
		} else if (target == manwhite) {
			
		} else if (target == macblack) {
			
		} else if (target == macwhite) {
			
		} else if (target == time) {
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
			JOptionPane.showMessageDialog(this, "Made by Posheto");
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
							sta.setText("Start");
							gameinfo2 = "Welcome";
							thread.stop();
						}
					} else {
						whiteTime--;
						if (whiteTime < 0) {
							JOptionPane.showMessageDialog(this, "White side overtime, black side wins");
							isGameOn = false;
							sta.setText("Start");
							gameinfo2 = "Welcome";
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

	public void mouseClicked(MouseEvent e) {
		
	}

	@SuppressWarnings("deprecation")
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				specialpoints[i][j] = 0;
			}
		}
		x = e.getY();
		y = e.getX();
		if (y >= 68 && y <= 428 && x >= 20 && x <=380 && isGameOn) {
			y = (y - 68) / 24;
			x = (x - 20) / 24;
			if (points[x][y] == 0) {
				if (isBlack) {
					gameinfo2 = "Black moves in (" + (x + 1) + ", " + (y + 1) + ")";
					points[x][y] = 1;
					isBlack = false;
				} else {
					gameinfo2 = "White moves in (" + (x + 1) + ", " + (y + 1) + ")";
					points[x][y] = 2;
					isBlack = true;
				}
				if (this.checkWin()) {
					JOptionPane.showMessageDialog(this, "The match is end! " + (isBlack ? "White side win!" : "Black side win!"));
					gameinfo2 = "Congratulations!";
					isGameOn = false;
					thread.suspend();;
				}
			} else {
				gameinfo2 = "Location has been occupied";
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
