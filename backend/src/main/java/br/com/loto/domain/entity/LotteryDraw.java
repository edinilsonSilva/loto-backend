package br.com.loto.domain.entity;

import br.com.loto.domain.enums.TypeGame;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Entidade que representa um jogo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lottery_draw")
public class LotteryDraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeGame gameType;

    private boolean accumulated;

    @Column(name = "draw_date")
    private LocalDate drawDate;

    @Column(name = "next_draw_date")
    private LocalDate nextDrawDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "drawn_numbers_in_order", joinColumns = @JoinColumn(name = "lottery_draw_id"))
    @Column(name = "number")
    private List<String> drawnNumbersInOrder;

    private boolean showDetailsByCity;

    private int specialDrawIndicator;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "draw_numbers", joinColumns = @JoinColumn(name = "lottery_draw_id"))
    @Column(name = "number")
    private List<String> drawNumbers;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "second_draw_numbers", joinColumns = @JoinColumn(name = "lottery_draw_id"))
    @Column(name = "number")
    private List<String> secondDrawNumbers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "lottery_draw_id")
    private List<PrizeBreakdown> prizeBreakdownList;

    private String drawLocation;

    private String drawCityState;

    private String heartTeamName;

    private int number;

    private int previousDrawNumber;

    private int finalDrawNumber_0_5;

    private int nextDrawNumber;

    private int gameNumber;

    private String notes;

    private String contingencyPrize;

    private int publicationType;

    private boolean lastDraw;

    private String collectedAmount;

    private String accumulatedAmount_0_5;

    private String specialAccumulatedAmount;

    private String nextAccumulatedAmount;

    private String estimatedNextDrawAmount;

    private String guaranteeReserveBalance;

    private String totalPrizeAmountTierOne;
}

