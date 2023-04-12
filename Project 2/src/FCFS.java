import java.util.ArrayList;
import java.util.List;

public class FCFS implements Algorithm{

	private List<Task> queue = new ArrayList<Task>();
	private CPU cpu = new CPU();
	private int taskRunTime = 0;
	public FCFS(List<Task> taskList) {
		this.queue = taskList;
	}
	
	public void schedule() {
		while(!queue.isEmpty()) {
			cpu.run(pickNextTask(), taskRunTime);
		}
	}
	public Task pickNextTask() {
		taskRunTime = queue.get(0).getBurst();
		return queue.remove(0);
	}
	
}
