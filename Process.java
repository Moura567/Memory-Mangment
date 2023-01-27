/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass_os3;

/**
 *
 * @author Lenovo
 */
public class Process {

    String PName;
    int PSize;
    boolean Allocated;

    public Process() {
    }

    public Process(String PName, int PSize, boolean Allocated) {
        this.PName = PName;
        this.PSize = PSize;
        this.Allocated = Allocated;
    }

    public void setProcessName(String PName) {
        this.PName = PName;
    }

    public void setProcessSize(int PSize) {
        this.PSize = PSize;
    }

    public String getProcessName() {
        return PName;
    }

    public int getProcessSize() {
        return PSize;
    }
}
