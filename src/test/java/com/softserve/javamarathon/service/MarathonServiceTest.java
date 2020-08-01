package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.repository.MarathonRepository;
import com.softserve.javamarathon.service.impl.MarathonServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MarathonServiceTest {
    @Mock
    private MarathonRepository marathonRepository;
    @InjectMocks
    private MarathonServiceImpl marathonService;
    private Marathon marathon1 = Marathon.builder().id(1L)
        .title("Java").build();
    private Marathon marathon2 = Marathon.builder().id(2L)
        .title("Python").build();
    private Marathon marathon3 = Marathon.builder().id(3L)
        .title("C#").build();


    @Test
    public void findAllTest() {
        List<Marathon> marathons = Arrays.asList(marathon1, marathon2, marathon3);
        when(marathonRepository.findAll()).thenReturn(marathons);
        assertEquals(marathons, marathonService.getAll());
    }

    @Test
    public void deleteByIdTest() {
        when(marathonRepository.findById(anyLong())).thenReturn(Optional.of(marathon1));
        doNothing().when(marathonRepository).delete(marathon1);
        marathonService.deleteMarathonById(1L);
        verify(marathonRepository, times(1)).delete(marathon1);

    }
    @Test
    public void saveTest() {
        when(marathonRepository.save(marathon1)).thenReturn(marathon1);
        assertEquals(marathon1, marathonService.createOrUpdate(marathon1));
    }
}
