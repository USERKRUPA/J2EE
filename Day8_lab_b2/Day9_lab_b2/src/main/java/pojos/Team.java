package pojos;


import javax.persistence.Column;
//JPA packages
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*create table teams (team_id int primary key auto_increment,name varchar(100),abbrevation varchar(10),owner varchar(20),max_age int,
batting_avg double,wickets_taken int);*/

@Entity //mandatory class level annotation to tell hibernate 
//that following is entity class whose life cycle managed by hibernate
@Table(name="teams")
public class Team {
	@Id	//mandatory field level annotation to tell hibernate to add PK constraint for following field
	@GeneratedValue(strategy = GenerationType.IDENTITY) //for auto id generation
	@Column(name="team_id")
	private Integer teamId;//as per founder(Gavin King) till hibernate 5 use id property explicitly serializable
	@Column(length=100)
	private String name;
	@Column(length=10)
	private String abbreviation;
	@Column(length=20)
	private String owner;
	@Column(name="max_age")
	private int maxAge;
	@Column(name="batting_avg")
	private double minBattingAvg;
	@Column(name="wickets_taken")
	private int minWicketsTaken;
	public Team() {
		// TODO Auto-generated constructor stub
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

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", name=" + name + ", abbreviation=" + abbreviation + ", owner=" + owner
				+ ", maxAge=" + maxAge + ", minBattingAvg=" + minBattingAvg + ", minWicketsTaken=" + minWicketsTaken
				+ "]";
	}

	
}
