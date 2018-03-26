package com.ramesh.matcher;

import java.util.LinkedList;

public class AutomataBasedMatcher implements StringMatcher {
	private String pattern;
	public LinkedList<Node> nodesList = new LinkedList<Node>();

	public AutomataBasedMatcher(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * constructing a finite state automata
	 * 
	 * @param pattern
	 * @return linked list of nodes
	 */
	public boolean canAccept(char c) {
		if (((int) c == 46) || ((int) c >= 65 && (int) c <= 122)) {
			return true;
		}
		return false;
	}

	public void createMapEntry(Node previous, Node present, Node next, char wildCardCharacter,int reversePosition) {
		switch (wildCardCharacter) {
		case '*':// previous-next,self loop
			previous.addToMap(next.data, next);
			if (present != null) {
				present.addToMap(present.data, present);
			} else {
				nodesList.get(reversePosition).addToMap(previous.data, previous);
			}

			break;
		case '+':// self loop
			if (present != null) {
				present.addToMap(present.data, present);
			} else {
				nodesList.get(reversePosition).addToMap(previous.data, previous);
			}
			break;
		case '?':// previous-next
			previous.addToMap(next.data, next);
			break;
		}
	}

	public LinkedList<Node> constructFSA(String pattern) {
		// creating a required nodes
		this.pattern = pattern;
		Node sNode = new Node("S");
		StringBuilder str = new StringBuilder(pattern);
		nodesList.add(sNode);
		for (int i = 0; i < str.length(); i++) {
			if (canAccept(str.charAt(i))) {
				// move till the operator and make a bunch
				int j = i;
				while (j < str.length() - 1) {
					if (str.charAt(j) != ')' && canAccept(str.charAt(j)) == false) {
						break;

					}
					j += 1;
				}
				if (j == str.length() - 2) {
					if (canAccept(str.charAt(j + 1))) {
						j++;
					}
				}
				Node dNode = new Node(str.substring(i, j + 1));
				nodesList.add(dNode);
				i = j;
			} else if (str.charAt(i) == '(') {
				Node dataNode = new Node(str.substring(i, i + 1));
				nodesList.add(dataNode);
			}
		}

		Node eNode = new Node("E");
		nodesList.add(eNode);

		// adding the links

		for (int i = 1; i < nodesList.size() - 1; i++) {
			if (nodesList.get(i).getName().contains("(")) {
				int j = nodesList.size() - 2;
				while (j > 0 && nodesList.get(j).getName().contains(")") == false) {
					j--;
				}
				nodesList.get(j).updateData(nodesList.get(j).getName().replace(')', ' '));
				char wildCardCharacter = nodesList.get(j).getName().charAt(nodesList.get(j).getName().length() - 1);
				nodesList.get(j).updateData(nodesList.get(j).getName().replace(wildCardCharacter, ' '));
				nodesList.remove(i);
				i--;
				if (j - (i + 1) == 1) {
					createMapEntry(nodesList.get(i), nodesList.get(i + 1), nodesList.get(j), wildCardCharacter,0);
				} else {
					createMapEntry(nodesList.get(i), null, nodesList.get(j), wildCardCharacter,j-1);
				}

			} else {
				char wildCardCharacter = nodesList.get(i).getName().charAt(nodesList.get(i).getName().length() - 1);
				if (canAccept(wildCardCharacter) == false) {
					nodesList.get(i).updateData(nodesList.get(i).getName().replace(wildCardCharacter, ' '));
				}
				createMapEntry(nodesList.get(i - 1), nodesList.get(i), nodesList.get(i + 1), wildCardCharacter,0);
			}
		}

		for (int i = 0; i < nodesList.size() - 1; i++) {
			nodesList.get(i).map.put(nodesList.get(i + 1).data, nodesList.get(i + 1));
		}
		System.out.println(nodesList.size());
		for (int i = 0; i < nodesList.size(); i++) {
			System.out.println(nodesList.get(i) + " " + nodesList.get(i).getName() + nodesList.get(i).map.toString());
		}
		return nodesList;
	}

	@Override
	public boolean isMatched(String text) {
		System.out.println(pattern);

		return true;
	}

}
