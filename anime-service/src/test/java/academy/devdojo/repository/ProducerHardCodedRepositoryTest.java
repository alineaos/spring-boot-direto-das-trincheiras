package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerHardCodedRepositoryTest {

    @InjectMocks
    private ProducerHardCodedRepository repository;
    @Mock
    private ProducerData producerData;
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
    @DisplayName("findAll returns a list with all producers")
    @Order(1)
    void findAll_ReturnsAllProducers_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        List<Producer> producers = repository.findAll();
        Assertions.assertThat(producers).isNotNull().hasSize(producerList.size());
    }

    @Test
    @DisplayName("findById returns a producer with given id")
    @Order(2)
    void findById_ReturnsProducerById_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        Producer expectedProducer = producerList.getFirst();
        Optional<Producer> producer = repository.findById(expectedProducer.getId());
        Assertions.assertThat(producer).isPresent().contains(expectedProducer);
    }

    @Test
    @DisplayName("findByName returns empty list when name is null")
    @Order(3)
    void findByName_ReturnsEmptyList_WhenNameIsNull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        List<Producer> producers = repository.findByName(null);
        Assertions.assertThat(producers).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName returnslist with found objects when name exists")
    @Order(4)
    void findByName_ReturnsFoundPRoducerInList_WhenNameIsFound() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        Producer expectedProducer = producerList.getFirst();
        List<Producer> producers = repository.findByName(expectedProducer.getName());
        Assertions.assertThat(producers).contains(expectedProducer);
    }

    @Test
    @DisplayName("save creates a producer")
    @Order(5)
    void save_CreatesProducer_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        Producer producerToSave = Producer.builder().id(99L).name("MAPPA").createdAt(LocalDateTime.now()).build();
        Producer producer = repository.save(producerToSave);

        Assertions.assertThat(producer).isEqualTo(producerToSave).hasNoNullFieldsOrProperties();

        Optional<Producer> producerSavedOptional = repository.findById(producerToSave.getId());
        Assertions.assertThat(producerSavedOptional).isPresent().contains(producerToSave);

    }

    @Test
    @DisplayName("delete removes a producer")
    @Order(6)
    void delete_RemoveProducer_WhenSuccessul() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        Producer producerToDelete = producerList.getFirst();
        repository.delete(producerToDelete);

        List<Producer> producers = repository.findAll();

        Assertions.assertThat(producers).doesNotContain(producerToDelete);
    }

    @Test
    @DisplayName("update updates a producer")
    @Order(7)
    void update_UpdatesProducer_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        Producer producerToUpdate = producerList.getFirst();
        producerToUpdate.setName("Aniplex");

        repository.update(producerToUpdate);

        Assertions.assertThat(this.producerList).contains(producerToUpdate);

        Optional<Producer> producerUpdatedOptional = repository.findById(producerToUpdate.getId());
        Assertions.assertThat(producerUpdatedOptional).isPresent();
        Assertions.assertThat(producerUpdatedOptional.get().getName()).isEqualTo(producerToUpdate.getName());


    }
}