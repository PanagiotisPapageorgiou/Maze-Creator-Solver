import java.lang.Object;
import java.util.Random;
import java.util.Scanner;

public class MazeCell {
    
    public boolean East;
    public boolean West;
    public boolean South;
    public boolean North;
    private MazeCell n,e,w,s;
    public boolean examined;
    public boolean visited;
    private Random generator;
    private int row,col;
    
    
    public MazeCell(int r,int c){
        setWalls(true,true,true,true);
        
        examined = false;
        visited = false;
        generator = new Random();

		row = r;
		col = c;
    }

	public void unvisit(){
		visited = false;
	}

	public void unexamine(){
		examined = false;
	}

	public void printCoordinates(){
		System.out.printf("[%d,%d]\n",row,col);
	}

	public int get_MazeCellrow(){
		return row;
	}
	
	public int get_MazeCellcol(){
		return col;
	}
    
    public boolean east(){
        return East;
    }
    
    public boolean west(){
        return West;
    }
    
    public boolean south(){
        return South;
    }
    
    public boolean north(){
        return North;
    }
    
    
    public void examine(){
        examined = true;
    }
    
    public boolean get_examined(){
         return examined;
    }
    
    
    public boolean hasAllWalls(){
        if((east()== true )&& (west()== true) && (north()== true) && (south()== true)){
            return true;
        }
        return false;
    }
    
      
    public void setNeighbors(MazeCell n, MazeCell e, MazeCell w, MazeCell s){
       this.n = n;
       this.e = e;
       this.w = w;
       this.s = s;
    }
    
    public MazeCell getNneighbor(){
        return n;
    }
    
    public MazeCell getSneighbor(){
        return s;
    }
    
    public MazeCell getWneighbor(){
        return w;
    }
    
    public MazeCell getEneighbor(){
        return e;
    }

    
    public MazeCell[] getNeighbors(){ //Epistrefei geitones pou boroume na episkeftoume
       								  
        int count = 0;
        int usedN=0,usedW=0,usedS=0,usedE=0;
        
        if (n != null){
            count++;
			//System.out.println("North is possible");
        }
        if(s != null){
            count++;
			//System.out.println("South is possible");
        }
        if(w != null){
            count++;
			//System.out.println("West is possible");
        }
        if(e != null){
			//System.out.println("East is possible");
            count++;
        }
        
        MazeCell[] neighbors = new MazeCell[count];
        
        for(int i=0; i<count; i++){
            if((n != null)&&(usedN == 0)){
                neighbors[i] = n;
                usedN = 1;
            }
            else if((w != null)&&(usedW == 0)){
                neighbors[i] = w;
                usedW = 1;
            }
            else if ((e != null)&&(usedE == 0)){
                neighbors[i] = e;
                usedE = 1;
            }
            else if ((s != null)&&(usedS == 0)){
                neighbors[i] = s;
                usedS = 1;
            }
        }
        return neighbors;
    }
    
    public MazeCell[] getValidNeighbors(){ //Epistrefei geitones pou na einai prosbasimoi omws
       								  
        int count = 0;
        int usedN=0,usedW=0,usedS=0,usedE=0;
        
		System.out.println("Time to check for valid neighbours");

		//if(n == null) System.out.println("North neighbour is null");
		//if(s == null) System.out.println("South neighbour is null");
		//if(w == null) System.out.println("West neighbour is null");
		//if(e == null) System.out.println("East neighbour is null");

        if (n != null){
			if((north() == false) && (n.south() == false)){
            	count++;
				System.out.println("North is possible");
			}
        }
        if(s != null){
			if((south() == false) && (s.north() == false)){
            	count++;
				System.out.println("South is possible");
			}
        }
        if(w != null){
			if((west() == false) && (w.east() == false)){
            	count++;
				System.out.println("West is possible");
			}
        }
        if(e != null){
			if((east() == false) && (e.west() == false)){
				System.out.println("East is possible");
            	count++;
			}
        }
        
		//System.out.printf("Count of neighbours: %d\n",count);
		if(count == 0) return null;

        MazeCell[] neighbors = new MazeCell[count];
        
        for(int i=0; i<count; i++){

//EIMAI MEGALOS NOOBAS META APO 5 WRES BRHKA ENA POLU POLU HLITHIO LATHOS EDW STI BFS 17/11/2013 if((n != null)&&(usedN == 0)){if((north() == false) && (n.south() == false))
//ebaine s lathos if/else if/else epeidi ebaine s if/else prwta koitwntas an einai null kati k meta elegxe an eixe pesmenous teixous,etsi ebaine s if/else if gia keli to opoio borei na exei shkwmenous teixous alla na min htan null k etsi telika xanotan ena loop

            if((n != null)&&(usedN == 0)&&(north() == false)&&(n.south() == false)){
					//System.out.println("North enters in the neighbourlist!");
                	neighbors[i] = n;
					//if(n == null) System.out.println("DAMN YOU NORTH");
					//if(neighbors[i] == null) System.out.println("DAMN YOU NORTH THING");
                	usedN = 1;
            }
            else if((w != null)&&(usedW == 0)&&(west() == false)&&(w.east() == false)){
					//System.out.println("West enters in the neighbourlist!");
                	neighbors[i] = w;
					//if(w == null) System.out.println("DAMN YOU WEST");
					//if(neighbors[i] == null) System.out.println("DAMN YOU WEST THING");
                	usedW = 1;
            }
            else if ((e != null)&&(usedE == 0)&&(east() == false) && (e.west() == false)){
					//System.out.println("East enters in the neighbourlist!");
                	neighbors[i] = e;
					//if(e == null) System.out.println("DAMN YOU EAST");
					//if(neighbors[i] == null) System.out.println("DAMN YOU EAST THING");
                	usedE = 1;
            }//if ((s != null)&&(usedS == 0))
            else if ((s != null) && (usedS == 0) && (south() == false) && (s.north() == false)){
					//System.out.println("I am a cool south");
					//System.out.println("South enters in the neighbourlist!");
					//if(s == null) System.out.println("DAMN YOU SOUTH");
                	neighbors[i] = s;
					//if(neighbors[i] == null) System.out.println("DAMN YOU SOUTH THING");
                	usedS = 1;
			}
				//if(south() == true) System.out.println("My south wall is up");
				//if(s.north() == true) System.out.println("My neighbour's north wall is up");
        }

		//for(int i = 0; i < neighbors.length; i++)
			//if(neighbors[i] == null) System.out.println("FUCK YOU GENERALLY");
        return neighbors;
    }
    
    public MazeCell selectRandomUnvisitedNeighbor(MazeCell[] Array){
		
		MazeCell randcell=null;
		int count = 0;

		if(Array.length == 0)
			return null;

		for(int i = 0; i < Array.length; i++){
			if(Array[i].get_visited() == false)	{
				//if(Array[i] == n) System.out.println("North is here!");
				//if(Array[i] == s) System.out.println("South is here!");
				//if(Array[i] == w) System.out.println("West is here!");
				//if(Array[i] == e) System.out.println("East is here!");
				count++;
				//System.out.println("WHAT IS LOVE\n");
			}
		}
		
		if(count == 0){
			System.out.println("No intact neighbouring cell to explore :(");
			return null;
		}		

		do{
			randcell = Array[generator.nextInt(Array.length)];
		}while(randcell.get_visited() == true);

		if(randcell == n)
			System.out.println("Going north!");
		else if(randcell == s)
			System.out.println("Going south!");
		else if(randcell == e)
			System.out.println("Going east!");
		else
			System.out.println("Going west");

		return randcell;

	}
    
    public void setWalls(boolean north, boolean east, boolean west, boolean south){
        North = north;
        East = east;
        West = west;
        South = south;
    }
    
    
    public void visit(){
        visited = true;
    }
    
    public boolean get_visited(){
         return visited;
    }
    
    public MazeCell anyMazeCell(MazeCell[] Array)
    {
      MazeCell randcell = Array[generator.nextInt(Array.length)];
      return randcell;  
    }

    public MazeCell anyIntactNeighbor(MazeCell[] Array){ //Will try to find and choose randomly an intact neighbour with his walls up
														 //Used when creating a labyrinth
		MazeCell randcell = null;
		int count = 0;

		if(Array.length == 0)
			System.out.println("No neighbours");
		for(int i = 0; i < Array.length; i++){
			if(Array[i].get_visited() == false)	{
				//if(Array[i] == n) System.out.println("North is here!");
				//if(Array[i] == s) System.out.println("South is here!");
				//if(Array[i] == w) System.out.println("West is here!");
				//if(Array[i] == e) System.out.println("East is here!");
				count++;
				//System.out.println("WHAT IS LOVE\n");
			}
		}
		if(count == 0){
			System.out.println("No intact neighbouring cell to explore :(");
			return null;
		}
		else{
			do{
				randcell = Array[generator.nextInt(Array.length)];
			}while(randcell.get_visited() == true);

			if(randcell == null) System.out.println("BINGO");
			if(randcell == n){
				System.out.println("I have chosen the north neighbor!");
				System.out.println("I am knocking down my northern wall and his southern wall!");
				setWalls(false,east(),west(),south());
				randcell.setWalls(randcell.north(),randcell.east(),randcell.west(),false);
			}
			else if(randcell == s){
				System.out.println("I have chosen my south neighbor!");
				System.out.println("I am knocking down my southern wall and his northern wall!");
				setWalls(north(),east(),west(),false);
				randcell.setWalls(false,randcell.east(),randcell.west(),randcell.south());
			}
			else if(randcell == w){
				System.out.println("I have chosen my west neighbor!");
				System.out.println("I am knocking down my western wall and his eastern wall!");
				setWalls(north(),east(),false,south());
				randcell.setWalls(randcell.north(),false,randcell.west(),randcell.south());
			}
			else{
				System.out.println("I have chosen my east neighbor!");
				System.out.println("I am knocking down my eastern wall and his western wall!");
				setWalls(north(),false,west(),south());
				randcell.setWalls(randcell.north(),randcell.east(),false,randcell.south());
			}

			return randcell;
		}
	
	}
    
   

/*	public MazeCell anyUnExaminedNeighbor(MazeCell[] Array){
		MazeCell randcell;
		int count = 0; //Metraei tous mh prosbasimous geitones wste ama eimai s adieksodo na gurisw pisw epistrefontas NULL
		boolean flag = false;

				
			for(int i = 0; i < Array.length; i++){
				if(Array[i] == n)
					if(((north() == false) && (n.south() == false)) && (n.examined() == false)){
						count++;
						System.out.println("North is accessible-unexamined");
					}
				if(Array[i] == s)
					if((south() == false) && (s.north() == false) && (s.examined() == false)){
						count++;
						System.out.println("South is accessible-unexamined");
					}
				if(Array[i] == w)
					if(((west() == false) && w.east() == false) && (w.examined() == false)){ 
						count++;
						System.out.println("West is accessible-unexamined");
					}
				if(Array[i] == e)
					if(((east() == false) && e.west() == false) && (e.examined() == false)){
						count++;
						System.out.println("East is accessible-unexamined");
					}
			}
			if(count == 0) return null;
			do{
				randcell = Array[generator.nextInt(Array.length)];
				if(randcell == n){
					if(((north() == false) && randcell.south() == false) && (randcell.examined() == false)){
						flag = true;
						System.out.println("Going north now!");
					}
					System.out.println("Randcell is north");
				}
				else if(randcell == s){
					if(((south() == false) && randcell.north() == false) && (randcell.examined() == false)){
				 		flag = true;
						System.out.println("Going south now!");
					}
					System.out.println("Randcell is south");
				}
				else if(randcell == w){
					if(((west() == false) && randcell.east() == false) && (randcell.examined() == false)){
 						flag = true;
						System.out.println("Going west now!");
					}
					System.out.println("Randcell is west");
				}
				else if(randcell == e){
					if(((east() == false) && randcell.west() == false) && (randcell.examined() == false)){
 						flag = true;
						System.out.println("Going east now!");
					}
					System.out.println("Randcell is east");
				}
			}while(flag == false);


			return randcell;
		

	}
 */  
 
}
