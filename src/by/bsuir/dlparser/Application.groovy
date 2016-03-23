package by.bsuir.dlparser
import groovy.swing.SwingBuilder
import javax.swing.JFrame

class Application {
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
    }

    static void main(String[] args){
        initWindow()
    }
}
