package search;

public class Less implements Compare{
    @Override
    public Boolean compare(int a, int b) {
        return a < b;
    }
}
