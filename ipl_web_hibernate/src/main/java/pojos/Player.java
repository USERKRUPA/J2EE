package pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name="players")
public class Player extends SuperEntity{
	
	@Column(name="first_name", length=20)
	private String firstName;
	@Column(name="last_name", length=20)
	private String lastName;
	private LocalDate dob;
	@Column(name="batting_avg")
	private double battingAvg;
	@Column(name="wickets_taken")
	private int wicketsTaken;
	@ManyToOne
	private Team team;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public double getBattingAvg() {
		return battingAvg;
	}
	public void setBattingAvg(double battingAvg) {
		this.battingAvg = battingAvg;
	}
	public int getWicketsTaken() {
		return wicketsTaken;
	}
	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "Player [ id" + getId() + "firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", battingAvg="
				+ battingAvg + ", wicketsTaken=" + wicketsTaken + "]";
	}
	
}
