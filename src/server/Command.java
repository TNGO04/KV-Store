package server;

public class Command {
  private String command;
  private String key;
  private String value;

  public Command (String command, String key, String value) {
    this.command = command;
    this.key = key;
    this.value = value;
  }

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
