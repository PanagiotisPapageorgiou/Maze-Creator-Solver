import java.io.IOException;
import java.lang.Object;
import java.util.Scanner;

public class Maze {
    
    private static int rows, cols;
    private MazeCell Start,End;
    private StackX theStack;
    private Queue theQueue;
    private PriorityQueue thePqueue;
    private MazeCell Array[][];

    public Maze(int rows, int cols, int rowStart, int colStart,int rowEnd,int colEnd){
		System.out.printf("A new intact %dx%d labyrinth is being created\n",rows,cols);
		System.out.printf("Starting coordinates are [%d,%d]\n",rowStart,colStart);
		System.out.printf("Ending coordinates are [%d,%d]\n",rowEnd,colEnd);
        this.rows = rows;
        this.cols = cols;
        Array = new MazeCell[rows][cols];
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                Array[i][j]= new MazeCell(i,j);
                setStartCell(Array[rowStart][colStart]);
		setEndCell(Array[rowEnd][colEnd]);
                setAllNeighbors();
        theStack = new StackX(size());
        theQueue = new Queue(size());
        thePqueue = new PriorityQueue(size());
    }
    
    public int getCols(){
        return cols;
    }
    
    public int getRows(){
        return rows;
    }
    
    public int size() {
		int Size = getCols()*getRows();
        return Size;
    }
    
    public void setStartCell(MazeCell cell){
        Start = cell;
    }
    
    public MazeCell getStartCell(){
        return Start;
    }
    
    public MazeCell getEndCell(){
        return End;
    }
    
    public void setEndCell(MazeCell cell){
        End = cell;
    }
    
    public MazeCell getCell(int row, int col){
        return Array[row][col];
   	}


/*=========================================CREATION ALGORITHMS=============================================================================================================*/

	public void create(){
		Scanner input = new Scanner(System.in);
		int number;

		do{
			System.out.println("\nPossible ways to create a labyrinth:");
			System.out.println("1.DFS.");
			System.out.println("2.BFS.");
			System.out.println("3.UCS.");
			//System.out.println("4.(Not ready)");
			//System.out.println("5.(Not ready)");
			//System.out.println("6.Cancel.");

			number = input.nextInt();
		}while((number != 1) && (number != 2) && (number != 3) && (number != 4) && (number != 5) && (number != 6));
		
		switch(number){
			case 1:
				createWithDFS();
				break;
			case 2:
            	                createBFSmaze();
				break;
			case 3:
                                createWithUCS();
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				break;
		}		

	}

	public void createWithDFS(){	//Uses DFS to explore a default labyrinth and creates a solvable labyrinth

        MazeCell current = getStartCell(),neighbor;
        int visitedCells = 1,TotalCells = size();
		ResetVisits();
		current.visit();

		if(theStack.isEmpty() == false){ 
			System.out.println("Stack must be emptied before running algorithm");
			do{
				theStack.pop();
			}while(theStack.isEmpty() == false);
		}

		System.out.println("Alright let's create a labyrinth using DFS!");
		while(visitedCells < TotalCells){
			System.out.printf("Visited: %d\n",visitedCells);
			System.out.print("Current cell is ");
			current.printCoordinates();
			printMyLabyrinth(current);
			if(current == getEndCell())
				System.out.println("We have successfully created a path to the desired End Cell :D");
			neighbor = current.anyIntactNeighbor(current.getNeighbors());
			if(neighbor != null){ //Exei geitones p dn exoume paei pote (gt eixan shkwmeno teixo)
				theStack.push(current);
				current = neighbor;
				current.visit();
				visitedCells++;
			}
			else
				if(theStack.isEmpty() == false){
					current = theStack.pop();
					System.out.println("Backtracking...");
				}	
		}
    }
       /* Breadth-First-Search( Maze m )
    EnQueue( m.StartNode )
    While Queue.NotEmpty 
        c <- DeQueue
        If c is the goal
            Exit
        Else
            Foreach neighbor n of c
                If n "Unvisited"
                    Mark n "Visited"                    
                    EnQueue( n )
            Mark c "Examined"                    
End procedure*/

	public void createBFSmaze(){ //Create BFS is possible but not recommended

    	MazeCell current = getStartCell(),neighbor;
        MazeCell[] AllNeighbors;
        //int visitedCells = 1,examinedCells=0,TotalCells = size();

		ResetVisits();
        ResetExamined();

		current.visit();

		if(theQueue.isEmpty() == false){ 
			System.out.println("Queue must be emptied before running algorithm");
			do{
				theQueue.remove();
			}while(theQueue.isEmpty() == false);
		}

		System.out.println("Alright let's create the labyrinth using BFS!");
        //System.out.printf("Visited: %d\n",visitedCells);
        theQueue.insert(current);
                
        while(theQueue.isEmpty() == false){
                    
            current = theQueue.remove();
			theQueue.printQueue(); 
        
			System.out.print("Current cell is ");
			current.printCoordinates();
			printMyLabyrinth(current);
			if(current == getEndCell())
				System.out.println("We have successfully created a path to the desired End Cell! :D");
            else{         
            //AllNeighbors = current.getNeighbors();
				if(current.get_examined() == false){
					AllNeighbors = current.getNeighbors();
					//for(int i = 0; i < AllNeighbors.length; i++){
						//if(AllNeighbors[i] == null){
							//System.out.println("My friend we are fucked");
						//}
					//}
					//if(AllNeighbors == null) System.out.println("ERRORRRRRRRRRRR");
					//System.out.printf("Array length is %d\n",AllNeighbors.length);
            		for(int i=0; i< AllNeighbors.length; i++){
            			//System.out.printf("CELL:[%d.%d] HAS BEEN: %b\n",AllNeighbors[i].get_MazeCellrow(),AllNeighbors[i].get_MazeCellcol(),AllNeighbors[i].get_visited());
						if(AllNeighbors[i].get_visited() == false){
                			AllNeighbors[i].visit();
							if(AllNeighbors[i] == current.getNneighbor()){ 
								System.out.println("Opening a path to the north neighbor");
								current.setWalls(false,current.east(),current.west(),current.south());
								//Array[current.getRows()-1][current.getCols()].setWalls(
								AllNeighbors[i].setWalls(AllNeighbors[i].north(),AllNeighbors[i].east(),AllNeighbors[i].west(),false);
							}
							else if(AllNeighbors[i] == current.getSneighbor()){
								System.out.println("Opening a path to the south neighbor");
								current.setWalls(current.north(),current.east(),current.west(),false);
								AllNeighbors[i].setWalls(false,AllNeighbors[i].east(),AllNeighbors[i].west(),AllNeighbors[i].south());
							}
							else if(AllNeighbors[i] == current.getWneighbor()){ 
								System.out.println("Opening a path to our west neighbor");
								current.setWalls(current.north(),current.east(),false,current.south());
								AllNeighbors[i].setWalls(AllNeighbors[i].north(),false,AllNeighbors[i].west(),AllNeighbors[i].south());
							}
							else{ 
								System.out.println("Opening a path to our east neighbor");
								current.setWalls(current.north(),false,current.west(),current.south());
								AllNeighbors[i].setWalls(AllNeighbors[i].north(),AllNeighbors[i].east(),false,AllNeighbors[i].south());
							}
                    //visitedCells++;
                    //System.out.printf("Visited: %d\n",visitedCells);
                    		theQueue.insert(AllNeighbors[i]);
                                        
            			}
                                   
					}
                         
        			current.examine();
        	//examinedCells++;
        	//System.out.printf("Examined : %d\n",examinedCells);

        	//if(theQueue.isEmpty() == false){
				//theQueue.remove();
				//System.out.println("Backtracking...");
        	//}
        	//current = theQueue.peekFront();
				}
			}
    	}
	}
        
    
     public void createWithUCS(){
        MazeCell current = getStartCell(),neighbor;
        MazeCell[] AllNeighbors;
        //int visitedCells = 1,examinedCells=0,TotalCells = size();

		ResetVisits();
        ResetExamined();

		current.visit();

		System.out.println("Alright let's create the labyrinth using UCS!");
        //System.out.printf("Visited: %d\n",visitedCells);

		if(thePqueue.isEmpty() == false){ 
			System.out.println("PQueue must be emptied before running algorithm");
			do{
				thePqueue.remove();
			}while(thePqueue.isEmpty() == false);
		}

        thePqueue.insert(current,current);
		thePqueue.printQueue();
                
        while(thePqueue.isEmpty() == false){
                    
            current = thePqueue.remove();
        
			System.out.print("Current cell is ");
			current.printCoordinates();
			printMyLabyrinth(current);
			if(current == getEndCell())
				System.out.println("We have successfully created a path to the desired End Cell! :D");
            else{         
            //AllNeighbors = current.getNeighbors();
				if(current.get_examined() == false){
					AllNeighbors = current.getNeighbors();
					//for(int i = 0; i < AllNeighbors.length; i++){
						//if(AllNeighbors[i] == null){
							//System.out.println("My friend we are fucked");
						//}
					//}
					//if(AllNeighbors == null) System.out.println("ERRORRRRRRRRRRR");
					//System.out.printf("Array length is %d\n",AllNeighbors.length);
            		for(int i=0; i< AllNeighbors.length; i++){
            			//System.out.printf("CELL:[%d.%d] HAS BEEN: %b\n",AllNeighbors[i].get_MazeCellrow(),AllNeighbors[i].get_MazeCellcol(),AllNeighbors[i].get_visited());
						if(AllNeighbors[i].get_visited() == false){
                			AllNeighbors[i].visit();
							if(AllNeighbors[i] == current.getNneighbor()){ 
								System.out.println("Opening a path to the north neighbor");
								current.setWalls(false,current.east(),current.west(),current.south());
								//Array[current.getRows()-1][current.getCols()].setWalls(
								AllNeighbors[i].setWalls(AllNeighbors[i].north(),AllNeighbors[i].east(),AllNeighbors[i].west(),false);
							}
							else if(AllNeighbors[i] == current.getSneighbor()){
								System.out.println("Opening a path to the south neighbor");
								current.setWalls(current.north(),current.east(),current.west(),false);
								AllNeighbors[i].setWalls(false,AllNeighbors[i].east(),AllNeighbors[i].west(),AllNeighbors[i].south());
							}
							else if(AllNeighbors[i] == current.getWneighbor()){ 
								System.out.println("Opening a path to our west neighbor");
								current.setWalls(current.north(),current.east(),false,current.south());
								AllNeighbors[i].setWalls(AllNeighbors[i].north(),false,AllNeighbors[i].west(),AllNeighbors[i].south());
							}
							else{ 
								System.out.println("Opening a path to our east neighbor");
								current.setWalls(current.north(),false,current.west(),current.south());
								AllNeighbors[i].setWalls(AllNeighbors[i].north(),AllNeighbors[i].east(),false,AllNeighbors[i].south());
							}
                    //visitedCells++;
                    //System.out.printf("Visited: %d\n",visitedCells);
                    		thePqueue.insert(AllNeighbors[i],current);
							//hePqueue.printQueue();
                                        
            			}
                                   
					}
                        
                        
        			current.examine();
        	//examinedCells++;
        	//System.out.printf("Examined : %d\n",examinedCells);

        	//if(theQueue.isEmpty() == false){
				//theQueue.remove();
				//System.out.println("Backtracking...");
        	//}
        	//current = theQueue.peekFront();
				}
			}
    	}
    }    

	public void createITSMaze(){ //Iterative-Deepening search,combines BFS and DFS,we will be constantly using DFS on lower levels of the labyrinth

	


	}

/*When the while loop terminates, the algorithm is completed. Every cell has been visited and thus no cell is inaccessible. Also, since we test each possible move to see if we've already been there, the algorithm prevents the creation of any open areas, or paths that circle back on themselves. 

We can put the start and end points wherever we want. This is another advantage of a perfect maze. Since, by definition, one and only one path will exist between any two points in the maze, we know that given a start/end pair, a unique solution to the maze must exist. 

Depth-First Search is the most common algorithm used in maze generation programs: it's simple to implement, works quickly, and generates very pretty mazes. The algorithm can also be used to solve mazes. This is how MazeGen generates solutions for all mazes, no matter which algorithm was used to create them.  
===============================================================================================================================================================================
*/

	public void printMyLabyrinth(MazeCell cell){

		int checkedD; //Flag to check if the south direction has been checked

		System.out.printf("\n\n==================THE LABYRINTH===================\n\n");
		for(int i = 0; i < rows; i++){
			checkedD = 0;
			for(int j = 0; j < cols; j++){
				if(checkedD == 0){
					if (Array[i][j].north() == true) System.out.print("XNNNNNNNX");
					else System.out.print("X       X");
					if(j == cols-1){ 
						System.out.printf("\n");
						j = -1;
						checkedD++;
						continue;
					}
				}
				if((checkedD == 1) || (checkedD == 2)){
					if((Array[i][j].west() == true) || (Array[i][j].east() == true)){
						if((Array[i][j].west() == true) && (Array[i][j].east() == false)){
							System.out.print('W');
							if(checkedD == 1) System.out.printf("%3d.%3d ",i,j);
							if(checkedD == 2){                                      
								if(Array[i][j] == getStartCell()) System.out.print(" START  ");
								else if(Array[i][j] == getEndCell()) System.out.print("FINISH  ");
								else {
									if(cell == null) System.out.print("        ");
									else 
										if(cell == Array[i][j]) System.out.print("current ");
										else System.out.print("        ");
								}
							}
						}
						else if((Array[i][j].west() == false) && (Array[i][j].east() == true)){
							System.out.print(' ');
							if(checkedD == 1) System.out.printf("%3d.%3dE",i,j);
							if(checkedD == 2){
								if(Array[i][j] == getStartCell()) System.out.print(" START E");
								else if(Array[i][j] == getEndCell()) System.out.print("FINISH E");
								else {
									if(cell == null) System.out.print("       E");
									else
										if(cell == Array[i][j]) System.out.print("currentE");
										else System.out.print("       E");
								}
							}	
						}
						else{
							System.out.print('W');
							if(checkedD == 1) System.out.printf("%3d.%3dE",i,j);
							if(checkedD == 2){
								if(Array[i][j] == getStartCell()) System.out.print(" START E");
								else if(Array[i][j] == getEndCell()) System.out.print("FINISH E");
								else {
									if(cell == null) System.out.print("       E");
									else
										if(cell == Array[i][j]) System.out.print("currentE");
										else System.out.print("       E");
								}
							}
						}												
					}
					else{ //east && west == false
							System.out.print(' ');
							if(checkedD == 1) System.out.printf("%3d.%3d ",i,j);
							if(checkedD == 2){
								if(Array[i][j] == getStartCell()) System.out.print(" START  ");
								else if(Array[i][j] == getEndCell()) System.out.print("FINISH  ");
								else {
									if(cell == null) System.out.print("        ");
									else
										if(cell == Array[i][j]) System.out.print("current ");
										else System.out.print("        ");
								}
							}	
					}
					if(j == cols-1){ 
						System.out.printf("\n");
						j = -1;
						checkedD++;
						continue;
					}
				}
				if(checkedD == 3){
					if (Array[i][j].south() == true) System.out.print("XSSSSSSSX");
					else System.out.print("X       X");
					if(j == cols-1){ 
						System.out.printf("\n");
						break;
					}
				}
			}
		}
	}

   
    
    public void setAllNeighbors(){

        Array[0][0].setNeighbors(null, Array[0][1], null, Array[1][0]);
        Array[0][getCols()-1].setNeighbors(null, null, Array[0][getCols()-2], Array[1][getCols()-1]);
        Array[getRows()-1][0].setNeighbors(Array[getRows()-2][0], Array[getRows()-1][1], null, null);
        Array[getRows()-1][getCols()-1].setNeighbors(Array[getRows()-2][getCols()-1], null, Array[getRows()-1][getCols()-2], null);

        for(int i=0; i<getRows(); i++){
            for(int j=0; j<getCols(); j++){
                if((i>=1) && (i<=getRows()-2) && (j>=1) && (j<=getCols()-2))
                	Array[i][j].setNeighbors(Array[i-1][j], Array[i][j+1], Array[i][j-1], Array[i+1][j]);
                else if((i==0) && (j>=1) && (j<=getCols()-2))
                    Array[i][j].setNeighbors(null, Array[i][j+1], Array[i][j-1], Array[i+1][j]);
                else if((i==getRows()-1) && (j>=1) && (j<=getCols()-2))
                    Array[i][j].setNeighbors(Array[i-1][j], Array[i][j+1], Array[i][j-1], null);
                else if((j==0) && (i>=1) && (i<=getRows()-2) )
                    Array[i][j].setNeighbors(Array[i-1][j], Array[i][j+1], null, Array[i+1][j]);
                else if((j==getCols()-1) && (i>=1) && (i<=getRows()-2))
                    Array[i][j].setNeighbors(Array[i-1][j], null, Array[i][j-1], Array[i+1][j]);
            }
        }
    }
    
    public void solveMaze(){
		Scanner input = new Scanner(System.in);
		int number;

		do{
			System.out.println("\nPossible ways to solve a labyrinth:");
			System.out.println("1.DFS.");
			System.out.println("2.BFS.");
			System.out.println("3.UCS.");
			System.out.println("4.(Not ready)");
			System.out.println("5.(Not ready)");
			System.out.println("6.Cancel.");

			number = input.nextInt();
		}while((number != 1) && (number != 2) && (number != 3) && (number != 4) && (number != 5) && (number != 6));
		
		switch(number){
			case 1:
				solveDFSMaze();
				break;
			case 2:
                                solveBFSMaze();
				break;
			case 3:
                                solveUCSMaze();
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				break;
		}
        
    }
   
	public void ResetVisits(){
		for(int i = 0; i < rows; i++)
			for(int j = 0;  j < cols; j++)
				Array[i][j].unvisit();
	}

	public void ResetExamined(){
		for(int i = 0; i < rows; i++)
			for(int j = 0;  j < cols; j++)
				Array[i][j].unexamine();
	}

	public void ResetMaze(){
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++){
				Array[i][j].unvisit();
				Array[i][j].unexamine();
				Array[i][j].setWalls(true,true,true,true);
			}
	}

        
        
	public void solveDFSMaze(){ //Use DFS algorithm to try and find the exit now!
   
		Scanner input = new Scanner(System.in);
   		MazeCell current = getStartCell(),neighbor;
    	int number;
		ResetVisits();
		current.visit();

		if(theStack.isEmpty() == false){ 
			System.out.println("Stack must be emptied before running algorithm");
			do{
				theStack.pop();
			}while(theStack.isEmpty() == false);
		}

		theStack.push(current);
		while(theStack.isEmpty() == false){
			current = theStack.peek(); //Des to pio panw sti stoiba auto einai to current
			System.out.print("Current cell is ");
			current.printCoordinates();
			printMyLabyrinth(current);
			if(current == getEndCell()){
				System.out.println("DFS has reached the End Cell exiting! :D");
				return;
			}

			neighbor = current.selectRandomUnvisitedNeighbor(current.getValidNeighbors());
			if(neighbor == null){
				theStack.pop(); //Backtracking
				System.out.println("Backtracking...");
			}
			else{
				neighbor.visit();
				theStack.push(neighbor);
			}
		}

    }
 
      
     public void solveBFSMaze(){
		
        MazeCell current = getStartCell(),neighbor;
        MazeCell[] AllNeighbors;
        //int visitedCells = 1,examinedCells=0,TotalCells = size();

		ResetVisits();
        ResetExamined();

		current.visit();

		if(theQueue.isEmpty() == false){ 
			System.out.println("Queue must be emptied before running algorithm");
			do{
				theQueue.remove();
			}while(theQueue.isEmpty() == false);
		}

		System.out.println("Alright let's solve the labyrinth using BFS!");
        //System.out.printf("Visited: %d\n",visitedCells);
        theQueue.insert(current);
                
        while(theQueue.isEmpty() == false){
                    
            current = theQueue.remove();
        
			System.out.print("Current cell is ");
			current.printCoordinates();
			printMyLabyrinth(current);
			if(current == getEndCell()){
				System.out.println("BFS has reached the End Cell exiting! :D");
				return;
			}
            else{         
            //AllNeighbors = current.getNeighbors();
				if(current.get_examined() == false){
					AllNeighbors = current.getValidNeighbors();
					//for(int i = 0; i < AllNeighbors.length; i++){
						//if(AllNeighbors[i] == null){
							//System.out.println("My friend we are fucked");
						//}
					//}
					//if(AllNeighbors == null) System.out.println("ERRORRRRRRRRRRR");
					//System.out.printf("Array length is %d\n",AllNeighbors.length);
            		for(int i=0; i< AllNeighbors.length; i++){
            			//System.out.printf("CELL:[%d.%d] HAS BEEN: %b\n",AllNeighbors[i].get_MazeCellrow(),AllNeighbors[i].get_MazeCellcol(),AllNeighbors[i].get_visited());
						if(AllNeighbors[i].get_visited() == false){
                			AllNeighbors[i].visit();
							if(AllNeighbors[i] == current.getNneighbor()) System.out.println("Visiting north neighbor");
							else if(AllNeighbors[i] == current.getSneighbor()) System.out.println("Visiting south neighbor");
							else if(AllNeighbors[i] == current.getWneighbor()) System.out.println("Visiting west neighbor");
							else System.out.println("Visiting east neighbor");
                    //visitedCells++;
                    //System.out.printf("Visited: %d\n",visitedCells);
                    		theQueue.insert(AllNeighbors[i]);
                                        
            			}
                                   
					}
                        
                        
        			current.examine();
        	//examinedCells++;
        	//System.out.printf("Examined : %d\n",examinedCells);

        	//if(theQueue.isEmpty() == false){
				//theQueue.remove();
				//System.out.println("Backtracking...");
        	//}
        	//current = theQueue.peekFront();
				}
			}
    	}
	}

   
    public boolean getUnexaminedNeighbor(MazeCell current, MazeCell[] neighbors){
        for(int i=1; i<=neighbors.length; i++){
            if(current.get_examined()==false) return true;
        }
        return false;
    }
    
    public void solveUCSMaze(){

    MazeCell current = getStartCell(),neighbor;
        MazeCell[] AllNeighbors;
        //int visitedCells = 1,examinedCells=0,TotalCells = size();

		ResetVisits();
                ResetExamined();

		current.visit();

		if(thePqueue.isEmpty() == false){ 
			System.out.println("PQueue must be emptied before running algorithm");
			do{
				thePqueue.remove();
			}while(thePqueue.isEmpty() == false);
		}

		System.out.println("Alright let's solve the labyrinth using UCS!");
        //System.out.printf("Visited: %d\n",visitedCells);
        thePqueue.insert(current,current);
                
        while(thePqueue.isEmpty() == false){
                    
            current = thePqueue.remove();
        
			System.out.print("Current cell is ");
			current.printCoordinates();
			printMyLabyrinth(current);
			if(current == getEndCell()){
				System.out.println("UCS has reached the End Cell exiting! :D");
				return;
			}
            else{         
            //AllNeighbors = current.getNeighbors();
				if(current.get_examined() == false){
					AllNeighbors = current.getValidNeighbors();
					//for(int i = 0; i < AllNeighbors.length; i++){
						//if(AllNeighbors[i] == null){
							//System.out.println("My friend we are fucked");
						//}
					//}
					//if(AllNeighbors == null) System.out.println("ERRORRRRRRRRRRR");
					//System.out.printf("Array length is %d\n",AllNeighbors.length);
            		for(int i=0; i< AllNeighbors.length; i++){
            			//System.out.printf("CELL:[%d.%d] HAS BEEN: %b\n",AllNeighbors[i].get_MazeCellrow(),AllNeighbors[i].get_MazeCellcol(),AllNeighbors[i].get_visited());
						if(AllNeighbors[i].get_visited() == false){
                			AllNeighbors[i].visit();
							if(AllNeighbors[i] == current.getNneighbor()) System.out.println("Visiting north neighbor");
							else if(AllNeighbors[i] == current.getSneighbor()) System.out.println("Visiting south neighbor");
							else if(AllNeighbors[i] == current.getWneighbor()) System.out.println("Visiting west neighbor");
							else System.out.println("Visiting east neighbor");
                    //visitedCells++;
                    //System.out.printf("Visited: %d\n",visitedCells);
                    		thePqueue.insert(AllNeighbors[i],current);
                                        
            			}
                                   
					}
                        
                        
        			current.examine();
        	//examinedCells++;
        	//System.out.printf("Examined : %d\n",examinedCells);

        	//if(theQueue.isEmpty() == false){
				//theQueue.remove();
				//System.out.println("Backtracking...");
        	//}
        	//current = theQueue.peekFront();
				}
			}
    	}
    }
    
    public void solveITSMaze(){

		
        
    }
}
