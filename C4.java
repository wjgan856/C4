import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class C4
{
  JFrame frame;
	Board board;
	public void init()
	{
		frame = new JFrame("Connect 4");
		board = new Board();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(715,675);
		frame.setResizable(false);
		
		board.setSize(715,675);
		frame.add(board, BorderLayout.CENTER);
		board.addMouseListener(board);
		board.addMouseMotionListener(board);
		
		frame.setVisible(true);
	}
	public static void main ( String [] args )
	{
		C4 explosive = new C4();
		explosive.init();
	}
}

class Board extends JPanel implements MouseListener, MouseMotionListener
{
	int win;
	int [][] board;
	int hoverColumn;
	int yloc;
	boolean turn;
	public Board()
	{
		board = new int[6][7];
		for ( int i = 0; i < 6; i++ )
		{
			for ( int j = 0; j < 7; j++ )
			{
				board[i][j] = 0;
			}
		}
		turn = true;
		win = 0;
	}
	public void place(int xloc)
	{
		int ypos = 0;
		xloc = xloc/100;
		for ( int i = 0; i < 6; i++ )
		{
			if ( board[i][xloc] == 0 )
				ypos = i;
		}
		if ( turn == true && board[ypos][xloc] == 0 && ypos < 6)
			board[ypos][xloc] = 1;
		else if ( turn == false && board[ypos][xloc] == 0 && ypos < 6)
			board[ypos][xloc] = 2;
		else 
		{
			if ( turn == false )
				turn = true;
			else
				turn = false;
		}
	}
	public void checkWin()
	{
		win = 3;
		for ( int i = 0; i < 6; i++ )
		{
			for ( int j = 0; j < 7; j++ )
			{
				if ( board[i][j] == 0)
					win = 0;
			}
		}
		
		for ( int i = 0; i < 3; i++ )
		{
			for ( int j = 0; j < 7; j++ )
			{
				if ( board[i][j] == 1 && board[i+1][j] == 1 && board[i+2][j] == 1 && board[i+3][j] == 1)
				{
					win = 1;
					board[i][j] = 3;
					board[i+1][j] = 3;
					board[i+2][j] = 3;
					board[i+3][j] = 3;
				}
				if ( board[i][j] == 2 && board[i+1][j] == 2 && board[i+2][j] == 2 && board[i+3][j] == 2)
				{
					win = 2;
					board[i][j] = 3;
					board[i+1][j] = 3;
					board[i+2][j] = 3;
					board[i+3][j] = 3;
				}
			}
		}
		
		for ( int i = 0; i < 6; i++ )
		{
			for ( int j = 0; j < 4; j++ )
			{
				if ( board[i][j] == 1 && board[i][j+1] == 1 && board[i][j+2] == 1 && board[i][j+3] == 1)
				{
					win = 1;
					board[i][j] = 3;
					board[i][j+1] = 3;
					board[i][j+2] = 3;
					board[i][j+3] = 3;
				}
				if ( board[i][j] == 2 && board[i][j+1] == 2 && board[i][j+2] == 2 && board[i][j+3] == 2)
				{
					win = 2;
					board[i][j] = 3;
					board[i][j+1] = 3;
					board[i][j+2] = 3;
					board[i][j+3] = 3;
				}
			}
		}
		for ( int i = 0; i < 3; i++ )
		{
			for ( int j = 0; j < 4; j++ )
			{
				if ( board[i][j] == 1 && board[i+1][j+1] == 1 && board[i+2][j+2] == 1 && board[i+3][j+3] == 1)
				{
					win = 1;
					board[i][j] = 3;
					board[i+1][j+1] = 3;
					board[i+2][j+2] = 3;
					board[i+3][j+3] = 3;
				}
				if ( board[i][j] == 2 && board[i+1][j+1] == 2 && board[i+2][j+2] == 2 && board[i+3][j+3] == 2)
				{
					win = 2;
					board[i][j] = 3;
					board[i+1][j+1] = 3;
					board[i+2][j+2] = 3;
					board[i+3][j+3] = 3;
				}
			}
		}
		
		for ( int i = 0; i < 3; i++)
		{
			for ( int j = 6; j>1; j--)
			{
				if ( board[i][j] == 1 && board[i+1][j-1] == 1 && board[i+2][j-2] == 1 && board[i+3][j-3] == 1)
				{
					win = 1;
					board[i][j] = 3;
					board[i+1][j-1] = 3;
					board[i+2][j-2] = 3;
					board[i+3][j-3] = 3;
				}
				if ( board[i][j] == 2 && board[i+1][j-1] == 2 && board[i+2][j-2] == 2 && board[i+3][j-3] == 2)
				{
					win = 2;
					board[i][j] = 3;
					board[i+1][j-1] = 3;
					board[i+2][j-2] = 3;
					board[i+3][j-3] = 3;
				}
			}
		}
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.yellow);
		
		g.setColor(Color.gray);
		for ( int i = 0; i < 7; i++ )
			g.fillRect(i * 100, 0, 1, 600 );
		for ( int i = 0; i < 7; i++ )
			g.fillRect(0, i * 100, 815, 1 );
		
		if ( win == 0)
		{
			g.setColor(Color.cyan);
			for ( int i = 0; i < 6; i++ )
			{
				if ( board[i][hoverColumn] == 0 )
					yloc = i;
			}
			g.fillOval(hoverColumn * 100,yloc * 100,100,100);
		}
		
		for ( int i = 0; i < 6; i++ )
		{
			for ( int j = 0; j < 7; j++ )
			{
				if ( board[i][j] == 1 )
				{
					g.setColor(Color.red);
					g.fillOval(j*100,i*100,100,100);
				}
				else if ( board[i][j] == 2 )
				{
					g.setColor(Color.black);
					g.fillOval(j*100,i*100,100,100);
				}
				else if ( board[i][j] == 3)
				{
					g.setColor(Color.green);
					g.fillOval(j*100,i*100,100,100);
				}
				else if ( i != yloc || j != hoverColumn)
				{
					g.setColor(Color.white);
					g.fillOval(j*100,i*100,100,100);
				}			
			}
		}
		
		g.setColor(Color.blue);
		g.setFont(new Font("Serif", Font.PLAIN, 20 ));
		if ( win == 0)
		{
			if ( turn == false )
				g.drawString("It is red's turn.", 350, 625 );
			else
				g.drawString("It is black's turn.", 350, 625 );
		}
		else if ( win == 1 )
			g.drawString("Red Wins!",350,625);
		else if ( win == 2)
			g.drawString("Black Wins!", 350, 625);
		else if ( win == 3)
			g.drawString("It is a tie!", 350, 625);
	}
	public void mouseClicked(MouseEvent e )
	{
		
	}
	public void mousePressed(MouseEvent e )
	{
		if ( win == 0 )
		{
			int x = e.getX();
			if ( turn == true)
				turn = false;
			else
				turn = true;
			place(x);
			checkWin();
			repaint();
		}
	}
	public void mouseReleased(MouseEvent e )
	{

	}
	public void mouseEntered(MouseEvent e )
	{
		
	}
	public void mouseExited(MouseEvent e )
	{

	}
	public void mouseDragged(MouseEvent e)
	{
		
	}
	public void mouseMoved(MouseEvent e)
	{
		if ( win == 0)
		{
			hoverColumn = e.getX()/100;
			if ( hoverColumn > 6)
				hoverColumn = 6;
			else if ( hoverColumn < 0 )
				hoverColumn = 0;
			repaint();
		}
	}
} 
