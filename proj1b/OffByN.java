public class OffByN implements CharacterComparator {

    private int interval;

    public OffByN(int N) {
        interval = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == interval;
    }

}
