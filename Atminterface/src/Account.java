import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final String pin;
    private BigDecimal balance;
    private final List<String> history = new ArrayList<>();

    public Account(String accountNumber, String pin, BigDecimal openingBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = openingBalance.setScale(2, RoundingMode.HALF_UP);
        history.add(ts() + " Account opened with balance " + this.balance);
    }

    public boolean authenticate(String pin) {
        return this.pin.equals(pin);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        checkPositive(amount);
        balance = balance.add(amount).setScale(2, RoundingMode.HALF_UP);
        history.add(ts() + " Deposit " + amount + " → balance " + balance);
    }

    public boolean withdraw(BigDecimal amount) {
        checkPositive(amount);
        if (balance.compareTo(amount) < 0) return false; // insufficient funds
        balance = balance.subtract(amount).setScale(2, RoundingMode.HALF_UP);
        history.add(ts() + " Withdraw " + amount + " → balance " + balance);
        return true;
    }

    public List<String> getHistory() {
        return history;
    }

    public String getMaskedAccount() {
        String last4 = accountNumber.length() > 4
                ? accountNumber.substring(accountNumber.length() - 4)
                : accountNumber;
        return "XXXX-XXXX-" + last4;
    }

    private void checkPositive(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    private String ts() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
