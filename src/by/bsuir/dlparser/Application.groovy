package by.bsuir.dlparser
import groovy.swing.SwingBuilder
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset

import javax.swing.JFrame
import java.awt.BorderLayout

class Application {
    static initDemoPlots(ApplicationForm form){
        (0..7).each {i ->
            def dataSet = new DefaultCategoryDataset()
            5.times {
                dataSet.addValue(it, "a", "${it}")
            }

            def barChart = ChartFactory.createBarChart3D("Chart ${i}",
                    "category axis label ",
                    "value axis label ",
                    dataSet,
                    PlotOrientation.VERTICAL,
                    false,
                    false,
                    false
            )


            def chartPanel = new ChartPanel(barChart, false)

            form."plot${i}Panel".setLayout(new BorderLayout())
            form."plot${i}Panel".add(chartPanel, BorderLayout.PAGE_START);

            form."plot${i}Panel".revalidate()
        }
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
