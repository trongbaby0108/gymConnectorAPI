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
        // mock ở đây tức là m sẽ tạo ra 1 cái repository ảo
        // khi mà những thứ gì liên quan tới db thì mày nên mock nó
        repository = mock(gymRepository.class);
        // ở đây tạo new cho nó nếu m cứ autowired như hồi trước thì khi viết UT
        // làm del gì có bean để xài nên phải có 1 constructor để import tk ở trên
        service = new gymServiceImpl(repository);
        // ở đây m tạo ra 1 instance để kiểm tra
        gym = new gym(1, "Trọng", "123", "123", "123@gmail.com", "123", true);
    }

    @Test
    // tên hàm muốn test _ giá trị truyền vào _ kết quả
    void findGymById_validId_Success() {
        // cú pháp when -> return đấy là m đang giả sử tk này (repository) nên nhớ là chỉ xài được với mấy tk ĐÃ MOCK
        // khi chạy hàm này sẽ trả cái m đang mong đợi
        when(repository.findById(1)).thenReturn(Optional.of(gym));
        // assert ở đấy tức là sẽ kiếm tra equal là bằng là kiểm tra xem kết quả trả về từ
        // cái hàm m muốn test có bằng cái mày mong đợi hay không
        // ở đây chắc cú là check hết field mà t lười :V
        assertEquals(service.findGymById(1).getName(), gym.getName());
    }

    @Test
    void findGymById_validId_ThrowNotFoundException() {
        // ở đây m sẽ giả sử nó lỗi
        when(repository.findById(2)).thenReturn(Optional.empty());
        // rồi assert equal nó
        assertThrows(NotFoundException.class, () -> service.findGymById(1));
    }
}