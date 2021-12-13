package jieun.hostMonitoringApplication.modules.host;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class HostControllerTest {

    @Autowired MockMvc mockMvc;

    @Test
    @DisplayName("호스트 등록 페이지 - 성공")
    void viewHosts_success() throws Exception {
        mockMvc.perform(get("/hosts"))
                .andExpect(status().isOk())
                .andExpect(view().name("host/view"));
    }

}
