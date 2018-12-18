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
            nodeid = Integer.parseInt(br.readLine());
            nodename = br.readLine();
            // shit = br.readLine();
            bg[nodeid].id = nodeid;
            bg[nodeid].name = nodename;
            // bg[nodeid].name = nodename;
            // bg[nodeid].setData(nodeid, nodename);
        }
        HashMap<Integer, bigGraph> mp;
        System.out.println("\nHow many edges are you planning  ? \n");
        edges = Integer.parseInt(br.readLine());
        System.out.println("\nEnter source node first, dest node second : \n");
        for (int i = 0; i < edges; i++) {
            node1 = Integer.parseInt(br.readLine());
            node2 = Integer.parseInt(br.readLine());
            if (!registered_nodes.contains(node1)) {

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
        System.out.println("\n\n\n");
        int userchoice;
        userchoice = Integer.parseInt(br.readLine());
        if (userchoice == 1) {

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
}
