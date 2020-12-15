package day8;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionsRunner {

    public int swapAndCount(List<String> instructionLines) {
        final List<Instruction> instructions = parseInstructions(instructionLines);
        for (Instruction instruction : instructions) {
            if (instruction.isJump() || instruction.isNoOpp()) {
                instruction.switchInstruction();
                final int accSum = returnAccumulatorSumWhenNotInfinite(instructions);
                if (accSum == -1) { //indication of infinite loop, switch instruction back and find the next candidate
                    instruction.switchInstruction();
                } else {
                    return accSum;
                }
            }
        }
        throw new RuntimeException("No instruction could fix the infinite loop");
    }

    private int returnAccumulatorSumWhenNotInfinite(List<Instruction> instructions){
        resetExecutions(instructions);
        int accSum = 0;
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction instruction = instructions.get(i);
            if (instruction.hasBeenExecuted()) {
                return -1; //indication of infinite loop
            }
            if (instruction.isJump()) {
                i = i + instruction.getValue() - 1;
            }
            if (instruction.isAccumulator()) {
                accSum += instruction.getValue();
            }
            instruction.execute();
        }
        return accSum;
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
