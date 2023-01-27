/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass_os3;

import java.util.*;

/**
 *
 * @author Lenovo
 */
public class FristFit {

    Scanner input = new Scanner(System.in);
    boolean yes = true;
    LinkedList<Process> processes;
    LinkedList<Partition> partitions;
    static int sz;

    public FristFit(LinkedList<Process> processes, LinkedList<Partition> partitions) {
        this.processes = processes;
        this.partitions = partitions;
        sz = partitions.size();
    }

    public void FirstFit(LinkedList<Process> processes, LinkedList<Partition> partitions) {

        for (int i = 0; i < processes.size(); i++) {
            for (int j = 0; j < partitions.size(); j++) {

                if (partitions.get(j).PtSize >= processes.get(i).PSize) {
                    processes.get(i).Allocated = true;
                    partitions.get(j).setProcess(processes.get(i));
                    partitions.get(j).PtSize -= processes.get(i).PSize;
                    partitions.get(j).Status = true;

                    if (partitions.get(j).PtSize > 0) {
                        sz += 1;
                        Partition partition = new Partition("partition" + (sz - 1), partitions.get(j).PtSize,
                                false);
                        partitions.add(j + 1, partition);
                        partitions.get(j).PtSize = 0;
                    }
                    break;
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
                for (Partition p : partitions) {
                    if (p.Status == false) {
                        totalRemaining += p.PtSize;
                    } else {
                        afterCompaction.add(p);
                    }
                }
                sz += 1;
                afterCompaction.add(new Partition("partition" + (sz - 1), totalRemaining, false));

                for (Process p : processes) {
                    if (p.Allocated == false) {
                        remainingProcesses.add(p);
                    }
                }
                yes = false;
                FirstFit(remainingProcesses, afterCompaction);
            }
        }
    }
}
