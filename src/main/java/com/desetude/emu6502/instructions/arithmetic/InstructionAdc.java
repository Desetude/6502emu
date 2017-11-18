package com.desetude.emu6502.instructions.arithmetic;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;

/**
 * ADd with Carry.
 */
public class InstructionAdc implements Instruction {

    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        store.regA = (store.regA + mode.read1(bus, store)) & 0xFF;
    }
}
