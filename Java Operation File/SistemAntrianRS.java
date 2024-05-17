
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class SistemAntrianRS {
    private Queue<String> antrian = new LinkedList<>();
    private int nomorAntrian = 1;

    public static void main(String[] args) {
        SistemAntrianRS sistem = new SistemAntrianRS();
        Scanner scanner = new Scanner(System.in);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        while (true) {
            try {
                System.out.println("1. Tambah pasien ke antrian");
                System.out.println("2. Hapus pasien dari antrian");
                System.out.println("3. Cek pasien berikutnya");
                System.out.println("4. Cek apakah antrian kosong");
                System.out.println("5. Keluar");

                System.out.print("Masukkan pilihan Anda: ");
                int pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan nama pasien: ");
                        String pasien = scanner.next();
                        String tanggal = dateFormat.format(date);
                        sistem.tambahAntrian(pasien, tanggal);
                        break;
                    case 2:
                        String pasienDihapus = sistem.hapusDariAntrian();
                        System.out.println("Pasien yang dihapus: " + pasienDihapus);
                        break;
                    case 3:
                        String pasienBerikutnya = sistem.cekPasienBerikutnya();
                        System.out.println("Pasien berikutnya: " + pasienBerikutnya);
                        break;
                    case 4:
                        boolean antrianKosong = sistem.isAntrianKosong();
                        System.out.println("Apakah antrian kosong? " + antrianKosong);
                        break;
                    case 5:
                        System.out.println("Terimakasih!");
                        scanner.close();
                        return;
                    default:
                        throw new Exception("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid. Silakan masukkan angka.");
                scanner.next(); 
            } catch (Exception e) {
                System.out.println("Terjadi error: " + e.getMessage());
            }
        }
    }

    // Tambah pasien ke antrian
    public void tambahAntrian(String pasien, String tanggal) {
        antrian.add(nomorAntrian + ". " + pasien + " - " + tanggal);
        nomorAntrian++;
    } 

    // Hapus pasien dari antrian
    public String hapusDariAntrian() {
        if (antrian.isEmpty()) {
            throw new RuntimeException("Antrian kosong. Tidak dapat menghapus pasien.");
        }
        return antrian.poll();
    }

    // Cek pasien berikutnya
    public String cekPasienBerikutnya() {
        if (antrian.isEmpty()) {
            throw new RuntimeException("Antrian kosong. Tidak ada pasien berikutnya.");
        }
        return antrian.peek();
    }

    // Cek apakah antrian kosong
    public boolean isAntrianKosong() {
        return antrian.isEmpty();
    }
}