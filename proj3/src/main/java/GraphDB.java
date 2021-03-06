import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {

    public class nodes{
        double lat;
        double lon;
        long id;
        List<Long> addConnectionID;
        public nodes(double lon, double lat, long id){
            this.lat = lat;
            this.lon = lon;
            this.id = id;
            addConnectionID = new ArrayList<Long>();
        }

        public List getAllAdjacents(){
            return addConnectionID;
        }

        public Long getID(){
            return id;
        }
        /*public void addConnectionID(long id){
            if(!addConnectionID.contains(id) && id != this.id) {
                addConnectionID.add(id);
            }
        }*/

    }

    public class ways{
        Long wayID;
        List<Long> nodesInWay;
        private String maxSpeed;
        public ways(long id) {
            wayID = id;
            nodesInWay = new ArrayList<Long>();
        }

        public void addRef(long ref) {
            nodesInWay.add(ref);
        }

        public void setMax(String s) {
            maxSpeed = s;
        }

        public List returnWayList() {
            return nodesInWay;
        }
    }



    /** Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc. */

    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     * @param dbPath Path to the XML file to be parsed.
     */

    Map<Long, nodes> bearMap;

    public GraphDB(String dbPath) {
        try {
            bearMap = new HashMap<>();
            File inputFile = new File(dbPath);
            FileInputStream inputStream = new FileInputStream(inputFile);
            // GZIPInputStream stream = new GZIPInputStream(inputStream);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputStream, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {

        //computes the length of all the nodes;
        /*Object[] allNodes = bearMap.keySet().toArray();

        for(int i = 0; i < allNodes.length; i++) {

            Node current = bearMap.get(allNodes[i]);
            //returns a list, if the returned list is empty, remove that node;
            if(current.getConnection().size() == 0) {
                bearMap.remove(current.getID());
            }
        }*/
        Object[] nodes = bearMap.keySet().toArray();
        for(int i = 0; i < nodes.length; i++) {
            nodes curr = bearMap.get(nodes[i]);
            if(curr.getAllAdjacents().isEmpty()) {
                bearMap.remove(curr.getID());
            }
        }
        // TODO: Your code here.
    }

    /**
     * Returns an iterable of all vertex IDs in the graph.
     * @return An iterable of id's of all vertices in the graph.
     */
    Iterable<Long> vertices() {
        //YOUR CODE HERE, this currently returns only an empty list.
        return bearMap.keySet();
    }

    /**
     * Returns ids of all vertices adjacent to v.
     * @param v The id of the vertex we are looking adjacent to.
     * @return An iterable of the ids of the neighbors of v.
     */
    Iterable<Long> adjacent(long v) {
        return bearMap.get(v).getAllAdjacents();
    }

    /**
     * Returns the great-circle distance between vertices v and w in miles.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The great-circle distance between the two locations from the graph.
     */
    double distance(long v, long w) {
        return distance(lon(v), lat(v), lon(w), lat(w));
    }

    static double distance(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double dphi = Math.toRadians(latW - latV);
        double dlambda = Math.toRadians(lonW - lonV);

        double a = Math.sin(dphi / 2.0) * Math.sin(dphi / 2.0);
        a += Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda / 2.0) * Math.sin(dlambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 3963 * c;
    }

    /**
     * Returns the initial bearing (angle) between vertices v and w in degrees.
     * The initial bearing is the angle that, if followed in a straight line
     * along a great-circle arc from the starting point, would take you to the
     * end point.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The initial bearing between the vertices.
     */
    double bearing(long v, long w) {
        return bearing(lon(v), lat(v), lon(w), lat(w));
    }

    static double bearing(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double lambda1 = Math.toRadians(lonV);
        double lambda2 = Math.toRadians(lonW);

        double y = Math.sin(lambda2 - lambda1) * Math.cos(phi2);
        double x = Math.cos(phi1) * Math.sin(phi2);
        x -= Math.sin(phi1) * Math.cos(phi2) * Math.cos(lambda2 - lambda1);
        return Math.toDegrees(Math.atan2(y, x));
    }

    /**
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    long closest(double lon, double lat) {

        //determines the length of the entire bearMap nodes
        //stores all the keys in the array
        //Long[] allNodes = bearMap.keySet().toArray(new Long[0]);
        long closestID = 0;
        double shortestDis = Double.MAX_VALUE;
        //iterates through the entire node list;
        for(long i : bearMap.keySet()) {
            nodes current = bearMap.get(i);
            double dis = distance(current.lon, current.lat, lon, lat);
            if(dis < shortestDis) {
                shortestDis = dis;
                closestID = current.getID();
            }
        }
        return closestID;
    }

    /**
     * Gets the longitude of a vertex.
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    double lon(long v) {
        return bearMap.get(v).lon;
    }

    /**
     * Gets the latitude of a vertex.
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    double lat(long v) {
        return bearMap.get(v).lat;
    }

    public void addNode(Long id, nodes n){
        bearMap.put(id, n);
    }

    public nodes getNode(Long v) {
        return bearMap.get(v);
    }


    //Connect the IDs to each Node list
    /*public void addConnection(Way way) {
        List<Long> list = way.getWayList();
        if(list.size() == 2) {
            Node first = bearMap.get(list.get(0));
            Node second = bearMap.get(list.get(1));

            first.addConnectionID(second.getID());
            second.addConnectionID(first.getID());
        } else {
            for(int i = 1; i < list.size() - 1; i++) {
                Node curr = bearMap.get(list.get(i));
                Node pre = bearMap.get(list.get(i - 1));
                Node next = bearMap.get(list.get(i + 1));

                curr.addConnectionID(next.getID());
                curr.addConnectionID(pre.getID());
                next.addConnectionID(curr.getID());
                pre.addConnectionID(curr.getID());

            }
        }
    }*/
}
