package schedule;

import java.util.ArrayList;

public class Scheduler {
	private Machine[] machines;
	/**
	 * Skapar ett schemaläggare för maskinerna i vektorn machines.
	 */
	public Scheduler(Machine[] machines) {
		this.machines = machines;
	}

	/**
	 * Fördelar jobben i listan jobs på maskinerna. Jobben är sortrade är
	 */
	public void makeSchedule(ArrayList<Job> jobs) {
		while (jobs.size() != 0) {
			int min = Integer.MAX_VALUE;
			int minPos = -1;
			for (int i = 0; i < machines.length; i++) {
				if (machines[i].getTotalTime()<min){
					min = machines[i].getTotalTime();
					minPos = i;
				}
			}
			machines[minPos].assignJob(jobs.get(0));
			// System.out.println("assigned"+jobs.get(0)+" to
			// "+machines[indexmachine]);
			jobs.remove(0);
		}

	}

	/** Skriver ut maskinernas scheman. */
	public void printSchedule() {
		StringBuilder builder = new StringBuilder();
		for (Machine m : machines) {
			builder.append(m.toString());
			builder.append("\n");
		}
		System.out.println(builder.toString());

	}
}
