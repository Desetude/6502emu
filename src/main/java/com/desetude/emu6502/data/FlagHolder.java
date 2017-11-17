package com.desetude.emu6502.data;

/**
 * Holds all the status flags.
 * This is typically done with a status register with 7 bits,
 * but booleans are nicer to work with here.
 */
public class FlagHolder {

    //Break flag - Indicates whether a BRK instruction has been executed
    //and is being handled.
    public boolean flagB = false;

    //Carry flag - Enables multi-byte arithmetic operations and indicates whether
    //the result of the previous operation on the accumulator register
    //overflowed from bit 7 or underflowed from bit 0.
    public boolean flagC = false;

    //Decimal flag - Indicates whether BCD (binary coded decimal) mode is used
    //in arithmetic operations (true) or binary mode is used (false).
    public boolean flagD = false;

    //Interrupt flag - Indicates whether interrupts are either prevented (false)
    //or enabled (true).
    public boolean flagI = true;

    //Overflow flag - Indicates whether the result of the previous operation
    //on the accumulator register was too large to fit in the register width
    //(less than -128 or more than 127 in the two's complement representation).
    public boolean flagV = false;

    //Zero flag - Indicates whether the result of the previous operation
    //on the accumulator register was 0.
    public boolean flagZ = false;

}
