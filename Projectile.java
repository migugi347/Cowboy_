import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.geometry.*;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.Image;

public class Projectile
{
	private double x, y, speedX;
	private double height, width;
	private ImageView imageView;
	
	
	public Projectile(Pane root, String url, double w, double h, double x, double y)
	{
		speedX = 50;
	imageView = new ImageView(new Image(url , w, h, false, false));
	imageView.setCache(true);
	setX(x);
	setY(y);
	root.getChildren().add(imageView);
	
		
	}
	
	protected ImageView getImageView()
	{
		return imageView;
		
	}
	
	public void removeBullet()
	{
		 imageView.setImage(null);
	}
	
    
	public double getX() {
		return imageView.getX();
	}


	public double getY() {
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


	public double getSpeedX() {
		return speedX;
	}


	public void setX(double x) {
		imageView.setX(x);
	}


	public void setY(double y) {
		imageView.setY(y);
	}


	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public Bounds getBounds()
	{
		return imageView.getBoundsInParent();
	}

	@Override
	public String toString()
	{
		return "[GameObj: (" + this.getX() + ", " + this.getY() +")]";
	}
	
	
	
}