/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass_os3;

/**
 *
 * @author Lenovo
 */
public class Partition {

    String PtName;
    int PtSize;
    boolean Status;
    Process process;

    public Partition() {
    }

    public Partition(String PtName, int PtSize, boolean s) {
        this.PtName = PtName;
        this.PtSize = PtSize;
        this.Status = s;
    }

    public void setProcess(Process p) {
        this.process = p;
    }

    public Process getProcess() {
        return process;
    }

    public void setPartitionName(String ptName) {
        this.PtName = ptName;
    }

    public String getPartitionName() {
        return PtName;
    }

    public void setPartitionSize(int ptSize) {
        this.PtSize = ptSize;
    }

    public int getPartitionSize() {
        return PtSize;
    }

}
