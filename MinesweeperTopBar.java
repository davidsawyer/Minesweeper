// This class represents the top bar (non grid) in the minesweeper window
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MinesweeperTopBar extends JPanel {

	private GridLayout layout;
	public static JLabel time, flagsLeft;
	private JLabel smiley;
	private ImageIcon smileyImage;
	private JButton smileyButton;

	/*
	Post: the Bar is created and shown, a flag counter, a restart button, and a timer
	*/
	public MinesweeperTopBar() {

		MenuListener listener = new MenuListener();
		
		layout = new GridLayout(1,3);
		setLayout(layout);

		flagsLeft = new JLabel("" + MinesweeperGrid.numOfFlagsLeft());
		time = new JLabel("time");
		
		smileyButton = new JButton();
		smileyImage = new ImageIcon("images/smiley.png");
		smiley = new JLabel();
		smiley.setIcon(smileyImage);
		
		smileyButton.add(smiley);
		smileyButton.addActionListener(listener);
		smileyButton.setBorder(BorderFactory.createRaisedBevelBorder());
		add(flagsLeft);
		add(smileyButton);
		add(time);
	}

	/*
	Pre: a flag has been shown or taken away
	Post: the counter updates
	*/
	public static void updateFlagsLeft() {

		flagsLeft.setText("" + MinesweeperGrid.numOfFlagsLeft());
		System.out.println(MinesweeperGrid.numOfFlagsLeft());
		flagsLeft.revalidate();
	}

	//Menu lisener for the reset button
	private class MenuListener implements ActionListener {

		/*
		Post: resets the current difficulty window
		*/
          public void actionPerformed (ActionEvent event) {
			if(event.getSource()==smileyButton){
			if (Minesweeper.getDiffToggle() == 1) {
                    Minesweeper.toggle(1);
               }
               else if (Minesweeper.getDiffToggle() == 2) {
                    Minesweeper.toggle(2);
               }
               else if (Minesweeper.getDiffToggle() == 3) {
                    Minesweeper.toggle(3);
               }
			}
		}
	}
}
