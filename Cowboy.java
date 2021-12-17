
interface Cowboy
{
	//move the character
	public void move();
	
	//return the health of the character
	public int getHealth();
	
	//shoot a bullet from the character
	public void shoot();
	
	//check if the player's character has health remaining
	public boolean isDead();
	
	//remove 1 point of health from character upon being hit
	public int loseHealth();
	
	
}
