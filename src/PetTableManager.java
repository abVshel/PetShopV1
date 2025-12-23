import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

public class PetTableManager {

    public ArrayList<Pet> petList = new ArrayList<>();
    public final String FILE_NAME = "petshop_data.csv";
    public DefaultTableModel tableModel;

    public PetTableManager() {
        tableModel = new DefaultTableModel(
                new String[]{"Waktu","Hewan","Kategori","Item","Harga"},0
        ){
            @Override
            public boolean isCellEditable(int r,int c){ return false; }
        };

        loadData();
        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for(Pet p : petList){
            tableModel.addRow(new Object[]{
                    p.waktu,p.hewan,p.kategori,p.item,p.harga
            });
        }
    }

    public void saveToFile() {
        try(PrintWriter pw = new PrintWriter(FILE_NAME)){
            pw.println("Tanggal,Hewan,Kategori,Item,Harga");
            for(Pet p : petList) pw.println(p.toCSV());
        }catch(Exception ignored){}
    }

    public void loadData() {
        File f = new File(FILE_NAME);
        if(!f.exists()) return;

        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            boolean first = true;
            while((line = br.readLine())!=null){
                if(first){ first=false; continue; }
                petList.add(Pet.fromCSV(line));
            }
        }catch(Exception ignored){}
    }
}
