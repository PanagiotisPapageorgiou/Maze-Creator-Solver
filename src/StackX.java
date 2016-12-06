public class StackX {

   
   private MazeCell[] st;
   private int top;
// ------------------------------------------------------------
   public StackX(int size)           // constructor
      {
      st = new MazeCell[size];// make array
      //System.out.printf("To size mpoumpouna einai...tatatataaaaaa : %d\n",size);
      top = -1;
      }
// ------------------------------------------------------------
   public void push(MazeCell j)   // put item on stack
      { 
       top++;
       st[top] = j;        
   }
// ------------------------------------------------------------
   public MazeCell pop()          // take item off stack
      { return st[top--]; }
// ------------------------------------------------------------
   public MazeCell peek()         // peek at top of stack
      { return st[top]; }
// ------------------------------------------------------------
   public boolean isEmpty()  // true if nothing on stack
      { return (top == -1); }
// ------------------------------------------------------------

	public void printStack(){
		int line = 0;

		System.out.println("Going to print the Stack\n!");
		if(top == -1)
			System.out.println("Nothing to print here!");
		else if (top == 0){
				System.out.println("---------\n");
				System.out.printf("|%3d.%3d|\n",st[0].get_MazeCellrow(),st[0].get_MazeCellcol());
				System.out.println("=========\n");
		}
		for(int i = 0; i < top; i++){
			System.out.println("---------\n");
			System.out.printf("|%3d.%3d|\n",st[i].get_MazeCellrow(),st[i].get_MazeCellcol());
			System.out.println("=========\n");
		}
	}

}  // end class StackX
