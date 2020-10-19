package tp4.ej11;

public class Main {
    public static void main(String[] args) {
        ClubVinos club = new ClubVinos();
        Thread admin = new Thread(new Administrador(club));
        admin.setDaemon(true);
        admin.start();

        for (int i = 0; i < 8; i++) {
            new Thread(new MiembroClub(club)).start();
        }
    }
}
