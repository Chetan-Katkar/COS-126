public class Tour {
    private class Node {
        private Point p;
        private Node  next;
    }

    Node first, a ,b , c ,d, s;//Reference varaiables

    // creates an empty tour
    public        Tour(){
        first = null;
    }

    // creates the 4-point tour a->b->c->d->a (for debugging)
    public        Tour(Point a, Point b, Point c, Point d) {

        //allocates the memory to the referece variables
        this.a =  new Node();
        this.a.p = a;
        this.b = new Node();
        this.b.p = b;
        this.c = new Node();
        this.c.p = c;
        this.d = new Node();
        this.d.p = d;

        first = this.a; //first now refers to first node of circular link list.
        this.a.next = this.b;  // a points to b
        this.b.next = this.c;  // b points to c
        this.c.next = this.d;  // c points to d
        this.d.next = this.a;  // d points back to a (circular link)
    }
    
    // returns the number of points in this tour
    public    int size(){
        if(first == null){//checks if the link list or Tour is empty if so returns 0.
            return 0;
        }
        else {
            Node current = first;//current assign the first node or starting node for traversal.
            int count = 1; //starts from first existing node so count already equals 1.
            while (current.next != first) {
                count++;
                current = current.next;
            }
            return count;
        }
    }

    // returns the length of this tour
    public double length(){
        if(first == null){//checks if the link list or Tour is empty if so returns 0.
            return 0.0;
        }
        double length = 0.0 ;//stores the length
        Node current = first;
        do {
                length += current.p.distanceTo(current.next.p);
                current = current.next;
        } while (current != first);

        return length;

    }

    // returns a string representation of this tour
    public String toString(){
        if (first == null) {//checks if the link list/Tour is empty if so returns empty string.
            return "";
        }
        else {
            StringBuilder sb = new StringBuilder();
            Node current = first;
            for(int i = 0 ;  i < size() ; i++){
                sb.append(current.p.toString());
                if(i +1 < size()) {
                    sb.append("\n");
                }
                current = current.next;
            }
            return sb.toString();
        }
    }
    // draws this tour to standard drawing
    public   void draw(){
        if(first == null){//checks if the link list/Tour is empty
            return ; //draws nothing.
        }
        else{
            Node point1 = first;//points the firs node.
            Node point2 = first.next;//points the second node.
            for(int i = 0 ; i < size()  ; i++) {
                point1.p.drawTo(point2.p);
                //updating point1 and point2
                point1 = point2;
                point2 = point2.next;
            }
        }
    }

    // inserts p using the nearest neighbor heuristic
    public   void insertNearest(Point p){
        if (first == null ){//checks if tour is empty and assign the first node as head of list
            Node node = new Node();
            node.next = node;//circular link
            node.p = p;
            first = node;
        }
        else {
            Node current = first;
            Point closest_point = first.p;//assigns the closest point as first node.
            double closest_dist = p.distanceTo(current.p);//assigns the distance between the first node and point p as minimum.
            for (int i = 0; i < size() - 1; i++) {
                current = current.next;
                //check and updates the minimum distance.
                if (closest_dist > p.distanceTo(current.p))
                {
                    closest_dist = p.distanceTo(current.p);
                    closest_point = current.p;

                }
            }
            Node node = new Node();//node to be insert after closest node.
            current = first;//again updating current node as first node

            //finds for closet_point node.
            while (current.p != closest_point) {
                current = current.next;//current node node will hold the closest node at the end of while loop.
            }
            //insert the node after the closest point node.
            node.next = current.next;
            current.next = node;
            node.p = p;
        }
    }

    // inserts p using the smallest increase heuristic
    public void insertSmallest(Point p) {
        if (first == null) {
            // If the tour is empty, create the first node with the point p
            Node node = new Node();
            node.p = p;
            node.next = node; // Circular link
            first = node;
        } else {
            Node current = first;
            Node bestNode = null; // Node after which we insert p
            double smallestIncrease = Double.MAX_VALUE;//assurs first valid calculation is smallest.

            do {
                // Compute the increase in tour length if p is inserted between current and current.next
                double currentIncrease = current.p.distanceTo(p) + p.distanceTo(current.next.p) - current.p.distanceTo(current.next.p);

                // Update if this insertion gives a smaller increase
                if (currentIncrease < smallestIncrease) {
                    smallestIncrease = currentIncrease;
                    bestNode = current; // Remember the node after which to insert
                }

                current = current.next;
            } while (current != first); // Loop through the entire tour

            // Insert p after bestNode
            Node Node = new Node();
            Node.p = p;
            Node.next = bestNode.next;
            bestNode.next = Node;
        }
    }

    // tests this class by directly calling all constructors and instance methods
    public static void main(String[] args){

        int width,hieght;

        width = StdIn.readInt();
        hieght = StdIn.readInt();

        StdDraw.setXscale(0,width);
        StdDraw.setYscale(0,hieght);


        Tour tour = new Tour();

        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point p = new Point(x, y);
            tour.insertSmallest(p);
            // tour.insertNearest(p);
        }

        System.out.println("Tour length: " + tour.length());
        System.out.println("Number of points: " + tour.size());
        tour.draw();
    }
}
