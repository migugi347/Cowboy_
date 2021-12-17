import java.awt.Dimension;

public class Grid
{
	public Cowboy[][] map;
	private int midLinePosition;

	public Grid()
	{
		map = new Cowboy[3][6];

		for (int out = 0; out < map.length; out++)
		{
			for (int in = 0; in < map[out].length; in++)
			{
				map[out][in] = new Empty();
			}
		}

		midLinePosition = 2;
	}

	public int getMidLinePosition()
	{
		return midLinePosition;
	}

	public boolean movementTimeOut(long timer)
	{
		if (System.currentTimeMillis() - timer < 200)
			return false;
		else
			return true;
	}

	public boolean shootTimeOut(long timer)
	{
		if (System.currentTimeMillis() - timer < 1000)
			return false;
		else
			return true;
	}

	public Cowboy[][] getGrid()
	{
		return map;
	}

	public Dimension positionOfSpritePlayerOne()
	{
		int y = (int) (getPlayerOnePos().getWidth());
		int x = (int) (getPlayerOnePos().getHeight());
		Dimension positionOfSprite = new Dimension((400 + (100 * x)), (250 + (150 * y)));
		return positionOfSprite;
	}

	public Dimension positionOfSpritePlayerTwo()
	{
		int y = (int) (getPlayerTwoPos().getWidth());
		int x = (int) (getPlayerTwoPos().getHeight());
		Dimension positionOfSprite = new Dimension((400 + (100 * x)), (250 + (150 * y)));
		return positionOfSprite;
	}

	public void movePlayerOne(int indicator)
	{
		int y = (int) (getPlayerOnePos().getWidth());
		int x = (int) (getPlayerOnePos().getHeight());

		/// leffttttttt
		if (indicator == 4)
		{
			map[x][y - 1] = map[x][y];
			map[x][y] = new Empty();
			System.out.println("sucessfully navigated from [" + x + "][" + y + "] to [" + (x) + "][" + (y - 1) + "]");
		}

		// rightttt
		else if (indicator == 6)
		{
			map[x][y + 1] = map[x][y];
			map[x][y] = new Empty();
			System.out.println("sucessfully navigated from [" + x + "][" + y + "] to [" + (x) + "][" + (y + 1) + "]");
		}

		else if (indicator == 8)
		{
			map[x - 1][y] = map[x][y];
			map[x][y] = new Empty();
			System.out.println("sucessfully navigated from [" + x + "][" + y + "] to [" + (x - 1) + "][" + (y) + "]");
		}

		else if (indicator == 2)
		{
			map[x + 1][y] = map[x][y];
			map[x][y] = new Empty();
			System.out.println("sucessfully navigated from [" + x + "][" + y + "] to [" + (x + 1) + "][" + (y) + "]");
		}
	}

	public void movePlayerTwo(int indicator)
	{
		int y = (int) (getPlayerTwoPos().getWidth());
		int x = (int) (getPlayerTwoPos().getHeight());

		/// leffttttttt
		if (indicator == 4)
		{
			map[x][y - 1] = map[x][y];
			map[x][y] = new Empty();
			System.out.println("sucessfully navigated from [" + x + "][" + y + "] to [" + (x) + "][" + (y - 1) + "]");
		}

		// rightttt
		else if (indicator == 6)
		{
			map[x][y + 1] = map[x][y];
			map[x][y] = new Empty();
			System.out.println("sucessfully navigated from [" + x + "][" + y + "] to [" + (x) + "][" + (y + 1) + "]");
		}

		else if (indicator == 8)
		{
			map[x - 1][y] = map[x][y];
			map[x][y] = new Empty();
			System.out.println("sucessfully navigated from [" + x + "][" + y + "] to [" + (x - 1) + "][" + (y) + "]");
		}

		else if (indicator == 2)
		{
			map[x + 1][y] = map[x][y];
			map[x][y] = new Empty();
			System.out.println("sucessfully navigated from [" + x + "][" + y + "] to [" + (x + 1) + "][" + (y) + "]");
		}
	}

	public void addPlayers(Player1 p1, Player2 p2)
	{
		if (midLinePosition == 2)
		{
			map[1][1] = p1;
			map[1][4] = p2;
		}
		else
		{
			map[1][midLinePosition] = p1;
			map[1][midLinePosition+1] = p2;
		}
	}

	// Y are rows and height
	// X are columns and width
	//
	public Dimension getPlayerOnePos()
	{
		for (int row = 0; row < map.length; row++)
		{
			for (int col = 0; col < map[row].length; col++)
			{
				if (map[row][col].getClass().getName().equals("Player1"))
				{
					Dimension g = new Dimension(col, row);
					// width //height
					return g;
				}

			}
		}
		return null;
	}

	public Dimension getPlayerTwoPos()
	{
		for (int row = 0; row < map.length; row++)
		{
			for (int col = 0; col < map[row].length; col++)
			{
				if (map[row][col].getClass().getName().equals("Player2"))
				{
					Dimension g = new Dimension(col, row);
					// width //height
					return g;
				}
			}
		}
		return null;

	}

	public void midLineLeft()
	{
		midLinePosition--;
	}

	public void midLineRight()
	{
		midLinePosition++;
	}

}