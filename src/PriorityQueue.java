
public class PriorityQueue {
    private int maxSize;
    private MazeCell[] queArray;
    private int nItems;
    private int front;
    private int rear;
    
    public PriorityQueue(int s){
    
      maxSize = s;
      queArray = new MazeCell[maxSize];
      front = 0;
      rear = -1;
      nItems = 0;
	}
    
    public void insert(MazeCell item, MazeCell current){
        int j;

		if(item == null) System.out.println("PROBLEEEEEEEEEM");

      	if(rear == maxSize-1){         // deal with wraparound
        	rear = -1;
			//System.out.println("PROBLEEEEEEEEEM2");
		}
        if (nItems==0){
            queArray[++rear]= item;
			//System.out.println("yoo");
			nItems++;
			//System.out.printf("rear is now %d\n",rear);
        }
        else{
			//System.out.println("PROBLEEEEEEEEEM3");
			//System.out.printf("j is %d\n",rear);
            for(j=rear; j>=front; j--){
				//System.out.println("PROBLEEEEEEEEEM4");
				//System.out.printf("j is %d\n",j);
                if(calculateDistance(item,current)<calculateDistance(queArray[j],current)){//epeidh ola ta kelia ston laburintho exoun apostasi ena
					System.out.println("Let's give priority to the closer ones!");
                    queArray[j+1]=queArray[j];                                              //ousiastika den tha ekteleitai pote auto to if
                }                                                                           //dhladh h UCS se auth th periptvsh tha douleuei san BFS.
                else{
					//System.out.println("PROBLEEEEEEEEEM6");
                    break;
                }
            }
			//System.out.println("yoo2");
            queArray[++rear]=item;
            nItems++;
	   }
                
    }
    
    public MazeCell remove(){
      MazeCell temp = queArray[front++]; // get value and incr front
      if(front == maxSize)           // deal with wraparound
         front = 0;
      nItems--;                      // one less item
      return temp;
    }
    
    public MazeCell peekFront(){
        return queArray[front];
    }
    
    public boolean isEmpty(){
        return (nItems == 0);
    }
    
    public boolean isFull(){
        return (nItems == maxSize);
    }
    
    public int calculateDistance(MazeCell A, MazeCell B){
       
        int colOfA = A.get_MazeCellcol();
        int rowOfA = A.get_MazeCellrow();
        int colOfB = B.get_MazeCellcol();
        int rowOfB = B.get_MazeCellrow();
        int distanceRows = rowOfB- rowOfA;
        int distanceCols = colOfB- colOfA;
        
		//if(A == null) System.out.println("PROBLEEEEEEEEEM7");
		//if(B == null) System.out.println("PROBLEEEM8");

        if(distanceRows<0)distanceRows=rowOfA-rowOfB;
        if(distanceCols<0)distanceCols=colOfA-colOfB;
        
         if((distanceRows == 0) || (distanceCols == 0)){
			if((distanceRows == 0) && (distanceCols == 0)){ 
				//System.out.println("PROBLEEEM8");
                return 0;
			}
         	else if(distanceRows == 0){
				//System.out.println("PROBLEEEM9");
                return distanceCols;
			}
         	else {
				//System.out.printf("distanceRows is %d\n",distanceRows);
                return distanceRows;
			}
		 }
         else{
         	if(distanceRows < distanceCols){
				//System.out.println("PROBLEEEM11");
            	return distanceRows;
			}
           	else{
				//System.out.println("PROBLEEEM12");
            	return distanceCols;
			}
		}
	}

	public void printQueue(){
		if(isEmpty()){
			System.out.println("Nothing to print here!");
			return;
		}
		
		System.out.print("QUEUE: ");
		for(int i = front; i <= rear; i++)
			System.out.printf("[%d.%d] ",queArray[i].get_MazeCellrow(),queArray[i].get_MazeCellcol());
		System.out.printf("\n");
	}

}
