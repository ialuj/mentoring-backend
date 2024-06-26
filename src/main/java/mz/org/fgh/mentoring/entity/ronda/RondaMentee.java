package mz.org.fgh.mentoring.entity.ronda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.fgh.mentoring.base.BaseEntity;
import mz.org.fgh.mentoring.entity.tutored.Tutored;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "RondaMentee")
@Table(name = "ronda_mentee")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RondaMentee extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RONDA_ID")
    private Ronda ronda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENTEE_ID")
    private Tutored tutored;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Override
    public String toString() {
        return "RondaMentee{" +
                ", tutored=" + tutored +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
