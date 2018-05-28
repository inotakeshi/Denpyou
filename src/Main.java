import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        InBean ib = new InBean();
        ib.setTorihikisakiName("セントラル商会");
        ib.setTorihikiDate( LocalDate.of(2018, 5, 2) );
        ib.setGoukei(400);

        ib.getLines().add(  new InLineBean("消しゴム",1,100) );
        ib.getLines().add(  new InLineBean("鉛筆",20,300) );

        OutBean ob = tenki( ib );
        System.out.println( ob.toString() );
    }

    @Override
    public String toString() {
        return "Main []";
    }

    static OutBean tenki( InBean ib)
    {
        int count=0;
        int goukei=0;
        OutBean ob=new OutBean();

//    	１）日付を書く
        ob.setTorihikiDate(ib.getTorihikiDate());

//    	２）取引先を書く
        ob.setTorihikisakiName(ib.getTorihikisakiName());

//    	３）表の外枠を作る
//    	　３，１）四角を書く
//    	　３，２）横線４本を書く
//    	　３，３）縦線３本を書く
//    	４）属性を書き込む

//    	５）１行ずつ番号を振りつつ表に書き込む
        for(InLineBean f:ib.getLines() )
        {
            OutLineBean olb=new OutLineBean();

//    	　５，１）（直前の番号を参照に）番号を振る
            count++;
//    	　５，２）商品名を書く
            olb.setShouhinName(f.getShouhinName());
            ob.getLines().add(olb);
//    	　５，３）数量を書く
            olb.setSuuryo(f.getSuuryo());
            ob.getLines().get(count-1).setSuuryo(f.getSuuryo());
//    	　５，４）金額を書く
            olb.setKingaku(f.getKingaku());
            ob.getLines().get(count-1).setKingaku(f.getKingaku());
            //ob.getLines().add(olb);
//    	　５，６）数量×金額を計算する(要らない)
//    	　５，７）合計金額に計算した値を加算する
            goukei+=olb.getKingaku();

//    	６）４行を超えた場合ページの切り替えを行う
            if(count%4==0)
            {
//    	　６，１）前回のページの日付を記憶する
//    	　６，２）日付に記録した値を入れる
//    	　６，３）取引先を記録する
//    	　６，４）取引先に記録した値を入れる
            }
//    	７）３）に戻る
        }
//    	８）伝票が終わったことを確認する
        if(count==ib.getLines().size()) {
//  	９）全ページの合計金額に現在記録されている合計金額を入れる
            ob.setGoukei(goukei);
            return ob;
        }
//
        return null;
    }

}
//------------------------------------
class InBean
{
    private LocalDate 			torihikiDate;
    private String	  			torihikisakiName;
    private int		  			goukei;
    private List<InLineBean> 	lines= new ArrayList<InLineBean>();

    public LocalDate getTorihikiDate() {
        return torihikiDate;
    }
    public void setTorihikiDate(LocalDate torihikiDate) {
        this.torihikiDate = torihikiDate;
    }
    public String getTorihikisakiName() {
        return torihikisakiName;
    }
    public void setTorihikisakiName(String torihikisakiName) {
        this.torihikisakiName = torihikisakiName;
    }
    public int getGoukei() {
        return goukei;
    }
    public void setGoukei(int goukei) {
        this.goukei = goukei;
    }
    public List<InLineBean> getLines() {
        return lines;
    }



}
class InLineBean
{
    private String shouhinName;
    private int		suuryo;
    private int		kingaku;



    public InLineBean(String shouhinName, int suuryo, int kingaku) {
        super();
        this.shouhinName = shouhinName;
        this.suuryo = suuryo;
        this.kingaku = kingaku;
    }
    public String getShouhinName() {
        return shouhinName;
    }
    public void setShouhinName(String shouhinName) {
        this.shouhinName = shouhinName;
    }
    public int getSuuryo() {
        return suuryo;
    }
    public void setSuuryo(int suuryo) {
        this.suuryo = suuryo;
    }
    public int getKingaku() {
        return kingaku;
    }
    public void setKingaku(int kingaku) {
        this.kingaku = kingaku;
    }


}
//------------------------------------
class OutBean
{
  private LocalDate 			torihikiDate;
  private String	  			torihikisakiName;
  private int		  			goukei;
  private List<OutLineBean> 	lines= new ArrayList<OutLineBean>();

    public LocalDate getTorihikiDate() {
        return torihikiDate;
    }
    public void setTorihikiDate(LocalDate torihikiDate) {
        this.torihikiDate = torihikiDate;
    }
    public String getTorihikisakiName() {
        return torihikisakiName;
    }
    public void setTorihikisakiName(String torihikisakiName) {
        this.torihikisakiName = torihikisakiName;
    }
    public int getGoukei() {
        return goukei;
    }
    public void setGoukei(int goukei) {
        this.goukei = goukei;
    }
    public List<OutLineBean> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "OutBean [torihikiDate=" + torihikiDate + ", torihikisakiName=" + torihikisakiName + ", goukei=" + goukei
                + ", lines=" + lines + "]";
    }

}
//------------------------------------
class OutLineBean
{
  private String shouhinName;
  private int		suuryo;
  private int		kingaku;
    public String getShouhinName() {
        return shouhinName;
    }
    public void setShouhinName(String shouhinName) {
        this.shouhinName = shouhinName;
    }
    public int getSuuryo() {
        return suuryo;
    }
    public void setSuuryo(int suuryo) {
        this.suuryo = suuryo;
    }
    public int getKingaku() {
        return kingaku;
    }
    public void setKingaku(int kingaku) {
        this.kingaku = kingaku;
    }

    @Override
    public String toString() {
        return "OutLineBean [shouhinName=" + shouhinName + ", suuryo=" + suuryo + ", kingaku=" + kingaku + "]";
    }


}