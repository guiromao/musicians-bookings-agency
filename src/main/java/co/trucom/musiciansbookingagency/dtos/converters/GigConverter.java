package co.trucom.musiciansbookingagency.dtos.converters;

import java.util.HashSet;
import java.util.Set;

import co.trucom.musiciansbookingagency.dtos.GigDto;
import co.trucom.musiciansbookingagency.models.Gig;
import co.trucom.musiciansbookingagency.utils.Utils;

public class GigConverter {

	public static Gig dtoToGig(GigDto dto) {
		Gig gig = new Gig();
		gig.setDate(Utils.stringToDate(dto.getDateStr()));
		gig.setLocation(dto.getLocation());

		return gig;
	}

	public static GigDto gigToDto(Gig gig) {
		return new GigDto(gig.getGigId(),
				gig.getLocation(),
				Utils.dateToString(gig.getDate()));
	}

	public static Set<GigDto> listToDto(Set<Gig> list) {
		Set<GigDto> result = new HashSet<>();

		for(Gig gig: list) {
			result.add(gigToDto(gig));
		}

		return result;
	}

}
