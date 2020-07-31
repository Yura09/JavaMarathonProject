package com.softserve.javamarathon.repository;

import com.softserve.javamarathon.entity.Marathon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

//@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MarathonRepositoryTest {
    private MarathonRepository marathonRepository;

    @Autowired
    public void setMarathonRepository(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Test
    public void newMarathonTest() {
        Marathon marathon = new Marathon();
        marathon.setTitle("java Marathon");
        marathonRepository.save(marathon);
        List<Marathon> marathons = marathonRepository.findAll();
        Assertions.assertFalse(marathons.isEmpty());
    }
}
