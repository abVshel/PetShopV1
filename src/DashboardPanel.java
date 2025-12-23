import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel(Runnable openList,
                          Runnable openForm,
                          Runnable openHistory){

        setLayout(new GridLayout(4,1,15,15));
        setBorder(new EmptyBorder(80,300,80,300));
        setBackground(Color.decode("#B6CBBD"));

        JLabel title = new JLabel("PET SHOP SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 32));

        JButton btnList = new JButton("List Data");
        DashboardStyle.menu(btnList);

        JButton btnInput = new JButton("Input Data");
        DashboardStyle.menu(btnInput);

        JButton btnHistory = new JButton("Riwayat Transaksi");
        DashboardStyle.menu(btnHistory);

        btnList.addActionListener(e -> openList.run());
        btnInput.addActionListener(e -> openForm.run());
        btnHistory.addActionListener(e -> openHistory.run());

        add(title);
        add(btnList);
        add(btnInput);
        add(btnHistory);
    }
}

/* ================= STYLE ================= */
class DashboardStyle {
    public static void menu(JButton btn){
        btn.setBackground(Color.decode("#504B38"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Poppins", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
    }
}
