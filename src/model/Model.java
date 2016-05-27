package model;

import java.util.ArrayList;

/**
 * Created by Sasha on 08.02.2016.
 */
public class Model {
//    public static void main(String[] args) {
////        ArrayList<Node> tre = new ArrayList<>();
////        Node nodes[] = new Node[]{new Node(0, 0), new Node(1, 0), new Node(2, 1), new Node(3, 2), new Node(4, 3),
////                new Node(5, 4),new Node(6, 5),new Node(7, 0),new Node(8, 8),new Node(9, 7),new Node(10, 6),new Node(11, 2),new Node(12, 2),new Node(13, 3)};
////        tre.addAll(Arrays.asList(nodes));
////        int[][] Mc = new int[][]
////                {       {0,1,1,1,0,0,0,0,0,0,0,0,0,0},
////                        {0,0,0,0,1,0,0,0,0,0,0,0,0,0},
////                        {0,0,0,0,1,1,0,0,0,0,0,0,0,0},
////                        {0,0,0,0,0,0,1,0,0,0,0,0,0,0},
////                        {0,0,0,0,0,0,0,1,1,0,0,0,0,0},
////                        {0,0,0,0,0,0,0,0,0,1,0,0,0,0},
////                        {0,0,0,0,0,0,0,0,0,0,1,0,0,0},
////                        {0,0,0,0,0,0,0,0,0,0,0,1,0,0},
////                        {0,0,0,0,0,0,0,0,0,0,0,1,0,0},
////                        {0,0,0,0,0,0,0,0,0,0,0,0,1,0},
////                        {0,0,0,0,0,0,0,0,0,0,0,0,1,0},
////                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1},
////                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1},
////                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
//        ArrayList<Node> tre = new ArrayList<>();
//        Node nodes[] = new Node[]{
//                new Node(0, 1), new Node(1, 2), new Node(2, 3), new Node(3, 4), new Node(4, 5),
//                new Node(5, 6),new Node(6, 7),new Node(7, 8),new Node(8, 9),
//                new Node(9, 10),new Node(10, 11),new Node(11, 12),new Node(12, 13),new Node(13, 14),
//                new Node(14, 15),new Node(15, 0)};
//        tre.addAll(Arrays.asList(nodes));
//        int[][] Mc = new int[][]
//                {       {0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
//                        {0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0},
//                        {0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0},
//                        {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
//        for (int i = 0; i < Mc.length; i++) {
//            for (int j = 0; j < Mc.length; j++) {
//                if (Mc[i][j] == 1) {
//                    nodes[i].addChild(nodes[j]);
//                }
//            }
//        }
//        Model m = new Model(Mc.length);
//        m.proceed(tre, Mc);
//        System.out.println(Arrays.toString(m.getCalculation()));
//        System.out.println(Arrays.toString(m.getReconfiguration()));
//
//
//
//    }


    protected int N;

    public Pair[] getCalculation() {
        return calculation;
    }

    public Pair[] getReconfiguration() {
        return reconfiguration;
    }

    protected Pair[] calculation;
    protected Pair[] reconfiguration;

    public static final double TIME_OF_LOAD = 0.1;


    static ArrayList<Integer> readyInstructions = new ArrayList<>();


    public Model(int n) {
        N = n;
        calculation = new Pair[n];
        reconfiguration = new Pair[n];
        for (int i = 0; i < n; i++) {
            reconfiguration[i] = new Pair();
            calculation[i] = new Pair();
        }
    }


    public void proceed(ArrayList<Node> graph, int[][] Mc) {
        double currentTimeOfRecon = 0;
        for (int i = 0; i < graph.size(); i++) {

            Node currentNode = graph.get(i);
            if (!readyInstructions.contains(currentNode.getInstruction())) {
                reconfiguration[currentNode.getNumber()] = new Pair(currentTimeOfRecon, currentTimeOfRecon + Node.instructions[currentNode.getInstruction()][1]);
                currentTimeOfRecon = currentTimeOfRecon + Node.instructions[currentNode.getInstruction()][1];
                readyInstructions.add(currentNode.getInstruction());
            }
            ArrayList<Node> childs = currentNode.getChilds();
            for (int j = 0; j < childs.size(); j++) {
                Node child = childs.get(j);
                if (!readyInstructions.contains(child.getInstruction())) {
                    reconfiguration[child.getNumber()] = new Pair(currentTimeOfRecon, currentTimeOfRecon + Node.instructions[child.getInstruction()][1]);
                    currentTimeOfRecon = currentTimeOfRecon + Node.instructions[child.getInstruction()][1];
                    readyInstructions.add(child.getInstruction());
                }
            }
//            if (freeNodes.contains(i)) {
//                calculation[i] = new Pair(0.0d, Node.instructions[currentNode.getInstruction()][0]);
//            } else {
                if (reconfiguration[i].getEnd()>getMaxTime(Mc, i)) {
                    calculation[i] = new Pair(reconfiguration[i].getEnd(),
                            reconfiguration[i].getEnd() + Node.instructions[currentNode.getInstruction()][0]);
                } else {
                    calculation[i] = new Pair(getMaxTime(Mc, i)+TIME_OF_LOAD,
                            getMaxTime(Mc, i)+TIME_OF_LOAD + Node.instructions[currentNode.getInstruction()][0]);
                }
//            }
        }
    }

    protected ArrayList<Integer> getFreeNodes(int[][] Mc) {
        ArrayList<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < Mc.length; i++) {
            int sum = 0;
            for (int j = 0; j < Mc[i].length; j++) {
                sum += Mc[j][i];
            }
            if (sum == 0) {
                nodes.add(i);
            }
        }
        return nodes;
    }

    protected double getMaxTime(int[][] Mc, int k) {
        double max = 0;
        for (int j = 0; j < Mc.length; j++) {
            if (Mc[j][k] == 1) {
                if (max < calculation[j].getEnd()) {
                    max = calculation[j].getEnd();
                }
            }
        }
        return max;
    }
}
