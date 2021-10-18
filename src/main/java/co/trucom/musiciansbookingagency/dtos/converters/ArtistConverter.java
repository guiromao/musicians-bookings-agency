package co.trucom.musiciansbookingagency.dtos.converters;

import org.modelmapper.ModelMapper;

import co.trucom.musiciansbookingagency.dtos.ArtistDto;
import co.trucom.musiciansbookingagency.models.Artist;

public class ArtistConverter {

	public static Artist dtoToArtist(ArtistDto dto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(dto, Artist.class);
	}

	public static ArtistDto artistToDto(Artist artist) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(artist, ArtistDto.class);
	}

}
