import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

/** 
 * Represents an invisible platform.
 * @author Feng Xiong, Michael Zhou
 * @version 2.0
 */
public class InvisPlatform extends Platform{
  
  /**
   * This indicates the bounds of the player, and helps calculate when to display the invis block
   */
  private Rectangle player;
  
  /**
   * Constructor
   * 
   * @param x indicates x location
   * @param y indicates y location
   * @param length indicates length of the block
   */
  public InvisPlatform(int x, int y, int length){
    super(x, y, length);
    setOn(true);
    try{setImage(ImageIO.read(new File("Resources/Blocks/invis.jpg")));}catch(IOException e){e.printStackTrace();}
  }
  
  /**
   * {@inheritDoc}
   */
  public void update(Graphics g){
    if(player.intersects(new Rectangle(getX()-50, getY()-50, getXSize()+100, getYSize()+100)))//If player approaches the block
      g.drawImage(getImage(), getX(), getY(), getXSize(), getYSize(), null);
  }
  
  /**
   * Sets the player's current position 
   * @param rectangle the player's rectangle for position
   */
  public void setPlayer(Rectangle rectangle){
    player = rectangle;
  }
}
