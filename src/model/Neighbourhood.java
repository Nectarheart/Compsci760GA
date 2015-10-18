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
	
	public MixingGroup getEmptyPG() {
		for (int i = 0; i < playGroups.size(); i++) {
			if (playGroups.get(i).getNumberOfChildren() < 5) {
				return playGroups.get(i);
			}
		}
		return null;
	}
	
	public MixingGroup getEmptyDC() {
		if (daycares.size() == 0) {
			daycares.add(new MixingGroup(Constants.DC, 0.08));
			return daycares.get(daycares.size()-1);
		}
		if (daycares.get(daycares.size()-1).getNumberOfChildren() < 14) {
			return daycares.get(daycares.size()-1);
		}
		daycares.add(new MixingGroup(Constants.DC, 0.08));
		return daycares.get(daycares.size()-1);
	}
}