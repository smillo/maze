package maze;
import java.util.ArrayList;


public class Tracker 
{	
	
	private int alive;
	private ArrayList<Trail> solutions;

	public Tracker()
	{
		this.alive=0;
		solutions= new ArrayList<Trail>();
		

	}
	
	public synchronized Trail getTrail(int n)
	{	
	
		if(n < solutions.size()) 
			return solutions.get(n);
	    else 
	    	return solutions.get(0);

		
	}

	public synchronized void registerSearcher()
	{
		alive++;
		
	}

	public synchronized void searcherFinished()
	{
		alive--;
		if(alive==0) notify();
		
		
	}
	
	public synchronized int numberOfSolutions()
	{
		return solutions.size();
		
	}

	public synchronized void trailFound(Trail t)
	{
		alive--;
	    solutions.add(t);
	    
	    if(alive == 0) notify();
	    
		
	}

	public synchronized int waitAllFinished()
	{
		while(alive != 0)
		      try {
		        wait();
		      }
		      catch (InterruptedException e){}; 
		    return solutions.size();
		
	
	}}
