package com.universityteam.flashmemorizer.config;

import com.universityteam.flashmemorizer.entity.*;
import com.universityteam.flashmemorizer.enums.ERating;
import com.universityteam.flashmemorizer.repository.*;
import com.universityteam.flashmemorizer.service.CardService;
import com.universityteam.flashmemorizer.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CardRepository cardRepo;
    private final DeckRepository deckRepo;
    private final UserRepository userRepo;
    private final UserCardRepository userCardRepo;
    private final SharedDeckRepository sharedDeckRepo;
    private final ImageService imageService;
    @Override
    public void run(String... args) throws Exception {
        clearAllData();
        initData();
    }

    private void clearAllData() {
        sharedDeckRepo.deleteAll();
        userCardRepo.deleteAll();
        cardRepo.deleteAll();
        deckRepo.deleteAll();
        userRepo.deleteAll();
    }

    private void initData() throws IOException {
        List<User> users = initUser();
        List<Deck> decks = initDeck( users.get(0) );
        List<Card> cards = initCard( decks.get(0) );
        initUserCard( cards.get(0), users.get(0) );
        initSharedDeck( users.get(1), decks.get(0) );
    }

    private List<User> initUser() {
        List<User> users = List.of(
                User.builder()
                        .username("username1")
                        .pass("pass2")
                        .email("user1@gmail.com")
                        .fullName("User One")
                        .registration(new Date(2023, 1, 1, 12, 12, 12))
                        .lastLogin(new Date(2023, 1, 2, 4, 12, 12))
                        .build(),
                User.builder()
                        .username("username2")
                        .pass("pass2")
                        .email("user2@gamil.com")
                        .fullName("User Two")
                        .registration(new Date(2023, 4, 4, 5, 5, 5))
                        .lastLogin(new Date(2023, 3, 1, 4, 5, 6))
                        .build()
        );
        return userRepo.saveAll(users);
    }

    private List<Deck> initDeck(User user) {
        List<Deck> decks = List.of(
                Deck.builder()
                        .name("Deck 1")
                        .desc("Deck Desc 1")
                        .creation(new Date(2023, 1, 1, 12, 0 ,0))
                        .modified(new Date(2023, 3, 1, 12, 0, 0))
                        .user(user)
                        .build(),
                Deck.builder()
                        .name("Deck 2")
                        .desc("Deck Desc 2")
                        .creation(new Date(2023, 1, 1, 12, 0 ,0))
                        .modified(new Date(2023, 3, 1, 12, 0, 0))
                        .user(user)
                        .build()
        );
        return deckRepo.saveAll(decks);
    }

    private List<Card> initCard(Deck deck) throws IOException {
        String currentFilePath = Paths.get("").toAbsolutePath().toString();
        String imagePath = currentFilePath +"\\src\\test\\java\\com\\universityteam\\flashmemorizer\\resource\\meo.jpg";
        byte[] imageBytes = imageService.convertPathToByte(imagePath);
        List<Card> cards = List.of(
                Card.builder()
                        .term("Term 1")
                        .desc("Description 1")
                        .img(imageBytes)
                        .deck(deck)
                        .build(),
                Card.builder()
                        .term("Term 2")
                        .desc("Description 2")
                        .img(imageBytes)
                        .deck(deck)
                        .build(),
                Card.builder()
                        .term("Term 3")
                        .desc("Description 3")
                        .img(imageBytes)
                        .deck(deck)
                        .build(),
                Card.builder()
                        .term("Term 4")
                        .desc("Description 4")
                        .img(imageBytes)
                        .deck(deck)
                        .build(),
                Card.builder()
                        .term("Term 5")
                        .desc("Description 5")
                        .img(imageBytes)
                        .deck(deck)
                        .build()
        );
        return cardRepo.saveAll(cards);
    }

    private List<UserCard> initUserCard(Card card, User user) {
        List<UserCard> userCards = List.of(
                UserCard.builder()
                        .cardId(card.getId())
                        .userId(user.getId())
                        .lastReview(new Date(2023, 1, 1, 12, 1, 2))
                        .reviewCount(4L)
                        .interval(12L)
                        .rating(ERating.HIGH)
                        .card(card)
                        .user(user)
                        .build()
        );
        return userCardRepo.saveAll(userCards);
    }

    private List<SharedDeck> initSharedDeck(User recipient, Deck deck) {
        List<SharedDeck> sharedDecks = List.of(
                SharedDeck.builder()
                        .recipient(recipient)
                        .deck(deck)
                        .creation(new Date(2023, 10, 10, 1, 3, 4))
                        .build()
        );
        return sharedDeckRepo.saveAll(sharedDecks);
    }
}
