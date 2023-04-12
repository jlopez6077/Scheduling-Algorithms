
import java.util.ArrayList;
import java.util.List;

public class RR implements Algorithm{

	private List<Task> queue = new ArrayList<Task>();
	private CPU cpu = new CPU();
	private static int quantum = 10;
	private int element = 0;
	private int taskRunTime;
	private boolean bigTask = false;
	
	public RR(List<Task> taskList) {
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
			queue.get(element).setBurst(queue.get(element).getBurst() - quantum);
			element ++;
			bigTask = false;
		}
		if(element == queue.size())
			element = 0;
		
		if(queue.get(element).getBurst() > quantum) {
			taskRunTime = quantum;
			bigTask = true;
			return queue.get(element);
		}
		else {
			taskRunTime = quantum -queue.get(element).getBurst();
			return queue.remove(element);
		}
	}
	
}