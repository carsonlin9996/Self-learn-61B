import java.util.ArrayList;
import java.util.List;

public class Way {
    Long ID;
    List<Long> wayList;
    private String maxSpeed;

    //constructor contains ID and list of ref in an ArrayList
    public Way(Long id) {
        this.ID = id;
        wayList = new ArrayList<>();
    }

    public void addRef(Long ref){
        //adds a new ref(ID) to the wayList.
        wayList.add(ref);
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getMaxSpeed(){
        return maxSpeed;
    }

    public List getWayList(){
        return wayList;
    }
}
