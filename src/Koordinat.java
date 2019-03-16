public class Koordinat {
    private int x;
    private int y;



    public Koordinat(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void moveForward(){
        y+=1;
    }
    public void moveBack(){
        y-=1;
    }
    public void moveLeft(){
        x-=1;
    }
    public void moveRight(){
        x+=1;
    }
    public void moveUpperLeft(){
        y++;
        x--;
    }
    public void moveUpperRight(){
        x++;
        y++;
    }
    public void moveLowerRight(){
        y--;
        x++;
    }
    public void moveLowerLeft(){
        y--;
        x--;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
