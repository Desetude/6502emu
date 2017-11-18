package com.desetude.emu6502.instructions.execution;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;
import com.desetude.emu6502.utils.MemoryUtils;

/**
 * JuMP to the specified address.
 */
public class InstructionJmp implements Instruction {

    private final boolean indirect;

    /**
     * @param indirect if {@code true}, execution to the address pointed by the supplied address, otherwise execution to the supplied address
     */
    public InstructionJmp(boolean indirect) {
        this.indirect = indirect;
    }

    //Ignore mode as this is JMP is a special case and doesnt follow the normal opcode structure
    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        int address;
        if (this.indirect) {
            address = bus.read2(MemoryUtils.programPop2(bus, store));
        } else {
            address = MemoryUtils.programPop2(bus, store); //popped b,4 and got 469
        }

        store.regPc = address;
    }

    @Override
    public boolean obtainMode() {
        return false;
    }

}
