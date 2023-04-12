import java.util.ArrayList;
import java.util.List;

public class Priority implements Algorithm{

	private List<Task> queue = new ArrayList<Task>();
	private CPU cpu = new CPU();
	
	public Priority(List<Task> taskList) {
		this.queue = taskList;
	}
	
	public void schedule() {
		Task tem;
		while(!queue.isEmpty()) {
			tem = pickNextTask();
			cpu.run(tem, tem.getBurst());
		}
	}
	public Task pickNextTask() {
		Task tem;
		int smallest = 0;
		for(int i = 0; i < queue.size();i++) {
			if(queue.get(i).getPriority() > queue.get(smallest).getPriority()) {
				smallest = i;
			}
			if(queue.get(i).getPriority() == queue.get(smallest).getPriority()) {
				if(queue.get(i).getBurst() < queue.get(smallest).getBurst()) {
					smallest = i;
				}
			}
		}
		tem = queue.get(smallest);
		queue.remove(smallest);
		
		return tem;
	}
	
}