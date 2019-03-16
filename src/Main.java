import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File dosya = new File(System.currentTimeMillis()+"-game.log");

        PrintWriter yaz = new PrintWriter(dosya);


        Asker[][] meydan = new Asker[16][16];
        List<Asker> kirmiziTakim = new ArrayList<>();
        List<Asker> maviTakim= new ArrayList<>();
        kirmiziTakimOlustur(meydan, kirmiziTakim,yaz);
        maviTakimOlustur(meydan, maviTakim,yaz);
        boolean kirmizi = true;//Kirmizi Takim
        boolean kirmiziAlive = true;
        boolean maviAlive = true;
        yaz.println("Oyun Başladı");
        while(kirmiziAlive&&maviAlive){
            Random rnd = new Random(); int oyuncu = rnd.nextInt(7); int hamle = rnd.nextInt(100);
            Asker asker = null;
            if(kirmizi){
                asker = kirmiziTakim.get(oyuncu);
                while(!asker.isAlive()) asker = kirmiziTakim.get(rnd.nextInt(7));
            }

            else{
                asker = maviTakim.get(oyuncu);
                while(!asker.isAlive()) asker = maviTakim.get(rnd.nextInt(7));
            }
            if(hamle<30) asker.Hareket_et(meydan);
            else if(hamle<60) {asker.Ates_et(meydan);}
            else asker.Bekle();
            kirmizi=!kirmizi;
            maviAlive = takimKontrolu(maviTakim,meydan);
            kirmiziAlive = takimKontrolu(kirmiziTakim,meydan);
        }
        if(kirmiziAlive){
            yaz.println("Kirmizi Takım Kazandı");

            System.out.println("Kirmizi Takim Kazandi");
        }
        else{
            yaz.println("Mavi Takım Kazandı");
            System.out.println("Mavi Takim Kazandi");
        }
        yaz.close();
    }

    private static boolean takimKontrolu(List<Asker> takim,Asker[][] meydan) {
        boolean tempMavi = false;
        for (Asker asker1 : takim) {
            tempMavi = tempMavi || asker1.isAlive();
            if(!asker1.isAlive()){ meydan[asker1.getKoordinat().getX()][asker1.getKoordinat().getY()]=null;}
        }
        return tempMavi;
    }

    private static void maviTakimOlustur(Asker[][] meydan, List<Asker> maviTakim,PrintWriter yaz) {
        Random rnd = new Random();
        int yuzbasiSayisi = rnd.nextInt(2);
        int tegmenSayisi = rnd.nextInt(2)+1;
        int erSayisi = 7-yuzbasiSayisi-tegmenSayisi;
        for (int i = 0; i < erSayisi; i++) {
            Er er = new Er(1, new Koordinat(15-i, 15),yaz);
            maviTakim.add(er);
            meydan[15-i][15]=er;
        }
        for (int i = 1; i <= tegmenSayisi; i++) {
            Tegmen tegmen = new Tegmen(1, new Koordinat(16-i, 14),yaz);
            maviTakim.add(tegmen);
            meydan[16-i][14]=tegmen;
        }
        if(yuzbasiSayisi>0){
            Yuzbasi yuzbasi = new Yuzbasi(0, new Koordinat(15, 13),yaz);
            maviTakim.add(yuzbasi);
            meydan[15][13]=yuzbasi;
        }
    }

    private static void kirmiziTakimOlustur(Asker[][] meydan, List<Asker> kirmiziTakim,PrintWriter yaz) {
        Random rnd = new Random();
        int yuzbasiSayisi = rnd.nextInt(2);
        int tegmenSayisi = rnd.nextInt(2)+1;
        int erSayisi = 7-yuzbasiSayisi-tegmenSayisi;
        for (int i = 0; i < erSayisi; i++) {
            Er er = new Er(0, new Koordinat(i, 0),yaz);
            kirmiziTakim.add(er);
             meydan[i][0]=er;
        }
        for (int i = 1; i <= tegmenSayisi; i++) {
            Tegmen tegmen = new Tegmen(0, new Koordinat(i, 1),yaz);
            kirmiziTakim.add(tegmen);
            meydan[i][1]=tegmen;
        }
        if(yuzbasiSayisi>0){
            Yuzbasi yuzbasi = new Yuzbasi(0, new Koordinat(0, 2),yaz);
            kirmiziTakim.add(yuzbasi);
            meydan[0][2]=yuzbasi;
        }
    }
}
