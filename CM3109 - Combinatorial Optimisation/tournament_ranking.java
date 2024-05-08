import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class tournament_ranking {

    public static void main(String[] args) {
        // parse command line arguments
        if (args.length != 1) {
            System.err.println("Usage: java tournament_ranking <file_path>");
            System.exit(1);
        }
        String filePath = args[0];

        // Set the initial solution, initial temperature, temperature length, cooling factor, and stop criterion
        int[] initial_solution = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35
        };
        double TI = 1.0;                // Initial Temperature
        int TL = 20;                   // Temperature Length
        double a = 0.9999;              // Cooling Rate for f(T) = a*T
        int stop_criterion = 20000;     // Number of iterations without improvement before stopping

        // Read the file and create the matrix
        InputData input_data = readfile(filePath);
        int num_participants = input_data.num_participants;
        Map<Integer, String> participants = input_data.participants;
        int[][] data = input_data.data;
        double[][] matrix = create_matrix(data, num_participants);

        // Run the simulated annealing algorithm
        long start_time = System.currentTimeMillis();
        Result result = simulated_annealing(TI, TL, a, stop_criterion, initial_solution, matrix);
        long end_time = System.currentTimeMillis();
        long elapsed_time = end_time - start_time;

        // Print the results
        int[] ranking = result.ranking;
        int[] scores = result.scores;

        String[] ranking_names = new String[ranking.length];
        for (int i = 0; i < ranking.length; i++) {
            ranking_names[i] = participants.get(ranking[i]);
            System.out.println((i + 1) + ". " + ranking_names[i]);
        }
        System.out.println("Kemeny Score: " + IntStream.of(scores).sum());
        System.out.println("Runtime: " + elapsed_time + " ms");

        // // Test parameter ranges
        // ParameterRanges parameter_ranges = new ParameterRanges();
        // parameter_ranges.TI = new double[] {1, 2, 3, 4, 5};
        // parameter_ranges.TL = new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        // parameter_ranges.a = new double[] {0.8, 0.85, 0.9, 0.95, 0.99, 0.995, 0.999, 0.9995, 0.9999};
        // parameter_ranges.stop_criterion = new int[] {100, 500, 1000, 2000, 4000, 6000, 8000, 10000, 12000, 14000, 16000, 18000, 20000};
        
        // BestResult best_result = test_params(matrix, initial_solution, parameter_ranges);
        // System.out.println("Best Score: " + best_result.best_score);
        // System.out.println("Best Parameters: TI=" + best_result.best_params.TI +
        //         " TL=" + best_result.best_params.TL +
        //         " a=" + best_result.best_params.a +
        //         " stop_criterion=" + best_result.best_params.stop_criterion);
    }

    private static class InputData {
        int num_participants;
        Map<Integer, String> participants;
        int[][] data;
    }

    private static class Result {
        int[] ranking;
        int[] scores;
    }

    private static class ParameterRanges {
        double[] TI;
        int[] TL;
        double[] a;
        int[] stop_criterion;
    }

    private static class BestResult {
        double best_score;
        Parameters best_params;
        List<Record> records;
    }

    private static class Parameters {
        double TI;
        int TL;
        double a;
        int stop_criterion;
    }

    private static class Record {
        double TI;
        int TL;
        double a;
        int stop_criterion;
        double score;
    }

    private static class NeighborResult {
        int[] neighbor;
        int index;
        int index2;

        NeighborResult(int[] neighbor, int index, int index2) {
            this.neighbor = neighbor;
            this.index = index;
            this.index2 = index2;
        }
    }

    // read the file and return the number of participants, the participants, and the weights data
    private static InputData readfile(String filePath) {
        InputData input_data = new InputData();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            input_data.num_participants = Integer.parseInt(lines.get(0).trim());
            input_data.participants = new HashMap<>();
            input_data.data = new int[lines.size() - input_data.num_participants - 2][3];

            for (int i = 1; i <= input_data.num_participants; i++) {
                String[] parts = lines.get(i).split(",");
                int participant_id = Integer.parseInt(parts[0]);
                String participant_name = parts[1];
                input_data.participants.put(participant_id, participant_name);
            }

            for (int i = input_data.num_participants + 2; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                for (int j = 0; j < 3; j++) {
                    input_data.data[i - input_data.num_participants - 2][j] = Integer.parseInt(parts[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return input_data;
    }

    // create a matrix from the weights data
    private static double[][] create_matrix(int[][] data, int numParticipants) {
        double[][] matrix = new double[numParticipants][numParticipants];

        for (int i = 0; i < numParticipants; i++) {
            for (int j = 0; j < numParticipants; j++) {
                matrix[i][j] = 0;
            }
        }
        
        for (int[] entry : data) {
            int weight = entry[0];
            int winner = entry[1];
            int loser = entry[2];
            matrix[winner - 1][loser - 1] += weight;
        }
        return matrix;
    }

    // calculate the Kemeny Score for a single ranking
    private static int kemeny_score(int driver_index, double[][] matrix, int[] R){
        int score = 0;
        for (int i = 0; i < R.length - driver_index; i++) {
            score += matrix[R[i + driver_index] - 1][R[driver_index] - 1];
        }
        return score;
    }

    // calculate the Kemeny Score for a given ranking
    private static int[] kemeny_scores(double[][] matrix, int[] R) {
        int[] score_list = new int[R.length];
        for (int i = 0; i < R.length; i++) {
            score_list[i] = kemeny_score(i, matrix, R);
        }
        return score_list;
    }

    // generate a neighbouring solution by picking an element at random and getting the next element in the list, if the element is the last element in the list, swap it with the first element    
    private static NeighborResult generate_neighbor(int[] current_ranking) {
        int[] neighbor = current_ranking.clone();
        int index = new Random().nextInt(neighbor.length);
        int index_2;
        if (index == neighbor.length - 1) {
            int temp = neighbor[0];
            neighbor[0] = neighbor[index];
            neighbor[index] = temp;
            index_2 = 0;
        } else {
            int temp = neighbor[index + 1];
            neighbor[index + 1] = neighbor[index];
            neighbor[index] = temp;
            index_2 = index + 1;
        }
        return new NeighborResult(neighbor, index, index_2);
    }

    // calculate kemeny scores for the two drivers that are swapped
    private static int[] calculate_costs(int driver_index_1, int driver_index_2, int[] costs, double[][] matrix, int[] x) {
        int[] neighbor_costs = costs.clone();
        neighbor_costs[driver_index_1] = kemeny_score(driver_index_1, matrix, x);
        neighbor_costs[driver_index_2] = kemeny_score(driver_index_2, matrix, x);
        return neighbor_costs;
    }

    // Simulated Annealing Algorithm
    private static Result simulated_annealing(double TI, int TL, double a, int stop_criterion, int[] initial_solution, double[][] matrix) {
        int[] x = initial_solution;         // construct initial solution
        double T = TI;                      // set initial temperature
        int changes_since_last_best = 0;    // set counter for number of iterations since last best solution

        int[] current_costs = kemeny_scores(matrix, x);     // set initial cost
        int[] best_scores = current_costs;                  // set best cost to initial cost
        int[] best_ranking = x;                             // set best solution to initial solution
        

        while (changes_since_last_best < stop_criterion) {                  // continue until stop criterion is met
            for (int i = 0; i < TL; i++) {                                  // perform TL iterations at each temperature
                NeighborResult neighbor_result = generate_neighbor(x);      // generate neighbouring solution
                int[] neighbor = neighbor_result.neighbor;                  // unpack neighbour
                int driver_index_1 = neighbor_result.index;
                int driver_index_2 = neighbor_result.index2;
                
                int[] neighbour_scores = calculate_costs(driver_index_1, driver_index_2, current_costs, matrix, neighbor);      // calculate neighbour cost
                double cost_change = IntStream.of(neighbour_scores).sum() - IntStream.of(current_costs).sum();                  // calculate cost change between current and neighbouring solution
                
                if (cost_change < 0) {                                  // if neighbouring solution has lower cost, accept it
                    x = neighbor;                                       // update current solution
                    current_costs = neighbour_scores;                   // update current costs
                    changes_since_last_best++;                          // increment counter for number of iterations since last best solution
                    if (IntStream.of(current_costs).sum() < IntStream.of(best_scores).sum()) {   // if current solution is better than best solution, update best solution
                        best_scores = neighbour_scores;                 // update best costs
                        best_ranking = x;                               // update best solution
                        changes_since_last_best = 0;                    // reset counter for number of iterations since last best solution
                    }
                } else {                                                // if neighbouring solution has higher cost, accept it with probability e^(-cost_change / T)    
                    double q = new Random().nextDouble();               // generate random number between 0 and 1
                    if (q < Math.exp(-cost_change / T)) {               // if q < e^(-cost_change / T), accept neighbouring solution
                        x = neighbor;                                   // update current solution
                        current_costs = neighbour_scores;               // update current costs
                        changes_since_last_best++;                      // increment counter for number of iterations since last best solution
                    }
                }
            }
            T = T * a;  // cool the temperature by a factor of a
        }

        Result result = new Result();
        result.ranking = best_ranking;
        result.scores = best_scores;
        return result;
    }

    // test parameter ranges and return the best score and parameters
    private static BestResult test_params(double[][] matrix, int[] initial_solution, ParameterRanges parameter_ranges) {
        BestResult best_result = new BestResult();
        best_result.best_score = Double.POSITIVE_INFINITY;
        best_result.best_params = new Parameters();
        best_result.records = new ArrayList<>();

        int inner_repitions = 20;
        double best_avg_score = Double.POSITIVE_INFINITY;
        
        for (double TI : parameter_ranges.TI) {
            for (int TL : parameter_ranges.TL) {
                for (double a : parameter_ranges.a) {
                    for (int stop_criterion : parameter_ranges.stop_criterion) {
                        double total_score = 0;
                        for (int i = 0; i < inner_repitions; i++) {
                            Result result = simulated_annealing(TI, TL, a, stop_criterion, initial_solution, matrix);

                            Record record = new Record();
                            record.TI = TI;
                            record.TL = TL;
                            record.a = a;
                            record.stop_criterion = stop_criterion;
                            record.score = IntStream.of(result.scores).sum();

                            best_result.records.add(record);

                            total_score += IntStream.of(result.scores).sum();

                            System.out.println("TI=" + TI + " TL=" + TL + " a=" + a + " stop_criterion=" + stop_criterion + " Score: " + IntStream.of(result.scores).sum());
                        }
                        double avg_score = total_score / inner_repitions;
                        if (avg_score < best_avg_score) {
                            best_avg_score = avg_score;
                            best_result.best_score = avg_score;
                            best_result.best_params.TI = TI;
                            best_result.best_params.TL = TL;
                            best_result.best_params.a = a;
                            best_result.best_params.stop_criterion = stop_criterion;
                        }
                    }
                }
            }
        }
        save_to_json(best_result.records);
        return best_result;
    }

    // save the results to a json file
    private static void save_to_json(List<Record> records) {
        try (FileWriter file = new FileWriter("data.json", true)) {
            file.write("[\n");
            for (Record record : records) {
                double TI = record.TI;
                int TL = record.TL;
                double a = record.a;
                int stop_criterion = record.stop_criterion;
                double score = record.score;
                
                file.write("    {\n");
                file.write("        \"TI\": " + TI + ",\n");
                file.write("        \"TL\": " + TL + ",\n");
                file.write("        \"a\": " + a + ",\n");
                file.write("        \"stop_criterion\": " + stop_criterion + ",\n");
                file.write("        \"score\": " + score + "\n");

                // remove the last comma if it is the last record
                if (record == records.get(records.size() - 1)) {
                    file.write("    }\n");
                } else {
                    file.write("    },\n");
                }
            }
            file.write("]\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
