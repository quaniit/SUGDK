package sugdk.graphics;

public class AnimatorListener {

	Animator animator;
	
	private double startValue;		//original value of the variable
	private double destValue;		//the destination value of the animator
	private double variable;		//the variable being changed
	private long length;			//time in nanoseconds that the animation should take
	
	private boolean direction;		//true = grow, false = shrink
	
	/**
	 * Actions to execute when the animator is done animating the object
	 */
	public void done()
	{
		
	}
	
	public void animate(double var, double value, long time)
	{
		startValue = var;
		destValue = value;
		variable = var;
		length = time;
		direction = startValue >= destValue;
		Animator.add(this);
	}
	
	/**
	 * Updates the variable to the value in scale of the time passed through
	 * @param time milliseconds into the animation
	 */
	public void update(long time)
	{
		variable = startValue + ((destValue - startValue) * (time/length));
		
		if (direction && variable > destValue)
			variable = destValue;
		else if (!direction && variable < destValue)
			variable = destValue;
	}
	
	/**
	 * @return the current value of the variable being animated
	 */
	public double getValue(){
		return variable;
	}
	
	/**
	 * @return the desired end value
	 */
	public double getDestValue(){
		return destValue;
	}
}
