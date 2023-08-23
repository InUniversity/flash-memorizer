package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.entity.SharedDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    Long countById(Long id);
    List<Deck> findByUserId(Long userId);

    @Query(value = "SELECT d.* FROM decks d " +
            "INNER JOIN users u ON d.user_id = u.id " +
            "WHERE u.username = :username", nativeQuery = true)
    List<Deck> findByUsername(String username);
}
