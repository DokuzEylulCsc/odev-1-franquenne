import java.io.PrintWriter;

public abstract class Asker {
    private boolean alive = true;
    private int team;
    private int health = 100;
    private Koordinat koordinat;

    PrintWriter yaz;

    abstract public void Bekle();

    public abstract void Hareket_et(Asker[][] meydan);

    public abstract void Ates_et(Asker[][] meydan);

    public Asker(int team, Koordinat koordinat, PrintWriter yaz) {
        this.team = team;
        this.koordinat = koordinat;
        this.yaz = yaz;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if(this.health<0){
            alive = false;
            System.out.println(getAskerAdı()+"öldü...");
        }
    }

    public Koordinat getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(Koordinat koordinat) {
        this.koordinat = koordinat;
    }

    public PrintWriter getYaz() {
        return yaz;
    }

    public void setYaz(PrintWriter yaz) {
        this.yaz = yaz;
    }
    protected abstract String getAskerAdı() ;
}
