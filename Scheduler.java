import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Scheduler {
    private List<PCB> q1;
    private List<PCB> q2;
    public String x;
    public x;

    // Constructor
    public Scheduler() {
        q1 = new ArrayList<>();
        q2 = new ArrayList<>();
    }

    // add a process to the appropriate queue
    public void addProcess(PCB process) {
        if (process.getPriority() == 1) {
            q1.add(process);
        } else if (process.getPriority() == 2) {
            q2.add(process);
        }
    }

    // Method to schedule processes and generate a report
    public void scheduleProcesses() {
        // Sort Q1 based on arrival time
        Collections.sort(q1, Comparator.comparingInt(PCB::getArrivalTime));
        
        // Implement Round-Robin scheduling for Q1 with time quantum 3 ms
        int currentTime = 0;
        for (PCB process : q1) {
            process.setStartTime(currentTime);
            int remainingTime = process.getCpuBurstTime();
            while (remainingTime > 0) {
                // Execute for time quantum or remaining time, whichever is smaller
                int executeTime = Math.min(3, remainingTime);
                currentTime += executeTime;
                remainingTime -= executeTime;
            }
            process.setTerminationTime(currentTime);
            process.calculateTurnaroundTime();
            process.calculateWaitingTime();
            process.calculateResponseTime();
            
            // here should write the SJF (Anwar) 
            
            
            
            
            
            
            // print all info 
            
            // this is for queue 1 
             for (PCB process : q1) {
            process.displayPCBInfo();
              } 
        
           // this is for queue 2 
             for (PCB process : q2) {
            process.displayPCBInfo();
               } 
               
               // Calculate and display average turnaround time, waiting time, and response time
        int totalTurnaroundTime = q1.stream().mapToInt(PCB::getTurnaroundTime).sum()
                + q2.stream().mapToInt(PCB::getTurnaroundTime).sum();
        int totalWaitingTime = q1.stream().mapToInt(PCB::getWaitingTime).sum()
                + q2.stream().mapToInt(PCB::getWaitingTime).sum();
        int totalResponseTime = q1.stream().mapToInt(PCB::getResponseTime).sum()
                + q2.stream().mapToInt(PCB::getResponseTime).sum();
        int totalProcesses = q1.size() + q2.size();

        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / totalProcesses));
        System.out.println("Average Waiting Time: " + (totalWaitingTime / totalProcesses));
        System.out.println("Average Response Time: " + (totalResponseTime / totalProcesses));
        
        // here the average info and the processes info should be saved to a file 
        
        
        
        
           
        }
        
        
        
   } 
