package com.desetude.emu6502;

import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;
import com.desetude.emu6502.instructions.arithmetic.InstructionAdc;
import com.desetude.emu6502.instructions.bitwise.InstructionAnd;
import com.desetude.emu6502.instructions.bitwise.InstructionAsl;
import com.desetude.emu6502.instructions.execution.InstructionJmp;
import com.desetude.emu6502.instructions.execution.InstructionJsr;
import com.desetude.emu6502.instructions.execution.InstructionRts;
import com.desetude.emu6502.instructions.flags.InstructionCld;
import com.desetude.emu6502.instructions.registers.InstructionLda;
import com.desetude.emu6502.instructions.registers.InstructionLdx;
import com.desetude.emu6502.instructions.registers.InstructionLdy;
import com.desetude.emu6502.instructions.registers.InstructionSta;
import com.desetude.emu6502.instructions.stack.InstructionTxs;
import com.desetude.emu6502.utils.MemoryUtils;
import com.google.common.base.Preconditions;

import java.util.HashMap;

public class Cpu {

    private final Bus bus;
    private final CpuStore store;
    private final HashMap<Integer, Instruction> instructions;

    public Cpu(Bus bus, CpuStore store) {
        this.bus = bus;
        this.store = store;

        this.instructions = new HashMap<>();
        this.addInstruction(new InstructionAdc(), 0x69, 0x65, 0x75, 0x6D, 0x7D, 0x79, 0x61, 0x71);
        this.addInstruction(new InstructionAnd(), 0x29, 0x25, 0x35, 0x2D, 0x3D, 0x39, 0x21, 0x31);
        this.addInstruction(new InstructionAsl(), 0x0A, 0x06, 0x16, 0x0E, 0x1E);
        this.addInstruction(new InstructionJsr(), 0x20);
        this.addInstruction(new InstructionRts(), 0x60);
        this.addInstruction(new InstructionSta(), 0x85, 0x95, 0x8D, 0x9D, 0x99, 0x81, 0x91);
        this.addInstruction(new InstructionLdx(), 0xA2, 0xA6, 0xB6, 0xAE, 0xBE);
        this.addInstruction(new InstructionTxs(), 0x9A);
        this.addInstruction(new InstructionCld(), 0xD8);
        this.addInstruction(new InstructionLdy(), 0xA0, 0xA4, 0xB4, 0xAC, 0xBC);
        this.addInstruction(new InstructionLda(), 0xA9, 0xA5, 0xB5, 0xAD, 0xBD, 0xB9, 0xA1, 0xB1);
        this.addInstruction(new InstructionJmp(false), 0x4C);
        this.addInstruction(new InstructionJmp(true), 0x6C);
    }

    public void addInstruction(Instruction instruction, int... opCodes) {
        for (int opCode : opCodes) {
            Preconditions.checkState(!this.instructions.containsKey(opCode), String.format("Duplicate OPCODE: 0x%02X", opCode));
            this.instructions.put(opCode, instruction);
        }
    }

    public void tick() {
        int opCode = MemoryUtils.programPop1(this.bus, this.store);

        Instruction instruction = this.instructions.get(opCode);
        Preconditions.checkNotNull(instruction, String.format("Failed to find InstructionMode for instruction with OPCODE: 0x%02X", opCode));

        InstructionMode opMode;
        if (instruction.obtainMode()) {
            opMode = this.getInstructionMode(opCode);
        } else {
            opMode = InstructionMode.IGNORED;
        }

        instruction.execute(opMode, this.bus, this.store);
    }

    public InstructionMode getInstructionMode(int opCode) {
        int addressingMode = (opCode & 0b00011100) >> 2;
        //There are 3 different instruction types which have different values
        //corresponding to different addressing modes
        int opType = opCode & 0b00000011;

        InstructionMode opMode = null;
        switch (opType) {
            case 0b00:
            case 0b10: {
                switch (addressingMode) {
                    case 0b000: //Immediate
                        opMode = InstructionMode.IMMEDIATE;
                        break;
                    case 0b001: //Zero Page
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop1(this.bus, this.store)
                        );
                        break;
                    case 0b010: //Accumulator
                        opMode = InstructionMode.ACCUMULATOR;
                        break;
                    case 0b011: //Absolute
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop2(this.bus, this.store)
                        );
                        break;
                    case 0b100: //Not used, ignored
                        break;
                    case 0b101: //Zero Page X
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop1(this.bus, this.store) + this.store.regX
                        );
                        break;
                    case 0b110: //Accumulator
                        opMode = InstructionMode.ACCUMULATOR;
                        break;
                    case 0b111: //Absolute X
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop2(this.bus, this.store) + this.store.regX
                        );
                        break;
                }
                break;
            }

            case 0b01: {
                switch (addressingMode) {
                    case 0b000: //Indirect Zero Page X
                        opMode = InstructionMode.createInMemoryMode(
                                this.bus.read2(MemoryUtils.programPop1(this.bus, this.store) + this.store.regX)
                        );
                        break;
                    case 0b001: //Zero Page
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop1(this.bus, this.store)
                        );
                        break;
                    case 0b010: //Immediate
                        opMode = InstructionMode.IMMEDIATE;
                        break;
                    case 0b011: //Absolute
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop2(this.bus, this.store)
                        );
                        break;
                    case 0b100: //Indirect Zero Page Y
                        opMode = InstructionMode.createInMemoryMode(
                                this.bus.read2(MemoryUtils.programPop1(this.bus, this.store)) + this.store.regY
                        );
                        break;
                    case 0b101: //Zero Page X
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop1(this.bus, this.store) + this.store.regX
                        );
                        break;
                    case 0b110: //Absolute Y
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop2(this.bus, this.store) + this.store.regY
                        );
                        break;
                    case 0b111: //Absolute X
                        opMode = InstructionMode.createInMemoryMode(
                                MemoryUtils.programPop2(this.bus, this.store) + this.store.regX
                        );
                        break;
                }

                break;
            }

            case 0b11: //Never used
                break;
        }


        return opMode;
    }

}
