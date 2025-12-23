import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FormPanel extends JPanel {

    JTextField txtWaktu, txtHarga;
    JComboBox<String> cbHewan, cbKategori, cbItem;

    PetShopApp app;
    PetTableManager manager;

    int editIndex = -1;

    class ItemData {
        String name;
        double price;
        ItemData(String n, double p){ name=n; price=p; }
    }

    ArrayList<ItemData> list = new ArrayList<>();

    public FormPanel(PetShopApp app, PetTableManager manager){
        this.app = app;
        this.manager = manager;

        setLayout(new GridLayout(7,2,10,10));
        setBorder(new EmptyBorder(40,300,40,300));
        setBackground(Color.decode("#B6CBBD"));

        txtWaktu = new JTextField();
        txtWaktu.setEditable(false);

        cbHewan = new JComboBox<>(new String[]{"Kucing","Anjing"});
        cbKategori = new JComboBox<>(new String[]{"Makanan","Kesehatan","Aksesoris"});
        cbItem = new JComboBox<>();
        txtHarga = new JTextField();
        txtHarga.setEditable(false);

        cbHewan.addActionListener(e -> updateItem());
        cbKategori.addActionListener(e -> updateItem());
        cbItem.addActionListener(e -> {
            int idx = cbItem.getSelectedIndex();
            if(idx>=0 && idx < list.size())
                txtHarga.setText(String.valueOf(list.get(idx).price));
        });

        JButton btnSave = new JButton("Simpan");
        FormStyle.save(btnSave);

        JButton btnBack = new JButton("Kembali");
        FormStyle.back(btnBack);

        btnSave.addActionListener(e -> saveData());
        btnBack.addActionListener(e -> app.card.show(app.mainPanel,"table"));

        add(new JLabel("Waktu"));
        add(txtWaktu);
        add(new JLabel("Hewan"));
        add(cbHewan);
        add(new JLabel("Kategori"));
        add(cbKategori);
        add(new JLabel("Item"));
        add(cbItem);
        add(new JLabel("Harga"));
        add(txtHarga);
        add(btnSave);
        add(btnBack);
    }

    public void openNew(){
        editIndex = -1;
        txtHarga.setText("");
        txtWaktu.setText(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        updateItem();
    }

    public void openEdit(Pet p){
        editIndex = manager.petList.indexOf(p);
        txtWaktu.setText(p.waktu);
        cbHewan.setSelectedItem(p.hewan);
        cbKategori.setSelectedItem(p.kategori);
        updateItem();
        cbItem.setSelectedItem(p.item);
        txtHarga.setText(String.valueOf(p.harga));
    }

    void updateItem(){
        cbItem.removeAllItems();
        list.clear();

        String h = cbHewan.getSelectedItem().toString();
        String k = cbKategori.getSelectedItem().toString();

        if(h.equals("Kucing") && k.equals("Makanan")){
            list.add(new ItemData("Royal Canin 1kg",150000));
            list.add(new ItemData("Whiskas 1kg",80000));
            list.add(new ItemData("Bolt 1kg",25000));
            list.add(new ItemData("Nature Bridge 1kg",100000));
            list.add(new ItemData("Majes 1kg",190000));
        }

        if(h.equals("Kucing") && k.equals("Kesehatan")){
            list.add(new ItemData("Obat kutu spray 30ml",20000));
            list.add(new ItemData("Suplemen gel",150000));
            list.add(new ItemData("Obat jamur 30ml",25000));
            list.add(new ItemData("Tetes mata 30ml",30000));
            list.add(new ItemData("Vitamin bulu",45000));
        }

        if(h.equals("Kucing") && k.equals("Aksesoris")){
            list.add(new ItemData("Satu set baju",50000));
            list.add(new ItemData("Bando",10000));
            list.add(new ItemData("Topi",15000));
            list.add(new ItemData("Tas",30000));
            list.add(new ItemData("Kalung nama",50000));
        }

        if(h.equals("Anjing") && k.equals("Makanan")){
            list.add(new ItemData("Majes 1kg",500000));
            list.add(new ItemData("Nature Bridge 1kg",150000));
            list.add(new ItemData("Nice Dog 1kg",25000));
            list.add(new ItemData("Pro Plan 1kg",100000));
            list.add(new ItemData("Royal Canin 1kg",70000));
        }

        if(h.equals("Anjing") && k.equals("Kesehatan")){
            list.add(new ItemData("Obat kutu spray 30ml",30000));
            list.add(new ItemData("Suplemen gel",160000));
            list.add(new ItemData("Obat jamur 30ml",25000));
            list.add(new ItemData("Tetes mata 30ml",40000));
            list.add(new ItemData("Vitamin bulu",55000));
        }

        if(h.equals("Anjing") && k.equals("Aksesoris")){
            list.add(new ItemData("Satu set baju",110000));
            list.add(new ItemData("Bando",12000));
            list.add(new ItemData("Topi",15000));
            list.add(new ItemData("Tas",30000));
            list.add(new ItemData("Kalung nama",50000));
        }

        for(ItemData i : list) cbItem.addItem(i.name);

        if(!list.isEmpty()){
            cbItem.setSelectedIndex(0);
            txtHarga.setText(String.valueOf(list.get(0).price));
        }
    }

    void saveData(){
        try {
            double harga = Double.parseDouble(txtHarga.getText());

            Pet p = new Pet(
                    txtWaktu.getText(),
                    cbHewan.getSelectedItem().toString(),
                    cbKategori.getSelectedItem().toString(),
                    cbItem.getSelectedItem().toString(),
                    harga
            );

            if(editIndex>=0)
                manager.petList.set(editIndex,p);
            else
                manager.petList.add(p);

            manager.saveToFile();
            manager.refreshTable();

            app.card.show(app.mainPanel,"table");

        } catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    "Harga tidak valid!");
        }
    }
}

/* ================= STYLE ================= */
class FormStyle {
    public static void save(JButton btn){
        btn.setBackground(Color.decode("#7ED6A5"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
    }

    public static void back(JButton btn){
        btn.setBackground(Color.decode("#A685E2"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
    }
}

