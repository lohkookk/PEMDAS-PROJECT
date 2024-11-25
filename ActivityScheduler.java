import java.io.*;
import java.util.*;

class Kegiatan {
    String nama;
    String deskripsi;
    String tanggal;
    String waktu;
    String tempat;

    Kegiatan(String nama, String deskripsi, String tanggal, String waktu, String tempat) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.tempat = tempat;
    }

    @Override
    public String toString() {
        return "Kegiatan: " + nama + "\nDeskripsi: " + deskripsi + "\nTanggal: " + tanggal + "\nWaktu: " + waktu + "\nLokasi: " + tempat;
    }
}

public class ActivityScheduler {
    private Kegiatan[] aktivitas;
    private int count = 0;
    private static final int MAX_ACTIVITIES = 10;

    public ActivityScheduler() {
        aktivitas = new Kegiatan[MAX_ACTIVITIES];
    }

    public static void main(String[] args) {
        ActivityScheduler scheduler = new ActivityScheduler();
        scheduler.memuatData();
        scheduler.displayMenu();
    }

    private void displayMenu() {
        Scanner pemdas = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n____Sistem Penjadwalan Kegiatan____");
            System.out.println("1. Tambah Kegiatan");
            System.out.println("2. Tampilkan Kegiatan");
            System.out.println("3. Simpan Data");
            System.out.println("4. Muat Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            choice = pemdas.nextLine();

            if (choice.equals("1")) {
                nambahKegiatan(pemdas);
            } else if (choice.equals("2")) {
                menampilkanKegiatan();
            } else if (choice.equals("3")) {
                menyimpanData();
            } else if (choice.equals("4")) {
                memuatData();
            } else if (!choice.equals("5")) {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }

        } while (!choice.equals("5"));

        System.out.println("Keluar dari aplikasi.");
        pemdas.close();
    }

    private void nambahKegiatan(Scanner pemdas) {
        if (count >= MAX_ACTIVITIES) {
            System.out.println("Kapasitas penuh! Tidak dapat menambah kegiatan lagi.");
            return;
        }

        System.out.print("Masukkan nama kegiatan: ");
        String nama = pemdas.nextLine();
        System.out.print("Masukkan deskripsi: ");
        String deskripsi = pemdas.nextLine();
        System.out.print("Masukkan tanggal: ");
        String tanggal = pemdas.nextLine();
        System.out.print("Masukkan waktu (WIB): ");
        String waktu = pemdas.nextLine();
        System.out.print("Masukkan lokasi: ");
        String tempat = pemdas.nextLine();

        aktivitas[count] = new Kegiatan(nama, deskripsi, tanggal, waktu, tempat);
        count++;
        System.out.println("Kegiatan ditambahkan.");
    }

    private void menampilkanKegiatan() {
        System.out.println("\n--- Daftar Kegiatan ---");
        for (int i = 0; i < count; i++) {
            System.out.println(aktivitas[i]);
            System.out.println("__________________________________");
            System.out.println();
        }
    }

    private void menyimpanData() {
        try (PrintWriter pemdas = new PrintWriter(new FileWriter("ListKegiatan.txt"))) {
            for (int i = 0; i < count; i++) {
                Kegiatan activity = aktivitas[i];
                pemdas.println("======================================================================="); 
                pemdas.println("Nama: " + activity.nama);
                pemdas.println("Deskripsi: " + activity.deskripsi);
                pemdas.println("Tanggal: " + activity.tanggal + " WIB");
                pemdas.println("Waktu: " + activity.waktu);
                pemdas.println("Lokasi: " + activity.tempat);
                pemdas.println("======================================================================="); 
                pemdas.println();
            }
            System.out.println("Data berhasil disimpan.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data.");
        }
    }

    private void memuatData() {
        File file = new File("ListKegiatan.txt");
        if (!file.exists()) {
            System.out.println("File data tidak ditemukan, memulai dengan data kosong.");
            return;
        }

        try (Scanner pemdas = new Scanner(file)) {
            count = 0;
            while (pemdas.hasNextLine() && count < MAX_ACTIVITIES) {
                String nama = pemdas.nextLine().substring(6);
                String deskripsi = pemdas.nextLine().substring(11);
                String tanggal = pemdas.nextLine().substring(9);
                String waktu = pemdas.nextLine().substring(7);
                String tempat = pemdas.nextLine().substring(9);
                aktivitas[count] = new Kegiatan(nama, deskripsi, tanggal, waktu, tempat);
                count++;

                if (pemdas.hasNextLine()) {
                    pemdas.nextLine(); 
                }
            }
            System.out.println("Data berhasil dimuat.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat data.");
        }
    }
}
