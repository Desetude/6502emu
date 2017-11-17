package com.desetude.emu6502;

import static com.desetude.emu6502.addressing.AddressingMode.*;
import static com.desetude.emu6502.instructions.Instructions.*;

import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;
import com.desetude.emu6502.instructions.arithmetic.InstructionAdc;
import com.desetude.emu6502.instructions.bitwise.InstructionAnd;
import com.desetude.emu6502.instructions.bitwise.InstructionAsl;
import com.desetude.emu6502.instructions.flags.InstructionCld;
import com.desetude.emu6502.instructions.execution.InstructionHlt;
import com.desetude.emu6502.instructions.registers.InstructionIny;
import com.desetude.emu6502.instructions.execution.InstructionJmp;
import com.desetude.emu6502.instructions.execution.InstructionJsr;
import com.desetude.emu6502.instructions.registers.InstructionLda;
import com.desetude.emu6502.instructions.registers.InstructionLdx;
import com.desetude.emu6502.instructions.registers.InstructionLdy;
import com.desetude.emu6502.instructions.execution.InstructionRts;
import com.desetude.emu6502.instructions.registers.InstructionSta;
import com.desetude.emu6502.instructions.registers.InstructionStx;
import com.desetude.emu6502.instructions.stack.InstructionTxs;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class CPU {

    private final MMU mmu;
    private final RegisterHolder regHolder;
    private final FlagHolder flagHolder;
    private final Map<Integer, Instruction> instructions;
    private boolean running = true;

    public CPU(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        this.mmu = mmu;
        this.regHolder = regHolder;
        this.flagHolder = flagHolder;

        this.instructions = ImmutableMap.<Integer, Instruction>builder()
                .put(ADC_L, new InstructionAdc(IMMEDIATE))
                .put(ADC_zp, new InstructionAdc(ZERO_PAGE))
                .put(ADC_zpX, new InstructionAdc(ZERO_PAGE_X))
                .put(ADC_a, new InstructionAdc(ABSOLUTE))
                .put(ADC_aX, new InstructionAdc(ABSOLUTE_X))
                .put(ADC_aY, new InstructionAdc(ABSOLUTE_Y))
                .put(ADC_zpIdX, new InstructionAdc(ZERO_PAGE_INDIRECT_X))
                .put(ADC_zpIdY, new InstructionAdc(ZERO_PAGE_INDIRECT_Y))

                .put(AND_L, new InstructionAnd(IMMEDIATE))
                .put(AND_zp, new InstructionAnd(ZERO_PAGE))
                .put(AND_zpX, new InstructionAnd(ZERO_PAGE_X))
                .put(AND_a, new InstructionAnd(ABSOLUTE))
                .put(AND_aX, new InstructionAnd(ABSOLUTE_X))
                .put(AND_aY, new InstructionAnd(ABSOLUTE_Y))
                .put(AND_zpIdX, new InstructionAnd(ZERO_PAGE_INDIRECT_X))
                .put(AND_zpIdY, new InstructionAnd(ZERO_PAGE_INDIRECT_Y))

                .put(ASL_A, new InstructionAsl(ACCUMULATOR))
                .put(ASL_zp, new InstructionAsl(ZERO_PAGE))
                .put(ASL_zpX, new InstructionAsl(ZERO_PAGE_X))
                .put(ASL_a, new InstructionAsl(ABSOLUTE))
                .put(ASL_aX, new InstructionAsl(ABSOLUTE_X))

                .put(LDA_a, new InstructionLda(ABSOLUTE))
                .put(LDA_L, new InstructionLda(IMMEDIATE))

                .put(STA_a, new InstructionSta(ABSOLUTE))
                .put(STX_a, new InstructionStx(ABSOLUTE))

                .put(JMP_a, new InstructionJmp(false))
                .put(JMP_id, new InstructionJmp(true))

                .put(LDX_a, new InstructionLdx(ABSOLUTE))
                .put(LDX_L, new InstructionLdx(IMMEDIATE))

                .put(LDY_a, new InstructionLdy(ABSOLUTE))
                .put(LDY_L, new InstructionLdy(IMMEDIATE))

                //TODO: Rest of instructions

                .put(INY_i, new InstructionIny())

                .put(TXS_i, new InstructionTxs())

                .put(CLD_i, new InstructionCld())

                .put(RTS_i, new InstructionRts())
                .put(JSR_a, new InstructionJsr())

                .put(HLT_i, new InstructionHlt(this))
                .build();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void tick() {
        if (!this.running) {
            return;
        }

        int opCode = this.mmu.programPop1();
        Instruction instruction = this.instructions.get(opCode);

        Preconditions.checkNotNull(instruction, String.format("Failed to get instruction with OPCODE: 0x%02X at PC: 0x%04X",
                opCode, this.regHolder.regPc));

        instruction.execute(this.mmu, this.regHolder, this.flagHolder);
    }

}
