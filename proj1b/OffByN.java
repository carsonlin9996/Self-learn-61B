public class OffByN implements CharacterComparator{

    private int offByN;

    public OffByN(int N){
        offByN = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        int diff = x - y;

        if(Math.abs(diff) == offByN){
            return true;
        }
        return false;
    }
}
