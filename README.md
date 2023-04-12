# Scheduling-Algorithms
I created several different process scheduling algorithms. The scheduler will be assigned a predefined set of tasks and will schedule the tasks
based on the selected scheduling algorithm. Each task is assigned a priority and CPU burst. The following scheduling algorithms are implemented:
* First-come, first-served (FCFS), which schedules tasks in the order in which
they request the CPU.
* Shortest-job-first (SJF), which schedules tasks in order of the length of the
tasks’ next CPU burst.
* Priority scheduling, which schedules tasks based on priority.
* Round-robin (RR) scheduling, where each task is run for a time quantum
(or for the remainder of its CPU burst).
* Priority with round-robin, which schedules tasks in order of priority and
uses round-robin scheduling for tasks with equal priority.

# Implementation
The tasks are provided in a text file and are formated as such [task name] [priority] [CPU burst].
```
T1, 4, 20
T2, 2, 25
T3, 3, 25
T4, 3, 15
T5, 10, 10
```
The file Driver.java reads in the schedule of tasks, inserts each task into a
Java ArrayList, and invokes the process scheduler by calling the schedule()
method. The following interface identifies a generic scheduling algorithm,
which the five different scheduling algorithms will implement:
```java
public interface Algorithm
{
// Implementation of scheduling algorithm
public void schedule();
// Selects the next task to be scheduled
public Task pickNetTask();
}
```
The schedule() method obtains the next task to be run on the CPU by invoking the pickNextTask() method and then executes this Task by calling the
static run() method in the CPU.java class.

The program is run as follows:

java Driver fcfs schedule.txt

# Solutions
The following text explains my process in creating each algorithm.
### FCFS.java
The schedule function is a while loop. While the queue is not empty, run next task .
As the name suggests, First come, First Severed. The pickNextTask() function returns and removes the first element in the queue.

### SJF.java
The schedule function is a while loop. While the queue is not empty, run next task.
As the name suggests, Shortest Job First picks the task with the shortest burst time to run. pickNextTask() finds the task with the shortest burst time. Before returning that task, it removes it from the queue.

### Priority.java
The schedule function is a while loop. While the queue is not empty, run next task.
The pickNextTask() is in a for loop. It iterates through the queue and finds the task with the highest priority. It then removes the task from the queue and returns it.
Note: Priorities range from 1 to 10, where a higher numeric value indicates a higher relative priority

### RR.java
The schedule function is a while loop. While the queue is not empty, run next task.
In Round Robin, each process is allocated a fixed amount of time. Also known as the time quantum. For round-robin scheduling, the length of a time quantum is 10 milliseconds.
The pickNextTask() function goes through the queue. It starts at element zero. Each time it checks if the task’s burst time, at array location [element], is larger than the quantum time. If that’s the case, it returns the task, it later subtracts the task’s burst time by the quantum time. Then it increments the element.
If the quantum time is greater than or equal to the burst time then the task is returned and removed from the queue.

### PriorityRR.java
The schedule function is a while loop. While the queue is not empty, run next task.
This is like Round Robin. The biggest difference is using task priority. It does the highest priority first. If there are more tasks with the same priority, then it allocates the quantum time to each until they’re finished.
pickNextTask() utilizes a bucket to determine if there are tasks with the same priority. This is done in a for loop. In that same for loop, the next task is chosen. It scans for the highest priority while also taking into consideration tasks with the same priority.
After choosing the next task, it then checks the bucket to see if there are similar prioritized tasks and if the task burst time is greater than the quantum time. If that’s the case, then it makes note that it needs to move on to the next tasks after returning current.
Else, it removes the task from the queue.
