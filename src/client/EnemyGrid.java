package client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EnemyGrid extends JPanel{
	
	private Client client;
	private ServerConnection connection;
	
	private EnemyCell[][] allCells;
	private boolean shooting = false;
    
    EnemyGrid(Client client, ServerConnection server)
	{
    	this.client = client;
    	this.connection = server;
		setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Initialize cells 
        allCells = new EnemyCell[10][10];
        
        for (int row = 0; row < allCells.length; row++) {
            for (int col = 0; col < allCells[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                allCells[row][col] = new EnemyCell(this, row, col);
                allCells[row][col].setStatus(0);
                add(allCells[row][col], gbc);
            }
        }
	}
		
	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public void setColor(int row, int col, Color color, boolean locked)
	{
		allCells[row][col].setBackground(color);
		if(locked)
			allCells[row][col].setLocked(true);
	}
	
	public Color getColor(int row, int col)
	{
		return allCells[row][col].getBackground();
	}
	
	// Reset 
	public void refreshGrid()
	{
        for (int row = 0; row < allCells.length; row++) {
            for (int col = 0; col < allCells[row].length; col++) {
            	if(allCells[row][col].getStatus() == 0)
            		allCells[row][col].setBackground(allCells[row][col].getBackground());
            }
        }
	}

	public void callShot(int row, int col) {
		
		connection.sendShot(new Shot(col, row));
	}
	
}
