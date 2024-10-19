import java.util.*;

public class Bold {
    int[] boldPos= new int[2];
    static Brik boldBrik;
    static Felt boldFelt;
    static Spiller spillerMedBold;
    static boolean holdtAfBrik=false;
    static Felt ledigtFelt;
    boolean spagetti;

    public Bold(){
    }

    public static void createBold(int y, int x){
        Bold bold = new Bold();
        bold.setBoldFelt(Bræt.bræt[y][x]);
    }
    public static void turnOver(Spiller spiller1, Spiller spiller2){
        spiller2.tabBold();
        spillerMedBold=spiller1;
        spiller1.givBold();
    }
    public void pickUp(Brik brik){
        brik.pickUpBold(this);
        boldFelt=null;
        holdtAfBrik=true;
        boldBrik=brik;
    }
    public void setBoldPos(int y, int x){
        boldPos[0]=y;
        boldPos[1]=x;
    }
    public void setBoldFelt(Felt felt){
        boldFelt=felt;
        System.out.println("WAT43");
        System.out.println(Arrays.toString(felt.getPos()));
        System.out.println("WAT54");
        setBoldPos(felt.getPos()[0],felt.getPos()[1]);
        System.out.println("WAT44");
        felt.setBoldPåFelt(this);
        System.out.println("WAT45");
    }

    public static Felt getBoldFelt() {
        return boldFelt;
    }

    public void setBoldBrik(Brik brik){
        boldBrik=brik;
        holdtAfBrik=true;
    }
    public static void clearBold(){
        boldBrik=null;
        boldFelt=null;
        spillerMedBold=null;
        holdtAfBrik=false;
        ledigtFelt=null;
    }

    public static boolean isHoldtAfBrik() {
        return holdtAfBrik;
    }

    public void isSpagetti(boolean spag) {
        this.spagetti=spag;
    }

    public static Felt getLedigtFelt() {
        return ledigtFelt;
    }
    public static void fjernLedigtFelt(){
        ledigtFelt=null;
    }

    public static void setLedigtFelt(Felt ledigtFelt) {
        Bold.ledigtFelt = ledigtFelt;
    }

    public static void findLedigtFelt(Felt felt){
        ArrayList<Felt> landeFelter = Bræt.getGrænseFelter(felt);
        landeFelter.remove(felt);
        for (Felt tfelt: Bræt.getGrænseFelter(felt)){
            if (!(tfelt.getBrikPåFelt()==null)){
                landeFelter.remove(tfelt);
            }
        }

        Collections.shuffle(landeFelter);

        try {
            ledigtFelt=landeFelter.getFirst();
        } catch (Exception e){
            landeFelter = Bræt.getGrænseFelter(felt);
            Collections.shuffle(landeFelter);
            findLedigtFelt(landeFelter.getFirst());
        }
    }
}
