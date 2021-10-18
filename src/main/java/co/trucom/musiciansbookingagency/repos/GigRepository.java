package co.trucom.musiciansbookingagency.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.trucom.musiciansbookingagency.models.Gig;

public interface GigRepository extends JpaRepository<Gig, Long> {

	@Query("SELECT g FROM Gig g WHERE artist_id = ?1")
	Set<Gig> findByArtistId(Long id);

	@Query("SELECT g FROM Gig g WHERE artist_id = ?1 AND location = ?2")
	Set<Gig> findByArtistAndCity(Long id, String city);

}
