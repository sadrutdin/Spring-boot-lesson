package sandro.lesson.spring.service.hospital;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandro.lesson.spring.dto.User;
import sandro.lesson.spring.service.user.UserService;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class HospitalServiceImpl implements HospitalService {
    private final Set<User> patientSet = new HashSet<>();

    @Autowired
    private UserService userService;

    @Override
    public boolean addPatient(User user) {
        Long userId = user.getId();
        if (userService.findById(userId).isPresent() &&
                !patientSet.contains(user) &&
                patientSet.add(user)) {
            log.info("Пациент положен в больницу");
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePatient(User user) {
        if (patientSet.contains(user) && patientSet.remove(user)) {
            log.info("Пациент выписан: '{}'", user);
            return true;
        }
        return false;
    }

    @Override
    public boolean death(User user) {
        if (patientSet.contains(user) && patientSet.remove(user) && userService.delete(user)) {
            log.info("Пациент умер: '{}'", user);
            return true;
        }
        return false;
    }
}
