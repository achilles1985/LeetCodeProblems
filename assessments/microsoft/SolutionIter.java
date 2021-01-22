package assessments.microsoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class SolutionIter implements Iterable<Integer> {
    private Reader inp;

    public SolutionIter(Reader inp) {
        this.inp = inp;
    }

    public Iterator<Integer> iterator() {
        return new FileNumbersIter(inp);
    }

    private class FileNumbersIter implements Iterator<Integer> {
        private BufferedReader bufferedReader;

        public FileNumbersIter(final Reader reader) {
            if (reader == null) {
                throw new IllegalArgumentException("Reader must not be null");
            }
            bufferedReader = new BufferedReader(reader);
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            String line = "";
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {

            }
            return toInteger(line);
        }

        private Integer toInteger(String str) {
            String trimmed = str.trim();
            char firstChar = trimmed.charAt(0);
            if (!Character.isDigit(firstChar) && firstChar != '+' && firstChar != '-') {
                return null;
            }
            long res = 0;
            int maxInteger = 1_000_000_000;
            int minInteger = -1_000_000_000;
            for (int i = (firstChar == '-' || firstChar == '+') ? 1 : 0; i < trimmed.length(); i++) {
                char symbol = trimmed.charAt(i);
                if (!Character.isDigit(symbol)) {
                    return null;
                }
                res = res * 10 + (symbol - '0');
                if (res < minInteger || res > maxInteger) {
                    return null;
                }
            }
            return firstChar == '-' ? -(int)res : (int)res;
        }
    }

    public static void main(String[] args) {
         for (Integer x : new SolutionIter(null)) {
             System.out.println(x);
         }
    }
}
