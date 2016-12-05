package domains;

import java.time.LocalDateTime;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public abstract class Run extends BaseModel<Run, Long> {


    private User user;
    private Float distance;
    private Integer duration;
    private LocalDateTime date;
    private FeelingAfterRun feeling;

    protected Run() {
        super();
    }

    public Run(Long id, final Integer version, User user, Float distance, Integer duration, LocalDateTime date, FeelingAfterRun feeling) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public FeelingAfterRun getFeeling() {
        return feeling;
    }

    public void setFeeling(FeelingAfterRun feeling) {
        this.feeling = feeling;
    }


}
