package testing.cracking.summing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintHalfAnHour {
  public static void main(String args[] ) throws Exception {
    new PrintHalfAnHour().printFinalHalfAnHour(args);
  }
  
  List<String> printFinalHalfAnHour(String[] filenames) {
    if (filenames == null || filenames.length <= 0) {
      return null;
    }
    Map<String, List<String>> allFileData = Stream.of(filenames).collect(Collectors.toMap(k -> k, k -> getFileLines(k)));
    
    // knowing the last event: O(2n)
    long latest = 0L;
    for (List<String> logData: allFileData.values()) {
      for (String line: logData) {
        long ts = getTimestampFromLogLine(line);
        if (ts > latest) {
          latest = ts;
        }
      }
    }
    List<String> output = new ArrayList<String>();
    for (List<String> logData: allFileData.values()) {
      for (String line: logData) {
        if (isWithinLastHalfAnHour(System.currentTimeMillis(), getTimestampFromLogLine(line))) {
          output.add(line);
        }
      }
    }
    
    return output;
    
  }
  
  List<String> getFileLines(String path) {
    return new ArrayList<String>();
  }
  
  long getTimestampFromLogLine(String logLine) {
    return 0L;
  }
  
  boolean isWithinLastHalfAnHour(long ts, long latest) {
    return (latest - ts) <= (1000 * 60 * 30);
  }
}
