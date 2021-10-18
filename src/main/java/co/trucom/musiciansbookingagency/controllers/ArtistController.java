package co.trucom.musiciansbookingagency.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.trucom.musiciansbookingagency.dtos.ArtistDto;
import co.trucom.musiciansbookingagency.dtos.GigDto;
import co.trucom.musiciansbookingagency.dtos.converters.ArtistConverter;
import co.trucom.musiciansbookingagency.models.Artist;
import co.trucom.musiciansbookingagency.models.Gig;
import co.trucom.musiciansbookingagency.services.ArtistService;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {

	private ArtistService artistService;

	public ArtistController(ArtistService service) {
		artistService = service;
	}

	@GetMapping("/{artistId}")
	public ResponseEntity<Artist> getArtist(@PathVariable Long artistId) {
		Artist artist = artistService.getArtistById(artistId).orElseGet(null);

		return new ResponseEntity<>(artist, HttpStatus.OK);
	}

	@GetMapping({"", "/"})
	public ResponseEntity<List<Artist>> getAllArtists() {
		return new ResponseEntity<>(artistService.getAll(), HttpStatus.OK);
	}

	@PostMapping({"", "/"})
	public ResponseEntity<Artist> saveArtist(@RequestBody ArtistDto dto) {
		Artist artist = ArtistConverter.dtoToArtist(dto);

		return new ResponseEntity<>(artistService.save(artist), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateArtist(@PathVariable Long id, @RequestBody ArtistDto dto) {
		Artist existingArtist = artistService.getArtistById(id).get();
		Artist updatedArtist = ArtistConverter.dtoToArtist(dto);
		
		BeanUtils.copyProperties(existingArtist, updatedArtist, "artist_id");
		artistService.save(updatedArtist);
		
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/{artistId}")
	public ResponseEntity deleteArtist(@PathVariable Long artistId) {
		artistService.deleteById(artistId);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/{artistId}/gigs")
	public ResponseEntity<GigDto> addGigToArtist(@PathVariable Long artistId, @RequestBody GigDto gigDto) {
		return new ResponseEntity<>(artistService.addGig(gigDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{artistId}/gigs")
	public ResponseEntity<Set<GigDto>> listOfGigs(@PathVariable Long artistId) {
		return new ResponseEntity<>(artistService.getGigs(artistId), HttpStatus.OK);
	}
	
	@GetMapping("/{artistId}/location")
	public ResponseEntity<Set<GigDto>> listOfGigsByCity(@PathVariable Long artistId, @RequestParam String city) {
		return new ResponseEntity<>(artistService.getGigsByCity(artistId, city), HttpStatus.OK);
	}

}
