// This class represents one spot on the Minesweeper board
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class GridPanel extends JPanel {

	private int boardNumber=0; // 0 for a blank space, -1 for a mine
	private int rightClickState;
	private boolean hasNotBeenLeftClicked = true;
	private JLabel numImage, flagImage, qMarkImage, bombImage, redBombImage;
	private ImageIcon num, flag, qmark, bomb, redbomb;
	private MouseTracker mouseTracker = new MouseTracker();
	private boolean blanked = false;
    private Color bg;

	//Constructor
	/*
	Post: loads in several images that could be displayed in the panel and hides them
	*/
	public GridPanel() {
		
		bg = new Color(getBackground().getRGB());
        addMouseListener(mouseTracker);
		
		rightClickState = 0;

		setBorder(BorderFactory.createRaisedBevelBorder());
		((FlowLayout)this.getLayout()).setVgap(1);

		numImage = new JLabel();
		flagImage = new JLabel();
          qMarkImage = new JLabel();
          bombImage = new JLabel();
          redBombImage = new JLabel();	

		flag = new ImageIcon("images/flag.png");
		flagImage.setIcon(flag);
		qmark = new ImageIcon("images/qmark.png");
		qMarkImage.setIcon(qmark);
		bomb = new ImageIcon("images/bomb.png");
		bombImage.setIcon(bomb);
		redbomb = new ImageIcon("images/redbomb.png");
		redBombImage.setIcon(redbomb);
		
		add(flagImage);
		add(qMarkImage);
		add(bombImage);
		add(redBombImage);
		
		flagImage.setVisible(false);
		qMarkImage.setVisible(false);
		bombImage.setVisible(false);
		redBombImage.setVisible(false);
	}
	
	/*
	Post: shows a mine
	*/
	public void showMine() {
		if(hasNotBeenLeftClicked) {
			setBorder(BorderFactory.createLineBorder(Color.gray));
			bombImage.setVisible(true);
		}
	}
	/*
	Post: assigns a number picture to each grid if necessary
	*/
	public void setImage() {
		switch(boardNumber) {

			case 1: num = new ImageIcon("images/1.png"); break;
            case 2: num = new ImageIcon("images/2.png"); break;
            case 3: num = new ImageIcon("images/3.png"); break;
            case 4: num = new ImageIcon("images/4.png"); break;
            case 5: num = new ImageIcon("images/5.png"); break;
            case 6: num = new ImageIcon("images/6.png"); break;
            case 7: num = new ImageIcon("images/7.png"); break;
            case 8: num = new ImageIcon("images/8.png"); break;
          }
		numImage.setIcon(num);
		add(numImage);
		numImage.setVisible(false);
	}
	
	/*
	Post: resets everything to its original value
	*/
	public void reset() {

		setBackground(bg);
        MinesweeperTopBar.updateFlagsLeft();
		numImage.setVisible(false);
		flagImage.setVisible(false);
        qMarkImage.setVisible(false);
        bombImage.setVisible(false);
        redBombImage.setVisible(false);
		setBorder(BorderFactory.createRaisedBevelBorder());
		boardNumber=0;
		rightClickState=0;
		blanked=false;
		hasNotBeenLeftClicked = true;
		MinesweeperGrid.setNumOfFlags(0);
		MinesweeperGrid.setNewGame();
		setImage();
	}

	/*
	Post: sets board number for a space
	*/
	public void setBoardNumber(int num) {

		boardNumber = num;
	}
	/*
	Post: adds one to the board number
	*/
	public void incrementBoardNumber() {

		boardNumber++;
	}

	/*
	Post: gets the board number
	*/
	public int getBoardNumber() {

		return this.boardNumber;
	}
	
	/*
	Post: return if it has been clicked or not
	*/
	public boolean hasBeenClicked() {

		return !hasNotBeenLeftClicked;
	}

	/*
	Post: toggles if it has been clicked or not
	*/
	public void setToggleClicked() {

		if(hasNotBeenLeftClicked)
			hasNotBeenLeftClicked=false;
		else
			hasNotBeenLeftClicked=true;
	}
	
	/*
	Post: returns if a space has been blanked out through the recursive algorithm
	*/
	public boolean isBlankedOut() {

		return blanked;
	}

	/*
	Post: blanks a space
	*/
	public void showBlank() {

		if(!blanked && hasNotBeenLeftClicked && !flagImage.isVisible()) {
			setBorder(BorderFactory.createLineBorder(Color.gray));
			numImage.setVisible(false);
			hasNotBeenLeftClicked = false;
			blanked=true;
		}
		else if(flagImage.isVisible()){
			numImage.setVisible(false);
			hasNotBeenLeftClicked = true;
			blanked=false;
		}
	}

	/*
	Post: toggles if it has been blanked or not
	*/
	public void setBlankFactor() {

		if(!blanked)
			blanked=true;
		else blanked=false;
	}
		

	/*
	Post: reveals what is in that spot on the grid and if it is blank, then it will reveal all bordering blanks
	*/
	public void leftClick() {

		if(!MinesweeperGrid.gameOver()) {
		if (!(flagImage.isVisible()) && boardNumber!=0) {
			if(qMarkImage.isVisible())
				qMarkImage.setVisible(false);
			numImage.setVisible(true);
			hasNotBeenLeftClicked = false;
		}

		if (getBoardNumber()==0) {
			if(Minesweeper.getDiffToggle()==1)
                    MinesweeperGrid.expandEasySpaces(this);
               else if(Minesweeper.getDiffToggle()==2)
                    MinesweeperGrid.expandMediumSpaces(this);
               else
                    MinesweeperGrid.expandHardSpaces(this);
		}
		else if (getBoardNumber() == -1 && !flagImage.isVisible()) {
			setBackground(Color.red);
            redBombImage.setVisible(true);
			if(Minesweeper.getDiffToggle()==1)
				MinesweeperGrid.revealEasyMines();
			else if(Minesweeper.getDiffToggle()==2)
				MinesweeperGrid.revealMediumMines();
			else
				MinesweeperGrid.revealHardMines();
		}
		else numImage.setVisible(true);
		}
        setBorder(BorderFactory.createLineBorder(Color.gray));
	}
	
	/*
	Post: either shows a flag, a question mark, or a blank space
	0 is blank
	1 is flagged
	2 is qmarked
	*/
	public void rightClick() {

		if(hasNotBeenLeftClicked && !MinesweeperGrid.gameOver()) {
			if (rightClickState==0) {
				if(boardNumber!=0 && boardNumber!=-1)
					numImage.setVisible(false);
				flagImage.setVisible(true);
				MinesweeperGrid.incrementNumOfFlags();
				MinesweeperTopBar.updateFlagsLeft();
				rightClickState=1;
			}	
			else if(rightClickState==1) {
				if(boardNumber!=0 && boardNumber!=-1)
					numImage.setVisible(false);
				flagImage.setVisible(false);
				qMarkImage.setVisible(true);
				MinesweeperGrid.decrementNumOfFlags();
				MinesweeperTopBar.updateFlagsLeft();
				rightClickState=2;
			}
			else {
				if(boardNumber!=0 && boardNumber!=-1)
					numImage.setVisible(false);
				qMarkImage.setVisible(false);
				rightClickState=0;

			}
		}
	}


	// mouse listener for both the left and rigth mouse buttonsi
	private class MouseTracker implements MouseListener {

          public void mousePressed(MouseEvent event) {}
		/*
		Post: calls either left or right click according to which button was pressed
		*/
          public void mouseClicked(MouseEvent event) {
		
			if(event.getButton() == MouseEvent.BUTTON1) {
                    leftClick();
               }
               else {
                    rightClick();
               }

		}
          public void mouseReleased(MouseEvent event) {}
          public void mouseEntered(MouseEvent event) {}
          public void mouseExited(MouseEvent event) {}
     }
}
