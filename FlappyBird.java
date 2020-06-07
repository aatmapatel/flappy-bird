// Author: Aatma Patel

// Friday June 6 2020

// MississaugaHacks2020

// A Social Distancing Game

package socialDistance;

import java.awt.Color;

import java.awt.Font;

import java.awt.Graphics;

import java.awt.Rectangle;

import java.util.ArrayList;

import java.util.Random;

import javax.swing.Timer;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

public class FlappyBird implements ActionListener, KeyListener, MouseListener {

	static FlappyBird flappyBird;

	int clicks, moveY, points;

	final int Width = 1300, Length = 800;

	boolean endGame, startGame;

	Random random;

	GameGraphics gameGraphics;

	Rectangle birdy;

	ArrayList<Rectangle> pillars;

	public FlappyBird() {
		JFrame jframe = new JFrame();

		Timer clock = new Timer(20, this);

		random = new Random();

		gameGraphics = new GameGraphics();

		birdy = new Rectangle(Width / 2 - 10, Length / 2 - 10, 20, 20);

		pillars = new ArrayList<Rectangle>();

		jframe.add(gameGraphics);

		jframe.addKeyListener(this);

		jframe.addMouseListener(this);

		jframe.setResizable(false);

		jframe.setVisible(true);

		jframe.setTitle("Quarantine Gaming: Flappy Bird");

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jframe.setSize(Width, Length);

		placePillar(true);

		placePillar(true);

		placePillar(true);

		placePillar(true);

		clock.start();
	}

	public void placePillar(boolean start)

	{

		int blank = 273;

		int width = 72;

		int length = 46 + random.nextInt(200);

		if (start)

		{
			pillars.add(new Rectangle(Width + width + pillars.size() * 300, Length - length - 120, width, length));

			pillars.add(new Rectangle(Width + width + (pillars.size() - 1) * 300, 0, width, Length - length - blank));
		}

		else

		{
			pillars.add(new Rectangle(pillars.get(pillars.size() - 1).x + 541, Length - length - 120, width, length));

			pillars.add(new Rectangle(pillars.get(pillars.size() - 1).x, 0, width, Length - length - blank));

		}

	}

	public void paintPillar(Graphics msm, Rectangle pillar)

	{

		Color newGreen = new Color(91, 197, 64);

		msm.setColor(newGreen.darker());

		msm.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);

		if (points >= 10) {

			Color newViolet = new Color(133, 89, 198);

			msm.setColor(newViolet.darker());

			msm.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);

		}
		if (points >= 2) {

			Color newPink = new Color(227, 80, 164);

			msm.setColor(newPink.darker());

			msm.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);

		}
		if (points >= 4) {

			Color newSky = new Color(7, 156, 212);

			msm.setColor(newSky.darker());

			msm.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);

		}
		if (points >= 6) {

			Color newYellow = new Color(222, 227, 80);

			msm.setColor(newYellow.darker());

			msm.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);

		}
		if (points >= 8) {

			Color newMint = new Color(10, 207, 174);

			msm.setColor(newMint.darker());

			msm.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);

		}
		if (points >= 10) {

			Color newOrange = new Color(207, 100, 10);

			msm.setColor(newOrange.darker());

			msm.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);

		}
		if (points >= 10) {

			Color newGrass = new Color(91, 197, 64);

			msm.setColor(newGrass.darker());

			msm.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);

		}
	}

	public void leap()

	{

		if (endGame)

		{
			birdy = new Rectangle(Width / 2 - 10, Length / 2 - 10, 20, 20);

			pillars.clear();

			moveY = 0;

			points = 0;

			placePillar(true);

			placePillar(true);

			placePillar(true);

			placePillar(true);

			endGame = false;

		}

		if (!startGame)

		{

			startGame = true;

		}

		else if (!endGame)

		{

			if (moveY > 0)

			{

				moveY = 0;

			}

			moveY -= 7;

		}

	}

	@Override

	public void actionPerformed(ActionEvent e)

	{
		int velocity = 8;

		clicks++;

		if (startGame)

		{

			for (int kjd = 0; kjd < pillars.size(); kjd++)

			{
				Rectangle pillar = pillars.get(kjd);

				pillar.x -= velocity;
			}

			if (clicks % 3 == 0 && moveY < 12)

			{
				moveY += 2;
			}

			for (int kjd = 0; kjd < pillars.size(); kjd++)

			{

				Rectangle pillar = pillars.get(kjd);

				if (pillar.x + pillar.width < 0)

				{

					pillars.remove(pillar);

					if (pillar.y == 0)

					{

						placePillar(false);

					}

				}

			}

			birdy.y += moveY;

			for (Rectangle pillar : pillars)

			{
				if (pillar.y == 0 && birdy.x + birdy.width > pillar.x + pillar.width / 2
						&& birdy.x + birdy.width / 2 < pillar.x + pillar.width / 2) {
					points++;

				} else if (points >= 10) {

					velocity = 9;

					pillar.x -= velocity;

				} else if (points >= 50) {

					velocity = 11;

					pillar.x -= velocity;
				}

				if (pillar.intersects(birdy)) {
					endGame = true;

					if (birdy.x <= pillar.x) {
						birdy.x = pillar.x - birdy.width;

					} else {
						if (pillar.y != 0) {
							birdy.y = pillar.y - birdy.height;
						} else if (birdy.y < pillar.height) {
							birdy.y = pillar.height;
						}
					}
				}
			}

			if (birdy.y > Length - 120 || birdy.y < 0)

			{

				endGame = true;

			}

			if (birdy.y + moveY >= Length - 120)

			{
				birdy.y = Length - 120 - birdy.height;

				endGame = true;

			}

		}

		gameGraphics.repaint();

	}

	public void repaint(Graphics psm)

	{
		Color newBlue = new Color(98, 197, 204);

		psm.setColor(newBlue);

		psm.fillRect(0, 0, Width, Length);

		Color newBeige = new Color(217, 211, 131);

		psm.setColor(newBeige);

		psm.fillRect(0, Length - 120, Width, 120);

		Color newGreen = new Color(91, 197, 64);

		psm.setColor(newGreen);

		psm.fillRect(0, Length - 120, Width, 20);

		Color newRed = new Color(170, 38, 6);

		psm.setColor(newRed);

		psm.fillRect(birdy.x, birdy.y, birdy.width, birdy.height);

		for (Rectangle pillar : pillars)

		{

			paintPillar(psm, pillar);

		}

		psm.setColor(Color.white);

		psm.setFont(new Font("Arial", 80, 100));

		if (!startGame)

		{

			psm.drawString("Click to start!", 75, Length / 2 - 50);

		}

		if (endGame)

		{

			psm.drawString("Game Over!", 100, Length / 2 - 50);

		}

		if (!endGame && startGame)

		{

			psm.drawString(String.valueOf(points), Width / 2 - 25, 100);

		}

	}

	public static void main(String[] args)

	{

		flappyBird = new FlappyBird();

	}

	@Override
	public void keyReleased(KeyEvent nsd) {
		if (nsd.getKeyCode() == KeyEvent.VK_SPACE) {
			leap();
		}
	}

	@Override
	public void keyTyped(KeyEvent nsd) {

	}

	@Override
	public void keyPressed(KeyEvent nsd) {

	}

	@Override
	public void mouseClicked(MouseEvent nsd)

	{

		leap();

	}

	@Override
	public void mousePressed(MouseEvent nsd)

	{

	}

	@Override
	public void mouseReleased(MouseEvent nsd)

	{

	}

	@Override
	public void mouseEntered(MouseEvent nsd) {
	}

	@Override
	public void mouseExited(MouseEvent nsd) {
	}

}
