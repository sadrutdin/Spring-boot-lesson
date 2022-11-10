package sandro.lesson.spring.service.hospital;

import sandro.lesson.spring.dto.User;

public interface HospitalService {
    boolean addPatient(User user);

    boolean deletePatient(User user);

    boolean death(User user);
}
