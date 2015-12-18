package se.lu.sysa11.schinnbehn.model;

/**
 * @author Ann-Kathrine
 */
public class ContactPerson {
	private String name;
	private String email;
	private String telephoneNbr;
	private Customer belongsTo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNbr() {
		return telephoneNbr;
	}

	public void setTelephonenNbr(String telephoneNbr) {
		this.telephoneNbr = telephoneNbr;
	}

	public Customer getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(Customer belongsTo) {
		this.belongsTo = belongsTo;
	}

}
