import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

public class Core extends Application
{
	Grid theGrid = new Grid();
	long millisTimeP1 = System.currentTimeMillis() - 751;
	long millisTimeP2 = System.currentTimeMillis() - 751;
	long millisShootP1 = System.currentTimeMillis();
	long millisShootP2 = System.currentTimeMillis();
	boolean gameOver = false;

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage)
	{

		stage.setTitle("High Noon Showdown");

		Pane root = new Pane();
		Scene scene = new Scene(root);
		stage.setScene(scene);

		Canvas canvas = new Canvas(1280, 720);
		root.getChildren().add(canvas);

		GraphicsContext graphc = canvas.getGraphicsContext2D();

		Image background = new Image("file:gameyCow45.png");
		Player1 p1 = new Player1(root, "file:cowboy.png", 90, 90, 400, 500);
		Player2 p2 = new Player2(root, "file:cowboy2.png", 90, 90, 850, 500);
		Midline midline = new Midline(root, "file:midline.png", 100, 230, 625, 450);
		theGrid.addPlayers(p1, p2);

		graphc.drawImage(background, 0, 0);

		ArrayList<String> input = new ArrayList<String>();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent event)
			{
				String code = event.getCode().toString();
				if (!input.contains(code))
					input.add(code);
			}
		});

		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				int vert = (int) (theGrid.getPlayerOnePos().getHeight());
				int hori = (int) (theGrid.getPlayerOnePos().getWidth());
				int vert2 = (int) (theGrid.getPlayerTwoPos().getHeight());
				int hori2 = (int) (theGrid.getPlayerTwoPos().getWidth());

				if (theGrid.movementTimeOut(millisTimeP1))
				{
					if (input.contains("A") && hori > 0)
					{

						System.out.println("P1 moving left");
						theGrid.movePlayerOne(4);
						Dimension positions = theGrid.positionOfSpritePlayerOne();
						Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
						int trueX = positions.height;
						int trueY = positions.width;
						int trueX2 = positionsTwo.height;
						int trueY2 = positionsTwo.width;
						System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
						System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
						p1.setX(trueX);
						p1.setY(trueY);
						input.clear();
						millisTimeP1 = System.currentTimeMillis();
					} else if (input.contains("D") && hori < theGrid.getMidLinePosition())
					{
						System.out.println("P1 moving right");
						theGrid.movePlayerOne(6);
						Dimension positions = theGrid.positionOfSpritePlayerOne();
						Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
						int trueX = positions.height;
						int trueY = positions.width;
						int trueX2 = positionsTwo.height;
						int trueY2 = positionsTwo.width;
						System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
						System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
						p1.setX(trueX);
						p1.setY(trueY);
						input.clear();
						millisTimeP1 = System.currentTimeMillis();
					}

					else if (input.contains("W") && vert > 0)
					{
						System.out.println("P1 moving up");
						theGrid.movePlayerOne(8);
						Dimension positions = theGrid.positionOfSpritePlayerOne();
						Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
						int trueX = positions.height;
						int trueY = positions.width;
						int trueX2 = positionsTwo.height;
						int trueY2 = positionsTwo.width;
						System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
						System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
						p1.setX(trueX);
						p1.setY(trueY);
						input.clear();
						millisTimeP1 = System.currentTimeMillis();
					}

					else if (input.contains("S") && vert < 2)
					{
						System.out.println("P1 moving down");
						theGrid.movePlayerOne(2);
						Dimension positions = theGrid.positionOfSpritePlayerOne();
						Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
						int trueX = positions.height;
						int trueY = positions.width;
						int trueX2 = positionsTwo.height;
						int trueY2 = positionsTwo.width;
						System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
						System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
						p1.setX(trueX);
						p1.setY(trueY);
						input.clear();
						millisTimeP1 = System.currentTimeMillis();
					}
				}
				if (theGrid.shootTimeOut(millisShootP1))
				{
					if (input.contains("E"))
					{
						double y = theGrid.positionOfSpritePlayerOne().height;
						double x = theGrid.positionOfSpritePlayerOne().width;

						Projectile bullet = new Projectile(root, "file:bulletW.png", 72, 72, y, x);
						System.out.println("P1 shot: " + bullet);

						Timeline timeLine = new Timeline(new KeyFrame(Duration.millis(25), (evt) ->
						{
							bullet.setX(bullet.getX() + bullet.getSpeedX());
							if (bullet.getBounds().intersects(p2.getBounds()))
							{
								p2.loseHealth();
								bullet.removeBullet();
								System.out.println("P2 Health : " + p2.getHealth());

								if (p2.getHealth() == 0)
								{
									p2.death();
									if (theGrid.getMidLinePosition() < 4)
									{
										theGrid.midLineRight();
										midline.setX(theGrid.getMidLinePosition()*150 + 325);
										long spawnTimer = System.currentTimeMillis() + 1000;
										while (System.currentTimeMillis() < spawnTimer)
										{
											// waiting...
										}
										p1.death();
										theGrid.addPlayers(p1, p2);
										p1.revive();
										p2.revive();
										Dimension positions = theGrid.positionOfSpritePlayerOne();
										Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
										int trueX = positions.height;
										int trueY = positions.width;
										int trueX2 = positionsTwo.height;
										int trueY2 = positionsTwo.width;
										System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
										System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
										p2.setX(trueX2);
										p2.setY(trueY2);
									} else
									{
										p2.death();
										graphc.fillText("Player 1 Wins!", 640, 360, 500);
										long closeTimer = System.currentTimeMillis() + 2000;
										while (System.currentTimeMillis() < closeTimer)
										{
											// waiting...
										}
										System.out.println("//////////////////////" + "\n" + "PLAYER ONE WON!" + "\n" + "//////////////////////");
										stage.close();
									}
								}
							}
						}));
						timeLine.setCycleCount(Animation.INDEFINITE);
						timeLine.play();

						input.clear();
						millisShootP1 = System.currentTimeMillis();
					}
				}
				if (theGrid.movementTimeOut(millisTimeP2))
				{
					if (input.contains("LEFT") && hori2 > theGrid.getMidLinePosition() + 1)
					{

						System.out.println("P2 moving left");
						theGrid.movePlayerTwo(4);
						Dimension positions = theGrid.positionOfSpritePlayerOne();
						Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
						int trueX = positions.height;
						int trueY = positions.width;
						int trueX2 = positionsTwo.height;
						int trueY2 = positionsTwo.width;
						System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
						System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
						p2.setX(trueX2);
						p2.setY(trueY2);
						input.clear();
						millisTimeP2 = System.currentTimeMillis();
					}

					else if (input.contains("RIGHT") && hori2 < 5)
					{
						System.out.println("P2 moving right");
						theGrid.movePlayerTwo(6);
						Dimension positions = theGrid.positionOfSpritePlayerOne();
						Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
						int trueX = positions.height;
						int trueY = positions.width;
						int trueX2 = positionsTwo.height;
						int trueY2 = positionsTwo.width;
						System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
						System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
						p2.setX(trueX2);
						p2.setY(trueY2);
						input.clear();
						millisTimeP2 = System.currentTimeMillis();
					}

					else if (input.contains("UP") && vert2 > 0)
					{
						System.out.println("P2 moving up");
						theGrid.movePlayerTwo(8);
						Dimension positions = theGrid.positionOfSpritePlayerOne();
						Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
						int trueX = positions.height;
						int trueY = positions.width;
						int trueX2 = positionsTwo.height;
						int trueY2 = positionsTwo.width;
						System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
						System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
						p2.setX(trueX2);
						p2.setY(trueY2);
						input.clear();
						millisTimeP2 = System.currentTimeMillis();
					}

					else if (input.contains("DOWN") && vert2 < 2)
					{
						System.out.println("P2 moving down");
						theGrid.movePlayerTwo(2);
						Dimension positions = theGrid.positionOfSpritePlayerOne();
						Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
						int trueX = positions.height;
						int trueY = positions.width;
						int trueX2 = positionsTwo.height;
						int trueY2 = positionsTwo.width;
						System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
						System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
						p2.setX(trueX2);
						p2.setY(trueY2);
						input.clear();
						millisTimeP2 = System.currentTimeMillis();
					}
				}
				if (theGrid.shootTimeOut(millisShootP2))
				{
					if (input.contains("ENTER"))
					{
						double y = theGrid.positionOfSpritePlayerTwo().height;
						double x = theGrid.positionOfSpritePlayerTwo().width;

						Projectile bulletW = new Projectile(root, "file:bullet.png", 75, 75, y, x);
						System.out.println("P2 shot: " + bulletW);

						Timeline timeLine = new Timeline(new KeyFrame(Duration.millis(25), (evt) ->
						{
							bulletW.setX(bulletW.getX() - bulletW.getSpeedX());
							if (bulletW.getBounds().intersects(p1.getBounds()))
							{
								p1.loseHealth();
								bulletW.removeBullet();
								System.out.println("P1 Health : " + p1.getHealth());

								if (p1.getHealth() == 0)
								{
									p1.death();
									if (theGrid.getMidLinePosition() > 0)
									{
										theGrid.midLineLeft();
										midline.setX(theGrid.getMidLinePosition() * 150 + 325);
										long spawnTimer = System.currentTimeMillis() + 1000;
										while (System.currentTimeMillis() < spawnTimer)
										{
											// waiting...
										}
										p2.death();
										theGrid.addPlayers(p1, p2);
										p1.revive();
										p2.revive();
										Dimension positions = theGrid.positionOfSpritePlayerOne();
										Dimension positionsTwo = theGrid.positionOfSpritePlayerTwo();
										int trueX = positions.height;
										int trueY = positions.width;
										int trueX2 = positionsTwo.height;
										int trueY2 = positionsTwo.width;
										System.out.println("drawing P1 sprite at [" + trueX + "][" + trueY + "]");
										System.out.println("drawing P2 sprite at [" + trueX2 + "][" + trueY2 + "]");
										p2.setX(trueX2);
										p2.setY(trueY2);
									} else
									{
										p1.death();
										long closeTimer = System.currentTimeMillis() + 2000;
										while (System.currentTimeMillis() < closeTimer)
										{
											// waiting...
										}
										System.out.println("//////////////////////" + "\n" + "PLAYER TWO WON!" + "\n" + "//////////////////////");
										stage.close();
									}
								}
							}
						}));
						timeLine.setCycleCount(Animation.INDEFINITE);
						timeLine.play();

						input.clear();
						millisShootP2 = System.currentTimeMillis();
					}
				}
			}
		}.start();

		stage.show();
	}
}
