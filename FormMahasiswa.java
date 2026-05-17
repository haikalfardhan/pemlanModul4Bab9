import javax.swing.*;
import java.awt.*;

// Fardhan Haikal Taufik
// 255150707111008
// Teknologi Informasi
public class FormMahasiswa extends JFrame {

    JTextField nama, ttl, noDaftar, telp, email;
    JTextArea alamat;
    JButton submit;

    public FormMahasiswa() {
        // 1. Setup Jendela Utama
        setTitle("Form Daftar Ulang Mahasiswa");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 2. Setup Panel dan Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(235, 235, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 3. Inisialisasi Komponen Input
        nama = new JTextField();
        ttl = new JTextField();
        noDaftar = new JTextField();
        telp = new JTextField();
        email = new JTextField();
        alamat = new JTextArea(3, 20);
        alamat.setLineWrap(true);

        submit = new JButton("SUBMIT");

        // 4. Memasukkan Komponen ke Panel
        addItem(panel, gbc, 0, "Nama Lengkap", nama);
        addItem(panel, gbc, 1, "Tanggal Lahir", ttl);
        addItem(panel, gbc, 2, "No. Pendaftaran", noDaftar);
        addItem(panel, gbc, 3, "No. Telp", telp);
        
        // Khusus alamat pakai JScrollPane
        addItem(panel, gbc, 4, "Alamat", new JScrollPane(alamat));
        
        addItem(panel, gbc, 5, "E-mail", email);

        // Menaruh tombol submit di kanan bawah
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 0; // Tombol tidak perlu melebar
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(submit, gbc);

        add(panel);
        setVisible(true);

        // 5. Aksi saat tombol ditekan
        submit.addActionListener(e -> prosesSubmit());
    }

    // Method bantuan agar rapi saat memasukkan komponen
    void addItem(JPanel panel, GridBagConstraints gbc, int y, String label, Component comp) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0; // Label tidak melebar
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // PENTING: Input text melebar mengisi sisa ruang
        panel.add(comp, gbc);
    }

    void prosesSubmit() {
        // Validasi Kosong
        if (nama.getText().isEmpty() || ttl.getText().isEmpty() ||
            noDaftar.getText().isEmpty() || telp.getText().isEmpty() ||
            alamat.getText().isEmpty() || email.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Semua kolom harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Konfirmasi
        int pilihan = JOptionPane.showConfirmDialog(this,
                "Apakah anda yakin data yang Anda isi sudah benar?",
                "Konfirmasi",
                JOptionPane.OK_CANCEL_OPTION);

        if (pilihan == JOptionPane.OK_OPTION) {
            tampilData();
        }
    }

    void tampilData() {
        JFrame frame = new JFrame("Data Mahasiswa");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);
        frame.setLayout(null);

        JLabel judul = new JLabel("Data Mahasiswa", SwingConstants.CENTER);
        judul.setBounds(100, 10, 200, 30);
        judul.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(judul);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setMargin(new Insets(10, 10, 10, 10));

        String text = String.format(
                "%-15s : %s\n%-15s : %s\n%-15s : %s\n%-15s : %s\n%-15s : %s\n%-15s : %s",
                "Nama", nama.getText(),
                "Tanggal Lahir", ttl.getText(),
                "No.Pendaftaran", noDaftar.getText(),
                "No.Telp", telp.getText(),
                "Alamat", alamat.getText(),
                "E-mail", email.getText());

        area.setText(text);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(30, 50, 320, 170);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));

        frame.add(scroll);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Dieksekusi langsung tanpa perantara SwingUtilities
        new FormMahasiswa();
    }
}