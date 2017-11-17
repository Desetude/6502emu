package com.desetude.emu6502.instructions.execution;

import com.desetude.emu6502.CPU;
import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * HaLT - Stops the CPU.
 */
public class InstructionHlt implements Instruction {

    private final CPU cpu;

    public InstructionHlt(CPU cpu) {
        this.cpu = cpu;
    }

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        this.cpu.setRunning(false);
    }

}
