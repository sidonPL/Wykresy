import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Scanner;

class LineChartEx extends JFrame {
    static Scanner scan = new Scanner(System.in);

    public LineChartEx() {
        initUI();
    }

    private void initUI() {
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        pack();
        setTitle("Wykres");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {
        float[] x = new float[1000];
        float[] y = new float[1000];
        System.out.println("Podaj ilosc zmiennych");
        int n=scan.nextInt();

        for(int i=0;i<n;i++)
        {
            System.out.println("Podaj zmienna x"+(i+1));
            x[i]=scan.nextFloat();
            System.out.println("Podaj zmienna y"+(i+1));
            y[i]=scan.nextFloat();
        }
        var series = new XYSeries("Dane");
        for(int i=0;i<n;i++)
        {
            series.add(x[i],y[i]);
        }

        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }


    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Tytul",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.setTitle(new TextTitle("Wykres", new Font("Serif", java.awt.Font.BOLD, 18)));
        return chart;
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new LineChartEx();
            ex.setVisible(true);
        });
    }
}