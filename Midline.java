import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Midline
{
	private ImageView imageView;

	public Midline(Pane root, String url, double w, double h, double x, double y)
	{

		imageView = new ImageView(new Image(url, w, h, false, false));
		imageView.setCache(true);
		setX(x);
		setY(y);
		root.getChildren().add(imageView);

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
}
