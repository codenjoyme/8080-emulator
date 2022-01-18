package spec;

public class Range {

    private int begin;
    private int end;

    public Range(int begin, int end) {
        this.begin = begin;
        this.end = end;
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
}
