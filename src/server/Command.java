package server;
/*
  Command class is used to store the command, key and value
 */
public class Command {
  private String command;
  private String key;
  private String value;

  // Constructor for Command class
  public Command (String command, String key, String value) {
    this.command = command;
    this.key = key;
    this.value = value;
  }

  // Constructor for Command class, in case no value provided
  public Command (String command, String key) {
    this.command = command;
    this.key = key;
    this.value = null;
  }

  public String getCommand() {
    return command;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
