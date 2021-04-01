package Payment;

import Account.Customer;
import Account.DiscountBand;
import Account.ValuedCustomer;
import Job.Job;
import Control.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

public class PaymentControl implements I_Payment {
    //public VectorOfPayments unnamed_VectorOfPayments_;
    //public VectorOfCards unnamed_VectorOfCards_;
    public Customer customer;
    public Vector<VectorOfPayments> vVecPaym;
    public VectorOfCards vecCard;
    private Control control;

    public Payment retrievePayment(int iD) {
        throw new UnsupportedOperationException();
    }

    public Payment[] retrieveListOfPayments(Customer customer) {
        throw new UnsupportedOperationException();
    }

    public Card[] retrieveCards(Customer customer) {
        throw new UnsupportedOperationException();
    }

    public void generateInvoice() {
        throw new UnsupportedOperationException();
    }

    public float applyDiscount(float price, Customer customer) {
        if (customer.getValued() == 1) {
            if (customer.getDiscountSet().getDiscounts().get(0) instanceof DiscountBand) {
                return 0f;
            }
        } else {
            return price;
        }
        return price;
    }

    public void addPayment(float amount, int date, Customer customer, Job[] jobs, int dueDate, Card card, String type) {
        //VectorOfPayments vecp = null;
        try {
            //add values
            String sql = "INSERT INTO `payment`(`date`, `paymentType`, `amount`) VALUES (?,?,?)";
            PreparedStatement preparedStatement = control.getDBC().getDBGateway().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, date);
            preparedStatement.setString(2, type);
            preparedStatement.setFloat(3, amount);

            control.getDBC().write(preparedStatement);

            //get id
            String sql3 = "SELECT paymentID FROM payment WHERE date = ? AND paymentType = ? and amount = ?";
            PreparedStatement prepStat = control.getDBC().getDBGateway().getConnection().prepareStatement(sql3);
            prepStat.setInt(1, date);
            prepStat.setString(2, type);
            prepStat.setFloat(3, amount);

            ResultSet rs = control.getDBC().read(prepStat);

            //handle result
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
                break;
            }

            //add card details
            if (id != 0) {
                if (card != null) {
                    String sql2 = "INSERT INTO `card_details`(`PaymentpaymentID`, `type`, `expiryDate`, `last4Dig`) VALUES (?,?,?,?)";
                    PreparedStatement p3 = control.getDBC().getDBGateway().getConnection().prepareStatement(sql2);
                    p3.setInt(1, id);
                    p3.setString(2, card.getType());
                    p3.setInt(3, card.getExpiryDate());
                    p3.setInt(4, card.getLast4Digits());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPaymentVectorToCustomer(Customer customer) {
        vVecPaym.add(new VectorOfPayments(customer, vecCard, this));
    }

    public VectorOfCards getVecCard() {
        return vecCard;
    }

    public Control getControl() {
        return control;
    }

    public PaymentControl(Control ctrl) {
        vecCard = new VectorOfCards();
        this.control = ctrl;
    }
}