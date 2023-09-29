package mz.org.fgh.mentoring.dto.tutor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.fgh.mentoring.dto.career.CareerDTO;
import mz.org.fgh.mentoring.dto.partner.PartnerDTO;
import mz.org.fgh.mentoring.entity.tutor.Tutor;
import mz.org.fgh.mentoring.entity.user.UserDTO;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO implements Serializable {
    private String uuid;
    private String name;

    private String sunname;

    @JsonProperty(value = "career")
    private CareerDTO careerDTO;

    private String phoneNumber;

    private String email;

    private boolean isUser;

    @JsonProperty(value = "partner")
    private PartnerDTO partnerDTO;

    @JsonProperty(value = "user")
    private UserDTO userDTO;

    public TutorDTO(Tutor tutor) {
        this.setUuid(tutor.getUuid());
        this.setName(tutor.getName());
        this.setSunname(tutor.getSurname());
        this.setCareerDTO(new CareerDTO(tutor.getCareer()));
        this.setPhoneNumber(tutor.getPhoneNumber());
        this.setEmail(tutor.getEmail());
        this.setUserDTO(new UserDTO(tutor.getUser()));

    }
}