package com.desetude.emu6502;

import com.desetude.emu6502.data.RegisterHolder;

/**
 * Memory management unit.
 *
 * 8KiB of memory.
 * 0x0000 -> 0x00FF: zero-page
 * 0x0100 -> 0x01FF: stack
 * 0x0200 -> 0xFFFF: ram
 *
 * All multi-byte writes are done in small-endian.
 */
public class MMU {

    private byte[] memory = new byte[0xFFFF];
    private final RegisterHolder regHolder;

    public MMU(RegisterHolder regHolder) {
        this.regHolder = regHolder;
    }

    /**
     * Returns the location of the top of the stack, which is 0x100 + the value of the S register.
     *
     * @return the location of the top of the stack
     */
    public int getStackPointer() {
        //regS only holds the lower byte of the stack pointer (0x100 to 0x1FF)
        return this.regHolder.regS + 0x100;
    }

    /**
     * Returns the data at the specified address.
     *
     * @param address 16-bit address of the data
     * @return data at address
     */
    public int memoryRead1(int address) {
        address &= 0xFFFF;
        return this.memory[address] & 0xFF; //Ensure when it's casted to an int any signage is removed
    }

    /**
     * Returns the data at the specified address.
     *
     * @param address 16-bit address of the data
     * @return 16-bit data at address
     */
    public int memoryRead2(int address) {
        address &= 0xFFFF;
        return (memoryRead1(address) & 0xFF) | (memoryRead1(address + 1) << 8); //Ensure when it's casted to an int any signage is removed
    }

    /**
     * Writes the specified data at the specified address.
     *
     * @param address 16-bit address to write the data to
     * @param data byte of data (least significant 8-bits used)
     */
    public void memoryWrite1(int address, int data) {
        //TODO: Possibly some permission system to disable memory writing to read-only areas, e.g. program area
        address &= 0xFFFF;
        data &= 0xFF;
        this.memory[address] = (byte) data;
    }

    /**
     * Writes the specified data at the specified address.
     *
     * @param address 16-bit address to write the data to
     * @param data 2 bytes of data (least significant 16-bits used)
     */
    public void memoryWrite2(int address, int data) {
        memoryWrite1(address, data & 0xFF);
        memoryWrite1(address + 1, data >> 8);
    }

    /**
     * Reads one byte from memory at the location of the program counter.
     *
     * @return The next byte of the program
     */
    public int programRead1() {
        return memoryRead1(this.regHolder.regPc);
    }

    /**
     * Reads one byte from memory at the location pointed to by the program
     * counter and then increases the program counter by 1.
     *
     * @return The popped byte from the program
     */
    public int programPop1() {
        int result = programRead1();
        programInc();
        return result;
    }

    /**
     * Reads two bytes from memory at the location pointed to by the program
     * counter.
     *
     * @return The next two bytes of the program
     */
    public int programRead2() {
        return memoryRead2(this.regHolder.regPc);
    }

    /**
     * Reads two bytes from memory at the location pointed to by the program
     * counter and increments the program counter by 2.
     *
     * @return The popped two bytes from the program
     */
    public int programPop2() {
        int result = programRead2();
        programInc();
        programInc();
        return result;
    }

    /**
     * Writes the specified one byte of data to the program counter + specified offset.
     *
     * @param offset of the location to write to from the program counter
     * @param data to write the least-significant two bytes of
     */
    public void programWrite1(int offset, int data) {
        memoryWrite1(this.regHolder.regPc + offset, data);
    }

    /**
     * Writes the specified two bytes of data to the program counter + specified offset.
     *
     * @param offset of the location to write to from the  program counter
     * @param data to write the least-significant two bytes of
     */
    public void programWrite2(int offset, int data) {
        memoryWrite2(this.regHolder.regPc + offset, data);
    }

    /**
     * Increments the program counter and overflows to 0 if the value requires more than 16-bits.
     */
    public void programInc() {
        //Prevent an overflow
        this.regHolder.regPc = (this.regHolder.regPc + 1) & 0xFFFF;
    }

    /**
     * Pushes one byte of data to the stack, decrementing the stack pointer.
     *
     * @param data to push the least significant 16-bits of
     */
    public void stackPush1(int data) {
        memoryWrite1(getStackPointer(), data);

        this.regHolder.regS--;
    }

    /**
     * Pushes two bytes of data to the stack, decrementing the stack pointer twice.
     *
     * @param data to push the least significant 16-bits of
     */
    public void stackPush2(int data) {
        stackPush1(data & 0xFF);
        stackPush1(data >> 8);
    }

    /**
     * Reads one byte of data from the stack and increments the stack pointer.
     *
     * @return one byte of data from the stack
     */
    public int stackPop1() {
        this.regHolder.regS++;

        return this.memory[getStackPointer()];
    }

    /**
     * Reads two bytes of data from the stack and increments the stack pointer twice.
     *
     * @return two bytes of data from the stack
     */
    public int stackPop2() {
        return (stackPop1() << 8) | (stackPop1() & 0xFF);
    }

}
