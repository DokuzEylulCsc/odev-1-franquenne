import java.io.PrintWriter;
import java.util.Random;

public class Tegmen extends Asker {

    public Tegmen(int team, Koordinat koordinat, PrintWriter yaz) {
        super(team, koordinat, yaz);
    }

    @Override
    protected String getAskerAdı() {

        return (getTeam()==0?"Kırmızı":"Mavi")+" Teğmen "+" ("+this+") ";
    }

    @Override
    public void Bekle() {
        yaz.println(getAskerAdı()+"Bekledi");
    }

    @Override
    public void Hareket_et(Asker[][] meydan) {
        if(!isAlive()) return;
        Random rnd = new Random();
        int yon = rnd.nextInt(4);
        Koordinat koordinat = getKoordinat();
        meydan[koordinat.getX()][koordinat.getY()] = null;
        if (yon == 1) {
            if (koordinat.getY() == 15) {
                Bekle();
            } else  if(meydan[koordinat.getX() ][koordinat.getY()+1] == null){
                koordinat.moveForward();
            }

        }
        else if(yon == 3){
            if (koordinat.getY() == 0){

                Bekle();
            }
            else if(meydan[koordinat.getX() ][koordinat.getY()-1] == null){
                koordinat.moveBack();
            }


        }
        else if(yon == 0){
            if(koordinat.getX()!=0 && meydan[koordinat.getX()-1][koordinat.getY()]== null){
                koordinat.moveLeft();
            }
            else{
                Bekle();
            }
        }
        else if(yon == 2){
            if(koordinat.getX()!=15 && meydan[koordinat.getX()+1][koordinat.getY()]==null){
                koordinat.moveRight();
            }
            else{
                Bekle();
            }
        }
        meydan[koordinat.getX()][koordinat.getY()] = this;
        yaz.println(getAskerAdı()+"Şuraya Hareket Etti : "+koordinat.toString());
    }

    @Override
    public void Ates_et(Asker[][] meydan) {
        int range = 2;
        Random rnd = new Random();
        int x = rnd.nextInt(3);
        int[] damage = {10, 20, 25};
        boolean fired = false;
        for (int i = getKoordinat().getX() - range < 0 ? 0 : getKoordinat().getX() - range; i <= Math.min(15, getKoordinat().getX() + range); i++) {
            for (int j = Math.max(getKoordinat().getY() - range, 0); j <= Math.min(15, getKoordinat().getY() + range); j++) {
                if (i == getKoordinat().getX() && j == getKoordinat().getY()) continue;
                if (meydan[i][j] != null && meydan[i][j].getTeam()!=this.getTeam()) {
                    meydan[i][j].setHealth(meydan[i][j].getHealth() - damage[x]);

                    fired = true;
                    Asker dusman = meydan[i][j];
                    yaz.println(getAskerAdı()+"Şuraya Ateş Etti : "+dusman.getAskerAdı()+";"+"Ateş Edilen Askerin Koordinati : "+ dusman.getKoordinat().toString()+" Verilen Hasar : "+damage[x]);

                    break;
                }
            }
            if (fired) break;
        }
    }


}
