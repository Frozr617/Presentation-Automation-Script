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
  public static File textFile;
  public static File imagePathFile;

  public static void main(String[] args) {
    try{
      textFile = new File("./files/texts/textForSlide.txt");
      textFile.createNewFile();
      mainWindow = new JFrame("Slide");
      mainWindow.setSize(1280, 720);
      mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startup();
    }
    catch(IOException e) {
      System.out.println("IO Error");
    }
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
      String slideText = slideTextTaker.nextLine();


      BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(textFile));
      textFileWriter.write(slideText);
      textFileWriter.newLine();
      textFileWriter.close();

      System.out.println("Ok, so now give me the path to your presentation background image: ");
      Scanner bgImagePathTaker = new Scanner(System.in);
      String bgImagePath = bgImagePathTaker.nextLine();
      String bgImagePathRemakeOne = bgImagePath.replace("'", "");
      String bgImagePathRemakeTwo = bgImagePathRemakeOne.replace(" ", "");
      BufferedImage bgimage = ImageIO.read(new File(bgImagePathRemakeTwo));
      int bgwidth = bgimage.getWidth();
      int bgheight = bgimage.getHeight();

      System.out.println("How many images woll be on your slide?");
      Scanner imagesCoundTaker = new Scanner(System.in);
      int imagesCound = imagesCoundTaker.nextInt();

      for (int i = 0; i != imagesCound; i++) {
        try{
          System.out.println("Type the path to your image: ");
          Scanner imagePathTaker = new Scanner(System.in);
          String imagePath = imagePathTaker.nextLine();
          String imagePathRemakeOne = imagePath.replace("'", "");
          String imagePathRemakeTwo = imagePathRemakeOne.replace(" ", "");
          imagePathFile = new File("./files/images/image" + i + ".txt");
          imagePathFile.createNewFile();
          BufferedWriter imageFileWriter = new BufferedWriter(new FileWriter(imagePathFile));
          imageFileWriter.write(imagePathRemakeTwo);
          imageFileWriter.close();
        }
        catch(IOException e) {
          System.out.println("IO Error");
        }
      }

      creatingSlide(bgImagePathRemakeTwo, textFile, bgwidth, bgheight, imagesCound);
    }
    catch(IOException e) {
      System.out.println("IO Error");
    }
  }
  public static void creatingSlide(String bgImagePath, File textFile, int bgWidth, int bgHeight, int imgCount) {
    try{
      JPanel background = new JPanel();
      JLabel backgroundImage = new JLabel();
      backgroundImage.setIcon(new ImageIcon(bgImagePath));
      background.add(backgroundImage);


      JPanel main = new JPanel();

      File image1File = new File("./files/images/image0.txt");
      Scanner image1FileScanner = new Scanner(image1File);
      JLabel image1 = new JLabel();
      image1.setIcon(new ImageIcon(image1FileScanner.nextLine()));
      main.add(image1);

      background.add(main);
      mainWindow.add(background);
      mainWindow.setSize(bgWidth, bgHeight);
      mainWindow.setVisible(true);
      System.out.println("Do you like it?\ny for Yes\nn for No");
      Scanner doYouLike = new Scanner(System.in);
      if (doYouLike.nextLine().equals("y")) {
        try{
          getSaveSnapShot(mainWindow, "slide.jpg");
        }
        catch(Exception e) {
          System.out.println("Error");
        }
      }
    }
    catch(FileNotFoundException er) {
      System.out.println("File Not Found!");
    }
  }
  public static BufferedImage getScreenShot(Component component) {

        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        // paints into image's Graphics
        component.paint(image.getGraphics());
        return image;
    }

    public static void getSaveSnapShot(Component component, String fileName) throws Exception {
        BufferedImage img = getScreenShot(component);
        // write the captured image as a PNG
        File slide1 = new File("slide.jpg");
        if (slide1.exists()) {
          File slide2 = new File("slide1.jpg");
          if (slide2.exists()) {
            File slide3 = new File("slide2.jpg");
          }
        }
        ImageIO.write(img, "jpg", slide1);
        saved();
    }
    public static void saved() {
      textFile.delete();
    }
}
