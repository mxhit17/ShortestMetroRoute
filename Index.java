import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Index{
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt){
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        // njf
        graph[0].add(new Edge(0, 1, 4));

        // dwk
        graph[1].add(new Edge(1, 0, 4));
        graph[1].add(new Edge(1, 2, 10));
        graph[1].add(new Edge(1, 3, 14));

        // dwk sec 21
        graph[2].add(new Edge(2, 1, 10));
        graph[2].add(new Edge(2, 11, 27));

        // kirti nagar
        graph[3].add(new Edge(3, 1, 14));
        graph[3].add(new Edge(3, 4, 9));
        graph[3].add(new Edge(3, 13, 4));

        // rajiv chowk
        graph[4].add(new Edge(4, 3, 9));
        graph[4].add(new Edge(4, 5, 3));
        graph[4].add(new Edge(4, 8, 8));
        graph[4].add(new Edge(4, 11, 2));

        // central sec
        graph[5].add(new Edge(5, 6, 28));
        graph[5].add(new Edge(5, 7, 21));

        // huda city
        graph[6].add(new Edge(6, 5, 28));

        // badarpur
        graph[7].add(new Edge(7, 5, 21));

        // yamuna bank 
        graph[8].add(new Edge(8, 4, 8));
        graph[8].add(new Edge(8, 9, 12));
        graph[8].add(new Edge(8, 10, 14));

        // vaishali
        graph[9].add(new Edge(9, 8, 12));

        // botanical garden
        graph[10].add(new Edge(10, 8, 14));

        // new delhi
        graph[11].add(new Edge(11, 2, 27));
        graph[11].add(new Edge(11, 4, 2));
        graph[11].add(new Edge(11, 16, 17));

        // mundka
        graph[12].add(new Edge(12, 13, 12));

        // ashok park main
        graph[13].add(new Edge(13, 3, 4));
        graph[13].add(new Edge(13, 12, 12));
        graph[13].add(new Edge(13, 15, 2));

        // rithala
        graph[14].add(new Edge(14, 15, 9));

        // inderlok
        graph[15].add(new Edge(15, 13, 2));
        graph[15].add(new Edge(15, 14, 9));
        graph[15].add(new Edge(15, 17, 16));

        // jahangirpuri
        graph[16].add(new Edge(16, 11, 17));

        // dilshad garden
        graph[17].add(new Edge(17, 15, 16));
    }

    public static void fillMetroStationName(String[] stationName){
        stationName[0] = "najafgarh";
        stationName[1] = "dwarka";
        stationName[2] = "dwarkaSector21";
        stationName[3] = "kirtiNagar";
        stationName[4] = "rajivChowk";
        stationName[5] = "centralSecretariat";
        stationName[6] = "hudaCity";
        stationName[7] = "badarpur";
        stationName[8] = "yamunaBank";
        stationName[9] = "vaishali";
        stationName[10] = "botanicalGarden";
        stationName[11] = "newDelhi";
        stationName[12] = "mundka";
        stationName[13] = "ashokParkMain";
        stationName[14] = "rithala";
        stationName[15] = "inderlok";
        stationName[16] = "jahangirpuri";
        stationName[17] = "dilshadGarden";
    }

    static class Pair implements Comparable<Pair> {
        int n;
        int path;

        public Pair(int n, int path){
            this.n = n;
            this.path = path;
        }
        
        @Override
        public int compareTo(Pair p2){
            return this.path - p2.path;
        }
    }

    public static void dijkstraPath(ArrayList<Edge> graph[], int src, int dest, String[] stationName){
        boolean vis[] = new boolean[graph.length];
        int dist[] = new int[graph.length];
        String path[] = new String[graph.length];

        for(int i = 0; i < dist.length; i++){
            if(i == src){
                dist[i] = 0;
                path[i] = "0";
            }else{
                dist[i] = Integer.MAX_VALUE;
                path[i] = "";
            }
            
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if(!vis[curr.n]){
                vis[curr.n] = true;
                for(int i = 0; i < graph[curr.n].size(); i++){
                    Edge e = graph[curr.n].get(i);
                    if(dist[curr.n] + e.wt < dist[e.dest]){
                        dist[e.dest] = dist[curr.n] + e.wt;
                        pq.add(new Pair(e.dest, dist[e.dest]));
                        path[e.dest] = path[curr.n] + "-" + e.dest;
                    }
                }
            }
        }

        // for(int i = 0; i < path.length; i++){
        //     System.out.println(path[i] + "      ->  " + dist[i]);
        // }
        System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-Distance : " + dist[dest] + "-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println();
        double time = dist[dest] / (double)45;
        System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-Time : " + time + " Hour(s)-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println();
        printPath(path[dest], stationName);
    }

    public static void printPath(String path, String[] stationName){
        ArrayList<String> p = new ArrayList<>();

        String temp = "";
        for(int i = 0; i < path.length(); i++){
            char ch = path.charAt(i);

            if(ch != '-'){
                temp = temp + ch;
            }else{
                p.add(temp);
                temp = "";
            }
        }

        System.out.print("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-Path : ");
        
        for(int i = 0; i < p.size(); i++){
            String t = p.get(i);
            int idx = Integer.parseInt(t);
            System.out.print(stationName[idx] + " -> ");
        }
        System.out.print("Dest-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }

    public static void main(String[] args) {
        String stationName[] = new String[18];
        fillMetroStationName(stationName);

        int V = 18;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        int src = 0;
        int dest = 1;

        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your location(source) : ");
        String s = sc.nextLine();
        // System.out.println();
        System.out.print("Please enter your destination : ");
        String d = sc.nextLine();

        for(int i = 0; i < stationName.length; i++){
            if(s.equals(stationName[i])){
                src = i;
            }
            if(d.equals(stationName[i])){
                dest = i;
            }
        }

        dijkstraPath(graph, src, dest, stationName);
    }
}