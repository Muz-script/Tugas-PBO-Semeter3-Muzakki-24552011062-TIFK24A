//Nama : MUZAKKI FADLILLAH GUNADI
//Kelas: TIF K 24A
//NIM : 24552011062
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        // ===== JUDUL =====
        Label lblJudul = new Label("FORM PENDAFTARAN MAHASISWA");
        lblJudul.getStyleClass().add("title");

        // ===== INPUT =====
        TextField tfNama = new TextField();
        tfNama.setPromptText("Masukkan Nama Lengkap");

        PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Minimal 6 karakter");

        ComboBox<String> cbJurusan = new ComboBox<>();
        cbJurusan.getItems().addAll(
                "Teknik Informatika",
                "Sistem Informasi",
                "Manajemen Informatika"
        );
        cbJurusan.setPromptText("Pilih Jurusan");

        RadioButton rbL = new RadioButton("Laki-laki");
        RadioButton rbP = new RadioButton("Perempuan");

        ToggleGroup genderGroup = new ToggleGroup();
        rbL.setToggleGroup(genderGroup);
        rbP.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(15, rbL, rbP);
        genderBox.setAlignment(Pos.CENTER_LEFT);

        CheckBox cbSetuju = new CheckBox("Saya menyetujui syarat & ketentuan");

        Button btnSimpan = new Button("SIMPAN DATA");
        btnSimpan.getStyleClass().add("btn-primary");

        // ===== GRID FORM =====
        GridPane grid = new GridPane();
        grid.getStyleClass().add("card");
        grid.setHgap(15);
        grid.setVgap(14);
        grid.setPadding(new Insets(30));

        grid.add(lblJudul, 0, 0, 2, 1);
        GridPane.setMargin(lblJudul, new Insets(0, 0, 15, 0));

        grid.add(new Label("Nama Lengkap"), 0, 1);
        grid.add(tfNama, 1, 1);

        grid.add(new Label("Password"), 0, 2);
        grid.add(pfPassword, 1, 2);

        grid.add(new Label("Jurusan"), 0, 3);
        grid.add(cbJurusan, 1, 3);

        grid.add(new Label("Jenis Kelamin"), 0, 4);
        grid.add(genderBox, 1, 4);

        grid.add(cbSetuju, 1, 5);
        grid.add(btnSimpan, 1, 6);

        GridPane.setMargin(btnSimpan, new Insets(15, 0, 0, 0));

        // ===== EVENT VALIDASI =====
        btnSimpan.setOnAction(e -> {

            if (tfNama.getText().isBlank()) {
                showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Nama tidak boleh kosong!");
                return;
            }

            if (pfPassword.getText().length() < 6) {
                showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Password minimal 6 karakter!");
                return;
            }

            if (cbJurusan.getValue() == null) {
                showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Silakan pilih jurusan!");
                return;
            }

            if (genderGroup.getSelectedToggle() == null) {
                showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Silakan pilih jenis kelamin!");
                return;
            }

            if (!cbSetuju.isSelected()) {
                showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Anda harus menyetujui syarat & ketentuan!");
                return;
            }

            showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Data mahasiswa berhasil disimpan ✔");
        });

        // ===== ROOT =====
        StackPane root = new StackPane(grid);
        root.setPadding(new Insets(40));

        // ===== ANIMASI =====
        FadeTransition ft = new FadeTransition(Duration.seconds(1.1), grid);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        // ===== SCENE =====
        Scene scene = new Scene(root, 560, 450);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("Aplikasi Pendaftaran Mahasiswa");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
