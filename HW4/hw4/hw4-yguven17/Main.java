import java.util.*;

public class Main {
	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> adjacency_list = new HashMap<String, ArrayList<String>>();
		ArrayList<String> adj_a = new ArrayList<>(List.of("B", "D", "E", "F", "H"));
		ArrayList<String> adj_b = new ArrayList<>(List.of("A", "C", "D", "E", "F", "G", "I"));
		ArrayList<String> adj_c = new ArrayList<>(List.of("B", "D", "E", "F", "H"));
		ArrayList<String> adj_d = new ArrayList<>(List.of("A", "B", "C", "E", "G", "H", "I"));
		ArrayList<String> adj_e = new ArrayList<>(List.of("A", "B", "C", "D", "F", "G", "H", "I"));
		ArrayList<String> adj_f = new ArrayList<>(List.of("A", "B", "C", "E", "G", "H", "I"));
		ArrayList<String> adj_g = new ArrayList<>(List.of("B", "D", "E", "F", "H"));
		ArrayList<String> adj_h = new ArrayList<>(List.of("A", "C", "D", "E", "F", "G", "I"));
		ArrayList<String> adj_i = new ArrayList<>(List.of("B", "D", "E", "F", "H"));

		adjacency_list.put("A", adj_a);
		adjacency_list.put("B", adj_b);
		adjacency_list.put("C", adj_c);
		adjacency_list.put("D", adj_d);
		adjacency_list.put("E", adj_e);
		adjacency_list.put("F", adj_f);
		adjacency_list.put("G", adj_g);
		adjacency_list.put("H", adj_h);
		adjacency_list.put("I", adj_i);

		System.out.println(possible_locks("A", 0, adjacency_list));
		//System.out.println("Stage 1 complated."); // to find the mistake in console
		System.out.println("-----------");
		print_result(possible_locks("B", 1, adjacency_list));
		//System.out.println("Stage 2 complated."); // to find the mistake in console
		System.out.println("-----------");
		print_result(possible_locks("C", 2, adjacency_list));
		//System.out.println("Stage 3 complated."); // to find the mistake in console
		System.out.println("-----------");
		print_result(possible_locks("D", 3, adjacency_list));
		//System.out.println("Stage 4 complated."); // to find the mistake in console
		System.out.println("-----------");
		System.out.println(possible_locks("E", 8, adjacency_list).size());
		//System.out.println("Stage 5 complated."); // to find the mistake in console
		System.out.println("-----------");
		System.out.println(possible_locks("F", 9, adjacency_list).size());
		//System.out.println("Stage 6 complated."); // to find the mistake in console
		System.out.println("-----------");
		System.out.println(possible_locks("G", 11, adjacency_list));
		//System.out.println("Stage 7 complated."); // to find the mistake in console
	}

	public static ArrayList<String> possible_locks(String start, int length,HashMap<String, ArrayList<String>> adj_list) {
		// Your code here
		
		if (length > adj_list.size() || length < 1) { // to check list size and depend on it return null
			return null;
		}

		String path = ""; // to store and print path info
		ArrayList<String> pathList = new ArrayList<>(List.of()); // to store path list 

		if (length == 1) { // if length equals 1 it returns path directly
			path = start;
			pathList.add(path);
			return pathList;
		}

		possible_locks(start, length, adj_list, path, pathList); // a recursion function to visit and find the paths

		return pathList;
	}

	// Your code here
	
	public static void possible_locks(String start, int length, HashMap<String, ArrayList<String>> adj_list, String current_path, ArrayList<String> all_paths) {
		
		current_path = current_path + start; // current path info
		
		if (length == 1) { // if length equals 1 it returns path directly
			all_paths.add(current_path);
			return;
		}
		for (String neighbours : adj_list.get(start)) { // to visit and find the path
			if (!current_path.contains(neighbours)) {
				possible_locks(neighbours, length - 1, adj_list, current_path, all_paths);
			}

		}
		return;
	}

	public static void print_result(ArrayList<String> result) {
		System.out.println("Total number of possible locks: " + result.size());
		for (String current_lock : result) {
			ArrayList<String> letters = new ArrayList<String>(Arrays.asList(current_lock.split("")));
			System.out.println(String.join(" -> ", letters));
		}
	}
}