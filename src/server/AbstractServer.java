package server;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
  AbstractServer class used to store common functionalities between the two UDP and TCP servers
 */
public abstract class AbstractServer implements IServer {
  protected Logger logger;
  protected HashMap<String, String> store;

  /*
    Constructor for AbstractServer
    @param logger: Logger object used to log messages
   */
  public AbstractServer(Logger logger) {
    this.logger = logger;
    this.store = new HashMap<String, String>();

    // prepopulate store with some key-value
    this.store.put("Banana", "Yellow");
    this.store.put("Apple", "Red");
    this.store.put("Grape", "Purple");
    this.store.put("Pear", "Green");
    this.store.put("Peach", "Pink");
    this.store.put("Strawberry", "Red");
    this.store.put("Blackberry", "Black");
    this.store.put("Blueberry", "Blue");
    this.store.put("Raspberry", "Red");
    this.store.put("Orange", "Orange");
  }

  /*
    Method to execute a command
    @param message: string containing the command to be executed
    @return: String containing the response to the command, to be sent back to client
   */
  protected String executeCommand(String message) {
    Command command = CommandParser.parseCommand(message);
    if (command != null) {
      // if command is get, attempt to get value from store and send back either value or error
      if (command.getCommand().equals("get")) {
        String value = store.get(command.getKey());
        if (value == null) {
          this.logger.log(Level.INFO, "Response: Key not found within store");
          return "Key not found";
        } else {
          this.logger.log(Level.INFO, "Response: " + value);
          return value;
        }
      // if command is put,  put key and value into store
      } else if (command.getCommand().equals("put")) {
        store.put(command.getKey(), command.getValue());
        this.logger.log(Level.INFO, "Response: Key added to store");
        return "Key added to store";
      // if command is delete, attempt to delete key from store and send back either value or error
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
    // if command is null or not predefined, invalid packet
    this.logger.log(Level.INFO, "Invalid command received of length " + message.length());
    return "Invalid command received";
  }
}
