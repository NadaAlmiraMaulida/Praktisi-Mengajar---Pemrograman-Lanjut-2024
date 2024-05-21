// 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

public class SistemAntrianRS_GUI {
    private JFrame frame;
    private JTextField txtNama;
    private JButton btnTambah;
    private JButton btnProses;
    private JButton btnSelesai;
    private JButton btnKosong;
    private JButton btnReset;
    private JButton btnKeluar;
    private JLabel lblPasien;
    private JLabel lblAntrian;
    private JTextArea txtAntrian;
    private JTextArea txtProses;
    private JTextArea txtSelesai;
    private SistemAntrianRS sistemAntrianRS;

    public SistemAntrianRS_GUI() {
        sistemAntrianRS = new SistemAntrianRS();
        initComponents();
        updateAntrianDisplay();
        updateProsesDisplay();
        updateSelesaiDisplay();
    }

    private void initComponents() {
        frame = new JFrame("Sistem Antrian RS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        txtNama = new JTextField(20);
        btnTambah = new JButton("Tambah");
        btnProses = new JButton("Proses");
        btnSelesai = new JButton("Selesai");
        btnKosong = new JButton("Kosong");
        btnReset = new JButton("Reset");
        btnKeluar = new JButton("Keluar");
        lblPasien = new JLabel("Nama Pasien:");
        lblAntrian = new JLabel("Daftar Antrian:");
        txtAntrian = new JTextArea(10, 20);
        txtAntrian.setEditable(false);
        txtProses = new JTextArea(5, 20);
        txtProses.setEditable(false);
        txtSelesai = new JTextArea(10, 20);
        txtSelesai.setEditable(false);

        JPanel panelUtama = new JPanel(new BorderLayout());
        JPanel panelInput = new JPanel(new GridLayout(3, 2));
        JPanel panelTombol = new JPanel(new GridLayout(1, 6));
        JPanel panelTengah = new JPanel(new GridLayout(1, 3));

        panelInput.add(lblPasien);
        panelInput.add(txtNama);
        panelInput.add(btnTambah);
        panelInput.add(btnProses);
        panelInput.add(btnSelesai);
        panelInput.add(btnKosong);

        panelTombol.add(btnReset);
        panelTombol.add(btnKeluar);

        panelTengah.add(new JScrollPane(txtAntrian));
        panelTengah.add(new JScrollPane(txtProses));
        panelTengah.add(new JScrollPane(txtSelesai));

        panelUtama.add(panelInput, BorderLayout.NORTH);
        panelUtama.add(panelTombol, BorderLayout.SOUTH);
        panelUtama.add(panelTengah, BorderLayout.CENTER);

        frame.add(panelUtama);

        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahAntrian();
            }
        });

        btnProses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesAntrian();
            }
        });

        btnSelesai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selesaiAntrian();
            }
        });

        btnKosong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAntrianKosong();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAntrian();
            }
        });

        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    public void tambahAntrian() {
        String nama = txtNama.getText().trim();
        if (!nama.isEmpty()) {
            sistemAntrianRS.tambahAntrian(nama);
            txtNama.setText("");
            updateAntrianDisplay();
        } else {
            JOptionPane.showMessageDialog(frame, "Nama pasien tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void prosesAntrian() {
        try {
            sistemAntrianRS.prosesAntrian();
            updateAntrianDisplay();
            updateProsesDisplay();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void selesaiAntrian() {
        try {
            sistemAntrianRS.selesaiAntrian();
            updateProsesDisplay();
            updateSelesaiDisplay();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void isAntrianKosong() {
        boolean antrianKosong = sistemAntrianRS.isAntrianKosong();
        String message = antrianKosong ? "Antrian kosong" : "Antrian tidak kosong";
        JOptionPane.showMessageDialog(frame, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resetAntrian() {
        sistemAntrianRS.resetAntrian();
        updateAntrianDisplay();
        updateProsesDisplay();
        updateSelesaiDisplay();
    }

    private void updateAntrianDisplay() {
        Queue<Pasien> antrian = sistemAntrianRS.getAntrian();
        txtAntrian.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Pasien pasien : antrian) {
            txtAntrian.append(pasien.getNama() + " - " + pasien.getWaktu().format(formatter) + "\n");
        }
    }

    private void updateProsesDisplay() {
        Pasien pasienProses = sistemAntrianRS.getPasienProses();
        txtProses.setText("");
        if (pasienProses != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            txtProses.append(pasienProses.getNama() + " - " + pasienProses.getWaktu().format(formatter) + "\n");
        }
    }

    private void updateSelesaiDisplay() {
        Queue<Pasien> antrianSelesai = sistemAntrianRS.getAntrianSelesai();
        txtSelesai.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Pasien pasien : antrianSelesai) {
            txtSelesai.append(pasien.getNama() + " - " + pasien.getWaktu().format(formatter) + "\n");
        }
    }

    public static void main(String[] args) {
        new SistemAntrianRS_GUI();
    }
}

class SistemAntrianRS {
    private Queue<Pasien> antrian;
    private Queue<Pasien> antrianSelesai;
    private Pasien pasienProses;

    public SistemAntrianRS() {
        antrian = new LinkedList<>();
        antrianSelesai = new LinkedList<>();
        pasienProses = null;
    }

    public void tambahAntrian(String nama) {
        antrian.add(new Pasien(nama, LocalDateTime.now()));
    }

    public void prosesAntrian() {
        if (pasienProses != null) {
            throw new RuntimeException("Pasien sedang diproses, selesaikan terlebih dahulu");
        }
        if (antrian.isEmpty()) {
            throw new RuntimeException("Antrian kosong, tidak ada pasien untuk diproses");
        }
        pasienProses = antrian.poll();
    }

    public void selesaiAntrian() {
        if (pasienProses == null) {
            throw new RuntimeException("Tidak ada pasien yang sedang diproses");
        }
        antrianSelesai.add(pasienProses);
        pasienProses = null;
    }

    public boolean isAntrianKosong() {
        return antrian.isEmpty();
    }

    public void resetAntrian() {
        antrian.clear();
        antrianSelesai.clear();
        pasienProses = null;
    }

    public Queue<Pasien> getAntrian() {
        return antrian;
    }

    public Pasien getPasienProses() {
        return pasienProses;
    }

    public Queue<Pasien> getAntrianSelesai() {
        return antrianSelesai;
    }
}

class Pasien {
    private String nama;
    private LocalDateTime waktu;

    public Pasien(String nama, LocalDateTime waktu) {
        this.nama = nama;
        this.waktu = waktu;
    }

    public String getNama() {
        return nama;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }
}
