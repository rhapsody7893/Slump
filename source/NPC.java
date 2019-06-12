import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

/**
 * This is the NPC class. It is an interactable object that the player can talk to. This part ties the game to the adversity
 * @author Michael Zhou, Feng Xiong
 * @version 2.0
 * Total time spend Michael: 0.5 hours
 * Modifications: May 24, 2019, Michael Zhou, Total time: 0.5 hours
 * added basic class structure
 * impleneted all the needed methods from OBstacle
 *added constuctor
 Modifications: May 29, 2019, Michael Zhou, Total time: 0.5 hours
 * modified the speak method so the the dialogue box will be properly formaed on the screen
 * made the text properly formated on the screen, if there is a \n the the text will start on a new line as inteneded
 */
public class NPC extends Obstacle {
  private String dialogue;
  private BufferedImage dialogueBox;
  private String answer = " ";
  private ArrayList<String> options = new ArrayList<String>();
  private boolean question;
  private boolean talked;
  
  //May 24, Michael created constructor for class
  /**
   * 
   * @param x left x coord
   * @param y top y coord
   * @param xSize width
   * @param ySize length
   * @param image character image
   * @param text dialog text
   */
  public NPC (int x, int y, int xSize, int ySize, BufferedImage image, String text) //added constuctor
  {
    super (x, y, xSize, ySize);
    talked = false;
    setOn(true);
    setImage(image);
    try{dialogueBox = ImageIO.read(new File("Resources/Dialogue/dialogue.png"));}catch(IOException e){}
    dialogue = text; 
  }
  
  //May 24, Michael created basic method outiline 
  //Modifications: May 29, 2019, Michael Zhou, Total time: 0.5 hours
  //modified the speak method so the the dialogue box will be properly formaed on the screen
  //made the text properly formated on the screen, if there is a \n the the text will start on a new line as inteneded
  /**
   * Dialogue pop up
   *
   * @param g graphics
   */
  public void speak (Graphics g) //added speak method for testing
  {//Should speak, and then close the speach bubble
    //modified the speak method so the the dialogue box will be properly formaed on the screen may 29 michael
    //  made the text properly formated on the screen, if there is a \n the the text will start on a new line as inteneded, May 29 michael
    g.setFont(new Font("SansSerif", Font.PLAIN, 20)); 
    if (dialogue.indexOf ("/q") < 0)
    {
      question = false;
      if (dialogue.indexOf ("/nPress any key to close...") < 0)
        dialogue += "/nPress any key to close...";
      g.drawImage(dialogueBox, 0, 600,800,800, 0,0,1520, 470, null); 
      int y = 650 - g.getFontMetrics().getHeight();
      for (String line : dialogue.split("/n"))
      {
        g.drawString(line, 50, y += g.getFontMetrics().getHeight());
      }
    }
    else 
    {
      question = true;
      int y = 600 - g.getFontMetrics().getHeight();
      String [] temp = new String [5];
      temp = dialogue.split ("/q");
      String text = temp [0];
      
      if (text.indexOf("/a") >= 0)
      {
        String [] t =  text.split ("/a");
        text = t [0];
        options.add (t [1]);
        options.add (temp [2]);
        options.add (temp [3]);
      }
      
      else
      {
        options.add(temp [1]);
        options.add(temp [2]);
        options.add(temp [3]);
        
        
        for (int i = 0; i <= 2; i ++)
        {
          if (options.get(i).indexOf ("/a") >= 0)
          {
            String [] t =  options.get(i).split ("/a");
            answer = t [1];
            options.set(i, t [0]);
            options.add(i + 1, answer);
          }
        }
      }
      g.drawImage(dialogueBox, 0, 530,800,800, 0,0,1520, 470, null); 
      for (String line : text.split("/n"))
      {
        g.drawString(line, 50, y += g.getFontMetrics().getHeight());
      }
      for (int i = 0; i <= 3; i ++)
      {
        if (options.get (i) == null)
        {
          options.set (i, "I do not know...");
        }
      }
      g.drawString("Press the number that coorasponds with your answer:", 50, y += g.getFontMetrics().getHeight());
      g.drawString("1. " + options.get(0), 50, y += g.getFontMetrics().getHeight());
      g.drawString("2. " + options.get(1), 50, y += g.getFontMetrics().getHeight());
      g.drawString("3. " + options.get(2), 50, y += g.getFontMetrics().getHeight());
      g.drawString("4. " + options.get(3), 50, y += g.getFontMetrics().getHeight());
    }
  }
  
  /**
   * Gets the correct answer
   * @return answer
   */
  public String getAnswer ()
  {
    return answer;
  }
  
  public boolean getTalked ()
  {
    return talked;
  }
  
  public void setTalked (boolean isTalked)
  {
    talked = isTalked;
  }
  public boolean isQuestion ()
  {
    return question;
  }
  
  public String getOption (int optionNumber)
  {
    return options.get(optionNumber-1);
  }
  
  public void setDialogue (String newDialogue)
  {
    dialogue = newDialogue;
  }
  
  //May 24, Michael implemented method
  public void update (Graphics g) //implemented update
  {
    g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
  }
}
