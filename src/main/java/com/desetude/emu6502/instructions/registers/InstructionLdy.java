package com.desetude.emu6502.instructions.registers;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.addressing.AddressingMode;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * LoaDs index Y with a value from memory.
 */
public class InstructionLdy implements Instruction {

    private final AddressingMode mode;

    public InstructionLdy(AddressingMode mode) {
        this.mode = mode;
    }

    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        regHolder.regY = this.mode.read1(mmu, regHolder);
    }

}
