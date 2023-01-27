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
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList<Partition> partitions = new LinkedList<Partition>();
        LinkedList<Process> processes = new LinkedList<Process>();

        System.out.print("Enter Number of partitions : ");
        int PtNum = input.nextInt();
        String PtName;
        int PtSize;
        for (int i = 0; i < PtNum; i++) {

            System.out.println("Enter Partition " + (i + 1) + " Name and its size ");
            PtName = input.next();
            PtSize = input.nextInt();
            Partition partition = new Partition(PtName, PtSize, false);
            partitions.add(partition);
            partition.setPartitionSize(PtSize);
        }
        System.out.print("Enter Number of processes : ");
        int PNum = input.nextInt();
        String PrName;
        int PrSize;
        for (int i = 0; i < PNum; i++) {
            System.out.println("Enter Process " + (i + 1) + " name and its size ");
            PrName = input.next();
            PrSize = input.nextInt();
            Process process = new Process(PrName, PrSize, false);
            processes.add(process);
            process.setProcessSize(PrSize);
        }
        System.out.println("1. First fit \n2. Worst fit \n3. Best fit\n");
        System.out.println("Select Choice: ");
        int ch = input.nextInt();
        if (ch == 1) {
            FristFit f = new FristFit(processes, partitions);
            f.FirstFit(processes, partitions);
        } else if (ch == 2) {
            WorstFit w = new WorstFit(processes, partitions);
            w.WorstFit(processes, partitions);
        } else if (ch == 3) {
            BestFit f = new BestFit(processes, partitions);
            f.BestFit(processes, partitions);
        }

    }

}
