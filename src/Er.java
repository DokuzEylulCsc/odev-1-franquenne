import java.io.PrintWriter;
import java.util.Random;

public class Er extends Asker {


    public Er(int team, Koordinat koordinat, PrintWriter yaz) {
        super(team, koordinat, yaz);
    }

    @Override
    protected String getAskerAdı() {
        return (getTeam() == 0 ? "Kırmızı" : "Mavi") + " Er " +" ("+this+") ";
    }

    @Override
    public void Bekle() {
        if (!isAlive()) return;
        yaz.println(getAskerAdı() + "Bekledi");
    }

    @Override
    public void Hareket_et(Asker[][] meydan) {
        if (!isAlive()) return;
        Random rnd = new Random();
        boolean ileri = rnd.nextBoolean();
        Koordinat koordinat = getKoordinat();
        meydan[koordinat.getX()][koordinat.getY()] = null;
        /* Üstte başlayan (Mavi) Takım için else ileridir. */
        if (ileri) {
            if (koordinat.getY() == 15) {
                if (koordinat.getX() != 15 && meydan[koordinat.getX() + 1][koordinat.getY()] == null) {
                    koordinat.moveRight();

                }
            } else if (meydan[koordinat.getX()][koordinat.getY() + 1] == null) {
                koordinat.moveForward();
            }

        } else {
            if (koordinat.getY() == 0) {
                if (koordinat.getX() != 0 && meydan[koordinat.getX() - 1][koordinat.getY()] == null) {
                    koordinat.moveLeft();
                }

            } else if (meydan[koordinat.getX()][koordinat.getY() - 1] == null) {
                koordinat.moveBack();
            }


        }
        meydan[koordinat.getX()][koordinat.getY()] = this;
        yaz.println(getAskerAdı() + "Şuraya Hareket Etti : " + koordinat.toString());
    }

    @Override
    public void Ates_et(Asker[][] meydan) {
        Random vur = new Random();
        int ates = vur.nextInt(3);
        int tahribat[] = {5, 10, 15};
        Koordinat koordinat = getKoordinat();

        Asker dusman = null;
        if (koordinat.getX() != 15 && meydan[koordinat.getX() + 1][koordinat.getY()] != null) {
            dusman = meydan[koordinat.getX() + 1][koordinat.getY()];

        } else if (koordinat.getX() != 15 && koordinat.getY() != 0 && meydan[koordinat.getX() + 1][koordinat.getY() - 1] != null) {
            dusman = meydan[koordinat.getX() + 1][koordinat.getY() - 1];

        } else if (koordinat.getY() != 0 && meydan[koordinat.getX()][koordinat.getY() - 1] != null) {
            dusman = meydan[koordinat.getX()][koordinat.getY() - 1];

        } else if (koordinat.getX() != 0 && koordinat.getY() != 0 && meydan[koordinat.getX() - 1][koordinat.getY() - 1] != null) {
            dusman = meydan[koordinat.getX() - 1][koordinat.getY() - 1];

        } else if (koordinat.getX() != 0 && meydan[koordinat.getX() - 1][koordinat.getY()] != null) {
            dusman = meydan[koordinat.getX() - 1][koordinat.getY()];

        } else if (koordinat.getX() != 0 && koordinat.getY() != 15 && meydan[koordinat.getX() - 1][koordinat.getY() + 1] != null) {
            dusman = meydan[koordinat.getX() - 1][koordinat.getY() + 1];

        } else if (koordinat.getY() != 15 && meydan[koordinat.getX()][koordinat.getY() + 1] != null) {
            dusman = meydan[koordinat.getX()][koordinat.getY() + 1];

        } else if (koordinat.getX() != 15 && koordinat.getY() != 15 && meydan[koordinat.getX() + 1][koordinat.getY() + 1] != null) {
            dusman = meydan[koordinat.getX() + 1][koordinat.getY() + 1];


        }
        if (dusman != null && dusman.getTeam() != this.getTeam()) {
            dusman.setHealth(dusman.getHealth() - tahribat[ates]);
            yaz.println(getAskerAdı() + "Şuraya Ateş Etti : " + dusman.getAskerAdı() +";"+ "Ateş Edilen Askerin Koordinati : " + dusman.getKoordinat().toString() + " Verilen Hasar : " + tahribat[ates]);
        }
    }


}
