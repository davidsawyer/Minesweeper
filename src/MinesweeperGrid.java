// this class represents a gameplay grid that handles actions having to do with actual gameplay
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MinesweeperGrid extends JPanel {
    
    private GridLayout gridLayout;
    private JMenuBar menuBar;
    private JMenu file, difficulty, help;
    private JMenuItem exit, easy, medium, hard, about;
    private static GridPanel[][] easyGrid, mediumGrid, hardGrid;
    private static boolean gameOver = false;
    private static int numOfFlags = 0;
    
    // Constructor: sets window  by initializing one of the three initializing methods
    public MinesweeperGrid() {
        
        createMenuBar();
        
        if(Minesweeper.getDiffToggle()==1)
            initEasy();
        else if(Minesweeper.getDiffToggle()==2)
            initMedium();
        else 
            initHard();
    }
    
    // Easy Grid
    // creates a 8 x 8 grid
    public void initEasy() {
        if(easyGrid==null) {
            setPreferredSize(new Dimension(160,160));
            gridLayout = new GridLayout(8,8);
            setLayout(gridLayout);
            easyGrid = new GridPanel[8][8];
            for(int i=0; i<8; i++) {
                for(int j=0; j<8; j++) {
                    easyGrid[i][j] = new GridPanel();
                    add(easyGrid[i][j]);
                }
            }
        }
        
        else if(easyGrid!=null) {
            
            for(int i=0; i<8; i++) {
                for(int j=0; j<8; j++) {
                    easyGrid[i][j].reset();
                }
            }
        }
        
        assignMinesAndNumbers(8, 8, 10);
        
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                easyGrid[i][j].setImage();
            }
        }
        updateUI();
        
    }
    
    // medium Grid
    // creates a 16 x 16 grid
    public void initMedium() {
        if(mediumGrid==null) {
            setPreferredSize(new Dimension(320,320));
            gridLayout = new GridLayout(16,16);
            setLayout(gridLayout);
            mediumGrid = new GridPanel[16][16];
            for(int i=0; i<16; i++) {
                for(int j=0; j<16; j++) {
                    
                    mediumGrid[i][j] = new GridPanel();
                    add(mediumGrid[i][j]);
                }
            }
        }
        
        else if(mediumGrid!=null) {
            
            for(int i=0; i<16; i++) {
                for(int j=0; j<16; j++) {
                    mediumGrid[i][j].reset();
                }
            }
        }
        
        assignMinesAndNumbers(16, 16, 40);
        
        for(int i=0; i<16; i++) {
            for(int j=0; j<16; j++) {
                mediumGrid[i][j].setImage();
            }
        }
        updateUI();
    }
    
    // Hard Grid
    // creates 24 x 20
    public void initHard() {
        if(hardGrid==null) {
            setPreferredSize(new Dimension(480,400));
            gridLayout = new GridLayout(20,24);
            setLayout(gridLayout);
            hardGrid = new GridPanel[20][24];
            for(int i=0; i<20; i++) {
                for(int j=0; j<24; j++) {
                    
                    hardGrid[i][j] = new GridPanel();
                    add(hardGrid[i][j]);
                }
            }
        }
        else if(hardGrid!=null) {
            for(int i=0; i<20; i++) {
                for(int j=0; j<24; j++) {
                    hardGrid[i][j].reset();
                }
            }
        }
        
        assignMinesAndNumbers(20, 24, 99);
        
        for(int i=0; i<20; i++) {
            for(int j=0; j<24; j++) {
                hardGrid[i][j].setImage();
            }
        }
        updateUI();
        
    }
    
    public void lookAtSpace(int x, int y, int gridXs, int gridYs) {
        
        if (Minesweeper.getDiffToggle()==1) {
            if (x>=0   && y>=0 && x<gridXs && y<gridYs && easyGrid[x][y].getBoardNumber()!=-1) 
                easyGrid[x][y].incrementBoardNumber();
        }
        else if (Minesweeper.getDiffToggle()==2) {
            if (x>=0   && y>=0 && x<gridXs && y<gridYs && mediumGrid[x][y].getBoardNumber()!=-1) 
                mediumGrid[x][y].incrementBoardNumber();
        }
        else if (Minesweeper.getDiffToggle()==3){
            if (x>=0   && y>=0 && x<gridXs && y<gridYs && hardGrid[x][y].getBoardNumber()!=-1) 
                hardGrid[x][y].incrementBoardNumber();
        }
        
    }
    
    public void assignMinesAndNumbers(int gridX, int gridY, int numOfMines) {
        
        int x, y;
        
        if (Minesweeper.getDiffToggle()==1) {
            for (int i = 0; i<numOfMines; i++) {
                
                x = (int)(Math.random()*gridX);
                y = (int)(Math.random()*gridY);
                
                if (easyGrid[x][y].getBoardNumber() != -1) {
                    easyGrid[x][y].setBoardNumber(-1);
                    lookAtSpace(x, y-1, gridX, gridY);
                    lookAtSpace(x+1, y-1, gridX, gridY);
                    lookAtSpace(x-1, y-1, gridX, gridY);
                    lookAtSpace(x, y+1, gridX, gridY);
                    lookAtSpace(x+1, y+1, gridX, gridY);
                    lookAtSpace(x-1, y+1, gridX, gridY);
                    lookAtSpace(x+1, y, gridX, gridY);
                    lookAtSpace(x-1, y, gridX, gridY);
                }
                else
                    i--;
            }
        }
        else if (Minesweeper.getDiffToggle()==2) {
            for (int i = 0; i<numOfMines; i++) {
                
                x = (int)(Math.random()*gridX);
                y = (int)(Math.random()*gridY);
                
                if (mediumGrid[x][y].getBoardNumber() != -1) {
                    mediumGrid[x][y].setBoardNumber(-1);
                    lookAtSpace(x, y-1, gridX, gridY);
                    lookAtSpace(x+1, y-1, gridX, gridY);
                    lookAtSpace(x-1, y-1, gridX, gridY);
                    lookAtSpace(x, y+1, gridX, gridY);
                    lookAtSpace(x+1, y+1, gridX, gridY);
                    lookAtSpace(x-1, y+1, gridX, gridY);
                    lookAtSpace(x+1, y, gridX, gridY);
                    lookAtSpace(x-1, y, gridX, gridY);
                }
                else
                    i--;
            }
        }
        else if (Minesweeper.getDiffToggle()==3){
            for (int i = 0; i<numOfMines; i++) {
                
                x = (int)(Math.random()*gridX);
                y = (int)(Math.random()*gridY);
                
                if (hardGrid[x][y].getBoardNumber() != -1) {
                    hardGrid[x][y].setBoardNumber(-1);
                    lookAtSpace(x, y-1, gridX, gridY);
                    lookAtSpace(x+1, y-1, gridX, gridY);
                    lookAtSpace(x-1, y-1, gridX, gridY);
                    lookAtSpace(x, y+1, gridX, gridY);
                    lookAtSpace(x+1, y+1, gridX, gridY);
                    lookAtSpace(x-1, y+1, gridX, gridY);
                    lookAtSpace(x+1, y, gridX, gridY);
                    lookAtSpace(x-1, y, gridX, gridY);
                }
                else
                    i--;
            }
        }
    }
    
    // Post: returns how many flags are left (can be negative)
    public static int numOfFlagsLeft() {
        
        if(Minesweeper.getDiffToggle()==1)
            return 10-numOfFlags;
        else if(Minesweeper.getDiffToggle()==2)
            return 40-numOfFlags;
        else
            return 99-numOfFlags;
    }
    
    // Post: sets number of flags
    public static void setNumOfFlags(int num) {
        
        numOfFlags = num;
    }
    
    // Post: increase number of flags by one
    public static void incrementNumOfFlags() {
        
        numOfFlags++;
    }
    
    // Post: decrease number of flags by one
    public static void decrementNumOfFlags() {
        
        numOfFlags--;
    }
    
    // Post: ses game over to true so the player can't click anything on the board
    public static void setNewGame() {
        
        gameOver=false;
    }
    
    // Post: returns the state of gameOver
    public static boolean gameOver() {
        
        return gameOver;
    }
    
    // Pre: a mine space was clicked
    // Post: shows all other mines in a 8 x 8
    public static void revealEasyMines() {
        
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if (easyGrid[i][j].getBoardNumber()==-1)
                    easyGrid[i][j].showMine();
            }
        }
        gameOver=true;
    }
    
    // Pre: a mine space was clicked
    // Post: shows all other mines in a 16*16
    public static void revealMediumMines() {
        
        for(int i=0; i<16; i++) {
            for(int j=0; j<16; j++) {
                if (mediumGrid[i][j].getBoardNumber()==-1)
                    mediumGrid[i][j].showMine();
            }
        }
        gameOver=true;
    }
    
    // Pre: a mine space was clicked
    // Post: shows all other mines in a 24*20
    public static void revealHardMines() {
        
        for(int i=0; i<20; i++) {
            for(int j=0; j<24; j++) {
                if (hardGrid[i][j].getBoardNumber()==-1)
                    hardGrid[i][j].showMine();
            }
        }
        gameOver=true;
    }
    
    private static void checkEasySpace(int x, int y) {
        
        if (x>=0   && y>=0 && x<8 && y<8){
            if (x>=0   && y>=0 && x<8 && y<8 && easyGrid[x][y].getBoardNumber()==0 && !easyGrid[x][y].isBlankedOut()) {
                if(!easyGrid[x][y].isBlankedOut()) {
                    easyGrid[x][y].showBlank();
                    easyGrid[x][y].setToggleClicked();
                    easyGrid[x][y].setBlankFactor();
                    expandEasySpaces(easyGrid[x][y]);
                }
            }
            else if (easyGrid[x][y].getBoardNumber()!=0)
                easyGrid[x][y].leftClick();
        }
    }
    
    private static void checkMediumSpace(int x, int y) {
        
        if (x>=0   && y>=0 && x<16 && y<16){
            if (x>=0   && y>=0 && x<16 && y<16 && mediumGrid[x][y].getBoardNumber()==0 && !mediumGrid[x][y].isBlankedOut()) {
                if(!mediumGrid[x][y].isBlankedOut()) {
                    mediumGrid[x][y].showBlank();
                    mediumGrid[x][y].setToggleClicked();
                    mediumGrid[x][y].setBlankFactor();
                    expandMediumSpaces(mediumGrid[x][y]);
                }
            }
            else if (mediumGrid[x][y].getBoardNumber()!=0)
                mediumGrid[x][y].leftClick();
        }
    }
    
    private static void checkHardSpace(int x, int y) {
        
        if (x>=0   && y>=0 && x<20 && y<24){
            if (x>=0   && y>=0 && x<20 && y<24 && hardGrid[x][y].getBoardNumber()==0 && !hardGrid[x][y].isBlankedOut()) {
                if(!hardGrid[x][y].isBlankedOut()) {
                    hardGrid[x][y].showBlank();
                    hardGrid[x][y].setToggleClicked();
                    hardGrid[x][y].setBlankFactor();
                    expandHardSpaces(hardGrid[x][y]);
                }
            }
            else if (hardGrid[x][y].getBoardNumber()!=0)
                hardGrid[x][y].leftClick();
        }
    }
    
    // Pre: a blank spot is left clicked on easy
    // Post: recursively reveals adjacent blank spaces up until it displays numbers
    public static void expandEasySpaces(GridPanel g) {
        // find the provided GridPanel g  
        int i=0, j=0, x=-2, y=-2;
    outerloop:
        for(i=0; i<8; i++) {
            for(j=0; j<8; j++) {
                if (easyGrid[i][j].equals(g)) {
                    x=i;
                    y=j;
                    easyGrid[i][j].showBlank();
                    break outerloop;
                }
            }
        }
        
        checkEasySpace(x, y-1);
        checkEasySpace(x, y+1);
        checkEasySpace(x+1, y);
        checkEasySpace(x-1, y);
        checkEasySpace(x+1, y+1);
        checkEasySpace(x-1, y-1);
        checkEasySpace(x+1, y-1);
        checkEasySpace(x-1, y+1);
    }
    
    // Pre: a blank spot is left clicked on medium
    // Post: recursively reveals adjacent blank spaces up until it displays numbers
    public static void expandMediumSpaces(GridPanel g) {
        // find the provided GridPanel g
        int i=0, j=0, x=-2, y=-2;
    outerloop:
        for(i=0; i<16; i++) {
            for(j=0; j<16; j++) {
                if (mediumGrid[i][j].equals(g)) {
                    x=i;
                    y=j;
                    mediumGrid[i][j].showBlank();
                    break outerloop;
                }
            }
        }
        
        checkMediumSpace(x, y-1);
        checkMediumSpace(x, y+1);
        checkMediumSpace(x+1, y);
        checkMediumSpace(x-1, y);
        checkMediumSpace(x+1, y+1);
        checkMediumSpace(x-1, y-1);
        checkMediumSpace(x+1, y-1);
        checkMediumSpace(x-1, y+1);
    }
    
    // Pre: a blank spot is left clicked in hard
    // Post: recursively reveals adjacent blank spaces up until it displays numbers
    public static void expandHardSpaces(GridPanel g) {
        // find the provided GridPanel g
        int i=0, j=0, x=-2, y=-2;
    outerloop:
        for(i=0; i<20; i++) {
            for(j=0; j<24; j++) {
                if (hardGrid[i][j].equals(g)) {
                    x=i;
                    y=j;
                    hardGrid[i][j].showBlank();
                    break outerloop;
                }
            }
        }
        
        checkHardSpace(x, y-1);
        checkHardSpace(x, y+1);
        checkHardSpace(x+1, y);
        checkHardSpace(x-1, y);
        checkHardSpace(x+1, y+1);
        checkHardSpace(x-1, y-1);
        checkHardSpace(x+1, y-1);
        checkHardSpace(x-1, y+1);
    }
    
    // Creates the menu bar for the frame
    public JMenuBar createMenuBar() {
        
        // Menu Bar
        menuBar = new JMenuBar();
        
        // File Menu
        file = new JMenu("File");
        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.META_MASK));
        exit.addActionListener(new MenuListener());
        menuBar.add(file);
        file.add(exit);
        
        //Difficulty Menu
        difficulty = new JMenu("Difficulty");
        easy = new JMenuItem("Easy");
        medium = new JMenuItem("Medium");
        hard = new JMenuItem("Hard");
        easy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.META_MASK));
        medium.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.META_MASK));
        hard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.META_MASK));
        easy.addActionListener(new MenuListener());
        medium.addActionListener(new MenuListener());
        hard.addActionListener(new MenuListener());
        menuBar.add(difficulty);
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
        
        //Help Menu
        help = new JMenu("Help");
        about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.META_MASK));
        about.addActionListener(new MenuListener());
        menuBar.add(help);
        help.add(about);
        
        return menuBar;
    }
    
    // MenuListener class listens for a chosen menu item and executes the appropriate event
    private class MenuListener implements ActionListener {
        
        // exits on exit, initializes easy on easy, initializes medium on medium, initializes hard on hard, and displays an about window on about
        public void actionPerformed (ActionEvent event) {
            
            if (event.getSource() == exit)
                System.exit(0);
            else if (event.getSource() == easy) {
                Minesweeper.toggle(1);
                initEasy();
            }
            else if (event.getSource() == medium) {
                Minesweeper.toggle(2);
                initMedium();
            }
            else if (event.getSource() == hard) {
                Minesweeper.toggle(3);
                initHard();
            }
            else if (event.getSource() == about)
                Minesweeper.displayAbout();
        }
    }
}
