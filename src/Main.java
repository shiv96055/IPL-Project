import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int SEASON = 1;
    public static final int CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int TEAM1 = 4;
    public static final int TEAM2 = 5;
    public static final int TOSS_WINNER = 6;
    public static final int TOSS_DECISION = 7;
    public static final int RESULT = 8;
    public static final int DL_APPLIED = 9;
    public static final int WINNER = 10;
    public static final int WIN_BY_RUN = 11;
    public static final int WIN_BY_WICKET = 12;
    public static final int PLAYER_OF_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int UMPIRE1 = 15;
    public static final int UMPIRE2 = 16;
    public static final int UMPIRE3 = 17;
    public static final int MATCH_INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int OVER = 4;
    public static final int BALL = 5;
    public static final int BATSMAN = 6;
    public static final int NON_STRIKER = 7;
    public static final int BOWLER = 8;
    public static final int IS_SUPPER_OVER = 9;
    public static final int WIDE_RUN = 10;
    public static final int BYE_RUN = 11;
    public static final int LEG_BYE_RUN = 12;
    public static final int NO_BALL_RUN = 13;
    public static final int PENALTY_RUN = 14;
    public static final int BATSMAN_RUN = 15;
    public static final int EXTRA_RUN = 16;
    public static final int TOTAL_RUN = 17;
    public static final int PLAYER_DISMISSED = 18;
    public static final int DISMISSED_KIND = 19;
    public static final int FIELDER = 20;

    public static void main(String[] args) {
        List<Match> matches = getMatchesData();
        List<Delivery> deliveryData = getDeliveryData();

      findNumberOfMatchesPlayedPerYear(matches);
      findNumberOfMatchesWonAllTeamInAllYear(matches);
      findExtraRunPerTeamIn2016(matches, deliveryData);
      findTopEconomicalBowlerIn2015(matches, deliveryData);
      countNumberOfMatchesPlayedPerTeamInAllYear(matches);
      findMostExtraRunGivenByBowler(matches, deliveryData);
      findMostNumberOfSixHittingBatsmanInEachVenue(matches, deliveryData);
      findNoBallIn2015InTeamByBowler(matches, deliveryData);
      findTopFiveBowlerWhoBowledMostNumberOfDotBallsIn2016(matches, deliveryData);
    }

    private static List<Match>getMatchesData(){
        List<Match>matches = new ArrayList<>();

        String filePath = "matches.csv";
        String line;

       try{
           BufferedReader  reader = new BufferedReader(new FileReader(filePath));
           reader.readLine();

           while((line = reader.readLine()) != null){
               String [] fields = line.split(",");
               Match match = new Match();

               match.setMatchId(fields[MATCH_ID]);
               match.setMatchSession(fields[SEASON]);
               match.setMatchCityName(fields[CITY]);
               match.setMatchDate(fields[MATCH_DATE]);
               match.setTeamOne(fields[TEAM1]);
               match.setTeamTwo(fields[TEAM2]);
               match.setTossWinner(fields[TOSS_WINNER]);
               match.setTossDecision(fields[TOSS_DECISION]);
               match.setResult(fields[RESULT]);
               match.setDlApply(fields[DL_APPLIED]);
               match.setMatchWinner(fields[WINNER]);
               match.setMatchWinByRun(fields[WIN_BY_RUN]);
               match.setMatchWinByWicket(fields[WIN_BY_WICKET]);
               match.setPlayerOfMatch(fields[PLAYER_OF_MATCH]);
               match.setMatchStadium(fields[MATCH_VENUE]);
              try{
                  match.setUmpireOne(fields[UMPIRE1]);
                  match.setUmpireTwo(fields[UMPIRE2]);
                  match.setUmpireThree(fields[UMPIRE3]);
              }catch(Exception e){
                  //e.printStackTrace();
              }
               matches.add(match);
           }
           reader.close();
       }catch(IOException e){
           e.printStackTrace();
    }
       return matches;
    }

    private static List<Delivery> getDeliveryData(){
        List<Delivery> deliveries = new ArrayList<>();
        String filePath = "deliveries.csv";
        String line;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();

            while((line = reader.readLine()) != null){
                String [] fields = line.split(",");

                Delivery delivery = new Delivery();

                delivery.setMatchId(fields[MATCH_ID]);
                delivery.setMatchInning(fields[MATCH_INNING]);
                delivery.setBattingTeam(fields[BATTING_TEAM]);
                delivery.setBowlingTeam(fields[BOWLING_TEAM]);
                delivery.setOver(fields[OVER]);
                delivery.setBall(fields[BALL]);
                delivery.setBatsman(fields[BATSMAN]);
                delivery.setNonStriker(fields[NON_STRIKER]);
                delivery.setBowler(fields[BOWLER]);
                delivery.setIsSuperOver(fields[IS_SUPPER_OVER]);
                delivery.setWideRun(fields[WIDE_RUN]);
                delivery.setByeRun(fields[LEG_BYE_RUN]);
                delivery.setLegByeRun(fields[LEG_BYE_RUN]);
                delivery.setNoBallRun(fields[NO_BALL_RUN]);
                delivery.setPenaltyRun(fields[PENALTY_RUN]);
                delivery.setBatsmanRun(fields[BATSMAN_RUN]);
                delivery.setExtraRun(fields[EXTRA_RUN]);
                delivery.setTotalRun(fields[TOTAL_RUN]);
                try {
                    delivery.setPlayerDismissed(fields[PLAYER_DISMISSED]);
                    delivery.setPlayerDismissalKind(fields[DISMISSED_KIND]);
                    delivery.setFielder(fields[FIELDER]);
                }catch(Exception e){
                 // e.printStackTrace();
                }
                deliveries.add(delivery);
            }
            reader.close();
        }catch(IOException e){
                e.printStackTrace();
    }
        return deliveries;
    }

    private static void findNumberOfMatchesPlayedPerYear(List<Match> matches){
                  HashMap<String, Integer> numMatchPlayedPerYear = new HashMap<>();

                  for (Match match : matches) {
                      String year = match.getMatchSession();

                      if (numMatchPlayedPerYear.containsKey(year)) {
                          numMatchPlayedPerYear.put(year, numMatchPlayedPerYear.get(year) + 1);
                      } else {
                          numMatchPlayedPerYear.put(year, 1);
                      }
                  }

                  for (Map.Entry<String, Integer> key : numMatchPlayedPerYear.entrySet()) {
                      System.out.println(key.getKey() + " " + key.getValue());
                  }

                  System.out.println();
    }

    private static void findNumberOfMatchesWonAllTeamInAllYear(List<Match> matches){
                      HashMap<String, Integer> numOfWonMatchPerTeam = new HashMap<>();

                      for(Match match : matches) {
                          String winnerTeam = match.getMatchWinner();

                          if (numOfWonMatchPerTeam.containsKey(winnerTeam)) {
                              numOfWonMatchPerTeam.put(winnerTeam, numOfWonMatchPerTeam.get(winnerTeam) + 1);
                          } else {
                              if (!winnerTeam.equals(""))
                                  numOfWonMatchPerTeam.put(winnerTeam, 1);
                          }
                      }

                      System.out.println("Number of matches won of all teams over all the years of IPL.");

                      for (Map.Entry<String, Integer> key : numOfWonMatchPerTeam.entrySet()) {
                          System.out.println(key.getKey() + " " + key.getValue());
                      }

                      System.out.println();
    }

    private static void findExtraRunPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
            HashSet<String> storeId = new HashSet<>();
            HashMap<String, Integer> extraRunPerTeam = new HashMap<>();

            for (Match match : matches) {
                String year = match.getMatchSession();
                String matchId = match.getMatchId();

                if (year.equals("2016")) {
                    storeId.add(matchId);
                }
            }

            for (Delivery delivery : deliveries) {
                String extraRun = delivery.getExtraRun();
                int val = Integer.parseInt(extraRun);
                String matchId = delivery.getMatchId();

                if (storeId.contains(matchId)) {
                    String team = delivery.getBowlingTeam();
                    if (extraRunPerTeam.containsKey(team)) {
                        extraRunPerTeam.put(team, extraRunPerTeam.get(team) + val);
                    } else {
                        extraRunPerTeam.put(team, val);
                    }
                }
            }

            System.out.println("For the year 2016 get the extra runs conceded per team.");

            for (Map.Entry<String, Integer> key : extraRunPerTeam.entrySet()) {
                System.out.println(key.getKey() + " " + key.getValue());
            }

            System.out.println();
    }

    private static void findTopEconomicalBowlerIn2015(List<Match>matches, List<Delivery>deliveries){
                    try {
                        HashSet<String> storeMatchId = new HashSet<>();

                        for (Match match : matches) {
                            String matchSession = match.getMatchSession();

                            if (matchSession.equals("2015")) {
                                storeMatchId.add(match.getMatchId());
                            }
                        }

                        HashMap<String, Integer> totalBowlerRun = new HashMap<>();
                        HashMap<String, Integer> totalBowlerBall = new HashMap<>();

                        for (Delivery delivery : deliveries) {
                            String matchId = delivery.getMatchId();

                            if (storeMatchId.contains(matchId)) {
                                String bowler = delivery.getBowler();
                                String runGivenByBowler = delivery.getTotalRun();
                                int run = Integer.parseInt(runGivenByBowler);

                                int wideBal = Integer.parseInt(delivery.getWideRun());
                                int noBal = Integer.parseInt(delivery.getNoBallRun());

                                if (totalBowlerRun.containsKey(bowler)) {
                                    totalBowlerRun.put(bowler, totalBowlerRun.get(bowler) + run);
                                } else {
                                    totalBowlerRun.put(bowler, run);
                                }

                                if (totalBowlerBall.containsKey(bowler)) {
                                    totalBowlerBall.put(bowler, totalBowlerBall.get(bowler) + 1 - noBal - wideBal);
                                } else if(wideBal == 0 && noBal == 0) {
                                    totalBowlerBall.put(bowler, 1);
                                }

                            }
                        }

                        HashMap<String, Double>economicalBowler = new HashMap<>();

                        for(Map.Entry<String, Integer> entry : totalBowlerBall.entrySet()){
                            String bowler = entry.getKey();
                            double bal = (double) entry.getValue();
                            double run = (double) totalBowlerRun.get(bowler);
                            double value = ((run / bal) * 6.0);
                            economicalBowler.put(bowler, value);
                        }

                        List<Map.Entry<String, Double>>entryList = new ArrayList<>(economicalBowler.entrySet());
                        entryList.sort(Map.Entry.comparingByValue());
                        System.out.println("most economical bowler in 2015");

                        for(Map.Entry<String, Double> entry : entryList){
                           System.out.println(entry.getKey() + " " + entry.getValue());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
    }

    private static void countNumberOfMatchesPlayedPerTeamInAllYear(List<Match>matches) {
            HashMap<String, Integer> countMatch = new HashMap<>();

            for (Match match : matches) {
                String team1 = match.getTeamOne();
                String team2 = match.getTeamTwo();

                if (countMatch.containsKey(team1)) {
                    countMatch.put(team1, countMatch.get(team1) + 1);
                } else {
                    countMatch.put(team1, 1);
                }

                if (countMatch.containsKey(team2)) {
                    countMatch.put(team2, countMatch.get(team2) + 1);
                } else {
                    countMatch.put(team2, 1);
                }
            }

            System.out.println("count number matches played team in all IPL Season");

            for (Map.Entry<String, Integer> key : countMatch.entrySet()) {
                System.out.println(key.getKey() + " " + key.getValue());
            }

            System.out.println();
    }

    private static void findMostExtraRunGivenByBowler(List<Match> matches, List<Delivery> deliveries){
        try {
            HashMap<String, String> storeId = new HashMap<>();

            for (Match match : matches) {
                if (match.getMatchSession().equals("2015")) {
                    storeId.put(match.getMatchId(), match.getMatchStadium());
                }
            }

            HashMap<String, HashMap<String, Integer>> extraRunBowlerByVenue = new HashMap<>();

            for (Delivery delivery : deliveries) {
                String bowler = delivery.getBowler();
                String matchId = delivery.getMatchId();
                String extraRun = delivery.getExtraRun();
                int run = Integer.parseInt(extraRun);

                if (storeId.containsKey(matchId)) {
                    String stadium = storeId.get(matchId);
                    if (extraRunBowlerByVenue.containsKey(stadium)) {
                        HashMap<String, Integer> innerHashMap = extraRunBowlerByVenue.get(stadium);
                        if (innerHashMap.containsKey(bowler)) {
                            innerHashMap.put(bowler, innerHashMap.get(bowler) + run);
                        } else {
                            innerHashMap.put(bowler, run);
                        }
                        extraRunBowlerByVenue.put(stadium, innerHashMap);
                    } else {
                        HashMap<String, Integer> innerMap = new HashMap<>();
                        innerMap.put(bowler, run);
                        extraRunBowlerByVenue.put(stadium, innerMap);
                    }
                }
            }

            for (String outerKey : extraRunBowlerByVenue.keySet()) {
                HashMap<String, Integer> innerMap = extraRunBowlerByVenue.get(outerKey);
                List<Map.Entry<String, Integer>> entryList = new ArrayList<>(innerMap.entrySet());
                entryList.sort((val1, val2) -> val2.getValue().compareTo(val1.getValue()));
                System.out.println(outerKey);

                for (Map.Entry<String, Integer> entry : entryList) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    break;
                }
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void findMostNumberOfSixHittingBatsmanInEachVenue(List<Match>matches, List<Delivery>deliveries){
            HashMap<String, String> storeId = new HashMap<>();

            for (Match match : matches) {
                String ide = match.getMatchId();
                String venue = match.getMatchStadium();

                storeId.put(ide, venue);
            }

            HashMap<String, HashMap<String, Integer>> mostNumberOfSixHitting = new HashMap<>();

            for (Delivery delivery : deliveries) {
                String isSixRun = delivery.getBatsmanRun();

                if (isSixRun.equals("6")) {
                    String matchIde = delivery.getMatchId();
                    String venue = storeId.get(matchIde);
                    String batsman = delivery.getBatsman();

                    HashMap<String, Integer> innerMap = new HashMap<>();
                    if (mostNumberOfSixHitting.containsKey(venue)) {
                         innerMap = mostNumberOfSixHitting.get(venue);
                        if (innerMap.containsKey(batsman)) {
                            innerMap.put(batsman, innerMap.get(batsman) + 1);
                        } else {
                            innerMap.put(batsman, 1);
                        }
                    } else {
                        innerMap.put(batsman, 1);
                    }
                    mostNumberOfSixHitting.put(venue, innerMap);
                }

            }

            System.out.println("Find the most number of six hitting batsman in each venues among all seasons");

            for (String outerKey : mostNumberOfSixHitting.keySet()) {
                HashMap<String, Integer> innerMap = mostNumberOfSixHitting.get(outerKey);
                List<Map.Entry<String, Integer>> entryList = new ArrayList<>(innerMap.entrySet());
                entryList.sort((val1, val2) -> val2.getValue().compareTo(val1.getValue()));

                System.out.println(outerKey);

                for (Map.Entry<String, Integer> entry : entryList) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    break;
                }

                System.out.println();
            }
    }

    private static void findNoBallIn2015InTeamByBowler(List<Match> matches, List<Delivery>deliveries){
                HashSet<String>storeMatchId = new HashSet<>();

                for(Match match : matches){
                    String matchSession = match.getMatchSession();

                    if(matchSession.equals("2015")){
                        storeMatchId.add(match.getMatchId());
                    }
                }

                HashMap<String, HashMap<String, Integer>>ans = new HashMap<>();

                for(Delivery delivery : deliveries){
                    String matchId = delivery.getMatchId();
                    if(storeMatchId.contains(matchId)){
                        String team = delivery.getBowlingTeam();
                        String bowler = delivery.getBowler();
                        int noBalRun = Integer.parseInt(delivery.getNoBallRun());

                        if(noBalRun != 0){
                            HashMap<String, Integer> innerMap = new HashMap<>();
                            if(ans.containsKey(team)){
                                innerMap = ans.get(team);
                                if(innerMap.containsKey(bowler)){
                                    innerMap.put(bowler, innerMap.get(bowler) + noBalRun);
                                }else{
                                    innerMap.put(bowler, noBalRun);
                                }

                            }else{
                                innerMap.put(bowler, noBalRun);
                            }
                            ans.put(team, innerMap);
                        }
                    }
                }

                for(String outerKey : ans.keySet()){
                    HashMap<String, Integer>innerKey = ans.get(outerKey);
                    System.out.println(outerKey);
                    for(Map.Entry<String, Integer>entry : innerKey.entrySet()){
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    }
                    System.out.println();
                }
    }

    private static void findTopFiveBowlerWhoBowledMostNumberOfDotBallsIn2016(List<Match> matches, List<Delivery> deliveries) {
            HashSet<String> storeMatchId = new HashSet<>();

            for (Match match : matches) {
                String matchSeason = match.getMatchSession();

                if (matchSeason.equals("2016")) {
                    storeMatchId.add(match.getMatchId());
                }
            }

            HashMap<String, Integer> mostNumberOfDotBalByBowler= new HashMap<>();

            for (Delivery delivery : deliveries) {
                String matchId = delivery.getMatchId();

                if (storeMatchId.contains(matchId)) {
                    String bowler = delivery.getBowler();
                    int run = Integer.parseInt(delivery.getTotalRun());

                    if (run == 0) {
                        if (mostNumberOfDotBalByBowler.containsKey(bowler)) {
                            mostNumberOfDotBalByBowler.put(bowler, mostNumberOfDotBalByBowler.get(bowler) + 1);
                        } else {
                            mostNumberOfDotBalByBowler.put(bowler, 1);
                        }
                    }
                }
            }

            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(mostNumberOfDotBalByBowler.entrySet());
            entryList.sort((val1, val2) -> val2.getValue().compareTo(val1.getValue()));

            int flag = 5;
            System.out.println("Top five bowler who bowled most number of dot balls in 2016 ");

            for (Map.Entry<String, Integer> entry : entryList) {
                if(flag == 0)
                    break;
                System.out.println(entry.getKey() + " " + entry.getValue());
                flag--;
            }
    }

}