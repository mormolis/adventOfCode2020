package day8;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionsRunner {

    public static final int THRESHOLD = 100;

    public int swapAndCount(List<String> instructionLines) {
        final List<Instruction> instructions = parseInstructions(instructionLines);
        correctInstructions(instructions);
        int accSum = 0;
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction instruction = instructions.get(i);
            if (instruction.isAccumulator()) {
                accSum += instruction.getValue();
            }
            if (instruction.isJump()) {
                i = i + instruction.getValue() - 1;
            }
        }
        return accSum;
    }

    private void correctInstructions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            if (instruction.isJump() || instruction.isNoOpp()) {
                instruction.switchInstruction();
                if (isInfinite(instructions)) {
                    instruction.switchInstruction();
                } else {
                    break;
                }
            }
        }
    }

    private boolean isInfinite(List<Instruction> instructions) {
        resetExecutions(instructions);
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction instruction = instructions.get(i);
            instruction.execute();
            if (instruction.hasThresholdReached(THRESHOLD)) {
                return true;
            }
            if (instruction.isJump()) {
                i = i + instruction.getValue() - 1;
            }
        }
        return false;
    }

    private void resetExecutions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            instruction.resetExecution();
        }
    }

    public int accumulatorValueSecondTimeInstruction(List<String> instructionLines) {
        List<Instruction> instructions = parseInstructions(instructionLines);
        int accSum = 0;
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction instruction = instructions.get(i);
            if (instruction.hasBeenExecuted()) {
                return accSum;
            }
            if (instruction.isAccumulator()) {
                accSum += instruction.getValue();
            }
            if (instruction.isJump()) {
                i = i + instruction.getValue() - 1;
            }
            instruction.execute();
        }
        return accSum;
    }

    private List<Instruction> parseInstructions(List<String> instructions) {
        return instructions.stream()
                .map(s -> s.split(" "))
                .map(a -> new Instruction(InstructionType.valueOf(a[0]), Integer.parseInt(a[1])))
                .collect(Collectors.toList());
    }
}
