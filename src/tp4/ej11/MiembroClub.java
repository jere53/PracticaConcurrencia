package tp4.ej11;

public class MiembroClub implements Runnable{
    private ClubVinos club;
    private boolean probaronSuVino = false;

    public MiembroClub(ClubVinos club) {
        this.club = club;
    }

    public boolean isProbaronSuVino() {
        return probaronSuVino;
    }

    public void setProbaronSuVino(boolean probaronSuVino) {
        this.probaronSuVino = probaronSuVino;
    }

    @Override
    public void run() {
        while (!probaronSuVino) {
            club.conseguirJarras();
            System.out.println("El miembro " + Thread.currentThread().getId() + " reservo sus jarras");
            club.mezclarVino();
            System.out.println("El miembro " + Thread.currentThread().getId() + " mezclo el vino y espera a que fermente");
            club.fermentarVino();
            System.out.println("El vino del miembro " + Thread.currentThread().getId() + " termino de fermentar");
            club.decantarVino();
            System.out.println("El vino del miembro " + Thread.currentThread().getId() + " esta listo");
            club.realizarCata(this);
        }
    }
}
