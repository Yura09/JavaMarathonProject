package com.softserve.javamarathon;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = JavaMarathonApplication.class)
class JavaMarathonApplicationTests {

    private  MarathonService marathonService;

    @Autowired
    public void setMarathonService(MarathonService marathonService) {
        this.marathonService = marathonService;
    }
    @Test
    void getMarathons_MarathonDoesntExist_ThrowsNoEntityException(){
        assertThrows(NoEntityException.class,()->
                marathonService.getMarathonById(10L));
    }
}
