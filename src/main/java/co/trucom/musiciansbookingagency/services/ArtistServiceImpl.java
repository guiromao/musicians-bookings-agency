package co.trucom.musiciansbookingagency.services;

import java.util.List;
import java.util.Optional;
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

@Service
public class ArtistServiceImpl implements ArtistService {

	private static final Logger log = Logger.getLogger(ArtistServiceImpl.class.getName());

	private ArtistRepository artistRepository;

	public ArtistServiceImpl(ArtistRepository artistRepo) {
		artistRepository = artistRepo;
	}

	@Override
	@Cacheable(value = {"artists", "gigs"}, key = "#id")
	public Optional<Artist> getArtistById(Long id) {
		log.log(Level.FINE, "Got artist with ID {0} from the database.", id);
		return artistRepository.findById(id);
	}
	
	@Override
	public GigDto saveGig(GigDto dto, Long artistId) {
		Gig gig = GigConverter.dtoToGig(dto);
		Artist artist = getArtistById(artistId).get();
		gig.setArtist(artist);
		artist.addGig(gig);
		artistRepository.saveAndFlush(artist);
		
		List<Gig> gigs = artist.getGigs();
		Gig lastGig = gigs.get(gigs.size() - 1);
		
		return GigConverter.gigToDto(lastGig);
	}

	@Override
	public List<Artist> getAll() {
		return artistRepository.findAll();
	}

	@Override
	@CachePut(value = "artists", key = "#artist.artistId", unless = "#artist.artistId == null")
	public Artist save(Artist artist) {
		return artistRepository.saveAndFlush(artist);
	}

	@Override
	@CacheEvict(value = "artists", key = "#id")
	public void deleteById(Long id) {
		artistRepository.deleteById(id);		
	}

	@Override
	@CacheEvict(value = "artists", allEntries = true)
	public void deleteAll() {
		artistRepository.deleteAll();
		//gigService.deleteAll();
	}

	@Override
	@CacheEvict(value = "artists", allEntries = true)
	public void deleteCache() {
		//Only for deleting cache values
	}

}
