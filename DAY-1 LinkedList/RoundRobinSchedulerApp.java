class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime = 0;
    int turnaroundTime = 0;
    Process next;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null;
    private Process tail = null;
    private int timeQuantum = 4;
    private int totalProcesses = 0;

    public void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
        totalProcesses++;
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }
        System.out.println("Process Queue:");
        Process temp = head;
        do {
            System.out.println("PID: " + temp.pid + ", Remaining: " + temp.remainingTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    public void simulate() {
        System.out.println("\n--- Round Robin Scheduling Simulation ---");
        int time = 0;
        Process current = head;

        while (head != null) {
            displayProcesses();
            System.out.println("\n[Time: " + time + "] Executing PID " + current.pid);

            if (current.remainingTime <= timeQuantum) {
                time += current.remainingTime;
                current.remainingTime = 0;
                current.turnaroundTime = time;
                current.waitingTime = current.turnaroundTime - current.burstTime;

                System.out.println("Process PID " + current.pid + " finished. TAT: " + current.turnaroundTime + ", WT: " + current.waitingTime);
                int pidToRemove = current.pid;
                current = current.next;
                removeProcessById(pidToRemove);
            } else {
                current.remainingTime -= timeQuantum;
                time += timeQuantum;
                current = current.next;
            }
        }

        System.out.println("\nAll processes have been executed.");
        displayResults();
    }

    private void removeProcessById(int pid) {
        if (head == null) return;

        Process current = head;
        Process prev = tail;

        do {
            if (current.pid == pid) {
                if (current == head && current == tail) {
                    head = tail = null;
                } else {
                    if (current == head) {
                        head = head.next;
                        tail.next = head;
                    } else if (current == tail) {
                        tail = prev;
                        tail.next = head;
                    } else {
                        prev.next = current.next;
                    }
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    private void displayResults() {
        int[] burstTimes = {10, 5, 8};
        int[] turnaroundTimes = {0, 0, 0};
        int[] waitingTimes = {0, 0, 0};
        int timeQuantum = this.timeQuantum;

        int time = 0;
        int[] remaining = burstTimes.clone();
        int n = burstTimes.length;
        boolean[] completed = new boolean[n];

        while (true) {
            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (remaining[i] > 0) {
                    done = false;
                    if (remaining[i] > timeQuantum) {
                        time += timeQuantum;
                        remaining[i] -= timeQuantum;
                    } else {
                        time += remaining[i];
                        turnaroundTimes[i] = time;
                        waitingTimes[i] = turnaroundTimes[i] - burstTimes[i];
                        remaining[i] = 0;
                    }
                }
            }
            if (done) break;
        }
        double totalWT = 0, totalTAT = 0;
        System.out.println("\n--- Final Results ---");
        for (int i = 0; i < n; i++) {
            System.out.println("PID " + (i + 1) + " | BT: " + burstTimes[i] +
                    " | TAT: " + turnaroundTimes[i] + " | WT: " + waitingTimes[i]);
            totalWT += waitingTimes[i];
            totalTAT += turnaroundTimes[i];
        }
        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / n);
    }
}
public class RoundRobinSchedulerApp {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        scheduler.addProcess(1, 10, 1);
        scheduler.addProcess(2, 5, 3);
        scheduler.addProcess(3, 8, 2);

        scheduler.simulate();
    }
}
