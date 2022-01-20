package spec;

import static spec.WordMath.hex16;
import static spec.WordMath.word;

public class Range {

    private int begin;
    private int end;

    /**
     * @param begin Начало диапазона (включиельно).
     * @param endOrLength Если отрицательное - то длинна диапазона,
     *                    если положительное - то конец диапазона (включиельно).
     */
    public Range(int begin, int endOrLength) {
        this.begin = begin;
        if (endOrLength >= 0) {
            this.end = endOrLength;
        } else {
            int length = -endOrLength;
            this.end = begin + length - 1;
        }
        this.begin = word(this.begin);
        this.end = word(this.end);
    }

    public int begin() {
        return begin;
    }

    public int end() {
        return end;
    }

    public boolean includes(int addr) {
        return begin <= addr && addr <= end;
    }

    public void begin(int addr) {
        begin = addr;
    }

    public void end(int addr) {
        end = addr;
    }

    public int length() {
        return end - begin + 1;
    }

    public int offset(int offset) {
        return begin + offset;
    }

    @Override
    public String toString() {
        return String.format("[%s:%s]",
                hex16(begin),
                hex16(end));
    }
}
