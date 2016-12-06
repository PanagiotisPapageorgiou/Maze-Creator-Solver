
public class Queue {
   private int maxSize;
   private MazeCell[] queArray;
   private int front;
   private int rear;
   private int nItems;
//--------------------------------------------------------------
   public Queue(int s)          // constructor
      {
      maxSize = s;
      queArray = new MazeCell[maxSize];
      front = 0;
      rear = -1;
      nItems = 0;
      }
//--------------------------------------------------------------
   public void insert(MazeCell j)   // put item at rear of queue
      {
      if(rear == maxSize-1)         // deal with wraparound
         rear = -1;
      queArray[++rear] = j;         // increment rear and insert
      nItems++;                     // one more item
      }
//--------------------------------------------------------------
   public MazeCell remove()         // take item from front of queue
      {
      MazeCell temp = queArray[front++]; // get value and incr front
      if(front == maxSize)           // deal with wraparound
         front = 0;
      nItems--;                      // one less item
      return temp;
      }
//--------------------------------------------------------------
   public MazeCell peekFront()      // peek at front of queue
      {
      return queArray[front];
      }
//--------------------------------------------------------------
   public boolean isEmpty()    // true if queue is empty
      {
      return (nItems==0);
      }
//--------------------------------------------------------------
   public boolean isFull()     // true if queue is full
      {
      return (nItems==maxSize);
      }
//--------------------------------------------------------------
   public int size()           // number of items in queue
      {
      return nItems;
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
