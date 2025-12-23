import javax.swing.*;
import java.awt.*;

public class PetShopApp extends JFrame {

    CardLayout card = new CardLayout();
    JPanel mainPanel = new JPanel(card);

    PetTableManager manager = new PetTableManager();

    ListPanel listPanel;
    HistoryPanel historyPanel;
    FormPanel formPanel;

    int editIndex = -1;

    public PetShopApp(){
        //judul
        setTitle("Pet Shop Management");
        setSize(950,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.setBackground(Color.decode("#FFF5E6"));

        DashboardPanel dash = new DashboardPanel(
                () -> card.show(mainPanel,"table"),
                () -> openForm(),
                () -> card.show(mainPanel,"history")
        );

        listPanel = new ListPanel(manager,
                () -> card.show(mainPanel,"menu"),
                this::editData,
                this::deleteData);

        historyPanel = new HistoryPanel(manager,
                () -> card.show(mainPanel,"menu"));

        formPanel = new FormPanel(this,manager);

        mainPanel.add(dash,"menu");
        mainPanel.add(listPanel,"table");
        mainPanel.add(historyPanel,"history");
        mainPanel.add(formPanel,"form");

        add(mainPanel);
        mainPanel.setBackground(Color.decode("#B6CBBD"));
        card.show(mainPanel,"menu");
    }

    void openForm(){
        editIndex = -1;
        formPanel.openNew();
        card.show(mainPanel,"form");
    }

    void editData(){
        int row = listPanel.table.getSelectedRow();
        if(row<0) return;

        row = listPanel.table.convertRowIndexToModel(row);
        editIndex = row;

        formPanel.openEdit(manager.petList.get(row));
        card.show(mainPanel,"form");
    }

    void deleteData(){
        int row = listPanel.table.getSelectedRow();
        if(row<0) return;

        row = listPanel.table.convertRowIndexToModel(row);
        manager.petList.remove(row);
        manager.saveToFile();
        manager.refreshTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PetShopApp().setVisible(true));
    }
}
