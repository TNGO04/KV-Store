package server;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
  /**
   * Format the given log record and return the formatted string.
   * <p>
   * The resulting formatted String will normally include a
   * localized and formatted version of the LogRecord's message field.
   * It is recommended to use the {@link Formatter#formatMessage}
   * convenience method to localize and format the message field.
   *
   * @param record the log record to be formatted.
   * @return the formatted log record
   */
  @Override
  public String format(LogRecord record) {
    String formatted = System.currentTimeMillis() + ": "
            + record.getLevel().getName() + " "
            + formatMessage(record) + "\n";

    return formatted;
  }
}