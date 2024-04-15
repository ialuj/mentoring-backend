package mz.org.fgh.mentoring.service.tutorprogrammaticarea;

import jakarta.inject.Singleton;
import mz.org.fgh.mentoring.dto.program.ProgramDTO;
import mz.org.fgh.mentoring.dto.programmaticarea.TutorProgrammaticAreaDTO;
import mz.org.fgh.mentoring.entity.program.Program;
import mz.org.fgh.mentoring.entity.tutorprogramaticarea.TutorProgrammaticArea;
import mz.org.fgh.mentoring.entity.user.User;
import mz.org.fgh.mentoring.repository.programaticarea.TutorProgrammaticAreaRepository;
import mz.org.fgh.mentoring.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class TutorProgrammaticAreaService {

    private final TutorProgrammaticAreaRepository tutorProgrammaticAreaRepository;
    private final UserRepository userRepository;
    public TutorProgrammaticAreaService(TutorProgrammaticAreaRepository tutorProgrammaticAreaRepository, UserRepository userRepository) {
        this.tutorProgrammaticAreaRepository = tutorProgrammaticAreaRepository;
        this.userRepository = userRepository;
    }
    public TutorProgrammaticArea create(final TutorProgrammaticArea tutorProgrammaticArea, Long userId){
        User user = userRepository.findById(userId).get();
        return this.tutorProgrammaticAreaRepository.save(tutorProgrammaticArea);
    }
    public TutorProgrammaticArea update(final TutorProgrammaticArea tutorProgrammaticArea){
        return this.tutorProgrammaticAreaRepository.update(tutorProgrammaticArea);
    }

    public List<TutorProgrammaticAreaDTO> findAllTutorProgrammaticAreas() {
        List<TutorProgrammaticArea> tutList = this.tutorProgrammaticAreaRepository.findAll();
        List<TutorProgrammaticAreaDTO> programs = new ArrayList<TutorProgrammaticAreaDTO>();
        for (TutorProgrammaticArea tut: tutList) {
            TutorProgrammaticAreaDTO tutDTO = new TutorProgrammaticAreaDTO(tut);
            programs.add(tutDTO);
        }
        return programs;
    }
    public Optional<TutorProgrammaticArea> findById(final Long id){
        return this.tutorProgrammaticAreaRepository.findById(id);
    }
}
