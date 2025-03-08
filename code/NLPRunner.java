public class NLPRunner {
  public static void main(String[] args) {
    String[] qs = {"How do you spend your free time?", "Describe your favorite season/weather in detail.", "Describe your favorite tastes/flavors.", "What qualities do you value most in a friend?"};

    
    Colors person = new Colors(qs);

    person.chat();
    
    
  }
}