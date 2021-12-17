import java.io.*;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player2 implements Cowboy
{
	private int health = 9;
	private ImageView imageView;
	private Pane r;
	private String u;
	private double wd;
	private double hd;
	public Player2(Pane root, String url, double w, double h, double x, double y)
	{
		r = root;
		u = url;
		wd = w;
		hd = h;
		imageView = new ImageView(new Image(url, w, h, false, false));
		imageView.setCache(true);
		setX(x);
		setY(y);
		root.getChildren().add(imageView);

	}

	public int getHealth()
	{
		return health;
	}

	public int loseHealth()
	{
		return --health;
	}

	protected ImageView getImageView()
	{
		return imageView;

	}

	public void death()
	{
		imageView.setImage(null);
	}

	public void revive()
	{
		imageView.setImage(new Image(u,wd,hd,false,false));
		health = 9;
	}
	
	public double getX()
	{
		return imageView.getX();
	}

	public double getY()
	{
		return imageView.getY();
	}

	public double getHeight()
	{
		return imageView.getImage().getHeight();
	}

	public double getWidth()
	{
		return imageView.getImage().getWidth();
	}

	public void setX(double x)
	{
		imageView.setX(x);
	}

	public void setY(double y)
	{
		imageView.setY(y);
	}

	public Bounds getBounds()
	{
		return imageView.getBoundsInParent();
	}

	public boolean isDead()
	{
		if (health == 0)
			return true;
		else
			return false;
	}

	@Override
	public void move()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void shoot()
	{
		// TODO Auto-generated method stub

	}
}