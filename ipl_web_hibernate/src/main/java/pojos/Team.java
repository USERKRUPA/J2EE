package pojos;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
//JPA packages
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity 
@Table(name="teams")
public class Team extends SuperEntity{
	
	@Column(length=20)
	private String name;
	@Column(length=5)
	private String abbreviation;
	@Column(length=20)
	private String owner;
	@Column(name="max_age")
	private int maxAge;
	@Column(name="min_batting_avg")
	private double minBattingAvg;
	@Column(name="min_wickets_taken")
	private int minWicketsTaken;
	
	@JoinColumn(name="player")
	@OneToMany(mappedBy = "team")
	private List<Player> players = new ArrayList<>();
	
	public Team() {
		
	}
	
	public Team(String name, String abbreviation, 
			String owner, int maxAge, double minBattingAvg,
			int minWicketsTaken) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.owner = owner;
		this.maxAge = maxAge;
		this.minBattingAvg = minBattingAvg;
		this.minWicketsTaken = minWicketsTaken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public double getMinBattingAvg() {
		return minBattingAvg;
	}

	public void setMinBattingAvg(double minBattingAvg) {
		this.minBattingAvg = minBattingAvg;
	}

	public int getMinWicketsTaken() {
		return minWicketsTaken;
	}

	public void setMinWicketsTaken(int minWicketsTaken) {
		this.minWicketsTaken = minWicketsTaken;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	//helper methods to add bidirectional association
	public void addPlayer(Player player) {
		players.add(player); //Player -> Team
		player.setTeam(this); //Team -> Player
	}

	//helper methods to remove bidirectional association
	public void removePlayer(Player player) {
		players.remove(player);
		player.setTeam(null);
	}

	@Override
	public String toString() {
		return "Team [id" + getId()+ "name=" + name + ", abbreviation=" + abbreviation + ", owner=" + owner + ", maxAge=" + maxAge
				+ ", minBattingAvg=" + minBattingAvg + ", minWicketsTaken=" + minWicketsTaken + "]";
	}
	
}
