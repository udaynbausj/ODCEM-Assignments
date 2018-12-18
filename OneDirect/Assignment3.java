import java.util.*;
import java.io.*;

class bigGraph {
    int id;
    String name;
    Vector<String> additional_info = new Vector<>();
    Vector<Integer> child_node = new Vector<>();
    Vector<Integer> parent_node = new Vector<>();

    bigGraph() {
        id = 0;
    }

    public void setData(int nodeid, String name) {
        id = nodeid;
        this.name = name;
    }
}

public class assignment4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        System.out.println("\nHow many Vertices are you planning ?\n");
        int vertices, edges, node1, node2, nodeid;
        String nodename, shit;
        vertices = Integer.parseInt(br.readLine());
        bigGraph bg[] = new bigGraph[10000];

        // Learnt a big Lesson
        for (int i = 0; i < 10000; i++) {
            bg[i] = new bigGraph();
        }

        HashSet<Integer> registered_nodes = new HashSet<>();
        System.out.println("\nEnter 'Node id' , 'Node name' : \n");
        // bg[0].setData(0, "");
        for (int i = 0; i < vertices; i++) {
            String str = br.readLine();
            String[] tokens = str.split(" ");
            nodeid = Integer.parseInt(tokens[0]);
            nodename = tokens[1];
            // shit = br.readLine();
            registered_nodes.add(nodeid);
            if (registered_nodes.contains(nodeid) == false) {
                System.out.println("Not found : ");
            }
            bg[nodeid].id = nodeid;
            bg[nodeid].name = nodename;
            // bg[nodeid].name = nodename;
            // bg[nodeid].setData(nodeid, nodename);
        }
        HashMap<Integer, bigGraph> mp = new HashMap<>();
        System.out.println("\nHow many edges are you planning  ? \n");
        edges = Integer.parseInt(br.readLine());
        System.out.println("\nEnter source node first, dest node second : \n");
        for (int i = 0; i < edges; i++) {
            String str = br.readLine();
            String[] tokens = str.split(" ");
            node1 = Integer.parseInt(tokens[0]);
            node2 = Integer.parseInt(tokens[1]);
            if (registered_nodes.contains(node1)) {
                addedge(mp, node1, node2, bg);
            } else {
                System.out.println("\nYou have entered Unregistered node : ");
            }
        }
        System.out.println("\nPlease select one of the options : \n\n");
        System.out.println("\n1.Get the immediate parents of a node, passing the node id as input parameter.");
        System.out.println("\n2.Get the immediate children of a node, passing the node id as input parameter.");
        System.out.println("\n3.Get the ancestors of a node, passing the node id as input parameter.");
        System.out.println("\n4.Get the descendants of a node, passing the node id as input parameter.");
        System.out.println("\n5.Delete dependency from a tree, passing parent node id and child node id.");
        System.out.println(
                "\n6.Delete a node from a tree, passing node id as input parameter. This should delete all the dependencies of the node.");
        System.out.println(
                "\n7.Add a new dependency to a tree, passing parent node id and child node id. This should check for cyclic dependencies.");
        System.out.println(
                "\n8.Add a new node to tree. This node will have no parents and children. Dependency will be established by calling the 7 number API.");
        System.out.println("\n\n");
        int userchoice;

        System.out.println("\nPlease enter the choice : ");

        userchoice = Integer.parseInt(br.readLine());
        if (userchoice == 1) {
            getImmediateParents(mp, registered_nodes);

        } else if (userchoice == 2) {

        } else if (userchoice == 3) {

        } else if (userchoice == 4) {

        } else if (userchoice == 5) {

        } else if (userchoice == 6) {

        } else if (userchoice == 7) {

        } else if (userchoice == 8) {

        } else {
            System.out.println("\nNot a valid Number : )");
        }
    }

    public static void addedge(HashMap<Integer, bigGraph> mp, int source, int dest, bigGraph bg[]) {
        bg[source].child_node.add(dest);
        bg[dest].parent_node.add(source);
        mp.put(source, bg[source]);
        mp.put(dest, bg[dest]);
    }

    public static void getImmediateParents(HashMap<Integer, bigGraph> mp, HashSet<Integer> s) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nodeid;
        System.out.println("\nPlease enter the node id : ");
        nodeid = Integer.parseInt(br.readLine());
        if (s.contains(nodeid)) {
            bigGraph b = new bigGraph();
            b = mp.get(nodeid);
            System.out.println("\nGetting Immediate parent nodes : ) \n");
            for (int i = 0; i < b.parent_node.size(); i++) {
                System.out.println(b.parent_node.elementAt(i) + " ");
            }
        } else {
            System.out.println("\nUnregistered Node : ");
        }
    }

}
