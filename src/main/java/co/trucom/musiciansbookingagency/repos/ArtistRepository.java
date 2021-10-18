package co.trucom.musiciansbookingagency.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.trucom.musiciansbookingagency.models.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
