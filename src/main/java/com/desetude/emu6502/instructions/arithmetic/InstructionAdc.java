package com.desetude.emu6502.instructions.arithmetic;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.addressing.AddressingMode;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * ADd with Carry.
 */
public class InstructionAdc implements Instruction {

    private final AddressingMode mode;

    public InstructionAdc(AddressingMode mode) {
        this.mode = mode;
    }

    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        //TODO: Implement flagD, flagZ, flagC
        int arg = this.mode.read1(mmu, regHolder);

        regHolder.regA = (regHolder.regA + arg) & 0xFF;
    }

}
