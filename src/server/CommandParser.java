package server;

public class CommandParser {
  private static final String delimiter = ",";

  public static Command parseCommand (String line) {
    String[] tokens = line.split(delimiter);

    // check validity of token
    if (tokens.length < 2) {
      return null;
    }


    String command = tokens[0].toLowerCase();

    // if valid put command
    if (command.equals("put") && tokens.length == 3) {
      String key = tokens[1];
      String value = tokens[2];
      return new Command(command, key, value);
    }
    // if valid "get" command
    else if (command.equals("get") && tokens.length == 2) {
      String key = tokens[1];
      return new Command(command, key);
    }
    // if valid delete command
    else if (command.equals("delete") && tokens.length == 2) {
      String key = tokens[1];
      return new Command(command, key);
    }
    else {
      return null;
    }
  }
}
