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
        return "Kegiatan: " + nama +
               "\nDeskripsi: " + deskripsi +
               "\nTanggal: " + tanggal +
               "\nWaktu: " + waktu +
               "\nLokasi: " + tempat;
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
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n ==== Sistem Penjadwalan Kegiatan ====");
            System.out.println("1. Tambah Kegiatan");
            System.out.println("2. Tampilkan Kegiatan");
            System.out.println("3. Simpan Data");
            System.out.println("4. Muat Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    tambahKegiatan(scanner);
                    break;
                case "2":
                    tampilkanKegiatan();
                    break;
                case "3":
                    simpanData();
                    break;
                case "4":
                    memuatData();
                    break;
                case "5":
                    System.out.println("Keluar dari aplikasi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (!choice.equals("5"));

        scanner.close();
    }

    private void tambahKegiatan(Scanner scanner) {
        if (count >= MAX_ACTIVITIES) {
            System.out.println("Kapasitas penuh! Tidak dapat menambah kegiatan lagi.");
            return;
        }

        System.out.print("Masukkan nama kegiatan: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Masukkan tanggal: ");
        String tanggal = scanner.nextLine();
        System.out.print("Masukkan waktu: ");
        String waktu = scanner.nextLine();
        System.out.print("Masukkan lokasi: ");
        String tempat = scanner.nextLine();

        aktivitas[count] = new Kegiatan(nama, deskripsi, tanggal, waktu, tempat);
        count++;
        System.out.println("Kegiatan berhasil ditambahkan.");
    }

    private void tampilkanKegiatan() {
        System.out.println("\n=== Daftar Kegiatan ===");
        if (count == 0) {
            System.out.println("Tidak ada kegiatan yang terdaftar.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(aktivitas[i]);
            System.out.println("__________________________________");
        }
    }

    private void simpanData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("ListKegiatan.txt"))) {
            for (int i = 0; i < count; i++) {
                Kegiatan activity = aktivitas[i];
                writer.println("=====================================================");
                writer.println("Nama      : " + activity.nama);
                writer.println("Deskripsi : " + activity.deskripsi);
                writer.println("Tanggal   : " + activity.tanggal);
                writer.println("Waktu     : " + activity.waktu);
                writer.println("Lokasi    : " + activity.tempat);
                writer.println("=====================================================");
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

        try (Scanner scanner = new Scanner(file)) {
            count = 0;

            while (scanner.hasNextLine() && count < MAX_ACTIVITIES) {
                String line = scanner.nextLine().trim();

                if (line.equals("=====================================================")) {
                    String nama = scanner.nextLine().replace("Nama      :", "").trim();
                    String deskripsi = scanner.nextLine().replace("Deskripsi :", "").trim();
                    String tanggal = scanner.nextLine().replace("Tanggal   :", "").trim();
                    String waktu = scanner.nextLine().replace("Waktu     :", "").trim();
                    String tempat = scanner.nextLine().replace("Lokasi    :", "").trim();

                    
                    if (scanner.hasNextLine()) scanner.nextLine();

                    
                    aktivitas[count] = new Kegiatan(nama, deskripsi, tanggal, waktu, tempat);
                    count++;
                }
            }
            System.out.println("Data berhasil dimuat. Total kegiatan: " + count);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat data.");
        } catch (Exception e) {
            System.out.println("Format file tidak valid atau ada kesalahan lainnya.");
        }
    }
}
