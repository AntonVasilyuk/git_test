package ru.job4j.profession;

/**.
*
* @author Anton Vasilyuk
* @version 1.0
* @since 0.1
*/

public class Profession {

	/**.
	* @name worker
	*/
	private String name;

	/**.
	* @higherEducation worker
	*/
	private boolean higherEducation = false;

	/**.
	* @experience worker
	*/
	private double experience;

	/**.
	* @payment worker
	*/
	private int payment;

	/**.
	* Constructor for class Profession
	* @param n name
	* @param h it's be education
	* @param e experiens
	* @param pay paymant working
	*/
	public Profession(String n, boolean h, double e, int pay) {
		this.name = n;
		this.higherEducation = h;
		this.experience = e;
		this.payment = pay;
	}
	/**.
	* Constructor for class Plumber
	* @param n name
	* @param h it's be education
	* @param e experiens
	*/
	public Profession(String n, boolean h, double e) {
		this.name = n;
		this.higherEducation = h;
		this.experience = e;
	}
	/**.
	* Constructor for class Plumber
	* @param n name
	*/
	public Profession(String n) {
		this.name = n;
	}

	/**.
	* Getter for take name
	* @return name
	*/
	public String getName() {
		return name;
	}

	/**.
	* Getter for take higherEducation
	* @return higherEducation
	*/
	public boolean gerIsHiEducation() {
		return higherEducation;
	}
	/**.
	* Getter for take experience
	* @return experience
	*/
	public double experience() {
		return experience;
	}

	/**.
	* Getter for take payment
	* @return payment
	*/
	public int getPayment() {
		return payment;
	}

	/**.
	* Method for description qualification worker
	* @return result
	*/
	public String qualificationSpec() {
		String result;
		if (higherEducation) {
			result = String.format("Описание специалиста: %s, есть высшее образование, стоимость вызова составляет %d долларов, его стаж составляет %.1f лет.", this.name, this.payment, this.experience);
		} else {
			result = String.format("Описание специалиста: %s, нет высшего образования, имеет среднеспециальное, его стаж составляет %.1f лет.", this.name, this.experience);
		}
		return result;

	}
}