package Sem5.Homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Tasks {

	public static void main(String[] args) {
		task4Func();
	}

//  Реализуйте структуру телефонной книги с помощью HashMap,
//  учитывая, что 1 человек может иметь несколько телефонов.

	public static void task1Func() {
		Logger logger = Logger.getAnonymousLogger();
		Map<String, ArrayList<String>> phoneBook = new HashMap<>();
		phoneBook.put("Ivan Petrov", new ArrayList<>(Arrays.asList("+123", "+456", "+789")));
		phoneBook.put("Ivan Ivanov", new ArrayList<>(Arrays.asList("+987", "+654", "+321")));
		phoneBook.put("Petr Petrov", new ArrayList<>(Arrays.asList("+753", "+321")));
		logger.info(phoneBook.toString());
	}


	//  Пусть дан список сотрудников
//  Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
//  Отсортировать по убыванию популярности.
	public static void task2Func() {
		Logger logger = Logger.getAnonymousLogger();
		ArrayList<String> peopleArray = new ArrayList<>();
		peopleArray.add("Иван Иванов");
		peopleArray.add("Светлана Петрова");
		peopleArray.add("Кристина Белова");
		peopleArray.add("Анна Мусина");
		peopleArray.add("Анна Крутова");
		peopleArray.add("Иван Юрин");
		peopleArray.add("Петр Лыков");
		peopleArray.add("Павел Чернов");
		peopleArray.add("Петр Чернышов");
		peopleArray.add("Мария Федорова");
		peopleArray.add("Марина Светлова");
		peopleArray.add("Мария Савина");
		peopleArray.add("Мария Рыкова");
		peopleArray.add("Марина Лугова");
		peopleArray.add("Анна Владимирова");
		peopleArray.add("Иван Мечников");
		peopleArray.add("Петр Петин");
		peopleArray.add("Иван Ежов");

		Map<String, Integer> countingNames = new HashMap<>();
		for (String i : peopleArray) {
			String name = i.split(" ")[0];
			if (countingNames.containsKey(name)) {
				countingNames.put(name, countingNames.get(name) + 1);
			} else {
				countingNames.put(name, 1);
			}
		}
		logger.info(countingNames.toString());

		Map<String, Integer> sortedNames = new LinkedHashMap<>();
		while (countingNames.size() > 0) {
			int currentMax = 0;
			String tempMaxName = "";
			for (String i : countingNames.keySet()) {
				if (countingNames.get(i) > currentMax) {
					currentMax = countingNames.get(i);
					tempMaxName = i;
				}
			}
			sortedNames.put(tempMaxName, currentMax);
			countingNames.remove(tempMaxName);
		}
		logger.info(sortedNames.toString());
	}


	//	Реализовать алгоритм пирамидальной сортировки (HeapSort).
	public static void task3Func() {
		Logger logger = Logger.getAnonymousLogger();
		int[] initArray = {5, 2, 8, 1, 7, 0, 2};
		int size = initArray.length;
		for (int i = size / 2 - 1; i >= 0; i--) {
			heapify(initArray, size, i);
		}
		for (int i = size - 1; i >= 0; i--) {
			int temp = initArray[i];
			initArray[i] = initArray[0];
			initArray[0] = temp;
			heapify(initArray, i, 0);
		}
		StringBuilder sb = new StringBuilder();
		for (int i : initArray) {
			sb.append(String.valueOf(i) + " ");
		}
		logger.info(sb.toString());
	}

	public static void heapify(int[] array, int size, int root) {
		int indexMax = root;
		int leftDoughter = 2 * root + 1;
		int rightDoughter = 2 * root + 2;

		if (leftDoughter < size && array[leftDoughter] > array[indexMax]) {
			indexMax = leftDoughter;
		}
		if (rightDoughter < size && array[rightDoughter] > array[indexMax]) {
			indexMax = rightDoughter;
		}
		if (indexMax != root) {
			int temp = array[root];
			array[root] = array[indexMax];
			array[indexMax] = temp;
			heapify(array, size, indexMax);
		}
	}

//	На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.

	public static void task4Func() {
		int SIZE = 8;
		int[] board = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			board[i] = 20;
		}
		board[0] = 4;
		int beginColumn = 0;
		for (int i = 1; i < SIZE; i++) {
			boolean isPossibleInline = false;
			for (int j = beginColumn; j < SIZE; j++) {
				if (checkCell(board, i, j)) {
					board[i] = j;
					isPossibleInline = true;
					beginColumn = 0;
					break;
				}
			}
			if (!isPossibleInline) {
				beginColumn = board[i-1]+1;
				i-=2;
			}
		}
		printBoard(board, SIZE);
	}

	public static boolean checkCell(int[] board, int line, int column) {
		boolean isPossible = true;
		for (int i = 0; i < board.length; i++) {
			if (board[i] == column && i != line) {
				isPossible = false;
			}
		}
		for (int i = 0; i < board.length; i++) {
			int step = line - i;
			if ((board[i] == column + step || board[i] == column - step) && i != line) {
				isPossible = false;
			}
		}
		return isPossible;
	}

	public static void printBoard(int[] board, int size) {
		for (int i = 0; i < size; i++) {
			String lineBoard = "| ";
			for (int j = 0; j < board[i]; j++) {
				lineBoard += "  | ";
			}
			lineBoard += "Q | ";
			for (int j = board[i] + 1; j < size; j++) {
				lineBoard += "  | ";
			}
			System.out.println(lineBoard);
			System.out.println("---------------------------------");
		}

//		for (int i = 0; i < size; i++) {
//			String lineBoard = "";
//			for (int j = 0; j < size; j++) {
//				String currentCell = " ";
//				if (board[i][j]==1){
//					currentCell = "Q";
//				}
//				if (j != size - 1) {
//					lineBoard += currentCell + " | ";
//				}
//				else{
//					lineBoard += currentCell;
//				}
//			}
//			System.out.printf("%s\n------------------------------\n", lineBoard);
//		}
	}
}
