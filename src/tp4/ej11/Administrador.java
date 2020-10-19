package tp4.ej11;

public class Administrador implements Runnable{
    ClubVinos club;

    public Administrador(ClubVinos club) {
        this.club = club;
    }

    @Override
    public void run() {
        while (true) {
            club.reponer();
        }
    }
}
