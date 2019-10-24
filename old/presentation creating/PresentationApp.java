import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;


public class PresentationApp
{
  public static String textScanner;
  public static int pasting_text_kind;
  public String path_to_text_file;
  public static void main(String[] args) {
    hello();
  }

  public static void hello()
  {
    System.out.println("Hi, man!!!");
    gettingFiles();
  }

  public static void gettingFiles()
  {
    System.out.println("Gimme the files you found to do this presentation\n\nWhat would you like to paste first?\n1.Text\n2.Image\n3.Video\n");

    Scanner input1 = new Scanner(System.in);
    int action1 = input1.nextInt();
    System.out.println("You entered: " + action1);

    if (action1 == 1) {
      pasting_text();
    }
    if (action1 == 2) {
      pasting_image();
    }
    if (action1 == 3) {
      System.out.println("3");
    }
  }

  public static void pasting_text()
  {
    System.out.println("What text would you like to paste first?\n1.From file\n2.Source text");

    Scanner text_kind = new Scanner(System.in);
    pasting_text_kind = text_kind.nextInt();

    if (pasting_text_kind == 1) {
      System.out.println("Type the path to file: ");
      Scanner path_text = new Scanner(System.in);
      String path_to_text_file = path_text.nextLine();

      File text_file = new File(path_to_text_file);

      System.out.println("Your path ot text file is: " + path_to_text_file);

      try{
        FileReader textFileReader = new FileReader(path_to_text_file);
        try{
          try{
            Scanner textFileScanner = new Scanner(textFileReader);
            textScanner = textFileScanner.nextLine();
            System.out.println("Text succesfully taken");
          }
          catch(InputMismatchException e)
          {
            System.out.println("error");
          }
        }
        catch(NoSuchElementException e)
        {
          System.out.println("Error");
        }
      }
      catch(IOException ex)
      {
        System.out.println("Error");
      }
    }
    if (pasting_text_kind == 2) {
      Scanner sourceText = new Scanner(System.in);
      //Creating new file where writing source text from user
      File sourceTextFile = new File("D:\\Programming\\Java\\Projects\\Helping for school\\presentation creating\\sourceTextFiles\\sourceFile.txt");
      try
      {
        if (sourceTextFile.createNewFile()) {
          System.out.println("File succesfully created!");
          System.out.println("Type your text here: ");
          Scanner textToWrite = new Scanner(System.in);
          String textToWriteSource = textToWrite.nextLine();
          FileWriter fileWriter = new FileWriter(sourceTextFile);
          //Writting to file
          fileWriter.write(textToWriteSource);
          //Closing text file
          fileWriter.close();
        }
        else if(sourceTextFile.exists())
        {
          sourceTextFile.delete();
          System.out.println("File succesfully created!");
          System.out.println("Type your text here: ");
          Scanner textToWrite = new Scanner(System.in);
          String textToWriteSource = textToWrite.nextLine();
          FileWriter fileWriter = new FileWriter(sourceTextFile);
          //Writting to file
          fileWriter.write(textToWriteSource);
          //Closing text file
          fileWriter.close();
        }
      }
      catch(IOException e)
      {
        System.out.println("Error");
        e.printStackTrace();
      }
    }
    System.out.println("Now we gonna paste image, right?\n1.Yes\n2.No");
    Scanner willWePasteImageNext = new Scanner(System.in);
    if (willWePasteImageNext.nextInt() == 1)
    {
      pasting_image();
    }
    if (willWePasteImageNext.nextInt() == 2)
    {
      slideProcessing();
    }
    else
    {
      System.out.println("Error");
    }
  }
  public static void pasting_image()
  {
    System.out.println("Give me the path to the image: ");
    slideProcessing();
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
    }
  public static void slideProcessing()
  {
    Image bgimage = null;
    Random randomTextPosition = new Random();
    Scanner image_path = new Scanner(System.in);
    File textFromSourceFile = new File("D:\\Programming\\Java\\Projects\\Helping for school\\presentation creating\\sourceTextFiles\\sourceFile.txt");
    try
    {
      Scanner sourceTextFileScanner = new Scanner(textFromSourceFile);
      BufferedImage image = ImageIO.read(new File(image_path.nextLine()));
      int width = randomTextPosition.nextInt(image.getWidth() + 200);
      int height = randomTextPosition.nextInt(image.getHeight() + 200);
      System.out.println("Image taken succesfully");
      System.out.println("Image is processing\n");

      //Graphics2D to edit image or display it
      Graphics2D g = (Graphics2D) image.getGraphics();
      g.setColor(Color.BLUE);
      //Putting image in panel named JPanel
      JLabel picLabel = new JLabel(new ImageIcon(image));
      //Creating JPanel named JPanel
      JPanel JPanel = new JPanel();
      JPanel.add(picLabel);
      JLabel textLabel;
      if (pasting_text_kind == 1) {
        textLabel = new JLabel(textScanner);
        textLabel.setLocation(width, height);
        textLabel.setSize(new Dimension(image.getWidth() - 100, image.getHeight() - 250));
        JPanel.add(textLabel);
      }
      if (pasting_text_kind == 2) {
        textLabel = new JLabel(sourceTextFileScanner.nextLine());
        textLabel.setLocation(width, height);
        JPanel.add(textLabel);
      }
      //Creating java window
      JFrame f = new JFrame();
      f.setSize(new Dimension(image.getWidth() + 100, image.getHeight()+ 100));
      //Adding JPanel to JFrame
      f.add(JPanel);
      f.setDefaultCloseOperation(EXIT_ON_CLOSE);
      //Showing JFrame window
      f.setVisible(true);

      System.out.println("Is it good?\n1.Yes\n2.No\n");
      Scanner isItGood = new Scanner(System.in);
      if (isItGood.nextInt() == 1) {
        try
        {
          getSaveSnapShot(JPanel, "slide.jpg");
          System.out.println("Will we continue?\n1.Yes\n2.No\n");
          Scanner willWeContinue = new Scanner(System.in);
          if (willWeContinue.nextInt() == 1) {
            gettingFiles();
          }
        }
        catch(Exception e)
        {
          System.out.println("Error");
        }
      }
    }
    catch (IOException e)
    {
      System.out.println("Error");
    }
  }
}
