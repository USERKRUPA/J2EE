package pojos;

import java.sql.Date;

public class Player {
	//player_id int primary key auto_increment,first_name varchar(20),last_name varchar(20),
	//dob date,batting_avg double,wickets_taken int,team_id int
	private int player_id;
	private String first_name;
	private String last_name;
	private Date dob;
	private double batting_avg;
	private int wickets_taken;
	private int team_id;
	
	public Player(int player_id, String first_name, String last_name, Date dob, double batting_avg, int wickets_taken,
			int team_id) {
		super();
		this.player_id = player_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.batting_avg = batting_avg;
		this.wickets_taken = wickets_taken;
		this.team_id = team_id;
	}

	public Player(String firstName, String lastName, Date dob, int bat_avg, int wkt) {
		this.first_name = firstName;
		this.last_name = lastName;
		this.dob = dob;
		this.batting_avg = bat_avg;
		this.wickets_taken = wkt;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public double getBatting_avg() {
		return batting_avg;
	}

	public void setBatting_avg(double batting_avg) {
		this.batting_avg = batting_avg;
	}

	public int getWickets_taken() {
		return wickets_taken;
	}

	public void setWickets_taken(int wickets_taken) {
		this.wickets_taken = wickets_taken;
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	@Override
	public String toString() {
		return "Player [player_id=" + player_id + ", first_name=" + first_name + ", last_name=" + last_name + ", dob="
				+ dob + ", batting_avg=" + batting_avg + ", wickets_taken=" + wickets_taken + ", team_id=" + team_id
				+ "]";
	}
	
}
