package academy.devdojo.service;

import academy.devdojo.domain.Producer;
import academy.devdojo.repository.ProducerHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerServiceTest {
    @InjectMocks
    private ProducerService service;
    @Mock
    private ProducerHardCodedRepository repository;

    private final List<Producer> producerList = new ArrayList<>();
    @BeforeEach
    void init() {
        Producer ufotable = Producer.builder().id(1L).name("Ufotable").createdAt(LocalDateTime.now()).build();
        Producer a1Pictures = Producer.builder().id(2L).name("A-1 Pictures").createdAt(LocalDateTime.now()).build();
        Producer davidProduction = Producer.builder().id(3L).name("David Production").createdAt(LocalDateTime.now()).build();
        Producer studioBones = Producer.builder().id(4L).name("Studio Bones").createdAt(LocalDateTime.now()).build();
        Producer productionIG = Producer.builder().id(5L).name("Production I. G.").createdAt(LocalDateTime.now()).build();
        producerList.addAll(List.of(ufotable, a1Pictures, davidProduction, studioBones, productionIG));
    }

    @Test
    @DisplayName("findAll returns a list with all producers when name is null")
    @Order(1)
    void findAll_ReturnsAllProducers_WhenNameIsNull() {
        BDDMockito.when(repository.findAll()).thenReturn(producerList);

        List<Producer> producers = service.findAll(null);
        Assertions.assertThat(producers).isNotNull().hasSize(producerList.size());
    }

    @Test
    @DisplayName("findAll returns list with found objects when argument exists")
    @Order(2)
    void findByName_ReturnsFoundProducerInList_WhenNameIsFound() {
        Producer producer = producerList.getFirst();
        List<Producer> expectedProducersFound = Collections.singletonList(producer);
        BDDMockito.when(repository.findByName(producer.getName())).thenReturn(expectedProducersFound);

        List<Producer> producers = service.findAll(producer.getName());
        Assertions.assertThat(producers).containsAll(expectedProducersFound);
    }

    @Test
    @DisplayName("findAll returns empty list when name is not found")
    @Order(3)
    void findAll_ReturnsEmptyList_WhenNameIsNotFound() {
        String name = "not-found";
        BDDMockito.when(repository.findByName(name)).thenReturn(Collections.emptyList());

        List<Producer> producers = service.findAll(name);
        Assertions.assertThat(producers).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findById returns a producer with given id")
    @Order(4)
    void findById_ReturnsProducerById_WhenSuccessful() {
        Producer expectedProducer = producerList.getFirst();
        BDDMockito.when(repository.findById(expectedProducer.getId())).thenReturn(Optional.of(expectedProducer));

        Producer producer = service.findByIdOrThrowNotFound(expectedProducer.getId());
        Assertions.assertThat(producer).isEqualTo(expectedProducer);
    }
    @Test
    @DisplayName("findById throws ResponseStatusException when producer is not found")
    @Order(5)
    void findById_ThrowsResponseStatusException_WhenProducerIsNotFound() {
        Producer expectedProducer = producerList.getFirst();
        BDDMockito.when(repository.findById(expectedProducer.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.findByIdOrThrowNotFound(expectedProducer.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("save creates a producer")
    @Order(6)
    void save_CreatesProducer_WhenSuccessful() {
        Producer producerToSave = Producer.builder().id(99L).name("MAPPA").createdAt(LocalDateTime.now()).build();
        BDDMockito.when(repository.save(producerToSave)).thenReturn(producerToSave);

        Producer savedProducer = service.save(producerToSave);

        Assertions.assertThat(savedProducer).isEqualTo(producerToSave);
    }

    @Test
    @DisplayName("delete removes a producer")
    @Order(7)
    void delete_RemoveProducer_WhenSuccessul() {
        Producer producerToDelete = producerList.getFirst();
        BDDMockito.when(repository.findById(producerToDelete.getId())).thenReturn(Optional.of(producerToDelete));
        BDDMockito.doNothing().when(repository).delete(producerToDelete);

        Assertions.assertThatNoException().isThrownBy(() -> service.delete(producerToDelete.getId()));
    }

    @Test
    @DisplayName("delete throws ResponseStatusException when producer is not found")
    @Order(8)
    void delete_ThrowsResponseStatusException_WhenProducerIsNotFound() {
        Producer producerToDelete = producerList.getFirst();
        BDDMockito.when(repository.findById(producerToDelete.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.delete(producerToDelete.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("update updates a producer")
    @Order(9)
    void update_UpdatesProducer_WhenSuccessful() {
        Producer producerToUpdate = producerList.getFirst();
        producerToUpdate.setName("Aniplex");
        BDDMockito.when(repository.findById(producerToUpdate.getId())).thenReturn(Optional.of(producerToUpdate));
        BDDMockito.doNothing().when(repository).update(producerToUpdate);

        Assertions.assertThatNoException().isThrownBy(() -> service.update(producerToUpdate));
    }

    @Test
    @DisplayName("update throws ResponseStatusException when producer is not found")
    @Order(10)
    void update_ThrowsResponseStatusException_WhenProducerIsNotFound() {
        Producer producerToUpdate = producerList.getFirst();
        BDDMockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.update(producerToUpdate))
                .isInstanceOf(ResponseStatusException.class);
    }
}