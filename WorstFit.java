/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass_os3;

/**
 *
 * @author Lenovo
 */
import static ass_os3.FristFit.sz;
import java.util.*;

public class WorstFit {

    Scanner input = new Scanner(System.in);
    boolean yes = true;
    LinkedList<Process> processes;
    LinkedList<Partition> partitions;
    static int sz;

    public WorstFit(LinkedList<Process> processes, LinkedList<Partition> partitions) {
        this.processes = processes;
        this.partitions = partitions;
        sz = partitions.size();
    }

    public void WorstFit(LinkedList<Process> processes, LinkedList<Partition> partitions) {

        for (int i = 0; i < processes.size(); i++) {
            int WIdx = -1;
            for (int j = 0; j < partitions.size(); j++) {
                if (partitions.get(j).PtSize >= processes.get(i).PSize) {
                    if (WIdx == -1) {
                        WIdx = j;
                    } else if (partitions.get(WIdx).PtSize < partitions.get(j).PtSize) {
                        WIdx = j;
                    }
                }
            }
            if (WIdx != -1) {
                processes.get(i).Allocated = true;
                partitions.get(WIdx).setProcess(processes.get(i));
                partitions.get(WIdx).PtSize -= processes.get(i).PSize;
                partitions.get(WIdx).Status = true;

                if (partitions.get(WIdx).PtSize > 0) {
                    sz += 1;
                    Partition partition = new Partition("partition" + (sz - 1), partitions.get(WIdx).PtSize,
                            false);
                    partitions.add(WIdx + 1, partition);
                    partitions.get(WIdx).PtSize = 0;
                }
            }
        }
        for (int i = 0; i < partitions.size(); i++) {
            if (partitions.get(i).Status != false) {
                System.out.println(partitions.get(i).PtName + "(" + partitions.get(i).process.PSize + " KB)" + "=> " + partitions.get(i).process.PName);
            } else {
                System.out.println(partitions.get(i).PtName + "(" + partitions.get(i).PtSize + " KB)" + "=> External Fragment");
            }
        }
        for (int i = 0; i < processes.size(); i++) {
            if (!processes.get(i).Allocated) {
                System.out.println(processes.get(i).PName + " Can Not be Allocated");
            }
        }
        //compaction
        if (yes == true) {
            System.out.println("Do you want to compact?\n1. yes\n2. no");
            int cmpt = Integer.parseInt(input.nextLine());
            if (cmpt == 1) {
                int totalRemaining = 0;
                LinkedList<Partition> afterCompaction = new LinkedList<>();
                LinkedList<Process> remainingProcesses = new LinkedList<>();
                //checking the partitions that have external fragment
                for (Partition p : partitions) {
                    if (p.Status == false) {
                        totalRemaining += p.PtSize;
                    } else {
                        afterCompaction.add(p);
                    }
                }
                sz += 1;
                afterCompaction.add(new Partition("partition" + (sz - 1), totalRemaining, false));
                //checking the processes that is'nt allocated
                for (Process p : processes) {
                    if (p.Allocated == false) {
                        remainingProcesses.add(p);
                    }
                }
                yes = false;
                WorstFit(remainingProcesses, afterCompaction);
            }
        }
    }
}
