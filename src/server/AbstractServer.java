package server;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class AbstractServer implements IServer {
  protected Logger logger;
  protected HashMap<String, String> store;

  public AbstractServer(Logger logger) {
    this.logger = logger;
    this.store = new HashMap<String, String>();
  }

  protected String executeCommand(Command command) {
    if (command != null) {
      if (command.getCommand().equals("get")) {
        String value = store.get(command.getKey());
        if (value == null) {
          this.logger.log(Level.INFO, "Response: Key not found within store");
          return "Key not found";
        } else {
          this.logger.log(Level.INFO, "Response: " + value);
          return value;
        }

      } else if (command.getCommand().equals("put")) {
        store.put(command.getKey(), command.getValue());
        this.logger.log(Level.INFO, "Response: Key added to store");
        return "Key added to store";

      } else if (command.getCommand().equals("delete")) {
        String value = store.remove(command.getKey());
        if (value == null) {
          this.logger.log(Level.INFO, "Response: Key not found");
          return "Key not found";
        } else {
          this.logger.log(Level.INFO, "Response: Key " + command.getKey() + " deleted");
          return "Key deleted";
        }
      }
    }
    // if command is null or not predefined
    this.logger.log(Level.INFO, "Invalid command received");
    return "Invalid command received";
  }
}
