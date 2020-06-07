// Author: Aatma Patel

// Friday June 5, 2020

// MississaugaHacks2020

// A Social Distancing Game

package socialDistance;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GameGraphics extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics sSB) {
		super.paintComponent(sSB);

		FlappyBird.flappyBird.repaint(sSB);

	}

}
