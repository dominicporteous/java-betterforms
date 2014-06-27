package src;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Form extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * This var is used for controlling the parents in 
	 * nested Forms, best to always null check before using.
	 */
	private Form master = null;
	
	/**
	 * This method constructs a new Form, extending the Swing JFrame
	 *
	 * return n/a
	 */
	public Form(){
		master = this;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
	}
	
	/*
	 * This method can be called on child Forms, allowing you to access the parent (to show/hide etc)
	 * Can also be used like this.getMaster().getMaster()...
	 *
	 * return n/a
	 */
	public Form getMaster(){
	    return this.master;
	}
	
	/**
	 * This method makes the form visible and returns the Form object.
	 *
	 * return Form this;
	 */
	public Form doShow(){
		setVisible(true);
		return this;
	}
    
    /**
	 * This method closes the form and returns the Form object. May
	 * hide or dispose of the object, depending on the value of getDefaultCloseOperation()
	 * which should be set using this.setDefaultCloseOperation(); in the constructer of the super class.
	 *
	 * return Form this;
	 */
	public Form close(){
		setVisible(false);
		return this;
	}
	
	/**
	 * This method centers the form on screen. Best to call this before calling doShow()
	 *
	 * Note: If and even number of screens are in use e.g. 2 or 4, it will center the form
	 *       across the middle of 2 screens (half on each)
	 *
	 * return Form this;
	 */
	public Form Center(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		return this;
	}
	
	/**
	 * This method captures a "screenshot" of the form and returns the absoilte file path for the temp
	 * file containing the screenshot. Useful for sending bug reports etc. 
	 *
	 * return String screenPath;
	 */
	public String screenshot() {
	    Rectangle rec = this.getBounds();
	    BufferedImage bufferedImage = new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_ARGB);
	    this.paint(bufferedImage.getGraphics());

	    try {
	        File temp = File.createTempFile("screenshot", ".png");
	        ImageIO.write(bufferedImage, "png", temp);
	        temp.deleteOnExit();
	        return temp.getAbsolutePath();
	    }catch(IOException e){ System.out.println("Unable to capture form screenshot!"); } // catch
	    
	    return "";
	}
	
}
