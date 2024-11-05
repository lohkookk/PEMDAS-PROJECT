import java.io.*;
import java.util.*;

class Activity {
    String name;
    String description;
    String date;
    String time;
    String location;  // New attribute for location

    Activity(String name, String description, String date, String time, String location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Kegiatan: " + name + "\nDeskripsi: " + description + "\nTanggal: " + date + "\nWaktu: " + time + "\nLokasi: " + location;
    }
}

public class ActivityScheduler {
    private List<Activity> ListKegiatan = new ArrayList<>();

    public static void main(String[] args) {
        ActivityScheduler scheduler = new ActivityScheduler();
        scheduler.loadData();
        scheduler.displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n--- Sistem Penjadwalan Kegiatan ---");
            System.out.println("1. Tambah Kegiatan");
            System.out.println("2. Tampilkan Kegiatan");
            System.out.println("3. Simpan Data");
            System.out.println("4. Muat Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: \n");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addActivity(scanner);
                    break;
                case "2":
                    displayActivities();
                    break;
                case "3":
                    saveData();
                    break;
                case "4":
                    loadData();
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
