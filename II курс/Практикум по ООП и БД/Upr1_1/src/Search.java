import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search extends JFrame {
    /**
     * Class that satisfies last problem
     **/

    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;
    int id = -1;

    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();

    JTable table = new JTable();
    JScrollPane myScroll = new JScrollPane(table);

    JButton searchBt = new JButton("Търси");
    JButton NavigateToHomeBt = new JButton("Назад");

    JLabel prisonerNameL = new JLabel("Име на затворник:");
    JTextField prisonerNameTF = new JTextField();

    JLabel courtNameL = new JLabel("Име на окръжен съд:");
    JTextField courtNameTF = new JTextField();

    public Search() {

        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        //NavigateToXFrame.addActionListener(new NavigateToXFrame());


        // START upPanel --------------------------------------
        upPanel.setLayout(new GridLayout(5, 2));
        upPanel.add(prisonerNameL);
        upPanel.add(prisonerNameTF);
        upPanel.add(courtNameL);
        upPanel.add(courtNameTF);

        this.add(upPanel);


        // START midPanel---------------------------------------
        midPanel.add(searchBt);
        midPanel.add(NavigateToHomeBt);
        this.add(midPanel);


        // START ACTION LISTENERS
        NavigateToHomeBt.addActionListener(new NavigateToHome());
        searchBt.addActionListener(new SearchAction());


        //downPanel -------------------------------------
        myScroll.setPreferredSize(new Dimension(350, 150));
        downPanel.add(myScroll);
        this.add(downPanel);
        refreshTable();

        this.setVisible(true);
    }

    public void refreshTable() {
        /** Refresh table function */
        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from ADDITIONALINFO");
            result = state.executeQuery();
            table.setModel(new MyModel(result));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class NavigateToHome implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current tab and open the new one
            dispose();
            HomePanel goHome = new HomePanel();
        }
    }

    class SearchAction implements ActionListener {
        /**
         * Class that handles the search function
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();

            String sql = "SELECT P.FNAME, I.SUD\n" +
                    "FROM ADDITIONALINFO R\n" +
                    "JOIN PRISONER P ON R.IDPRISONER = P.ID\n" +
                    "JOIN INFO I ON I.ID = R.IDINFO\n" +
                    "WHERE P.FNAME = ? AND I.SUD = ?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, prisonerNameTF.getText());
                state.setString(2, courtNameTF.getText());
                result = state.executeQuery();
                table.setModel(new MyModel(result));
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }


}


