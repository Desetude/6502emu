package com.desetude.emu6502.instructions.registers;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.addressing.AddressingMode;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * LoaDs Accumulator with a value from memory.
 */
public class InstructionLda implements Instruction {

    private final AddressingMode mode;

    public InstructionLda(AddressingMode mode) {
        this.mode = mode;
    }

    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        regHolder.regA = this.mode.read1(mmu, regHolder);
    }

}
