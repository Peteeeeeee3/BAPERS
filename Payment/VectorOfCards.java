package Payment;

import Account.Customer;

import java.util.Vector;

public class VectorOfCards {
	private Vector<Card> vecCard;

	public void addCard(Card card) {
		vecCard.add(card);
	}

	public void removeCard(int last4Digits) {
		for (int i = 0; i < vecCard.size(); i++) {
			if (vecCard.get(i).getLast4Digits() == last4Digits) {
				vecCard.remove(i);
			}
		}
	}

	public int traverse(Customer customer) {
		throw new UnsupportedOperationException();
	}

	public Card retrieveCard(int last4Digits) {
		for (int i = 0; i < vecCard.size(); i++) {
			if (vecCard.get(i).getLast4Digits() == last4Digits) {
				return vecCard.get(i);
			}
		}
		return null;
	}

	public VectorOfCards() {}
}