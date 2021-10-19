package co.trucom.musiciansbookingagency.services;

import java.util.List;
import java.util.Optional;

import co.trucom.musiciansbookingagency.dtos.GigDto;
import co.trucom.musiciansbookingagency.models.Gig;

public interface GigService {

	Optional<Gig> findById(Long id, Long artistId);

	List<GigDto> getGigsFromArtistId(Long artistId);

	List<GigDto> getGigsFromArtistAndCity(Long artistId, String city);

	void deleteAll();

}
