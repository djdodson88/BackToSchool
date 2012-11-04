package minigames.warehouse;

/**
 * Stack for 2D arrays
 * @author Cagatay Sahin
 */
public class UndoStack {
    
    private int[][][] stack;
    private int capacity;
    private int size;
    private int head;
        
    //Takes capacity as argument
    public UndoStack(int _capacity, int numXLength, int numYLength){
        
        capacity = _capacity;
        size = 0;
        head = capacity - 1;
        stack = new int[capacity][numXLength][numYLength];
    }
    
    public int getCapacity(){
        return capacity;
    }
    public int getSize(){
        return size;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public boolean isFull(){
        return size == capacity;
    }
    
    public void push (int[][] nums){
                
        if(!isFull())
            size++;
        //Use modulo operation for circular array
        head = (head + 1) % capacity;
        
        //Deep copy
        for(int i = 0; i < nums.length; i++ )
            System.arraycopy(nums[i], 0, stack[head][i], 0, nums[i].length);
                
    }
    
    //Precondition: this method is called only if stack is not empty
    public int[][] pop(){        
        size--;        
        int[][] temp = new int[(stack[head]).length][(stack[head][0]).length];
        for(int i = 0; i < (stack[head]).length; i++ )
            System.arraycopy((stack[head])[i], 0, temp[i], 0, (stack[head])[i].length); 
        
        //Aviod negative values in modulo
        head = (head - 1 + capacity ) % capacity;
        return temp;            
    }    
}
