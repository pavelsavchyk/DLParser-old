package by.bsuir.dlparser
import groovy.swing.SwingBuilder
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.general.SeriesException
import org.jfree.data.time.Second
import org.jfree.data.time.TimeSeries
import org.jfree.data.time.TimeSeriesCollection
import org.jfree.data.xy.XYDataset

import javax.swing.JFrame
import java.awt.BorderLayout

class Application {
    static initDemoPlots(ApplicationForm form){

        def createDataset = { Number offset ->
            final TimeSeries series = new TimeSeries( "Random Data" );
            Second current = new Second( );
            int value = 1;
            for (int i = 0; i < 20; i++)
            {
                try
                {
                    series.add(current, new Double( value + offset ) );
                    current = ( Second ) current.next( );
                    value ^= 1;
                }
                catch ( SeriesException e )
                {
                    System.err.println("Error adding to series");
                }
            }

            // return new TimeSeriesCollection(series);
            return series;
        }

        def drawTimeDiagram = { ->
            TimeSeriesCollection allData = new TimeSeriesCollection();
            (3).times {
                def data = createDataset(1.1 * it)
                allData.addSeries(data)
            }
            def barChart = ChartFactory.createXYStepChart("Chart", "", "", allData)


            def chartPanel = new ChartPanel(barChart, false)

            form.plotPanel.setLayout(new BorderLayout())
            form.plotPanel.add(chartPanel, BorderLayout.PAGE_START);
            form.plotPanel.revalidate()
        }

        drawTimeDiagram()
    }

    static initWindow(){
        def form = new ApplicationForm()
        def frame = new SwingBuilder().frame(
                title:"DLParser",
                contentPane: form.mainPanel,
                defaultCloseOperation: JFrame.EXIT_ON_CLOSE,
        )


        frame.pack()
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH)
        frame.setVisible(true)

        initDemoPlots(form)
    }

    static void main(String[] args){
        initWindow()
    }
}
