import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SistemAntrianRS {
    private Queue<String> antrian = new LinkedList<>();

    public static void main(String[] args) {
        SistemAntrianRS sistem = new SistemAntrianRS();
        Scanner scanner = new Scanner(System.in);

        while (true) {
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
                    sistem.tambahAntrian(pasien);
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
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    // Tambah pasien ke antrian
    public void tambahAntrian(String pasien) {
        antrian.add(pasien);
    }

    // Hapus pasien dari antrian
    public String hapusDariAntrian() {
        return antrian.poll();
    }

    // Cek pasien berikutnya
    public String cekPasienBerikutnya() {
        return antrian.peek();
    }

    // Cek apakah antrian kosong
    public boolean isAntrianKosong() {
        return antrian.isEmpty();
    }
}
