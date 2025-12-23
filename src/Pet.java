public class Pet {
    String jenis, waktu, hewan, kategori, item;
    double harga;

    public Pet(String w, String h, String k, String i, double hg) {
        waktu = w; hewan = h; kategori = k; item = i; harga = hg;
    }

    String toCSV() {
        return waktu + "," + hewan + "," + kategori + "," + item + "," + harga;
    }

    static Pet fromCSV(String s) {
        String[] d = s.split(",");
        return new Pet(d[0], d[1], d[2], d[3], Double.parseDouble(d[4]));
    }
}
