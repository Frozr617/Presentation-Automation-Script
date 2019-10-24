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
      /*BufferedImage bgimg = ImageIO.read(new File(bgImagePath));

      int width = bgimg.getWidth();
      int height = bgimg.getHeight();*/

      creatingSlide(bgImagePath, textFile);
    }
    catch(IOException e) {
      System.out.println("IO Error");
    }
  }
  public static void creatingSlide(String bgImagePath, File textFile) {
    JPanel background = new JPanel();
    JLabel backgroundImage = new JLabel();
    backgroundImage.setIcon(new ImageIcon(bgImagePath));
    background.add(backgroundImage);
    mainWindow.add(background);
    mainWindow.setVisible(true);
  }
}
