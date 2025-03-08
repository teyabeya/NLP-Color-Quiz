import java.util.ArrayList;
import java.util.Scanner;

public class Colors{

  //questions
  private String[] questions;
  //text files
  private ArrayList<String> freetime;
  private ArrayList<String> seasons;
  private ArrayList<String> tastes;
  private ArrayList<String> values;
  //counters
  private int count;
  private int red;
  private int blue;
  private int yellow;
  private int green;
  private int purple;

  /*
  paramaterized constructor-creates objects and converts files to array lists
  pre:argument holds 4 relevant questions, textfiles exist
  post:object created files with keywords created.
  @param questions - list of strings that holds the questions to prompt the user with
  */
  public Colors(String[] questions){
    this.questions = questions;
    freetime = FileReader.toStringList("freetime.txt");
    seasons = FileReader.toStringList("seasons.txt");
    tastes = FileReader.toStringList("tastes.txt");
    values = FileReader.toStringList("values.txt");
  }

  /*
  main method - prompts the user and calls methods
  pre: scanner is called into the method
  post: input scanner has closed, if quiz played, color result is revealed
  */
public void chat() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Welcome to the Colors Quiz. Ready to start? ");
    String choice = input.nextLine();
    
    if (choice.equals("yes") || choice.equals("y")) {
      red = 0;
      blue = 0;
      yellow = 0;
      green = 0;
      purple = 0;
      count =0;

      System.out.println(questions[0]);
      String userResponse = input.nextLine();
      analyze(convertToWords(userResponse));

      System.out.println(questions[1]);
      userResponse = input.nextLine();
      analyze(convertToWords(userResponse));

      System.out.println(questions[2]);
      userResponse = input.nextLine();
      analyze(convertToWords(userResponse));

      System.out.println(questions[3]);
      userResponse = input.nextLine();
      analyze(convertToWords(userResponse));

      System.out.println("");
      System.out.println("Results are in!");
      System.out.println("");
      
      tallyUp(red, blue, yellow, green, purple);
      System.out.println("");
      

      input.close();
      chat();
      
    } else {
      input.close();
      System.out.println("Okay, bye!");
    }
}

  /*
  Tally up - finds the max tally and prints all colors with that amount
  pre: colors have in int value
  post: at least one color value printed
  @param int red - the color score
  @param int yellow - the color score
  @param int blue - the color score
  @param int green - the color score
  @param int purple - the color score
  */
  public void tallyUp(int red, int blue, int yellow, int green, int purple){
    String[] colorQualities = {"Red: You are bold, energetic, and competitive. You thrive on excitement and challenges.",
        "Blue: You are calm, thoughtful, and introspective. You value wisdom, depth, and trust.",
        "Yellow: You are cheerful, social, and full of life. You bring energy and positivity wherever you go.",
        "Green: You are nurturing, balanced, and connected to nature. You value kindness and harmony.",
        "Purple: You are creative, mysterious, and imaginative. You appreciate uniqueness and self-expression."};
    int[] colorTally = {red, blue, yellow, green, purple};
    int max = red;
    for (int i = 1; i<5;i++){
      if (max<colorTally[i]){
        max = colorTally[i];
      }
    }
    System.out.println("You are...");
    for (int i = 0; i<5; i++){
      if (colorTally[i] == max){
        System.out.println(colorQualities[i]);
      }
    }
  }

  /*
  analyze - adds a point to the color if keyword matches the list of keywords for that color
  pre: user must have a response that has been turned into an array list
  post: color tallys are the same or greater than before, count increased by 1
  @param respond arraylist string - an arraylist of strings making up the users response.
  */
  public void analyze(ArrayList<String> respond) {
    ArrayList<String> ans = currentA();
    for (int i = 0; i < ans.size(); i++) {
        ArrayList<String> keywords = convertToWords(ans.get(i));
        for (String userWord : respond) { 
            for (String keyword : keywords) { 
                if (userWord.equalsIgnoreCase(keyword)) { 
                    if (i == 0) {
                        red++;
                    } else if (i == 1) {
                        blue++;
                    } else if (i == 2) {
                        yellow++;
                    } else if (i == 3) {
                        green++;
                    } else {
                        purple++;
                    }
                }
            }
        }
    }

    count++;
}

  /*
  currentA - finds and returns the current arraylist of keywords lists
  pre: count is 0 or greater
  post: arraylist string is returned
  @return Arraylist string - a list of strings, each string contains
  a list of corresponding keywords to color and question
  */
  public ArrayList<String> currentA(){
    if(count == 0){
      return freetime;
    } else if(count == 1){
      return seasons;
    } else if (count == 2){
      return tastes;
    } else{
      return values;
    }
  }


  /*
  convert to words - turns a string into an array of words without symbols
  pre: the text doesn't contain symbols other than !:.,
  post: no symbols in the words
  @param String text - the text to turn into an array of words
  @return array list string - an arraylist of words
  */
  public ArrayList<String> convertToWords(String text) {
    ArrayList<String> words = new ArrayList<String>();
    int space = text.indexOf(" ");
  
    while (space != -1) {
      if (text.substring(space-1, space).equals(",") ||
          text.substring(space-1, space).equals(".")||
          text.substring(space-1, space).equals("!")||
          text.substring(space-1, space).equals(":")){
        String currentWord = text.substring(0, space-1);
        words.add(currentWord);   
      }else{
        String currentWord = text.substring(0, space);
      words.add(currentWord);
      }
      text = text.substring(space + 1);
      space = text.indexOf(" ");
    }

    while (text.endsWith(".") ||
           text.endsWith("!") ||
           text.endsWith(",") ||
           text.endsWith(":")) {
        text = text.substring(0, text.length() - 1);
    }
    words.add(text);
    return words;
  }
  
}