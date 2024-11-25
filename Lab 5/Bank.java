interface BankInterface {
    public void setBalance(float balance) ;
    public void setInterestRate(float interestRate) ;
    public float getBalance() ;
    public float getInterestRate() ;
}

public class Bank implements BankInterface {
    float interestRate = 0;
    float balance =0;

    public float getBalance() {
        return balance;
    }
    public float getInterestRate() {
        return interestRate;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }


    public static void main(String[] args) {
        Bank bankA = new Bank();
        bankA.setBalance(10000);
        bankA.setInterestRate(7f);
        System.out.println("Balance: " + bankA.getBalance());
        System.out.println("Interest Rate: " + bankA.getInterestRate());

        Bank bankB = new Bank();
        bankB.setBalance(150000);
        bankB.setInterestRate(7.4f);
        System.out.println("Balance: " + bankB.getBalance());
        System.out.println("Interest Rate: " + bankB.getInterestRate());

        Bank bankC = new Bank();
        bankC.setBalance(200000);
        bankC.setInterestRate(7.9f);
        System.out.println("Balance: " + bankC.getBalance());
        System.out.println("Interest Rate: " + bankC.getInterestRate());
    }
}

