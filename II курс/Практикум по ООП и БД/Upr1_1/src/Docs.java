import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Docs extends JFrame {
    /**
     * class to create and handle the functionality for Docs panel
     */

    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;
    int id = -1;

    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();

    JLabel fsvedeniqL = new JLabel("Сведения");
    JLabel lnamePurviSvidetelL = new JLabel("Име на първи свидетел(задължително)");
    JLabel sexL = new JLabel("Пол на свидетеля");
    JLabel fnameProkuror = new JLabel("Прокурор");
    JLabel fnameAdvokat = new JLabel("Адвокат");
    JLabel fnameSud = new JLabel("Съд");

    JTextField fnameTF = new JTextField();
    JTextField lnameTF = new JTextField();
    JTextField ageTF = new JTextField();
    JTextField reasonTF = new JTextField();
    JTextField fnameSudTF = new JTextField();

    String[] item = {"Мъж", "Жена"};
    JComboBox<String> sexCombo = new JComboBox<String>(item);
    JComboBox<String> infoCombo = new JComboBox<String>();

    JTable table = new JTable();
    JScrollPane myScroll = new JScrollPane(table);

    JButton addBt = new JButton("Добави");
    JButton deleteBt = new JButton("Изтрий");
    JButton editBt = new JButton("Редактирай");
    JButton searchBt = new JButton("Търси");
    JButton refreshBt = new JButton("Обнови");
    JButton returnBt = new JButton("Назад");

    public Docs() {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        // upPanel --------------------------------------
        upPanel.setLayout(new GridLayout(6, 2));
        upPanel.add(fsvedeniqL);
        upPanel.add(fnameTF);
        upPanel.add(lnamePurviSvidetelL);
        upPanel.add(lnameTF);
        upPanel.add(sexL);
        upPanel.add(sexCombo);
        upPanel.add(fnameProkuror);
        upPanel.add(ageTF);
        upPanel.add(fnameAdvokat);
        upPanel.add(reasonTF);
        upPanel.add(fnameSud);
        upPanel.add(fnameSudTF);

        this.add(upPanel);

        //midPanel---------------------------------------
        midPanel.add(addBt);
        midPanel.add(deleteBt);
        midPanel.add(editBt);
        midPanel.add(searchBt);
        midPanel.add(refreshBt);
        midPanel.add(infoCombo);
        midPanel.add(returnBt);

        this.add(midPanel);

        // Action listeners
        addBt.addActionListener(new AddAction());
        deleteBt.addActionListener(new DeleteAction());
        searchBt.addActionListener(new SearchAction());
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
        /** Method to refresh the table in real time **/

        infoCombo.removeAllItems();

        String sql = "select id, svedeniq, svidetel1, sex, prokuror, advokat, sud from INFO";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + "." +
                        result.getObject(2).toString() + " " +
                        result.getObject(3).toString();
                infoCombo.addItem(item);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        /** refresh table function **/
        conn = DBConnection.getConnection();
        try {
            state = conn.prepareStatement("select * from INFO");
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
        /** Method to clear the input fields **/
        fnameTF.setText("");
        lnameTF.setText("");
        ageTF.setText("");
        reasonTF.setText("");
        fnameSudTF.setText("");
    }


    class AddAction implements ActionListener {
        /**
         * Class to handle btn click
         **/

        @Override
        public void actionPerformed(ActionEvent e) {
            /** Method wich is binded with the btn to add new register to the database **/

            conn = DBConnection.getConnection();
            String sql = "insert into INFO(svedeniq, svidetel1, sex, prokuror, advokat, sud) values(?,?,?,?,?,?)";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1, fnameTF.getText());
                state.setString(2, lnameTF.getText());
                state.setString(3, sexCombo.getSelectedItem().toString());
                state.setString(4, ageTF.getText());
                state.setString(5, reasonTF.getText());
                state.setString(6, fnameSudTF.getText());
                state.execute();
                refreshTable();
                refreshCombo();
                clearForm();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }


    class EditActionPrisoner implements ActionListener {
        /**
         * Class to handle btn click
         **/

        @Override
        public void actionPerformed(ActionEvent e) {
            /** Method to edit already existing register in the database **/

            conn = DBConnection.getConnection();
            if (id > 0) {
                String sql = "update info set svedeniq=?, svidetel1=?, sex=?, prokuror=?, advokat=?, sud=? where id=?";

                try {
                    state = conn.prepareStatement(sql);

                    state.setString(1, fnameTF.getText());
                    state.setString(2, lnameTF.getText());
                    state.setString(3, sexCombo.getSelectedItem().toString());
                    state.setString(4, ageTF.getText());
                    state.setString(5, reasonTF.getText());
                    state.setString(6, fnameSudTF.getText());
                    state.setInt(7, id);
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
         **/
        @Override
        public void mouseClicked(MouseEvent e) {
            /** Method that gets values from the input fields**/

            int row = table.getSelectedRow();
            id = Integer.parseInt(table.getValueAt(row, 0).toString());
            fnameTF.setText(table.getValueAt(row, 1).toString());
            lnameTF.setText(table.getValueAt(row, 2).toString());
            ageTF.setText(table.getValueAt(row, 4).toString());
            reasonTF.setText(table.getValueAt(row, 5).toString());
            fnameSudTF.setText(table.getValueAt(row, 6).toString());
            if (table.getValueAt(row, 3).toString().equals("???")) {
                sexCombo.setSelectedIndex(0);
            } else {
                sexCombo.setSelectedIndex(1);
            }


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
         **/
        @Override
        public void actionPerformed(ActionEvent e) {
            /** Method that deletes already existing register in the database **/

            conn = DBConnection.getConnection();
            String sql = "delete from INFO where id=?";

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


    class SearchAction implements ActionListener {
        /**
         * Class that handles search btn functionality
         **/

        @Override
        public void actionPerformed(ActionEvent e) {
            /** Method that handles searching logic **/

            conn = DBConnection.getConnection();
            String sql = "select * from INFO where sud=?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, fnameSudTF.getText());
                result = state.executeQuery();
                table.setModel(new MyModel(result));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }


    class RefreshAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();
        }
    }


    class returnBt implements ActionListener {
        /**
         * Class that handles return btn activity
         **/

        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current tab and open the new one
            dispose();
            HomePanel frame = new HomePanel();
        }
    }

}
