package com.desetude.emu6502.instructions.registers;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;

public class InstructionSta implements Instruction {

    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        mode.write1(bus, store, store.regA);
    }

}
