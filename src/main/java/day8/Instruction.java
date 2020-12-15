package day8;

import java.util.Objects;

public class Instruction {
    private InstructionType instruction;
    private final int value;
    private int timesExecuted;

    public Instruction(InstructionType instruction, int value) {
        this.instruction = instruction;
        this.value = value;
        this.timesExecuted = 0;
    }

    public void resetExecution() {
        timesExecuted = 0;
    }

    public void execute() {
        timesExecuted++;
    }

    public boolean hasBeenExecuted() {
        return timesExecuted > 0;
    }

    public boolean isAccumulator() {
        return InstructionType.acc.equals(instruction);
    }

    public boolean isJump() {
        return InstructionType.jmp.equals(instruction);
    }

    public boolean isNoOpp() {
        return InstructionType.nop.equals(instruction);
    }

    public void switchInstruction() {
        if (isAccumulator()) {
            throw new UnsupportedOperationException("cannot change accumulator");
        }
        if (isJump()) {
            this.instruction = InstructionType.nop;
        } else if (isNoOpp()) {
            this.instruction = InstructionType.jmp;
        }
    }


    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Instruction " + "(" + super.toString() + ") -> "
                + instruction + ' ' +
                +value + ' ' + timesExecuted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return value == that.value && timesExecuted == that.timesExecuted && Objects.equals(instruction, that.instruction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instruction, value, timesExecuted);
    }
}

enum InstructionType {
    jmp, nop, acc
}