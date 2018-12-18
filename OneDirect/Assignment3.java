import java.util.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

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
            getImmediateChildren(mp, registered_nodes);
        } else if (userchoice == 3) {
            ancestors(mp, registered_nodes);
        } else if (userchoice == 4) {
            descendants(mp, registered_nodes);
        } else if (userchoice == 5) {
            deleteDependancy(mp, registered_nodes);
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
                System.out.print(b.parent_node.elementAt(i) + " ");
            }
        } else {
            System.out.println("\nUnregistered Node : ");
        }
    }

    public static void getImmediateChildren(HashMap<Integer, bigGraph> mp, HashSet<Integer> s) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeid;
        System.out.println("\nEnter the nodeid to find the immediate children : ");
        nodeid = Integer.parseInt(br.readLine());
        if (s.contains(nodeid)) {
            bigGraph b = new bigGraph();
            b = mp.get(nodeid);
            System.out.println("\nGetting Immediate Children nodes : ");
            for (int i = 0; i < b.child_node.size(); i++) {
                System.out.print(b.child_node.elementAt(i) + " ");
            }
        } else {
            System.out.println("\nUnregistered Node : )");
        }
    }

    public static void helper_ancestor(HashMap<Integer, bigGraph> mp, int node, Vector<Boolean> visited) {
        if (visited.get(node) == false) {
            bigGraph b = mp.get(node);
            for (int i = 0; i < b.parent_node.size(); i++) {
                System.out.print(b.parent_node.get(i) + " ");
                visited.set(b.parent_node.get(i), true);
            }
        }
    }

    public static void ancestors(HashMap<Integer, bigGraph> mp, HashSet<Integer> s) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nEnter the node to find his ancestors : )");
        int nodeid;
        nodeid = Integer.parseInt(br.readLine());
        if (s.contains(nodeid)) {
            bigGraph b = mp.get(nodeid);
            Vector<Boolean> visited = new Vector<>();
            for (int i = 0; i < 10000; i++) {
                visited.add(false);
            }

            for (int i = 0; i < b.parent_node.size(); i++) {
                System.out.print(b.parent_node.elementAt(i) + " ");
                helper_ancestor(mp, b.parent_node.get(i), visited);
            }

        } else {
            System.out.println("\nUnregistered Node : ) ");
        }
    }

    public static void helper_descendant(HashMap<Integer, bigGraph> mp, Vector<Boolean> visited, int node) {
        if (visited.get(node) == false) {
            bigGraph b = mp.get(node);
            for (int i = 0; i < b.child_node.size(); i++) {
                System.out.print(b.child_node.get(i) + " ");
                visited.set(b.child_node.get(i), true);
            }
        }
    }

    public static void descendants(HashMap<Integer, bigGraph> mp, HashSet<Integer> s) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter the node to find his descendants : )");
        int nodeid;
        nodeid = Integer.parseInt(br.readLine());
        if (s.contains(nodeid)) {
            bigGraph b = mp.get(nodeid);
            Vector<Boolean> visited = new Vector<>();
            for (int i = 0; i < 10000; i++) {
                visited.add(false);
            }
            for (int i = 0; i < b.child_node.size(); i++) {
                System.out.print(b.child_node.get(i) + " ");
                helper_descendant(mp, visited, b.child_node.get(i));
            }
        } else {
            System.out.println("\nUnregistered Node : )");
        }
    }

    public static void deleteDependancy(HashMap<Integer, bigGraph> mp, HashSet<Integer> s) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nEnter the parent node : ) ");
        int parent_node;
        parent_node = Integer.parseInt(br.readLine());
        if (s.contains(parent_node)) {
            int child_node;
            System.out.println("\nEnter the Child Node : ) ");
            child_node = Integer.parseInt(br.readLine());
            if (s.contains(child_node)) {
                bigGraph b1 = mp.get(parent_node);
                bigGraph b2 = mp.get(child_node);
                for (int i = 0; i < b1.child_node.size(); i++) {
                    if (b1.child_node.get(i) == child_node) {
                        b1.child_node.remove(i);
                        // b2.parent_node.remove(child_node);
                        for (int j = 0; j < b2.parent_node.size(); j++) {
                            if (b2.parent_node.get(j) == child_node) {
                                b2.parent_node.remove(j);
                                break;
                            }
                        }
                        System.out.println("\nDependancy Deleted : ");
                        return;
                    }
                }
                System.out.println("Sorry there is no relationship between these two nodes : )");
                return;
            } else {
                System.out.println("Not a valid Child Node :) ");
            }
        } else {
            System.out.println("Not a valid node : )");
        }
    }

    public static void deleteNode(HashMap<Integer, bigGraph> mp, HashSet<Integer> s) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nPlease enter the node to be deleted : )");
        int nodeid = Integer.parseInt(br.readLine());
        if (s.contains(nodeid)) {
            // we need to remove all the dependancy to the node;
            // we can get all the nodes which are connected to our node by refering
            // parent_node,child_node vector
            bigGraph b1 = mp.get(nodeid);
            for (int i = 0; i < b1.child_node.size(); i++) {
                int child = b1.child_node.get(i);
                bigGraph b2 = mp.get(child);
                for (int j = 0; j < b2.parent_node.size(); j++) {
                    if (b2.parent_node.get(j) == nodeid) {
                        b2.parent_node.remove(j);
                        break;
                    }
                }
                b1.child_node.set(i, 0);
            }

            for (int i = 0; i < b1.parent_node.size(); i++) {
                int parent = b1.parent_node.get(i);
                bigGraph b2 = mp.get(parent);
                for (int j = 0; j < b2.child_node.size(); j++) {
                    if (b2.child_node.get(j) == nodeid) {
                        b2.child_node.remove(j);
                        break;
                    }
                }
                b1.parent_node.set(i, 0);
            }
            System.out.println("\nSuccessfully deleted a node and it's dependancies :  )");
        } else {
            System.out.println("\nUnregistered Node : )");
        }
    }

    public static boolean cycleCheck(HashMap<Integer, bigGraph> mp, HashSet<Integer> s) throws IOException {

    }

    public static void newDependancy(HashMap<Integer, bigGraph> mp, HashSet<Integer> s) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int parent_node, child_node;
        System.out.println("\nEnter the parent node : ) ");
        parent_node = Integer.parseInt(br.readLine());
        if (s.contains(parent_node)) {
            System.out.println("\nEnter the child node ; ) ");
            child_node = Integer.parseInt(br.readLine());
            if (s.contains(child_node)) {
                bigGraph b1 = mp.get(parent_node);
                bigGraph b2 = mp.get(child_node);
                if (b1.child_node.contains(child_node)) {
                    System.out.println("\nRelationship already exists : ) ");
                    return;
                } else {
                    // first form a relation, then check if there exists a cycle;
                    b1.child_node.add(child_node);
                    b2.parent_node.add(parent_node);
                    // now dependancy is formed.
                    // now check for cycle;
                    if (cycleCheck(mp, s)) {
                        System.out.println("\nThis relationship forms a cycle : )");
                        // we need to remove the formed relation ship;
                        b1.child_node.remove(b1.child_node.size() - 1);
                        b2.parent_node.remove(b2.parent_node.size() - 1);
                    } else {
                        System.out.print("\nNew Dependancy added : )");
                    }
                }
            } else {
                System.out.println("\nUnregistered Child Node : )");
            }
        } else {
            System.out.println("\nUnregistered Parent node : )");
            return;
        }
    }

}
