package co.trucom.musiciansbookingagency.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import co.trucom.musiciansbookingagency.dtos.GigDto;
import co.trucom.musiciansbookingagency.models.Artist;

public interface ArtistService {

	Optional<Artist> getArtistById(Long id);

	List<Artist> getAll();

	Artist save(Artist artist);

	void deleteById(Long id);

	GigDto addGig(GigDto dto);

	Set<GigDto> getGigs(Long artistId);

	Set<GigDto> getGigsByCity(Long artistId, String city);
}
