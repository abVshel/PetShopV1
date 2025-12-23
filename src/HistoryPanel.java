import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class HistoryPanel extends JPanel {

    public JTable table;

    public HistoryPanel(PetTableManager manager, Runnable goMenu){

        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(Color.decode("#B6CBBD"));

        table = new JTable(manager.tableModel);
        TableRowSorter sorter = new TableRowSorter(manager.tableModel);
        table.setRowSorter(sorter);

        JPanel bottom = new JPanel();
        JButton btnBack = new JButton("⬅ Kembali");
        HistoryStyle.back(btnBack);

        btnBack.addActionListener(e -> goMenu.run());
        bottom.add(btnBack);

        add(new JScrollPane(table),BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }
}

/* ================= STYLE ================= */
class HistoryStyle {
    public static void back(JButton btn){
        btn.setBackground(Color.decode("#B2CD9C"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
    }
}
