package co.trucom.musiciansbookingagency.services;

import java.util.List;
import java.util.Optional;

import co.trucom.musiciansbookingagency.dtos.GigDto;
import co.trucom.musiciansbookingagency.models.Artist;

public interface ArtistService {

	Optional<Artist> getArtistById(Long id);

	List<Artist> getAll();

	Artist save(Artist artist);
	
	GigDto saveGig(GigDto gig, Long artistId);

	void deleteById(Long id);

	void deleteAll();

	void deleteCache();
}
