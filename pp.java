import java.io.*;
import java.util.*;

class Activity {
    String name;
    String description;
    String date;
    String time;
    String location;  

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
