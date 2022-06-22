package Core;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	// janela do game

	public Window(int with, int height, String title, Game game) {
		
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(with,height));
		frame.setMaximumSize(new Dimension(with,height));
		frame.setMinimumSize(new Dimension(with,height));
		
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
