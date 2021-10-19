package co.trucom.musiciansbookingagency.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.trucom.musiciansbookingagency.models.Artist;
import co.trucom.musiciansbookingagency.repos.ArtistRepository;


@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = MusiciansBookingAgencyApplication.class)
@DataRedisTest
class ArtistServiceTest {

//	@Autowired
//	CacheManager cacheManager;

	@MockBean
	ArtistRepository artistRepository;

	@MockBean
	GigService gigService;

	ArtistServiceImpl artistService;

	Artist artist;

	@BeforeEach
	void setup() {
		artistService = new ArtistServiceImpl(artistRepository);

		artist = new Artist("Franz Ferdinand");
		artist.setArtistId(1L);

		Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
	}

	@Test
	void repositoryMustReturnCorrespondentArtist() {
		Artist resultArtist = artistService.getArtistById(1L).get();

		assertEquals(artist, resultArtist);
	}

	@Test
	void databaseShouldNotBeConsultedIfArtistIsInCache() {
		artistService.save(artist);
		Optional<Artist> retrievedArtist = artistService.getArtistById(1L);
		Optional<Artist> anotherCall = artistService.getArtistById(1L);

//		Optional<Artist> cachedArtist = Optional.ofNullable(cacheManager.getCache("artists"))
//				.map(c -> c.get(1L, Artist.class));
//
//		assertEquals(retrievedArtist, cachedArtist);

		Mockito.verify(artistRepository, Mockito.atMostOnce()).findById(1L);
	}

}
