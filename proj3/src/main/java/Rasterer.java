import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    private final double SL = 288200;
    private final double lonDifference = MapServer.ROOT_LRLON - MapServer.ROOT_ULLON;
    private final double latDifference = MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT;
    private final double startPixel = ((MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / MapServer.TILE_SIZE) * SL;

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        // System.out.println(params);
        double queryLRLON = params.get("lrlon");
        double queryLRLAT = params.get("lrlat");
        double queryULLON = params.get("ullon");
        double queryULLAT = params.get("ullat");

        //queryWidth in pixels.
        double queryWidth = params.get("w");

        //queryHeight in pixels.
        double queryHeight = params.get("h");

        boolean querySuccess = queryCheck(queryULLON, queryULLAT, queryLRLON, queryLRLAT);

        double LonDpp = calculateLonDPP(queryULLON, queryLRLON, queryWidth);

        int depth = calculateDepth(LonDpp);

        double[] longitude = getLong(depth, queryULLON, queryLRLON);
        double[] latitude = getLat(depth, queryULLAT, queryLRLAT);

        String[][] grid = renderGrid((int)longitude[1], (int)longitude[3], (int)latitude[1], (int)latitude[3], depth);
        //String[][] renderGrid = renderGrid(depth, (int) longitude[1], (int) longitude[3], (int) latitude[1], (int) latitude[3]);
        System.out.println("depth is " + depth);

        //System.out.println(params);


        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", grid);
        results.put("raster_ul_lon", longitude[0]);
        results.put("raster_ul_lat", latitude[0]);
        results.put("raster_lr_lon", longitude[2]);
        results.put("raster_lr_lat", latitude[2]);
        results.put("depth", depth);
        results.put("query_success", querySuccess);

        //System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
                          // + "your browser.");
        return results;
    }


    private double calculateLonDPP(double ullon, double lrlon, double width) {
        double xDist = lrlon - ullon;
        System.out.println(xDist);

        double lonDPP = xDist * SL / width;

        return lonDPP;
    }

    private int calculateDepth(double LonDpp) {
        int depth = 0;
        double initialDPP = lonDifference * SL / MapServer.TILE_SIZE;

        while(true){
            if(depth == 7){
               return depth;
            }
            if(LonDpp > initialDPP){
                return depth;
            }

            initialDPP = initialDPP / 2;
            depth += 1;
        }
    }

    private double[] getLat(int depth, double queryULLAT, double queryLRLAT){
        double[] lat = new double[4];
        double tileHeight = latDifference / Math.pow(2, depth);
        double startULLAT = MapServer.ROOT_ULLAT;
        double startLRLAT = MapServer.ROOT_LRLAT;
        int counterMax = 0;
        int counterMin = (int) Math.pow(2, depth) - 1;
        while(true){
            if(queryULLAT > startULLAT - tileHeight){
                lat[0] = startULLAT;
                lat[1] = counterMax;
                break;
            }
            counterMax += 1;
            startULLAT -= tileHeight;
        }

        while(true) {
            if(queryLRLAT <  startLRLAT + tileHeight) {
                lat[2] = startLRLAT;
                lat[3] = counterMin;
                break;
            }
            counterMin -= 1;
            startLRLAT += tileHeight;
        }
        return lat;
    }

    private double[] getLong(int depth, double queryULLON, double queryLRLON) {
        double[] lon = new double[4];
        double tileWidth = lonDifference / Math.pow(2, depth);
        double startULLON = MapServer.ROOT_ULLON;
        double endLRLON = MapServer.ROOT_LRLON;

        int counterStart = 0;
        int counterEnd = (int) Math.pow(2, depth) - 1;
        while(true) {
            if(queryULLON < startULLON + tileWidth) {
                lon[0] = startULLON;
                lon[1] = counterStart;
                break;
            }
            startULLON += tileWidth;
            counterStart += 1;
        }

        while(true) {
            if(queryLRLON > endLRLON - tileWidth) {
                lon[2] = endLRLON;
                lon[3] = counterEnd;
                break;
            }
            endLRLON -= tileWidth;
            counterEnd -= 1;
        }
      return lon;
    }

    private String[][] renderGrid(int startX, int endX, int startY, int endY, int depth) {
        System.out.println(endY - startY);
        System.out.println(endX - startX);
        String[][] grid = new String[endY - startY + 1][endX - startX + 1];

        for(int i = startY; i <= endY; i++) {
            for(int j = startX; j <= endX; j++) {
                grid[i - startY][j - startX] = "d" + depth + "_x" + j + "_y" + i + ".png";
            }
        }

        return grid;
    }


    //checks if params are in bound.
    private boolean queryCheck(double ullon, double ullat, double lrlon, double lrlat){
        if(ullon > MapServer.ROOT_LRLON || ullat < MapServer.ROOT_LRLAT
                || lrlon < MapServer.ROOT_ULLON || lrlat > MapServer.ROOT_ULLAT) {
            return false;
        }
        return true;
    }

}
