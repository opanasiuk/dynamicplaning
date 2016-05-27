package model;

import java.util.ArrayList;

/**
 * Created by Sasha on 02.02.2016.
 */
public class Node {

    public static double[][] instructions = new double[][]{{0.5, 0.1}, {0.8, 0.1}, {2, 0.1}, {1.5, 0.1}, {2.5, 0.1}, {2, 0.1}, {0.5, 0.1}, {0.5, 0.1},{0.5, 0.1}
                                                        , {1.2, 0.1}, {2.3, 0.1}, {1.8, 0.1}, {0.6, 0.1}, {0.7, 0.1},{0.9, 0.1},{0.2, 0.1}};

    private int number;
    private int instruction;
    private ArrayList<Node> childs;

    public Node(int number, int instruction) {
        this.number = number;
        this.instruction = instruction;
        childs = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getInstruction() {
        return instruction;
    }

    public void setInstruction(int instruction) {
        this.instruction = instruction;
    }

    public ArrayList<Node> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Node> childs) {
        this.childs = childs;
    }

    public void addChild(Node child) {
        childs.add(child);
    }
}
