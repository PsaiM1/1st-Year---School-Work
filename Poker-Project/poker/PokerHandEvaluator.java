package poker;

public class PokerHandEvaluator {
	//YOUR IMPLEMENTATION HERE
	//THE METHODS MUST MATCH THE DESCRIPTIONS EXACTLY
	
	/* An example of a method whose signature satisfies the 
	 * Description provided to you in the documentation.
	 * 
	 * Naturally, you would replace the body of these methods with
	 * your logic ... that is, remove the exception and replace
	 * it with the appropriate logic that tests the incoming
	 * array of Cards for the desired property---in this
	 * case you would return true if and only if the array 
	 * of Cards represented a straight flush.
	 */
	public static boolean hasStraightFlush(Card[] cards) {
		boolean StraightFlush = false;
		boolean Straight = false;
		int count = 0;
		for (int j = 0; j < cards.length - 1; j++) {
			if (cards[j].getValue() < cards[j + 1].getValue()) {
				count++;
			}
		}
		if (cards[4].getValue() == 1) {
			count++;
		}
		if (count == 4) {
			Straight = true;
		}
		int countTwo = 0;
		boolean Flush = false;
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				if (cards[i].getSuit() == cards[j].getSuit() && i != j) {
					countTwo++;
				}
			}
		}
		if (countTwo == 16) {
			Flush = true;
		}
		if (Straight == true && Flush == true) {
			StraightFlush = true;
		}
		return StraightFlush;
	}
	
	public static boolean hasStraight(Card [] cards) {
		boolean Straight = false;
		int count = 0;
		 for (int i = 0; i < cards.length - 1; i++) {
			if ((cards[i].getValue() == 1) && (i != 0 || i != 4)) {
				return Straight;
			}
	        int index = i;
	        for (int j = i + 1; j < cards.length; j++) {
	                if (cards[j].getValue() < cards[index].getValue()) 
	                    index = j;
	        }
	        Card copyCard = cards[index];  
	        cards[index] = cards[i];
	        cards[i] = copyCard;
	        }
		for (int j = 0; j < cards.length - 1; j++) {
			if (cards[j].getValue() < cards[j + 1].getValue()) {
				count++;
			}
		}
		if (cards[4].getValue() == 1) {
			count++;
		}
		if (count == 4) {
			Straight = true;
		}
		return Straight;
	}
	
	public static boolean hasFlush(Card[] cards) {
		int count = 0;
		boolean Flush = false;
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				if (cards[i].getSuit() == cards[j].getSuit() && i != j) {
					count++;
				}
			}
		}
		if (count == 16) {
			Flush = true;
		}
		return Flush;
	}
	
	public static boolean hasFourOfAKind(Card[] cards) {
		boolean FourKind = false;
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				for (int k = 2; k < cards.length; k++) {
					for (int m = 3; m < cards.length; m++) {
						if ((cards[i].getValue() == cards[j].getValue() && cards[j].getValue()== cards[k].getValue() && cards[k].getValue() == cards[m].getValue()) && (i != j && j != k && k != m)) {
							FourKind = true;
						}
					}
				}
			}
		}
		return FourKind;
	}

	
	public static boolean hasFullHouse(Card[] cards) {
		boolean ThreeKind = false;
		boolean Pair = false;
		boolean FullHouse = false;
		int ThreeVal = 0;
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				for (int k = 2; k < cards.length; k++) {
					if ((cards[i].getValue() == cards[j].getValue() && cards[j].getValue()== cards[k].getValue()) && (i != j && j != k)) {
						ThreeVal = cards[i].getValue();
						ThreeKind = true;
					}
				}
			}
		}
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				if (cards[i].getValue() == cards[j].getValue() && i != j && cards[i].getValue() != ThreeVal) {
					Pair = true;
				}
			}
		}
		if (ThreeKind == true && Pair == true) {
			FullHouse = true;
		}
		return FullHouse;
	}
	
	public static boolean hasThreeOfAKind(Card[] cards) {
		boolean ThreeKind = false;
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				for (int k = 2; k < cards.length; k++) {
					if ((cards[i].getValue() == cards[j].getValue() && cards[j].getValue()== cards[k].getValue()) && (i != j && j != k)) {
						ThreeKind = true;
					}
				}
			}
		}
		return ThreeKind;
	}
	
	public static boolean hasTwoPair(Card[] cards) {
		boolean PairOne = false;
		boolean PairTwo = false;
		boolean TwoPair = false;
		int PairOneVal = 0;
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				if (cards[i].getValue() == cards[j].getValue() && i != j) {
					PairOneVal = cards[i].getValue();
					PairOne = true;
				}
			}
		}
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				if (cards[i].getValue() == cards[j].getValue() && i != j && cards[i].getValue() != PairOneVal) {
					PairTwo = true;
				}
			}
		}
		if (PairOne == true && PairTwo == true) {
			TwoPair = true;
		}
		return TwoPair;
	}
	
	public static boolean hasPair(Card[] cards) {
		boolean Pair = false;
		for (int i = 0; i < cards.length; i++) {
			for (int j = 1; j < cards.length; j++) {
				if (cards[i].getValue() == cards[j].getValue() && i != j) {
					Pair = true;
				}
			}
		}
		return Pair;
	}
	
}

