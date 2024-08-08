package com.alissonfgc.casacerta.config;

import com.alissonfgc.casacerta.entities.Client;
import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;
import com.alissonfgc.casacerta.repository.ClientRepository;
import com.alissonfgc.casacerta.repository.ImmobileRepository;
import com.alissonfgc.casacerta.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class Instantiation implements CommandLineRunner {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ImmobileRepository immobileRepository;

    @Autowired
    private ClientRepository clientRepository;



    @Override
    public void run(String... args) throws Exception {
        Seller s1 = new Seller(null, "Jao", "jao@gmail.com", "(61) 9 8181-8181", "999.999.999.99", "12345678");
        Seller s2 = new Seller(null, "maria", "maria@gmail.com", "(61) 9 9191-9191", "333.333.333.33", "12345687");
        Seller s3 = new Seller(null, "antonio", "antonio@gmail.com", "(61) 9 8585-8585", "777.777.777.77", "12345699");

        sellerRepository.saveAll(Arrays.asList(s1, s2, s3));

        Immobile i1 = new Immobile(s1, "./imageURL", "19999-999", "apartment", "-latitude", "-longitude", 60.00, "DF", "ceilandia", "neighborhood", LocalDate.now(), "Lorem impsulum udhasb hasbdu asi hasbdu uavdaus", "Lorem", null);
        Immobile i2 = new Immobile(s2, "./imageURL", "29999-999", "apartment", "-latitude", "-longitude", 60.00, "DF", "taguatinga", "neighborhood", LocalDate.now(), "Lorem impsulum udhasb hasbdu asi hasbdu uavdaus", "Lorem", null);
        Immobile i3 = new Immobile(s3, "./imageURL", "39999-999", "house", "-latitude", "-longitude", 60.00, "DF", "taguatinga", "neighborhood", LocalDate.now(), "Lorem impsulum udhasb hasbdu asi hasbdu uavdaus", "Lorem", null);
        Immobile i4 = new Immobile(s1, "./imageURL", "19999-999", "apartment", "-latitude", "-longitude", 60.00, "GO", "aguas lindas", "neighborhood", LocalDate.now(), "Lorem impsulum udhasb hasbdu asi hasbdu uavdaus", "Lorem", null);
        Immobile i5 = new Immobile(s2, "./imageURL", "29999-999", "commercial", "-latitude", "-longitude", 60.00, "DF", "taguatinga", "neighborhood", LocalDate.now(), "Lorem impsulum udhasb hasbdu asi hasbdu uavdaus", "Lorem", null);
        Immobile i6 = new Immobile(s3, "./imageURL", "39999-999", "house", "-latitude", "-longitude", 60.00, "GO", "aguas lindas", "neighborhood", LocalDate.now(), "Lorem impsulum udhasb hasbdu asi hasbdu uavdaus", "Lorem", null);

        immobileRepository.saveAll(Arrays.asList(i1, i2, i3, i4, i5, i6));

        Client c1 = new Client(null, "tonho", "tonho@gmail.com", "(61) 9 8181-9191", "999.999.333.99", "12215678");
        Client c2 = new Client(null, "tonhao", "tonhao@gmail.com", "(61) 9 9191-8181", "999.333.777.33", "12399687");
        Client c3 = new Client(null, "toin", "toin@gmail.com", "(61) 9 8585-9191", "777.333.999.77", "12955699");

        clientRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
}
