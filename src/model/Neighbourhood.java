package model;

import java.util.*;

public class Neighbourhood extends MixingGroup {
	
	private ArrayList<MixingGroup> playGroups;
	private ArrayList<MixingGroup> daycares;
	private ArrayList<MixingGroup> families;
	
	public Neighbourhood(int type, double prob, ArrayList<MixingGroup> playGroups, ArrayList<MixingGroup> daycares, ArrayList<MixingGroup> families) {
		super(type,prob);
		this.playGroups = playGroups;
		this.daycares = daycares;
		this.families = families;
	}
	
	public ArrayList<MixingGroup> getPlayGroups() {
		return playGroups;
	}
	
	public ArrayList<MixingGroup> getDaycares() {
		return daycares;
	}
	
	public ArrayList<MixingGroup> getFamilies() {
		return families;
	}
}