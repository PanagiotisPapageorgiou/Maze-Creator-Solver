import java.util.Scanner;

public class NewMain {

    //public static int Rows, Cols=50;
    //public static int row_s, col_s=1;
    public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int choice,rows,cols,startRow,startCol,endRow,endCol;
		Maze maze = null;
		boolean exit = false;
        
		System.out.println("Welcome to our Maze Solving/Creating program!");
		
		do{
			do{
				System.out.println("\n\nWhat do you want to do?");
				System.out.println("1.Create a labyrinth.");
				System.out.println("2.Solve the labyrinth.");
				System.out.println("3.Reset the labyrinth.");
				System.out.println("4.Print the labyrinth.");
				System.out.println("5.Exit.");
	
				choice = input.nextInt();

			}while((choice != 1) && (choice !=2) && (choice !=3) && (choice != 4) && (choice !=5));

			switch(choice){
				case 1:
					do{
						System.out.println("\n\nSelect a size for the labyrinth:");
						System.out.println("1.Small (5x5 start:[0,0] end: [4,4].");
						System.out.println("2.Medium (10x10 start:[0,0] end: [9,9].");
						System.out.println("3.Large (50x50 start:[0,0] end [49,49].");
						System.out.println("4.My own size and Stard/End coordinates!");
						System.out.println("5.Cancel.");
						
						choice = input.nextInt();
					}while((choice != 1) && (choice !=2) && (choice !=3) && (choice != 4) && (choice !=5));

					switch(choice){
						case 1:
							maze = new Maze(5,5,0,0,4,4);
							maze.create();
							break;
						case 2:
							maze = new Maze(10,10,0,0,9,9);
							maze.create();
							break;
						case 3:
							maze = new Maze(50,50,0,0,49,49);
							maze.create();
							break;
						case 4:
							System.out.print("Give the number of rows: ");
							do{
								rows = input.nextInt();
							}while(rows <= 0);
							System.out.print("\nGive the number of cols: ");
							do{
								cols = input.nextInt();
							}while(cols <= 0);
							System.out.print("\nGive the starting row and the starting col");
							do{
								startRow = input.nextInt();
								startCol = input.nextInt();
							}while((startRow <= 0) || (startCol <= 0));
							System.out.print("\nGive the ending row and the ending col");
							do{
								endRow = input.nextInt();
								endCol = input.nextInt();
							}while((startRow <= 0) || (startCol <= 0) || ((startRow == endRow) && (startCol == endCol)));
							break;
						case 5:
							break;
					}
					break;
				case 2:
					if(maze == null) System.out.println("You need to create a labyrinth first!");
					else maze.solveMaze(); 
					break;
				case 3:
					if(maze == null) System.out.println("No maze to reset!");
					else maze.ResetMaze();
					break;
				case 4:
					if(maze == null) System.out.println("You need to create a labyrinth first!");
					else maze.printMyLabyrinth(null);
					break;
				default:
					exit = true;
					break;
			}	

    	}while(exit == false);
	}
    
}
