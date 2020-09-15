package com.indir.moviesdb.service;

import com.indir.moviesdb.MoviesdbApplication;
import com.indir.moviesdb.domain.Country;
import com.indir.moviesdb.dto.CountryDto;
import com.indir.moviesdb.mapper.CountryMapper;
import com.indir.moviesdb.repository.CountryRepository;
import com.indir.moviesdb.service.implementation.CountryServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@Transactional
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/script.sql")
@WebAppConfiguration
@SpringBootTest(classes = MoviesdbApplication.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

public class CountryServiceTest {

    @Mock
    CountryRepository countryRepository;
    CountryService countryService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        countryService = new CountryServiceImpl(countryRepository, CountryMapper.INSTANCE);
    }

    @Test
    void getCountries() throws  Exception{
        List<Country> categories = Arrays.asList(new Country(), new Country(), new Country());
        when(countryRepository.findAll()).thenReturn(categories);
    }

    @Test
    void findById() {
        Country country = new Country();
        country.setId(1);
        country.setName("Russia");

        when(countryRepository.findById(anyInt())).thenReturn(Optional.of(country));

        CountryDto countryDto = countryService.findById(1);
        assertEquals(1, countryDto.getId());
        assertEquals("Russia", countryDto.getName());
    }

    @Test
    void deleteCountry() throws Exception{
        Country country = new Country();
        country.setId(13);
        country.setName("Croatia");

        Mockito.when(countryRepository.findById(13)).thenReturn(Optional.of(country));
        countryService.deleteCountry(country.getId());
        Mockito.verify(countryRepository, times(1)).delete(country);

    }
}