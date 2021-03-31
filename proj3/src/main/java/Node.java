import java.util.ArrayList;
import java.util.List;

public class Node {
    double lat;
    double lon;
    long ID;
    List<Long> connectionID;

    public Node(long id, double lat, double lon){
        this.ID = id;
        this.lat = lat;
        this.lon = lon;
        //an arrayList that stores all the connected vertices' ID
        connectionID = new ArrayList<>();
    }


    //adds the ID to the connectionId list, shows the node is connected to some other nodes.
    public void addConnectionID(long id){
        if(!connectionID.contains(id) && id != ID) {
            connectionID.add(id);
        }
    }

    //returns the list of connectionID's associated with the current node.
    public List getConnection() {
        return connectionID;
    }

    //returns the ID of the current node.
    public Long getID() {
        return ID;
    }
}
