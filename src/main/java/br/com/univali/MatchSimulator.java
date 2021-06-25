package br.com.univali;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Random;

class MatchSimulator {
  private static final float HOME_SKILL_MULTIPLIER = 1.1F;

  private final Team home;
  private final Team away;
  private final Random random;
  private final Clock clock;

  MatchSimulator(Team home, Team away, Random random, Clock clock) {
    this.home = home;
    this.away = away;
    this.random = random;
    this.clock = clock;
  }

  Result execute() {
    int homeSkill = (int) (home.calculateTotalSkill() * HOME_SKILL_MULTIPLIER);
    int awaySkill = away.calculateTotalSkill();

    int goals = generateNumberOfGoals();
    var homeGoals = 0;
    var awayGoals = 0;
    while (goals > 0) {
      goals--;
      if (random.nextInt(homeSkill) >= random.nextInt(awaySkill)) {
        homeGoals++;
      } else {
        awayGoals++;
      }
    }
    return new Result(homeGoals, awayGoals, LocalDate.now(clock));
  }

  private int generateNumberOfGoals() {
    var goals = 0;
    while (random.nextBoolean()) {
      goals++;
    }
    return goals;
  }

  static record Result(int homeGoals, int awayGoals, LocalDate date) {}
}