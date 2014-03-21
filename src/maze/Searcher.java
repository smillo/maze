package maze;

public class Searcher extends Thread
{
	private Trail trail;
	private SearchableMaze maze;
	private Tracker tracker;
	private MazePosition current;
	private MazePosition previous;
	
	public Searcher(SearchableMaze maze, MazePosition from, Trail following, Tracker tr)
	{
		this.trail=following.clone();
		this.maze=maze;
		this.tracker = tr;
		this.current= from;
		tracker.registerSearcher();
	}
	
	
	public void run () {
		Searcher new_searcher;
		MazePosition[] sposta;
		int x;
		  
		  do { 
		    x = -1;
		    sposta = trail.mark(current);
		    for (int i = 0; i < sposta.length; i++) {
		      
		      
		        if(!sposta[i].equals(previous))
		           if(!trail.visited(sposta[i] )) {
		             
		             if(x == -1) x = i;
		             else {
		              
		            	 new_searcher = new Searcher(maze, sposta[i],
		                       trail, tracker);
		            	 new_searcher.start ();
		             }
		           }
		     }
		    if(x != -1) {
		      previous = current;
		      current = sposta [x];
		    }
		  } while(x != -1);
		  if(maze.atExit(current))
		    tracker.trailFound(trail);
		  else    tracker.searcherFinished();
		} 
		
}
