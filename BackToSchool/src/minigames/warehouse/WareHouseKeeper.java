package minigames.warehouse;
 
/**
 *
 * @author  Cagatay Sahin
 */

/*import ucigame.Image;
import ucigame.Sound;
import ucigame.Sprite;
import ucigame.Ucigame;*/
import ucigame.*;

public class WareHouseKeeper extends Ucigame
{    
    final int MAX_LEVELS = 10;
    final int MAX_UNDO = 10;
    boolean isGameStarted;
    int currentLevel;
    int hitCount;
    int numBoxes;
    int yerX;
    int yerY;
    int moveCount;
    int spriteX;
    int spriteY;
    int [][] nums;   
    int [][] dots;
    
    Sound sound1;    
    
    Sprite welldone;
    Sprite controlPanel;
    Sprite yer;
    Sprite levelLabel;
    Sprite restart;
    Sprite undo;
    Sprite countLabel;
    Sprite startScreen;
    
    Image ground;
    Image wall;
    Image box;
    Image guy;
    Image groundDot;
    Image boxDot;
    Image guyDot;
    
    UndoStack stack;
    
    public void setup()
    {            
        sound1 = getSound("sounds/harder_better_faster_stronger_8bit.mp3");
        sound1.loop();
        isGameStarted = false;
        currentLevel = 1;
        window.size(1100, 600);
        window.title("MiniGame");
        window.showFPS();
        canvas.background(222,214,173);       
        
        startScreen = makeSprite( getImage("art/warehouse/start.jpg"),1100, 600);
        startScreen.position(0, 0);
        levelLabel = makeSprite( getImage("art/warehouse/level.jpg"),118, 61);
        restart = makeButton( "Restart", getImage("art/warehouse/restart.jpg"),118, 61 );
        undo = makeButton( "Undo", getImage("art/warehouse/undo.jpg"),118, 61 );
        controlPanel = makeSprite( getImage("art/warehouse/controlPanel.jpg"), 200, 600);
        welldone = makeButton( "WellDone", getImage("art/warehouse/welldone.jpg"),118, 111 );
        countLabel = makeSprite( getImage("art/warehouse/level.jpg"),118, 61);
        
        controlPanel.position(900, 0);
        controlPanel.pin(levelLabel, 42, 20);
        controlPanel.pin(restart, 42, 100);
        controlPanel.pin(undo, 42, 390);
        controlPanel.pin(welldone, 42, 220);
        controlPanel.pin(countLabel, 42, 500);
        welldone.hide();                
                
        ground = getImage("art/warehouse/ground.jpg" );
        wall = getImage("art/warehouse/wall.jpg" );
        box = getImage("art/warehouse/box.jpg");
        guy = getImage("art/warehouse/guy.jpg" );
        groundDot = getImage("art/warehouse/groundDot.jpg" );
        boxDot = getImage("art/warehouse/boxDot.jpg");
        guyDot = getImage("art/warehouse/guyDot.jpg");
        
        initLevel(currentLevel);
        framerate(12);
    }

    public void draw()
    {        
        canvas.clear();
        
        if(isGameStarted)
        {   controlPanel.draw();
            yer.draw();
            levelLabel.putText("Level:  " + currentLevel , 40, 37);
            countLabel.putText("Moves:  " + moveCount , 23, 35);
        }
        else
        {
            startScreen.draw();
        }
    }

    public void onKeyPress()
    {
        if(isGameStarted)
        {    
            if (keyboard.isDown(keyboard.UP, keyboard.W))
            {
                if( spriteX > 0 && nums[spriteX - 1][spriteY] == 0 )
                {
                    stack.push(nums);
                    nums[spriteX][spriteY] = 0;
                    nums[spriteX - 1][spriteY] = 3;
                    spriteX--;
                    moveCount++;
                    
                }
                else if( spriteX > 1 && nums[spriteX - 1][spriteY] == 2 && nums[spriteX - 2][spriteY] == 0 )
                {
                    stack.push(nums);
                    nums[spriteX][spriteY] = 0;
                    nums[spriteX - 1][spriteY] = 3;
                    nums[spriteX - 2][spriteY] = 2;
                    spriteX--;
                    moveCount++;
                    
                }
            }

            if (keyboard.isDown(keyboard.DOWN, keyboard.S))
            {
                if( spriteX < nums.length-1 && nums[spriteX + 1][spriteY] == 0 )
                {
                    stack.push(nums);
                    nums[spriteX][spriteY] = 0;
                    nums[spriteX + 1][spriteY] = 3;
                    spriteX++;
                    moveCount++;
                    
                }
                else if( spriteX < nums.length-2 && nums[spriteX + 1][spriteY] == 2 && nums[spriteX + 2][spriteY] == 0 )
                {
                    stack.push(nums);
                    nums[spriteX][spriteY] = 0;
                    nums[spriteX + 1][spriteY] = 3;
                    nums[spriteX + 2][spriteY] = 2;
                    spriteX++;
                    moveCount++;
                    
                }
            }        

            if (keyboard.isDown(keyboard.LEFT, keyboard.A))
            {
                if( spriteY > 0 && nums[spriteX][spriteY - 1] == 0 )
                {
                    stack.push(nums);
                    nums[spriteX][spriteY] = 0;
                    nums[spriteX][spriteY-1] = 3;
                    spriteY--;
                    moveCount++;
                    
                }
                else if ( spriteY > 1 && nums[spriteX][spriteY - 1] == 2 && nums[spriteX][spriteY - 2] == 0 )
                {
                    stack.push(nums);
                    nums[spriteX][spriteY] = 0;
                    nums[spriteX][spriteY-1] = 3;
                    nums[spriteX][spriteY-2] = 2;
                    spriteY--; 
                    moveCount++;
                    
                }
            }

            if (keyboard.isDown(keyboard.RIGHT, keyboard.D))
            {            
                if( spriteY < nums[0].length-1 && nums[spriteX][spriteY + 1] == 0 )
                {
                    stack.push(nums);
                    nums[spriteX][spriteY] = 0;
                    nums[spriteX][spriteY + 1] = 3;
                    spriteY = spriteY + 1;
                    moveCount++;
                    
                }
                else if( spriteY < nums[0].length-2 && nums[spriteX][spriteY + 1] == 2 && nums[spriteX][spriteY + 2] == 0 )
                {
                    stack.push(nums);
                    nums[spriteX][spriteY] = 0;
                    nums[spriteX][spriteY + 1] = 3;
                    nums[spriteX][spriteY + 2] = 2;
                    spriteY++;
                    moveCount++;
                    
                }
            }        
        }//End of if(isGameStarted)
    
        if (keyboard.isDown(keyboard.R))
        {
            sound1.stop();
            setup();
        }
        if(keyboard.isDown(keyboard.SPACE))
        {
            if(!isGameStarted)
            {
                isGameStarted = true;
                startScreen.hide();
            }
        }
        
        if (keyboard.key() == keyboard.M)
        {
             sound1.play();
        }
        if(keyboard.key() == keyboard.N)
        {
             sound1.stop();
        }
    
        reDraw();
    }
    
    public void initLevel(int lvl){        
        
        //Create and initialize the nums array for the level
        nums = new int[(LevelConstants.getNums(lvl)).length][(LevelConstants.getNums(lvl))[0].length];
        for(int i = 0; i < (LevelConstants.getNums(lvl)).length; i++ )
            System.arraycopy((LevelConstants.getNums(lvl))[i], 0, nums[i], 0, (LevelConstants.getNums(lvl))[i].length);
        stack = new UndoStack(MAX_UNDO, nums.length, nums[0].length);        
        moveCount = 0;
        dots =  LevelConstants.getDots(lvl);
        numBoxes = LevelConstants.getNumBoxes(lvl);
        spriteX = LevelConstants.getSpriteX(lvl);
        spriteY = LevelConstants.getSpriteY(lvl);
        yer = makeTiledSprite(nums[1].length, nums.length, 50, 50);
        yer.position(LevelConstants.getYerX(lvl), LevelConstants.getYerY(lvl)); 
        reDraw();
    }
    
    //Re-Draw the Tiled Spirit (Called after modifying the nums array)
    public void reDraw(){
        hitCount = 0;
        
        for(int i = 0; i < nums.length; i++ )
        {    
            for(int j = 0; j < nums[0].length; j++ )
            {    if(nums[i][j] == 0) 
                    yer.setTiles( (dots[i][j] == 0 ? ground : groundDot),0,0,j,i);
                else if(nums[i][j] == 1)
                    yer.setTiles(wall,0,0,j,i);
                else if(nums[i][j] == 2)
                {
                    if(dots[i][j] == 0)
                        yer.setTiles( box,0,0,j,i);
                    else
                    {
                        yer.setTiles( boxDot,0,0,j,i);
                        hitCount++;
                    }                    
                }                    
                else
                    yer.setTiles( (dots[i][j] == 0 ? guy : guyDot),0,0,j,i);
            }
        }
        if(hitCount == numBoxes)
        {
            welldone.show();
        }
    }
    
    public void onClickRestart()
    {
        initLevel(currentLevel);
    }
    public void onClickWellDone()
    {
        welldone.hide();
        if(currentLevel < MAX_LEVELS )
            initLevel(++currentLevel);        
    }
      
    public void onClickUndo()
    {
        if(!stack.isEmpty())
        {
            nums = stack.pop(); 
            moveCount--;
            reDraw();
        }
        for( int i = 0; i < nums.length; i++ )
            for( int j = 0; j < nums[i].length; j++ )
                if(nums[i][j] == 3)
                {
                    spriteX = i;
                    spriteY = j;
                }
    }
}
