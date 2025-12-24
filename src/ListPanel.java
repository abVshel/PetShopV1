import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class ListPanel extends JPanel {

    public JTable table;
    JTextField txtSearch;

    public ListPanel(PetTableManager manager,
                     Runnable goMenu,
                     Runnable editAction,
                     Runnable deleteAction){

        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(Color.decode("#B6CBBD"));

        table = new JTable(manager.tableModel);
        TableRowSorter sorter = new TableRowSorter(manager.tableModel);
        table.setRowSorter(sorter);

        JPanel top = new JPanel(new BorderLayout(5,5));
        txtSearch = new JTextField();

        txtSearch.addActionListener(e -> {
            String key = txtSearch.getText();

            try {
                sorter.setRowFilter(
                        key.isEmpty() ? null : RowFilter.regexFilter("(?i)" + key)
                );

                if(table.getRowCount()==0){
                    throw new Exception("Data tidak ditemukan!");
                }

            } catch (Exception ex){
                JOptionPane.showMessageDialog(null,
                        "Data tidak ditemukan!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);

                sorter.setRowFilter(null);
                txtSearch.setText("");
            }
        });

        top.add(new JLabel("Cari: "), BorderLayout.WEST);
        top.add(txtSearch, BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton btnEdit = new JButton("✏ Edit");
        ListStyle.edit(btnEdit);

        JButton btnDelete = new JButton("🗑 Hapus");
        ListStyle.delete(btnDelete);

        JButton btnBack = new JButton("⬅ Kembali");
        ListStyle.back(btnBack);

        btnEdit.addActionListener(e -> editAction.run());
        btnDelete.addActionListener(e -> deleteAction.run());
        btnBack.addActionListener(e -> goMenu.run());

        bottom.add(btnEdit);
        bottom.add(btnDelete);
        bottom.add(btnBack);

        add(top,BorderLayout.NORTH);
        add(new JScrollPane(table),BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }
}

/* ================= STYLE ================= */
class ListStyle {

    public static void edit(JButton btn){
        btn.setBackground(Color.decode("#CA7842"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
    }

    public static void delete(JButton btn){
        btn.setBackground(Color.decode("#4B352A"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
    }

    public static void back(JButton btn){
        btn.setBackground(Color.decode("#B2CD9C"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
    }
}
