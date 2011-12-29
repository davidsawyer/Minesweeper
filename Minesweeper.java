// Minesweeper: the class that groups everything together and conatins the main method
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Minesweeper {

     // Panel object so we can call that showMessageDialog() method on it
	private static JFrame frame, frame2, frame3;
	private static MinesweeperGrid bottomPanel, bottomPanel2, bottomPanel3;
	private static MinesweeperTopBar topPanel, topPanel2, topPanel3;
	private static GridBagConstraints c, c2, c3;
	private static int diffToggle = 1;

     public static void main(String[] args) {
		
		// Moves menu bar to the mac menu bar
		          System.setProperty("apple.laf.useScreenMenuBar", "true");
		//long t0 = System.currentTimeMillis();
		//MinesweeperTopBar nemo = new MinesweeperTopBar();

		// Setting up the frame
          frame = new JFrame ("Minesweeper");
          frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		frame2 = new JFrame ("Minesweeper");
		frame2.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		frame3 = new JFrame ("Minesweeper");
		frame3.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

          // Setting up the panel
          frame.setLayout(new GridBagLayout());
          c = new GridBagConstraints();

		frame2.setLayout(new GridBagLayout());
		c2 = new GridBagConstraints();
	
		frame3.setLayout(new GridBagLayout());
		c3 = new GridBagConstraints();
		
		// instantiate MinesweeperGrids
		bottomPanel = new MinesweeperGrid();
        c.fill = GridBagConstraints.SOUTH;
		c.gridx = 0;
        c.gridy = 1;
        frame.add(bottomPanel,c);
		
		diffToggle=2;
		
        bottomPanel2 = new MinesweeperGrid();
        c.fill = GridBagConstraints.SOUTH;
        c.gridx = 0;
        c.gridy = 1;
        frame2.add(bottomPanel2,c);
		
		diffToggle = 3;

         bottomPanel3 = new MinesweeperGrid();
          c.fill = GridBagConstraints.SOUTH;
          c.gridx = 0;
          c.gridy = 1;
          frame3.add(bottomPanel3,c);

          // instantiate MinesweeperTopBars
          topPanel = new MinesweeperTopBar();
          c.fill = GridBagConstraints.NORTH;
          c.gridx = 0;
          c.gridy = 0;
          frame.add(topPanel,c);

          topPanel2 = new MinesweeperTopBar();
          c2.fill = GridBagConstraints.NORTH;
          c2.gridx = 0;
          c2.gridy = 0;
          frame2.add(topPanel2,c2);

          topPanel3 = new MinesweeperTopBar();
          c3.fill = GridBagConstraints.NORTH;
          c3.gridx = 0;
          c3.gridy = 0;
          frame3.add(topPanel3,c3);

          // Disable resizing of the window and setting up the MenuBar
          frame.setResizable(false);
          frame.setJMenuBar(bottomPanel.createMenuBar());
	
		frame2.setResizable(false);
		frame2.setJMenuBar(bottomPanel2.createMenuBar());

		frame3.setResizable(false);
		frame3.setJMenuBar(bottomPanel3.createMenuBar());
         
          // Pack everything up and set it to visible
		//frame.setVisible(true);
         toggle(1);
/*
		while (true) {
		long t1 = System.currentTimeMillis();
		int seconds = (int) (t1-t0)/1000;

		
		String aString2 = Integer.toString(seconds);
		nemo.time.setText(" \tTime: "+aString2);
		System.out.println("hello");
		try
		{
		Thread.sleep(999);
		}
		catch (InterruptedException e)
		{
		// nothing to say
		}
		}*/
	}

	/*
	Pre: All the Panels have been instantiated
	Post: the appropriate window has been made visible
	*/
	public static void toggle(int num) {
		
		diffToggle = num;

		if(num==1){
			bottomPanel.initEasy();
			//frame.invalidate();
            frame.pack();
            //frame.validate();
			frame.setVisible(true);
			frame2.setVisible(false);
			frame3.setVisible(false);
		}
		
		else if(num==2){
			bottomPanel2.initMedium();
            //frame.invalidate();
            frame2.pack();

			//frame.pack();
			//frame.validate();
            frame.setVisible(false);
            frame2.setVisible(true);
            frame3.setVisible(false);
          }

		else{
			bottomPanel3.initHard();
            //frame.invalidate();
            frame3.pack();

			//frame.pack();
			//frame.validate();

            frame.setVisible(false);
            frame2.setVisible(false);
            frame3.setVisible(true);
          }

	}

	public static int getDiffToggle() {
		return diffToggle;
	}

	/*
     Post: The About window is displayed
     */
	public static void displayAbout() {

           JOptionPane.showMessageDialog(bottomPanel,
               "Minesweeper\nAuthor: David Sawyer\n\n"+
               "Minesweeper is a game in which the goal is to find\n"+
               "where all of the mines are on the board without\n"+
               "hitting them\n\n"+
               "About Minesweeper", JOptionPane.PLAIN_MESSAGE);
     }
}
