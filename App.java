import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.util.InputMismatchException;;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;



public class Main{

  public static JFrame mainWindow;
  public static String presentationName;
  public static Path presentationRepo;

  public static void main(String[] args) {
    mainWindow = new JFrame("Slide");
    mainWindow.setSize(1280, 720);
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    startup();
  }
  public static void startup() {
    try{
      System.out.println("Type the name of your presentation: ");
      Scanner presentationNameTaker = new Scanner(System.in);
      presentationName = presentationNameTaker.nextLine();
      presentationRepo = Paths.get("./Presentations/" + presentationName);
      Files.createDirectories(presentationRepo);
      takingDataForSlide();
    }
    catch(IOException e) {
      System.out.println("IO Error");
    }
  }
  public static void takingDataForSlide() {
    try{
      System.out.println("Type the text for your slide: ");
      Scanner slideTextTaker = new Scanner(System.in);
      String slideTest = slideTextTaker.nextLine();

      File textFile = new File("./files/texts/textForSlide.txt");
      BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(textFile));
      textFileWriter.write(slideTest);
      textFileWriter.newLine();

      System.out.println("Ok, so now give me the path to your presentation background image: ");
      Scanner bgImagePathTaker = new Scanner(System.in);
      String bgImagePath = bgImagePathTaker.nextLine();
      String bgImagePathRemakeOne = bgImagePath.replace("'", "");
      String bgImagePathRemakeTwo = bgImagePathRemakeOne.replace(" ", "");
      BufferedImage bgimage = ImageIO.read(new File(bgImagePathRemakeTwo));
      int bgwidth = bgimage.getWidth();
      int bgheight = bgimage.getHeight();

      creatingSlide(bgImagePath, textFile, bgwidth, bgheight);
    }
    catch(IOException e) {
      System.out.println("IO Error");
    }
  }
  public static void creatingSlide(String bgImagePath, File textFile, int bgWidth, int bgHeight) {
    JPanel background = new JPanel();
    JLabel backgroundImage = new JLabel();
    backgroundImage.setIcon(new ImageIcon(bgImagePath));
    background.add(backgroundImage);
    mainWindow.add(background);
    mainWindow.setSize(bgWidth, bgHeight);
    mainWindow.setVisible(true);
  }
}
