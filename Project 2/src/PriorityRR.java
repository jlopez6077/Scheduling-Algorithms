import java.util.ArrayList;
import java.util.List;

public class PriorityRR implements Algorithm{

	private List<Task> queue = new ArrayList<Task>();
	private CPU cpu = new CPU();
	private static int quantum = 10;
	private int prior = 0;
	private int taskRunTime;
	private boolean bigTask = false;
	private int[] bucket = new int[11];
	private int previous = 0;
	
	public PriorityRR(List<Task> taskList) {
		this.queue = taskList;
	}
	
	public void schedule() {
		Task tem;
		while(!queue.isEmpty()) {
			tem = pickNextTask();
			cpu.run(tem, taskRunTime);
		}
	}
	public Task pickNextTask() {
		if(bigTask) {
			queue.get(prior).setBurst(queue.get(prior).getBurst() - quantum);
			bigTask = false;
		}
		prior = 0;
		for(int i = 1; i < 11; i++){ 	//clears bucket
			bucket[i] = 0;
		}
		for(int i = 0; i < queue.size();i++) {
			bucket[queue.get(i).getPriority()] += 1;
			if(queue.get(i).getPriority() > queue.get(prior).getPriority()) {
				prior = i;
			}
			else if(queue.get(i).getPriority() == queue.get(prior).getPriority() && previous == prior){
				prior = i;
			}
		}
		
		if(queue.get(prior).getBurst() > quantum && bucket[queue.get(prior).getPriority()] > 1) {
		//if there is more tasks with same prior and burst time is greater than quantum
				taskRunTime = quantum;
				bigTask = true;
				previous = prior;
				return queue.get(prior);
		}
		else {
			bigTask = false;
			taskRunTime = quantum -queue.get(prior).getBurst();
			return queue.remove(prior);
		}
	}
	
}