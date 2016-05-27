package view;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.DynamicModel;
import model.Model;
import model.Node;
import model.Pair;
import view.GanttChart.ExtraData;

// TODO: use date for x-axis
public class MainWindow extends Application {

    private Model m;

    @Override public void start(Stage stage) {

        stage.setTitle("");

        setM();
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < m.getCalculation().length; i++) {
            titles.add(String.valueOf(i));
        }

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("Час ");
        xAxis.setTickLabelFill(Color.BLACK);
        xAxis.setMinorTickCount(4);

        yAxis.setLabel("Номер вершини графа");
        yAxis.setTickLabelFill(Color.BLACK);
        yAxis.setTickLabelGap(20);
        yAxis.setCategories(FXCollections.<String>observableArrayList(titles));

        chart.setTitle("Результати моделювання");
        chart.setLegendVisible(true);
        chart.setBlockHeight(20);

        Pair[] calc = m.getCalculation();
        Pair [] rec = m.getReconfiguration();

        XYChart.Series [] series = new XYChart.Series [calc.length];


        for (int i = 0; i < series.length; i++) {
            series[i] = new XYChart.Series();
            long startCalc = Math.round(calc[i].getStart()*100);
            long lengthCalc = Math.round((calc[i].getEnd()-calc[i].getStart())*100);
            long startRec = Math.round(rec[i].getStart()*100);
            long lengthRec = Math.round((rec[i].getEnd()-rec[i].getStart())*100);
            long synchroTime = Math.round(Model.TIME_OF_LOAD*100);
            series[i].getData().add(new XYChart.Data(startCalc, String.valueOf(i), new ExtraData(lengthCalc, "status-red")));
            series[i].getData().add(new XYChart.Data(startRec, String.valueOf(i), new ExtraData(lengthRec, "status-green")));
            series[i].getData().add(new XYChart.Data(startCalc-synchroTime, String.valueOf(i), new ExtraData(synchroTime, "status-yellow")));
        }

        chart.getData().addAll(series);

        chart.getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());

        Scene scene  = new Scene(chart,620,450);
        stage.setScene(scene);
        stage.show();
    }

    public void setM() {
        ArrayList<Node> tre = new ArrayList<>();
        Node nodes[] = new Node[]{
                new Node(0, 1), new Node(1, 1), new Node(2, 1), new Node(3, 4), new Node(4, 5),
                new Node(5, 6),new Node(6, 7),new Node(7, 8),new Node(8, 9),
                new Node(9, 10),new Node(10, 11),new Node(11, 15),new Node(12, 15),new Node(13, 14),
                new Node(14, 15),new Node(15, 0)};
        tre.addAll(Arrays.asList(nodes));
        int[][] Mc = new int[][]
                {       {0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        for (int i = 0; i < Mc.length; i++) {
            for (int j = 0; j < Mc.length; j++) {
                if (Mc[i][j] == 1) {
                    nodes[i].addChild(nodes[j]);
                }
            }
        }
        m = new DynamicModel(Mc.length);
        m.proceed(tre, Mc);
    }

    public static void main(String[] args) {
        launch(args);
    }
}