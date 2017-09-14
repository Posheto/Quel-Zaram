import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Test2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	JMenuBar jmb;
	JMenu menu1;
	JMenuItem item1;
	JMenu smenu1, smenu2;
	JMenuItem sitem1, sitem2, sitem3, sitem4;
	JTextField tf1, tf2;
	JTextArea ta1;
	
	public Test2() {
		
		jmb = new JMenuBar();
		menu1 = new JMenu("系统功能");
		item1 = new JMenuItem("登录");
		smenu1 = new JMenu("人人对弈");
		smenu2 = new JMenu("人机对弈");
		sitem1 = new JMenuItem("人执黑");
		sitem2 = new JMenuItem("人执白");
		sitem3 = new JMenuItem("人执黑");
		sitem4 = new JMenuItem("人执白");
		tf1 = new JTextField("未登录");
		tf2 = new JTextField("游戏未开始");
		ta1 = new JTextArea();
		
		jmb.add(menu1);
		menu1.add(item1);
		menu1.add(smenu1);
		menu1.add(smenu2);
		smenu1.add(sitem1);
		smenu1.add(sitem2);
		smenu2.add(sitem3);
		smenu2.add(sitem4);
		
		tf1.setEditable(false);
		tf1.setFont(new Font("宋体", Font.BOLD, 12));
		tf1.setHorizontalAlignment(SwingConstants.CENTER);
		tf2.setEditable(false);
		tf2.setFont(new Font("宋体", Font.BOLD, 12));
		tf2.setHorizontalAlignment(SwingConstants.CENTER);
		ta1.setEditable(false);
		ta1.setBackground(new Color(220, 191, 157));;
		
		this.setJMenuBar(jmb);
		this.add(tf1, BorderLayout.NORTH);
		this.add(ta1, BorderLayout.CENTER);
		this.add(tf2, BorderLayout.SOUTH);
		
		this.setTitle("五子棋大战 V1.0");
		this.setSize(500, 500);
		this.setLocation((screen_width - 500)/2, (screen_height - 500)/2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	public void paint(Graphics graphics) {
		
		super.paint(graphics);
		
		BufferedImage bi = new BufferedImage(497, 502, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		
		Graphics2D rim = (Graphics2D)g;
		rim.setColor(Color.BLACK);
		rim.setStroke(new BasicStroke(3.0f));
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
		
		graphics.drawImage(bi, 0, 0, this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
