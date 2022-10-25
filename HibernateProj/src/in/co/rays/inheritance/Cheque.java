package in.co.rays.inheritance;

public class Cheque extends Payment{
	
	private int chequeNumber;
	private String bankName;
	
	public Cheque() {
		// TODO Auto-generated constructor stub
	}

	public int getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(int chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	

}
