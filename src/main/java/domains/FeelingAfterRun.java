package domains;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public class FeelingAfterRun {
    private Integer id;

    private String feeling;

    public FeelingAfterRun(Integer id, String feeling){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getFeeling(){
        return feeling;
    }

    public void setFeeling(String feeling){
        this.feeling=feeling;
    }

}
