package schedule;

import java.util.ArrayList;

public class Machine {
	public int nbr;
	public ArrayList<Job> machineJobList = new ArrayList<Job>();

	/** Skapar maskin nr nbr. */
	public Machine(int nbr) {
		this.nbr = nbr;
	}

	/** Tar reda på maskinens nr. */
	public int getNbr() {
		return nbr;
	}

	/** Tilldelar maskinen jobbet j. */
	public void assignJob(Job j) {
		machineJobList.add(j);

	}

	/** Tar bort alla jobb från maskinen. */
	public void clearJobs() {
		machineJobList.clear();

	}

	/** Tar bort och returnerar nästa jobb som maskinen ska utföra. 
	 	Returnerar null om maskinen inte har några jobb. */
	public Job getNextJob() {
		if(machineJobList.size() != 0){
			Job tempjob = machineJobList.get(0);
			machineJobList.remove(0);
			return tempjob;
		}
		return null;
	}

	/** Tar reda på den totala tiden för maskinens jobb. */
	public int getTotalTime() {
		int sumt = 0;
		for(Job j: machineJobList){
			sumt += j.getTime();
		}
		return sumt;
	}

	/**
	 * Returnerar en sträng som innehåller maskinens nr samt maskinens
	 * schemalagda jobb inom [] med kommatecken mellan.
	 */
	public String toString() {
		return "Machine nbr: " + nbr +" "+ "Jobs: " + machineJobList.toString();
	}

}