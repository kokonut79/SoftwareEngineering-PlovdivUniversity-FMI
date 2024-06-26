import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JFrame {
    /**
     * class that handles navigation through panels
     */


    JPanel encapsulate = new JPanel();
    JButton NavigateToPrisonersFrame = new JButton("Prisoners");
    JButton NavigateTDocsFrame = new JButton("Docs");
    JButton NavigateToRelationFrame = new JButton("Relation");
    JButton NavigateToSearchFrame = new JButton("Search");


    public HomePanel() {

        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        encapsulate.setLayout(new GridLayout(4, 1));
        encapsulate.add(NavigateToPrisonersFrame);
        encapsulate.add(NavigateTDocsFrame);
        encapsulate.add(NavigateToRelationFrame);
        encapsulate.add(NavigateToSearchFrame);
        this.add(encapsulate);

        NavigateToPrisonersFrame.addActionListener(new NavigateToPrisonersFrameFunc());
        NavigateTDocsFrame.addActionListener(new NavigateToDocsFrameFunc());
        NavigateToRelationFrame.addActionListener(new NavigateToRelationFrameFunc());
        NavigateToSearchFrame.addActionListener(new NavigateToSearchFrameFunc());
        this.setVisible(true);
    }


    class NavigateToPrisonersFrameFunc implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current tab and open the new one
            dispose();
            Prisoners frameName = new Prisoners();
        }
    }


    class NavigateToDocsFrameFunc implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current tab and open the new one
            dispose();
            Docs frameName = new Docs();
        }
    }


    class NavigateToRelationFrameFunc implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current tab and open the new one
            dispose();
            Relation frameName = new Relation();
            //Relation frameName = new Relation();
        }
    }


    class NavigateToSearchFrameFunc implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current tab and open the new one
            dispose();
            Search frameName = new Search();
        }
    }

}