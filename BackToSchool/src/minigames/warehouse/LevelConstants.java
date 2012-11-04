package minigames.warehouse;

/**
 *
 * @author Cagatay Sahin
 * In the 2D num arrays, 0: ground, 1: wall, 2: box, 3: player.
 * In the 2D dots arrays, 0: un-dotted coordinate, 1: dotted coordinate.
 * getSpriteX(int lvl): returns X coordinate of player at the very beginning for each level.
 * getSpriteY(int lvl): returns Y coordinate of player at the very beginning for each level.
 * getNumBoxes(int lvl): returns number of boxes in each level.
 * getYerX(int lvl): returns the X coordinate of "the Game Viewport" to be pinned on canvas.
 * getYerY(int lvl): returns the Y coordinate of "the Game Viewport" to be pinned on canvas.
 * Note: a dotted coordinate should match either ground, box, or the player.
 */
public class LevelConstants {
    
    
    final static int [][] nums1 ={ 
                            {1,1,1,1,1,1,1,1,1},
                            {1,3,0,0,1,1,1,1,1},
                            {1,0,2,2,1,1,1,0,1},
                            {1,0,2,0,1,1,1,0,1},
                            {1,1,1,0,1,1,1,0,1},
                            {1,1,1,0,0,0,0,0,1},
                            {1,1,0,0,0,1,0,0,1},
                            {1,1,0,0,0,1,1,1,1},
                            {1,1,1,1,1,1,1,1,1}};
    
 
    final static int [][] dots1 ={ 
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,1,0},
                            {0,0,0,0,0,0,0,1,0},
                            {0,0,0,0,0,0,0,1,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0}};
    
    
    final static int[][] nums2 ={ 
                            {1,1,1,1,1,1,1,1,1,1},
                            {1,1,0,0,0,0,0,1,1,1},
                            {1,1,2,1,1,1,0,0,0,1},
                            {1,0,3,0,2,0,0,2,0,1},
                            {1,0,0,0,0,0,2,0,1,1},
                            {1,1,0,0,1,0,0,0,1,1},
                            {1,1,1,1,1,1,1,1,1,1},                            
                            };
    
    final static int[][] dots2 ={ 
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,1,1,0,0,0,0,0,0},
                            {0,0,1,1,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0}};
    
    final static int[][] nums3 ={
                            {1,1,1,1,1,1},
                            {1,1,0,0,1,1},
                            {1,3,2,0,1,1},
                            {1,1,2,0,1,1},
                            {1,1,0,2,0,1},
                            {1,0,2,0,0,1},
                            {1,0,0,0,0,1},
                            {1,1,1,1,1,1}};
    final static int[][] dots3 ={
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,1,0,0,0,0},
                            {0,1,1,0,1,0},
                            {0,0,0,0,0,0}};
    
    final static int[][] nums4 ={
                            {1,1,1,1,1,1,1,1},
                            {1,1,3,0,1,1,1,1},
                            {1,1,0,2,0,0,1,1},
                            {1,1,1,0,1,0,1,1},
                            {1,0,1,0,1,0,0,1},
                            {1,0,2,0,0,1,0,1},
                            {1,0,0,0,0,2,0,1},
                            {1,1,1,1,1,1,1,1}};
            
    final static int[][] dots4 ={
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,1,0,0,0,0,0,0},
                            {0,1,0,0,0,0,0,0},
                            {0,1,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0}};
    
    final static int[][] nums5 ={
                            {1,1,1,1,1,1,1,1,1,1,1,1,1},
                            {1,1,1,1,0,0,0,0,0,1,1,1,1},
                            {1,0,0,0,0,1,1,1,0,1,1,1,1},
                            {1,0,1,0,1,0,0,0,0,1,1,1,1},
                            {1,0,1,0,2,0,2,1,0,0,1,1,1},
                            {1,0,1,0,0,2,0,0,1,0,1,1,1},
                            {1,0,0,1,2,0,2,0,1,0,1,1,1},
                            {1,0,0,0,0,0,1,0,1,0,1,1,1},
                            {1,1,0,1,1,1,0,0,0,0,0,3,1},
                            {1,1,0,0,0,0,0,1,1,0,0,0,1},
                            {1,1,1,1,1,1,1,1,1,1,1,1,1},
                            };
    
    final static int[][] dots5 ={
                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,1,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,1,0,0,0,0},
                            {0,0,0,0,0,1,0,0,0,0,0,0,0},
                            {0,0,1,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,1,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0},
                            };
    
    final static int[][] nums6 ={
                            {1,1,1,1,1,1,1,1,1,1},
                            {1,1,1,1,0,0,1,0,3,1},
                            {1,1,1,0,0,0,1,0,0,1},
                            {1,1,1,2,0,2,0,2,0,1},
                            {1,1,1,0,2,1,1,0,0,1},
                            {1,1,1,0,2,0,1,0,1,1},
                            {1,0,0,0,0,0,0,0,1,1},
                            {1,1,1,1,1,1,1,1,1,1},
                            };
    final static int[][] dots6 ={
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,1,1,1,1,1,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            };
    
    final static int[][] nums7 ={
                            {1,1,1,1,1,1,1,1,1,1},
                            {1,1,1,1,0,0,0,0,1,1},
                            {1,1,0,0,2,1,1,0,1,1},
                            {1,0,0,2,0,2,0,0,3,1},
                            {1,0,0,0,2,0,2,0,1,1},
                            {1,1,1,1,1,1,0,0,1,1},
                            {1,1,1,1,1,1,1,1,1,1}};
    
    final static int[][] dots7 ={
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,1,0,0,0,0,0,0,0},
                            {0,1,1,0,0,0,0,0,0,0},
                            {0,1,1,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0}};
    
    final static int[][] nums8 = {
                            {1,1,1,1,1,1,1,1,1,1,1},
                            {1,1,0,0,1,1,0,0,0,1,1},
                            {1,1,0,0,0,2,0,0,0,1,1},
                            {1,1,2,0,1,1,1,0,2,1,1},
                            {1,1,0,1,0,0,0,1,0,1,1},
                            {1,1,0,1,0,0,0,1,0,1,1},
                            {1,0,2,0,0,2,0,0,2,0,1},
                            {1,0,0,0,0,0,1,0,3,0,1},
                            {1,1,1,1,1,1,1,1,1,1,1},        
                            };
            
    final static int[][] dots8 = {
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,1,1,1,0,0,0,0},
                            {0,0,0,0,1,1,1,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},       
                            };            
            
    final static int[][] nums9 = {
                            {1,1,1,1,1,1,1,1},
                            {1,1,1,0,0,0,0,1},
                            {1,1,1,2,2,2,0,1},
                            {1,3,0,2,0,0,0,1},
                            {1,0,2,0,0,0,1,1},
                            {1,1,1,1,0,0,1,1},
                            {1,1,1,1,1,1,1,1}
                            };
    final static int[][] dots9 = {
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,1,1,0,0},
                            {0,0,0,1,1,1,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0}
                            };
    
    
   final static int[][] nums10 = {
                            {1,1,1,1,1,1,1,1,1,1,1,1},  
                            {1,1,0,0,1,1,1,1,0,0,0,1},
                            {1,0,2,0,1,1,1,1,2,0,0,1},
                            {1,0,0,2,0,0,0,0,0,2,0,1},
                            {1,1,0,0,0,0,1,0,3,0,1,1},
                            {1,1,1,1,1,1,1,1,1,1,1,1}};

   final static int[][] dots10 = {
                            {0,0,0,0,0,0,0,0,0,0,0,0},  
                            {0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,1,1,1,1,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0}};

    
    public static int[][] getNums(int lvl){
        if( lvl == 1 )
            return nums1;
        if( lvl == 2 )
            return nums2;
        if( lvl == 3 )
            return nums3; 
        if( lvl == 4 )
            return nums4;
        if( lvl == 5 )
            return nums5;
        if( lvl == 6 )
            return nums6;
        if( lvl == 7 )
            return nums7;
        if( lvl == 8 )
            return nums8;
        if( lvl == 9 )
            return nums9;
        if( lvl == 10 )
            return nums10;
     
        
        return nums1;
    }
    public static int[][] getDots(int lvl){
        if( lvl == 1 )
            return dots1;
        if( lvl == 2 )
            return dots2;
        if( lvl == 3 )
            return dots3;
        if( lvl == 4 )
            return dots4;
        if( lvl == 5 )
            return dots5;
        if( lvl == 6 )
            return dots6;
        if( lvl == 7 )
            return dots7;
        if( lvl == 8 )
            return dots8;
        if( lvl == 9 )
            return dots9;
        if( lvl == 10 )
            return dots10;
        
        return dots1;
    }
    public static int getNumBoxes(int lvl){
        if(lvl == 1)
            return 3;
        if( lvl == 2)
            return 4;
        if( lvl == 3 )
            return 4;
        if( lvl == 4 )
            return 3;
        if( lvl == 5 )
            return 5;
        if( lvl == 6 )
            return 5;
        if( lvl == 7 )
            return 5;
        if( lvl == 8 )
            return 6;
        if( lvl == 9 )
            return 5;
        if( lvl == 10 )
            return 4;
        
        
       return 3;
    }
    public static int getSpriteX(int lvl){
        if(lvl == 1)
            return 1;
        if(lvl == 2)
            return 3;
        if( lvl == 3 )
            return 2;
        if( lvl == 4 )
            return 1;
        if( lvl == 5 )
            return 8;
        if( lvl == 6 )
            return 1;
        if( lvl == 7 )
            return 3;
        if( lvl == 8 )
            return 7;
        if( lvl == 9 )
            return 3; 
        if( lvl == 10 )
            return 4;
            
        
        return 1;
    }
    public static int getSpriteY(int lvl){
        if(lvl == 1)
            return 1;
        if(lvl == 2)
            return 2;
        if( lvl == 3 )
            return 1;
        if( lvl == 4 )
            return 2;
        if( lvl == 5 )
            return 11;
        if( lvl == 6 )
            return 8;
        if( lvl == 7 )
            return 8;
        if( lvl == 8 )
            return 8;
        if( lvl == 9 )
            return 1;
        if( lvl == 10 )
            return 8;
        
        
        return 1;
    }
    public static int getYerX(int lvl){
        if(lvl == 1)
            return 210;
        if(lvl == 2)
            return 200;
        if(lvl == 3)
            return 310;
        if(lvl == 4)
            return 290;        
        if(lvl == 5)
            return 120;
        if(lvl == 6)
            return 200;
        if(lvl == 8)
            return 190;
        if(lvl == 9)
            return 250;
        if(lvl == 10)
            return 170;
        return 200;
    }
    public static int getYerY(int lvl){
        if(lvl == 1)
            return 70;
        if(lvl == 2)
            return 110;
        if(lvl == 3)
            return 110;
        if(lvl == 4)
            return 90;        
        if(lvl == 5)
            return 25;
        if(lvl == 6)
            return 100;
        if(lvl == 8)
            return 100;
        if(lvl == 9)
            return 120;
        if(lvl == 10)
            return 150;
        return 150;
    }
}
