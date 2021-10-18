package co.trucom.musiciansbookingagency.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import co.trucom.musiciansbookingagency.dtos.GigDto;
import co.trucom.musiciansbookingagency.dtos.converters.GigConverter;
import co.trucom.musiciansbookingagency.models.Artist;
import co.trucom.musiciansbookingagency.models.Gig;
import co.trucom.musiciansbookingagency.repos.ArtistRepository;
import co.trucom.musiciansbookingagency.repos.GigRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

	private static final Logger log = Logger.getLogger(ArtistServiceImpl.class.getName());

	private ArtistRepository artistRepository;
	private GigRepository gigRepository;

	public ArtistServiceImpl(ArtistRepository artistRepo, GigRepository gigRepo) {
		artistRepository = artistRepo;
		gigRepository = gigRepo;
	}

	@Override
	@Cacheable(value = "artists", key = "#id")
	public Optional<Artist> getArtistById(Long id) {
		log.log(Level.FINE, "Got artist with ID {0} from the database.", id);
		return artistRepository.findById(id);
	}

	@Override
	public List<Artist> getAll() {
		return artistRepository.findAll();
	}

	@Override
	@CachePut(value = {"artists"}, key = "#artist.artistId", unless = "#artist.artistId == null")
	public Artist save(Artist artist) {
		return artistRepository.saveAndFlush(artist);
	}

	@Override
	@CacheEvict(value = "artists", key = "#id")
	public void deleteById(Long id) {
		artistRepository.deleteById(id);		
	}

	@Override
	public GigDto addGig(Long artistId, GigDto dto) {
		Artist artist = getArtistById(artistId).get();
		Gig gig = GigConverter.dtoToGig(dto);
		gig.setArtist(artist);
		artist.addGig(gig);
		save(artist);
		//artist.printGigs();

		gig = gigRepository.saveAndFlush(gig);

		return GigConverter.gigToDto(gig);
	}

	@Override
	public Set<GigDto> getGigs(Long artistId) {
		return GigConverter.listToDto(gigRepository.findByArtistId(artistId));
	}

	@Override
	public Set<GigDto> getGigsByCity(Long artistId, String city) {
		return GigConverter.listToDto(gigRepository.findByArtistAndCity(artistId, city));
	}

	@Override
	@CacheEvict(value = "artists", allEntries = true)
	public void deleteAll() {
		artistRepository.deleteAll();
		gigRepository.deleteAll();
	}

}
