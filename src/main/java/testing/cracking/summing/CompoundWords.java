package testing.cracking.summing;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompoundWords {
    private List<String> englishWords;

    public static void main(String args[] ) throws Exception {
        new CompoundWords().detectCompoundWords(args);
    }

    private List<String> detectCompoundWords(String[] input) {
        englishWords = Stream.of(input).collect(Collectors.toList());
        if (!Optional.ofNullable(input).isPresent() || input.length <= 0) {
            return null;
        }
        
        // englishWords.length = N
        // input.length = M
        // Complexity O(2n)
        List<String> output = new ArrayList<>();
        for (int j = 0; j < input.length; j++) {
            Optional<String> possibleMatch = getPossibleMatch(input[j]);
            if (possibleMatch.isPresent()) {
                String match = possibleMatch.get();
                String compound = input[j];
                String remainder = compound.substring(compound.indexOf(match), match.length() - 1);
                Optional<String> remainderInDictionary = getPossibleMatch(remainder);
                if (remainderInDictionary.isPresent()) {
                    output.add(compound);
                }
            }
        }
        
        return output;
    }
    
        // englishWords = ['aire', 'plan']
        // input = ['airplane'];
        // plan
        private Optional<String> getPossibleMatch(String singleWord) {
            return englishWords.stream().filter(w -> {
                return !singleWord.equals(w) && singleWord.indexOf(w) != -1;
            }).findAny();
        }
    }
