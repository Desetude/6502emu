package com.desetude.emu6502.addressing;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.RegisterHolder;

import java.util.function.Function;

public interface AddressingMode {

    AddressingMode IMMEDIATE = new ImmediateMode();
    AddressingMode ACCUMULATOR = new AccumulatorMode();
    AddressingMode ZERO_PAGE = new ZeroPageMode();
    AddressingMode ZERO_PAGE_X = new ZeroPageXMode();
    AddressingMode ABSOLUTE = new AbsoluteMode();
    AddressingMode ABSOLUTE_X = new AbsoluteXMode();
    AddressingMode ABSOLUTE_Y = new AbsoluteYMode();
    AddressingMode ZERO_PAGE_INDIRECT_X = new ZeroPageIndirectXMode();
    AddressingMode ZERO_PAGE_INDIRECT_Y = new ZeroPageIndirectYMode();

    int read1(MMU mmu, RegisterHolder regHolder);

    void write1(MMU mmu, int value);

    void modify1(MMU mmu, RegisterHolder regHolder, Function<Integer, Integer> function);

}
