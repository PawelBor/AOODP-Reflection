package ie.gmit.sw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
//GUI class + file picker 
public class FacePanel {
	//make new Jframe
    static JFrame frame;

    static void displayJFrame() {
        frame = new JFrame("JAR Stability Calculator");

        //create ImgIcon 
        //ImageIcon imgIcon = new ImageIcon("jaricon.png");
        
        // create showTableButton with title + set its size
        JButton showTableBtn = new JButton("Select JAR File"); // , imgIcon
        showTableBtn.setPreferredSize(new Dimension(400, 100));
        JButton newJarFileBtn = new JButton("<html><b>New Window:<br> JAR Select</b></html>");
        
        JLabel jrStab = new JLabel("<html><b>JAR Stability Calculator</b></html>");
        jrStab.setFont(new Font("Serif", Font.PLAIN, 30));
        // add the listener to the jbutton to handle the "pressed" event
        showTableBtn.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {

                String path = "";

                MetricCalculator metCalc = new MetricCalculator(fileC(path));
                //Hide the button once it is clicked (That's the open JAR button) - first button on first frame
                showTableBtn.hide();
                jrStab.hide();

                String[] tableHeader = {
                    "ClassName",
                    "InDegree",
                    "OutDegree",
                    "Stability"
                };
                //create table with data from MetricCalculator and the headers from tableHeader
                JTable table = new JTable(metCalc.getData(), tableHeader);
                //create 2 panels
                JPanel panel = new JPanel();
                JPanel statusBar = new JPanel();
                //make border for statusBar and make it lowered bevel type
                statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //labe jl with info
                JLabel jl = new JLabel("<html>G00313594<br>Pawel Borzym<br>Adv.OOP<br>GMIT</html>");
                //add label to statusBar
                statusBar.add(jl);

                table.getColumnModel().getColumn(0).setPreferredWidth(200);
                panel.add(new JScrollPane(table), BorderLayout.CENTER);
                //make new component + set font and font color
                Component text = new JLabel("<html><b>(IN)Afferent Couplings (Ca):</b><br>The number of edges incident on a type, i.e. the number of types with a dependency on another type." + "<br><br><b>(OUT)Efferent Couplings (Ce):</b><br> the number of edges emanating from a type, i.e. the number of types that a given type is dependent upon.</html>");
                text.setFont(new Font("Serif", Font.PLAIN, 14));
                text.setForeground(Color.getHSBColor(0, 1, (float) 0.5));
                //create close button
                JButton btnClose = new JButton("Close");
                //listener for close button
                btnClose.addActionListener(new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);

                    }
                });
                //new jar file listener
                newJarFileBtn.addActionListener(new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	displayJFrame();

                    }
                });
                //add stuff to frame and set its layout
                frame.getContentPane().setLayout(new BorderLayout());
                frame.getContentPane().add(panel, BorderLayout.NORTH);
                frame.getContentPane().add(text, BorderLayout.CENTER);
                frame.getContentPane().add(btnClose, BorderLayout.SOUTH);
                frame.getContentPane().add(statusBar, BorderLayout.EAST);
                frame.getContentPane().add(newJarFileBtn, BorderLayout.WEST);

                //Result frame
                frame.setSize(800, 700);
                frame.setVisible(true);
            }
        });

        // put the button on the frame
        frame.getContentPane().setLayout(new FlowLayout());
        frame.add(jrStab);
        frame.add(showTableBtn);
        

        // set up the JAR Selection Frame + add colors
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.getHSBColor(0, 0, (float) 0.75));
    }
    //file chooser for selecting JAR file
    private static String fileC(String path) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choose Your File");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setAcceptAllFileFilterUsed(false);
            	//print directory and selected file else print no selection
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : " + chooser.getSelectedFile());

            } else {
                System.out.println("No Selection ");
            }
            //assign file path to path and return that path
            path = chooser.getSelectedFile().toString();
            return path;
        } //fileC
}//FacePanel