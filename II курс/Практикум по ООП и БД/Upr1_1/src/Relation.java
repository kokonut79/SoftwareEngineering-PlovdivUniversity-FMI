import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

import javax.swing.*;

public class Relation extends JFrame {
    /**
     * class to create and handle the functionality for Relation panel
     */

    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;
    int id = -1;

    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();


    JComboBox<String> prisonerCombo = new JComboBox<String>();
    JComboBox<String> docsCombo = new JComboBox<String>();
    JLabel prisonerL = new JLabel("Избери затворник");
    JLabel docsL = new JLabel("Избери документ");
    JLabel dateInL = new JLabel("Дата на влизане в затвора");
    JLabel dateOutL = new JLabel("Дата на излизане в затвора");

    JTextField dateInTF = new JTextField();
    JTextField dateOutTF = new JTextField();


    JTable table = new JTable();
    JScrollPane myScroll = new JScrollPane(table);

    JButton addBt = new JButton("Добави");
    JButton deleteBt = new JButton("Изтрий");
    JButton editBt = new JButton("Редактирай");
    JButton refreshBt = new JButton("Обнови");
    JButton returnBt = new JButton("Назад");

    public Relation() {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        // upPanel --------------------------------------
        upPanel.setLayout(new GridLayout(5, 2));
        dateInTF.setToolTipText("yyyy-MM-dd");
        dateOutTF.setToolTipText("yyyy-MM-dd");
        upPanel.add(prisonerL);
        upPanel.add(prisonerCombo);
        upPanel.add(docsL);
        upPanel.add(docsCombo);
        upPanel.add(dateInL);
        upPanel.add(dateInTF);
        upPanel.add(dateOutL);
        upPanel.add(dateOutTF);
        //call the refresh methods so the info will be shown
        refreshDocsCombo();
        refreshPrisonerCombo();

        this.add(upPanel);

        //midPanel---------------------------------------
        midPanel.add(addBt);
        midPanel.add(deleteBt);
        midPanel.add(editBt);
        midPanel.add(refreshBt);
        midPanel.add(returnBt);

        this.add(midPanel);

        // Action listeners
        addBt.addActionListener(new AddAction());
        deleteBt.addActionListener(new DeleteAction());
        refreshBt.addActionListener(new RefreshAction());
        editBt.addActionListener(new EditActionPrisoner());
        returnBt.addActionListener(new returnBt());

        //downPanel -------------------------------------
        myScroll.setPreferredSize(new Dimension(350, 150));
        downPanel.add(myScroll);
        this.add(downPanel);
        refreshTable();
        table.addMouseListener(new MouseAction());
        refreshCombo();

        this.setVisible(true);
    }

    public void refreshCombo() {
        /** Method to refresh the table in real time */

        String sql = "select * from ADDITIONALINFO";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + "." +
                        result.getObject(2).toString() + " " +
                        result.getObject(3).toString() + " " +
                        result.getObject(4).toString();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        /** Refresh table function */
        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from ADDITIONALINFO");
            result = state.executeQuery();
            table.setModel(new MyModel(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clearForm() {
        /** Method to clear the input fields */
        dateInTF.setText("");
        dateOutTF.setText("");
    }


    public void refreshPrisonerCombo() {
        /** Method that gets info from X table and displays it into comboBox */
        prisonerCombo.removeAllItems();
        String sql = "select id, fname, lname from prisoner";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + "." +
                        result.getObject(2).toString() + " " +
                        result.getObject(3).toString();
                prisonerCombo.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void refreshDocsCombo() {
        /** Method that gets info from X table and displays it into comboBox */
        docsCombo.removeAllItems();
        String sql = "select id, sud, prokuror, advokat from info";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + ". Окръжен съд:" +
                        result.getObject(2).toString() + " | Прокурор: " +
                        result.getObject(3).toString() + " | Адвокат: " +
                        result.getObject(4).toString();
                docsCombo.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    class AddAction implements ActionListener {
        /**
         * Class that handles add info to the sqldb
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String comboSTRPrisoner = prisonerCombo.getSelectedItem().toString();
            String comboSTRDocs = docsCombo.getSelectedItem().toString();


            conn = DBConnection.getConnection();
            String sql = "insert into ADDITIONALINFO (idprisoner ,idinfo, osudenvliza, osudenizliza) values(?,?,?,?)";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(comboSTRPrisoner.substring(0, comboSTRPrisoner.indexOf('.')).toString()));
                state.setInt(2, Integer.parseInt(comboSTRDocs.substring(0, comboSTRDocs.indexOf('.')).toString()));
                state.setDate(3, Date.valueOf(dateInTF.getText()));
                state.setDate(4, Date.valueOf(dateOutTF.getText()));

                state.execute();
                refreshPrisonerCombo();
                refreshDocsCombo();
                refreshTable();
                clearForm();
                refreshCombo();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    class EditActionPrisoner implements ActionListener {
        /**
         * Method to edit record in db
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            /** Method to edit already existing register in the database **/
            conn = DBConnection.getConnection();
            if (id > 0) {
                String sql = "update ADDITIONALINFO set osudenvliza=?, osudenizliza=? where id=? ";

                try {
                    state = conn.prepareStatement(sql);
                    state.setDate(1, Date.valueOf(dateInTF.getText()));
                    state.setDate(2, Date.valueOf(dateOutTF.getText()));
                    state.setInt(3, id);
                    state.execute();
                    refreshTable();
                    refreshCombo();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    class MouseAction implements MouseListener {
        /**
         * Class to handle mouse action
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            /** Method that gets values from the input fields */

            int row = table.getSelectedRow();
            id = Integer.parseInt(table.getValueAt(row, 0).toString());
            dateInTF.setText(table.getValueAt(row, 3).toString());
            dateOutTF.setText(table.getValueAt(row, 4).toString());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }


    class DeleteAction implements ActionListener {
        /**
         * Class that handles delete btn functionality
         */

        @Override
        public void actionPerformed(ActionEvent e) {
            /** Method that deletes already existing register in the database */

            conn = DBConnection.getConnection();
            String sql = "delete from ADDITIONALINFO where id=?";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, id);
                state.execute();
                refreshTable();
                refreshCombo();
                clearForm();
                id = -1;
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }


    class RefreshAction implements ActionListener {
        /**
         * Refresh method
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();
        }
    }


    class returnBt implements ActionListener {
        /**
         * Class that handles return btn activity
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current tab and open the new one
            dispose();
            HomePanel frame = new HomePanel();
        }
    }

}
