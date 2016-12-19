package domains;


/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public class FeelingAfterRun extends BaseModel<FeelingAfterRun, Long> {

    private String feeling;

    public FeelingAfterRun() {
        super();
    }

    public FeelingAfterRun(Long id, final Integer version, String feeling) {
        super(id, version);
        setFeeling(feeling);
    }


    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }


    @Override
    public int compareTo(FeelingAfterRun o) {
        if (super._compareTo(o)==-1)return -1;
        else
            if (o.getFeeling().equals(this.getFeeling()) &&o.getId().equals(this.getId()) && o.getVersion().equals(this.getVersion()))
                return 0;

        else return -1;
    }
}
