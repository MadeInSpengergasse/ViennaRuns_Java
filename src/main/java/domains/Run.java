package domains;


import java.sql.Date;

public class Run extends BaseModel<Run, Long> {


    private User user;
    private Float distance;
    private Integer duration;
    private Date date;
    private FeelingAfterRun feeling;

    public Run() {
        super();
    }

    public Run(Long id, final Integer version, User user, Float distance, Integer duration, Date date, FeelingAfterRun feeling) {
        super(id, version);

        setUser(user);
        setDistance(distance);
        setDuration(duration);
        setDate(date);
        setFeeling(feeling);

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FeelingAfterRun getFeeling() {
        return feeling;
    }

    public void setFeeling(FeelingAfterRun feeling) {
        this.feeling = feeling;
    }


    @Override
    public int compareTo(Run o) {

        if(super._compareTo(o)==-1) return -1;
        else
        if(o.getUser().equals(this.getUser()) &&
                o.getDistance().equals(this.getDistance()) &&
                o.getDuration().equals(this.getDuration()) &&
                o.getDate().equals(this.getDate()) &&
                o.getFeeling().equals(this.getFeeling()))
            return 0;
        else
            return -1;
    }
}
