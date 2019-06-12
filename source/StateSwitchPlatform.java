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

/** This class represents a platform that swtiches on and off
  * @author Feng Xiong, Michael Zhou
  * @version 2.0
  * Total time spent Feng: 1.5 hours
  * Total time spent Michael: 0.5 hours
  */ 
public class StateSwitchPlatform extends Obstacle{//Added June 2 by feng, 0.5 hour
  //If the platform is black or white  
  private boolean type;
  
  /** Constructor
    * @param x xcord of the platform
    * @param y ycord of the platform
    * @param length length of the platform
    * @param type is the platform black or white
    */ 
  public StateSwitchPlatform(int x, int y, int length, boolean type){
    super(x, y, length, 30);
    if(type)
      try{
      setImage(ImageIO.read(new File("Resources/Blocks/black.png")));
    }catch(IOException e){
      e.printStackTrace();
    }
    else
      try{setImage(ImageIO.read(new File("Resources/Blocks/white.png")));}catch(IOException e){e.printStackTrace();}
    this.type = type;
    setOn(type);
  }
  
  /** Return the type (black or white)
    * @return the type (black or white)
    */
  public boolean getType(){
    return type;
  }
  
  /** Change the type (black or white)
    * 
    */ 
  public void flipType(){
    type = !type;
  }
  
  /**
   * {@inheritDoc}
   */
  public void update(Graphics g){
    if(getOn())
      for (int i = 0; i <= getXSize(); i+=30)
      g.drawImage(getImage(), getX()+i, getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
  }
}
