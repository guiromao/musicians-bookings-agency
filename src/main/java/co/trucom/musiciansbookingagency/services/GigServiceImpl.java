package co.trucom.musiciansbookingagency.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.trucom.musiciansbookingagency.dtos.GigDto;
import co.trucom.musiciansbookingagency.dtos.converters.GigConverter;
import co.trucom.musiciansbookingagency.models.Gig;
import co.trucom.musiciansbookingagency.repos.GigRepository;

@Service
public class GigServiceImpl implements GigService {

	private GigRepository gigRepository;

	public GigServiceImpl(GigRepository repo) {
		gigRepository = repo;
	}

	public Optional<Gig> findById(Long id, Long artistId) {
		return gigRepository.findById(id);
	}

	@Override
	public List<GigDto> getGigsFromArtistId(Long artistId) {
		return GigConverter.listToDto(gigRepository.findByArtistId(artistId));
	}

	@Override
	public List<GigDto> getGigsFromArtistAndCity(Long artistId, String city) {
		return GigConverter.listToDto(gigRepository.findByArtistAndCity(artistId, city));
	}

	@Override
	public void deleteAll() {
		gigRepository.deleteAll();
	}

}
