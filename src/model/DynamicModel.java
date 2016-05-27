package model;

import java.util.ArrayList;

/**
 * Created by Кумпутер on 15.05.2016.
 */
public class DynamicModel extends Model {
    public DynamicModel(int n) {
        super(n);
    }

    @Override
    public void proceed(ArrayList<Node> graph, int[][] Mc) {
        double baseTime = 0;
        double curRec = 0;
        for (int i = 0; i < graph.size(); i++) {
            Node curNode = graph.get(i);
            if (!readyInstructions.contains(curNode.getInstruction())) {
                reconfiguration[curNode.getNumber()] = new Pair(curRec, curRec + Node.instructions[curNode.getInstruction()][1]);
                curRec = curRec + Node.instructions[curNode.getInstruction()][1];
                readyInstructions.add(curNode.getInstruction());
            }

            if (curRec < getMaxTime(Mc, i)) {
                curRec = getMaxTime(Mc, curNode.getNumber())+TIME_OF_LOAD;
            }

            ArrayList<Node> childs = curNode.getChilds();
            for (int j = 0; j < childs.size(); j++) {
                Node child = childs.get(j);
                if (!readyInstructions.contains(child.getInstruction())) {
                    reconfiguration[child.getNumber()] = new Pair(curRec, curRec + Node.instructions[child.getInstruction()][1]);
                    curRec = curRec + Node.instructions[child.getInstruction()][1];
                    readyInstructions.add(child.getInstruction());
                }
            }
            if (reconfiguration[curNode.getNumber()].getEnd() > getMaxTime(Mc, i)) {
                calculation[curNode.getNumber()] = new Pair(reconfiguration[curNode.getNumber()].getEnd()+TIME_OF_LOAD,
                        reconfiguration[curNode.getNumber()].getEnd() + TIME_OF_LOAD + Node.instructions[curNode.getInstruction()][0]);
            } else {
                calculation[curNode.getNumber()] = new Pair(getMaxTime(Mc, curNode.getNumber()) + TIME_OF_LOAD,
                        getMaxTime(Mc, curNode.getNumber()) + TIME_OF_LOAD + Node.instructions[curNode.getInstruction()][0]);
            }
        }
    }
}
