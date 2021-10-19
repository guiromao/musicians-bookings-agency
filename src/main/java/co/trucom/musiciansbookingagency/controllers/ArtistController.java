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
import co.trucom.musiciansbookingagency.dtos.converters.GigConverter;
import co.trucom.musiciansbookingagency.models.Artist;
import co.trucom.musiciansbookingagency.models.Gig;
import co.trucom.musiciansbookingagency.services.ArtistService;
import co.trucom.musiciansbookingagency.services.GigService;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {

	private ArtistService artistService;
	private GigService gigService;

	public ArtistController(ArtistService artService, GigService gService) {
		artistService = artService;
		gigService = gService;
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

	@DeleteMapping({"", "/"})
	public ResponseEntity deleteAll() {
		artistService.deleteAll();
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/{artistId}")
	public ResponseEntity deleteArtist(@PathVariable Long artistId) {
		artistService.deleteById(artistId);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/cache")
	public ResponseEntity deleteCache() {
		artistService.deleteCache();
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/{artistId}/gigs")
	public ResponseEntity<GigDto> addGigToArtist(@PathVariable Long artistId, @RequestBody GigDto gigDto) {
		GigDto dto = artistService.saveGig(gigDto, artistId);

		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@GetMapping("/{artistId}/gigs")
	public ResponseEntity<List<GigDto>> listOfGigs(@PathVariable Long artistId) {
		return new ResponseEntity<>(gigService.getGigsFromArtistId(artistId), HttpStatus.OK);
	}
	
	@GetMapping("/{artistId}/location")
	public ResponseEntity<List<GigDto>> listOfGigsByCity(@PathVariable Long artistId, @RequestParam String city) {
		return new ResponseEntity<>(gigService.getGigsFromArtistAndCity(artistId, city), HttpStatus.OK);
	}

}
