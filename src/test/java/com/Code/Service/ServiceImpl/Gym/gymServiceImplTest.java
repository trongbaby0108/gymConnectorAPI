package com.Code.Service.ServiceImpl.Gym;

import com.Code.Exception.NotFoundException;
import com.Code.Repository.Gym.gymRepository;
import com.Code.Entity.Gym.gym;
import com.Code.Service.ServiceImpl.Gym.gymServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class gymServiceImplTest {

    private gymRepository repository;
    private gymServiceImpl service;
    private gym gym;
    @BeforeEach
    void setUp() {
        repository = mock(gymRepository.class);
        service = new gymServiceImpl(repository);
        gym = new gym(1, "Trọng", "123", "123", "123@gmail.com", "123", true);
    }

    @Test
    void findGymById_validId_Success() {
        when(repository.findById(1)).thenReturn(Optional.of(gym));
        assertEquals(service.findGymById(1).getName(), gym.getName());
    }

    @Test
    void findGymById_validId_ThrowNotFoundException() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ChangeSetPersister.NotFoundException.class, (Executable) service.findGymById(1));
    }
}