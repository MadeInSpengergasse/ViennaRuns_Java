package domains;


/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public abstract class FeelingAfterRun extends BaseModel<FeelingAfterRun, Long> {

    private String feeling;

    protected FeelingAfterRun() {
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


}
