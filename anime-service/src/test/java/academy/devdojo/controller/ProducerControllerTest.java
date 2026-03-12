package academy.devdojo.controller;

import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.ProducerMapperImpl;
import academy.devdojo.repository.ProducerData;
import academy.devdojo.repository.ProducerHardCodedRepository;
import academy.devdojo.service.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = ProducerController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "academy.devdojo") // importa as classes, melhor para projetos pequenos e/ou com muitos importes
//@Import({ProducerMapperImpl.class, ProducerService.class, ProducerHardCodedRepository.class, ProducerData.class}) // melhor para projetos grandes e/ou poucos importes
class ProducerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProducerData producerData;
    private final List<Producer> producerList = new ArrayList<>();
    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    void init() {
        String dateTime = "2026-03-12T16:23:08.5225638";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime,formatter);

        Producer ufotable = Producer.builder().id(1L).name("Ufotable").createdAt(localDateTime).build();
        Producer a1Pictures = Producer.builder().id(2L).name("A-1 Pictures").createdAt(localDateTime).build();
        Producer davidProduction = Producer.builder().id(3L).name("David Production").createdAt(localDateTime).build();
        Producer studioBones = Producer.builder().id(4L).name("Studio Bones").createdAt(localDateTime).build();
        Producer productionIG = Producer.builder().id(5L).name("Production I. G.").createdAt(localDateTime).build();
        producerList.addAll(List.of(ufotable, a1Pictures, davidProduction, studioBones, productionIG));
    }

    @Test
    @DisplayName("findAll returns a list with all producers when name is null")
    @Order(1)
    void findAll_ReturnsAllProducers_WhenNameIsNull() throws Exception {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        String response = readResourceFile("producer/get-producer-null-name-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/producers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    private String readResourceFile(String fileName) throws IOException {
        File file = resourceLoader.getResource("classpath:%s".formatted(fileName)).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }
}