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
    System.out.println("Type the text for your slide: ");
    Scanner slideTextTaker = new Scanner(System.in);
    String slideTest = slideTextTaker.nextLine();
    File textFile = new File("./files/texts/textForSlide.txt");
  }
}
